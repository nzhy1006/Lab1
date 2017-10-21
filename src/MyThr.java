import java.util.HashMap;
import java.util.Vector;

public class MyThr extends Thread {
    private static boolean[][] flag;
    private static int nxt = -1;
    private Graph g;
    public static boolean[][] getFlag() {
        return flag;
    }
    public static void setFlag(final boolean[][] flag) {
        MyThr.flag = flag;
    }
    public static int getNxt() {
        return nxt;
    }
    public static void setNxt(final int nxt) {
        MyThr.nxt = nxt;
    }
    public Graph getG() {
        return g;
    }

    public void setG(final Graph g) {
        this.g = g;
    }
    MyThr(final Graph g) {
        this.g = g; }
        int mGo() {
        if (nxt == -2) { // have ended
            return -2;
        }
        if (nxt == -1) {
            flag = new boolean[g.getWordsid()][g.getWordsid()];
            nxt = (int) (Math.random() * g.getWordsid());
            ShowRWay.setWords(ShowRWay.getWords() +  g.getWordlist().elementAt(nxt) + "\n");
            //System.out.println(g.wordlist.elementAt(nxt));
        }
        int num = g.getVec().elementAt(nxt).size();
        if (num == 0) {
            return -1;
        }
        HashMap<Integer, Integer> tpm = g.getVec().elementAt(nxt);
        Vector<Integer> tp = new Vector<Integer>();
        for (HashMap.Entry<Integer, Integer> entry : tpm.entrySet()) {
            /*System.out.println("Key = " + entry.getKey() + ",
            Value = " + entry.getValue());*/
            tp.add(entry.getKey());
        }
        int nnxt = tp.elementAt((int) (Math.random() * num));
        //System.out.println(g.wordlist.elementAt(nnxt));
        ShowRWay.setWords(ShowRWay.getWords() +  g.getWordlist().elementAt(nnxt) + "\n");
        if (flag[nxt][nnxt]) {
            return -3;
        }
        flag[nxt][nnxt] = true;
        nxt = nnxt;
        return 0;
    }
    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) { }
            int ret = mGo();
            if (ret == -1) {
                ShowRWay.getEcho().setText("no next word!");
                nxt = -2;
            }
            if (ret == -3) {
                ShowRWay.getEcho().setText("repeat edge!");
                nxt = -2;
            }
            if (ret == 0) {
                ShowRWay.getEcho().setText("travelling...");
            }
            ShowRWay.getTxtA().setText(ShowRWay.getWords());
        }
    }
}

