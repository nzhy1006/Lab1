package lab1;

import java.util.HashMap;
import java.util.Vector;

public class MyThr extends Thread {
	static boolean[][] flag;
	static int nxt = -1;
	Graph g;
	MyThr(Graph g) {
		if (flag == null) {
			flag = new boolean[g.wordsid][g.wordsid];
		}
		this.g = g;
	}
	int mGo() {
		if (nxt == -2) {
			return -2;
		}
		if (nxt == -1) {
			nxt = (int)(Math.random() * g.wordsid);
			System.out.println(g.wordlist.elementAt(nxt));
		}
		int num = g.vec.elementAt(nxt).size();
		if (num == 0) {
			return -1;
		}
		HashMap<Integer, Integer> tpm = g.vec.elementAt(nxt);
		Vector<Integer> tp = new Vector<Integer>();
		for (HashMap.Entry<Integer, Integer> entry : tpm.entrySet()) {
			tp.add(entry.getKey());
		    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
		} 
		int n_nxt = tp.elementAt((int)(Math.random() * num));
		System.out.println(g.wordlist.elementAt(n_nxt));
		if (flag[nxt][n_nxt] == true) {
			return -1;
		}
		flag[nxt][n_nxt] = true;
		nxt = n_nxt;
		return 0;
	}
	public void run() {
		while (true) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {  
            } 
			if (mGo() == -1) {
				nxt = -2;
			}
		}
	}
}
