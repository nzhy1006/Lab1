import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Lab1 {

    private static Graph gra;
    private static String strS;
    private static Vector<String> str;

    public static Graph getGra() {
        return gra;
    }

    public static void setGra(Graph gra) {
        Lab1.gra = gra;
    }

    public static String getStrS() {
        return strS;
    }

    public static void setStrS(final String strS) {
        Lab1.strS = strS;
    }

    public static Vector<String> getStr() {
        return str;
    }

    public static void setStr(final Vector<String> str) {
        Lab1.str = str;
    }

    public static void main(final String[] args) {
        MainF a = new MainF();
        a.go();
    }
    static Graph createDirectedGraph(final String filename) {
        Graph g = new Graph();
        g.readFile(filename);
        return g;
    }
    static void showDirectedGraph(final Graph gg) {
        JFrame frame = new JFrame("展示有向图");
        JButton butt = new JButton("save as a jpg");
        frame.getContentPane().add(BorderLayout.SOUTH, butt);
        MyG ll = new MyG(gg); //extends JLabel
        frame.add(BorderLayout.CENTER, ll);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        butt.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Container con = frame.getContentPane();
                BufferedImage img = new BufferedImage(
                        1200,
                        650, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = img.createGraphics();
                con.printAll(g2d);
                File f = new File("./aaa.jpg");
                try {
                    ImageIO.write(img, "jpg", f);
                    butt.setText("保存成功！");
                } catch (IOException ex) {
                }
                g2d.dispose();
            }
        });
    }
    static String queryBridgeWords(final Graph gg, final String word1, final String word2) {
        if (!gg.getWords().containsKey(word1)
                || !gg.getWords().containsKey(word2)) {
            strS = "No word 1 or word 2 in the graph";
            return strS;
        }
        int xid = gg.getWords().get(word1);
        int yid = gg.getWords().get(word2);
        HashMap<Integer, Integer> tpm = gg.getVec().elementAt(xid);
        str = new Vector<String>();
        for (HashMap.Entry<Integer, Integer> entry : tpm.entrySet()) {
            if (gg.getVec().elementAt(entry.getKey()).containsKey(yid)) {
                str.addElement(gg.getWordlist().elementAt(entry.getKey()));
            }
        }
        if (str.isEmpty()) {
            strS = "no bridge";
            return strS;
        } else {
            strS = "bridge:";
            for (int i = 0; i < str.size(); i++) {
                strS += " " + str.elementAt(i);
            }
            return strS;
        }
    }
    static String generateNewText(final Graph gg, final String inputText) {
        String[] div = inputText.split(" ");
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < div.length - 1; i++) {
            ans.append(ans + " " + div[i]);
            if (queryBridgeWords(gg, div[i].toLowerCase(),
                    div[i + 1].toLowerCase()).charAt(6) == ':') {
                ans.append(" " + str.elementAt((int) (Math.random() * 100) % str.size()));
            }
        }
        ans.append(" " + div[div.length - 1]);
        return ans.toString();
    }

    static String calcShortestPath(final Graph gg,
                                   final String word1, final String word2) {
        str = new Vector<String>();
        int[] d = new int[gg.getWordsid()];
        int[] nxt = new int[gg.getWordsid()];
        int[] path = new int[gg.getWordsid()];
        for (int i = 0; i < d.length; i++) {
            d[i] = -1;
            nxt[i] = -1;
            path[i] = -1;
        }
        Queue<Integer> q = new LinkedList<Integer>();
        if (!gg.getWords().containsKey(word1) || !gg.getWords().containsKey(word2)) {
            return "no start words or end words in the graph";
        }
        int sid = gg.getWords().get(word1);
        int eid = gg.getWords().get(word2);
        q.offer(sid);
        d[sid] = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            HashMap<Integer, Integer> tp
                    = gg.showEs(gg.getWordlist().elementAt(x));
            for (HashMap.Entry<Integer, Integer> entry : tp.entrySet()) {
                int v = entry.getKey();
                int val = entry.getValue();
                if (d[v] == -1 || d[v] > d[x] + val) {
                    d[v] = d[x] + val;
                    nxt[v] = x;
                    q.offer(v);
                }
            }
        }
        if (d[eid] == -1) {
            return "no way to " + word2;
        }
        int e = eid;
        while (nxt[e] != -1) {
            path[e] = nxt[e];
            e = nxt[e];
        }
        JFrame frame = new JFrame("展示最短路");
        MyG ll = new MyG(gg, path);
        frame.add(ll);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setVisible(true);
        return "" + d[eid];
    }
    static String randomWalk(final Graph g) {
        MyThr.setNxt(-1);
        ShowRWay.setWords("");
        new ShowRWay(gra).show();
        return ShowRWay.getWords();
    }
}

