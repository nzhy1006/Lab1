import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Vector;

public class Graph {
    private int wordsid = 0;
    private Vector<HashMap<Integer, Integer>> vec =
            new Vector<HashMap<Integer, Integer>>();  //heads
    private HashMap<String, Integer> words =
            new HashMap<String, Integer>();  // trans words to int
    private Vector<String> wordlist = new Vector<String>();
    private boolean[] mark;

    public int getWordsid() {
        return wordsid;
    }

    public void setWordsid(int wordsid) {
        this.wordsid = wordsid;
    }

    public Vector<HashMap<Integer, Integer>> getVec() {
        return vec;
    }

    public void setVec(final Vector<HashMap<Integer, Integer>> vec) {
        this.vec = vec;
    }

    public HashMap<String, Integer> getWords() {
        return words;
    }

    public void setWords(HashMap<String, Integer> words) {
        this.words = words;
    }

    public Vector<String> getWordlist() {
        return wordlist;
    }

    public void setWordlist(Vector<String> wordlist) {
        this.wordlist = wordlist;
    }

    public boolean[] getMark() {
        if(mark == null){
            return null;
        }
        else {
            return (boolean[])mark.clone();
        }
    }

    public void setMark(final boolean[] mark) {
        if (mark == null) {
            this.mark = null;
        }
        else {
            this.mark = (boolean[])mark.clone();
        }
    }

    String line(final String str) {
        String strs = str.toLowerCase();
        StringBuilder ss = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < strs.length(); i++) {
            if (strs.charAt(i) >= 'a' && strs.charAt(i) <= 'z'
                    || strs.charAt(i)
                    >= 'A' && strs.charAt(i) <= 'Z') {
                ss.append(strs.charAt(i));
                flag = true;
            } else {
                if (flag) {
                    ss.append(' ');
                    flag = false;
                }
            }
        }
        return ss.toString();
    }
    void readFile(final String filePath) {
        wordsid = 0;
        vec = new Vector<HashMap<Integer, Integer>>();  //heads
        words = new HashMap<String, Integer>();  // trans words to int
        wordlist = new Vector<String>();
//        Vector<HashMap<Integer, Boolean>> mark =
//                new Vector<HashMap<Integer, Boolean>>();
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                String end = null;
                lineTxt = bufferedReader.readLine();
                while (lineTxt != null) {
                    String str = line(lineTxt);
                    if (!str.equals("")) {
                        String[] spl = str.split(" ");
                        if (end != null) {
                            addE(end, spl[0]);
                        }
                        for (int i = 0; i < spl.length - 1; i++) {
                            addE(spl[i], spl[i + 1]);
                        }
                        end = spl[spl.length - 1];
                    }
                    lineTxt = bufferedReader.readLine();
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
//            e.printStackTrace();
        }
    }
    void addN(final String x) {
        if (!words.containsKey(x)) {
            words.put(x, wordsid);
            wordlist.add(x);
            HashMap<Integer, Integer> tpm =
                    new HashMap<Integer, Integer>();
            vec.addElement(tpm);
            wordsid = wordsid + 1;
        }
    }
    void addE(final String u, final String v) {
        addN(u);
        addN(v);
        int uid = words.get(u);
        int vid = words.get(v);
        int evalue = 1;
        if (vec.elementAt(uid).containsKey(vid)) {
            evalue = vec.elementAt(uid).get(vid) + 1;
        }
        vec.elementAt(uid).put(vid, evalue);
    }
    HashMap<Integer, Integer> showEs(final String x) {
        if (!words.containsKey(x)) {
            System.out.println(x + "dose not exist!");
            return new HashMap<Integer, Integer>();
        }
        int xid = words.get(x);
        HashMap<Integer, Integer> tpm = vec.elementAt(xid);
        return tpm;
    }
}
