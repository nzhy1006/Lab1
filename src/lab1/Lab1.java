package lab1;

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
import java.util.Vector;//b1

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Lab1 {
	static Graph gra;
	static String strS;
	static Vector<String> str;
	public static void main(String[] args) {
		MainF a = new MainF();
		a.go();
	}
	static Graph createDirectedGraph(String filename) {
		Graph g = new Graph();
		g.readFile(filename);
		return g;
	}
	static void showDirectedGraph(Graph G) {
		JFrame frame = new JFrame("展示有向图");
		JButton butt = new JButton("save as a jpg");
		frame.getContentPane().add(BorderLayout.SOUTH, butt);
        MyG ll=new MyG(G); //extends JLabel
        frame.add(BorderLayout.CENTER, ll);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setVisible(true);
        butt.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                Container con = frame.getContentPane();
                BufferedImage img = new BufferedImage(
                	1200,650,BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = img.createGraphics();
                con.printAll(g2d);
                File f = new File("./aaa.jpg");
                try {
                	ImageIO.write(img,"jpg",f);
                	butt.setText("保存成功！");
                } catch (IOException ex) {
                }
                g2d.dispose();
            }  
        });
	}
	static String queryBridgeWords(Graph G, String word1, String word2) {
		if(G.words.containsKey(word1) == false || G.words.containsKey(word2) == false) {
			strS = "No word 1 or word 2 in the graph";
			return strS;
		}
		int xid = G.words.get(word1);
		int yid = G.words.get(word2);
		HashMap<Integer, Integer> tpm = G.vec.elementAt(xid);
		str = new Vector<String>();
		for (HashMap.Entry<Integer, Integer> entry : tpm.entrySet()) { 
			if(G.vec.elementAt(entry.getKey()).containsKey(yid)) {
				str.addElement(G.wordlist.elementAt(entry.getKey()));
			}
		}
		if(str.isEmpty() == true) {
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
	static String generateNewText(Graph G, String inputText) {
		String[] div = inputText.split(" ");
		String ans = "";
		for(int i = 0; i < div.length - 1; i++) {
			ans = ans + " " + div[i];
			if(queryBridgeWords(G, div[i].toLowerCase(), div[i + 1].toLowerCase()).charAt(6) == ':') {
				ans = ans + " " + str.elementAt((int)(Math.random() * 100) % str.size());
			}
		}
		ans = ans + " " + div[div.length - 1];
		return ans;
	}
	
    static String calcShortestPath(Graph G, String word1, String word2) {
    	str = new Vector<String>();
    	int[] d = new int[G.wordsid];
    	int[] nxt = new int[G.wordsid];
    	int[] path = new int[G.wordsid];
    	for(int i = 0; i < d.length; i++) {
    		d[i] = -1;
    		nxt[i] = -1;
    		path[i] = -1;
    	}
    	Queue<Integer> q = new LinkedList<Integer>();
    	if (G.words.containsKey(word1) == false || G.words.containsKey(word2) == false) {
    		return "no start words or end words in the graph";
    	}
    	int sid = G.words.get(word1);
    	int eid = G.words.get(word2);
    	q.offer(sid);
    	d[sid] = 0;
    	while (q.isEmpty() == false) {
    		int x = q.poll();
    		HashMap<Integer, Integer> tp = G.showEs(G.wordlist.elementAt(x));
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
        MyG ll=new MyG(G, path);
        frame.add(ll);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200,700);
        frame.setVisible(true);
    	return "" + d[eid];
	}
    static String randomWalk(Graph G) {
    	MyThr.nxt = -1;
    	ShowRWay.words = "";
    	new ShowRWay(gra).show();
    	return ShowRWay.words;
    }
}