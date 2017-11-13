package lab1;


import java.util.HashMap;
import java.util.Vector;

public class MyThr extends Thread {
	static boolean[][] flag;
	static int nxt = -1;
	Graph g;
	MyThr(Graph g) {
		this.g = g;
	}
	int mGo() {
		if (nxt == -2) { // have ended
			return -2;
		}
		if (nxt == -1) {
			flag = new boolean[g.wordsid][g.wordsid];
			nxt = (int)(Math.random() * g.wordsid);
			ShowRWay.words += g.wordlist.elementAt(nxt) + "\n";
			//System.out.println(g.wordlist.elementAt(nxt));
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
		//System.out.println(g.wordlist.elementAt(n_nxt));
		ShowRWay.words += g.wordlist.elementAt(n_nxt) + "\n";
		if (flag[nxt][n_nxt] == true) {
			return -3;
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
			int ret = mGo();
			if (ret == -1) {
				ShowRWay.echo.setText("no next word!");
				nxt = -2;
			}
			if (ret == -3) {
				ShowRWay.echo.setText("repeat edge!");
				nxt = -2;
			}
			if (ret == 0) {
				ShowRWay.echo.setText("travelling...");
			}
			ShowRWay.txtA.setText(ShowRWay.words);
		}
	}
}
