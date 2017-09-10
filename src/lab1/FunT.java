package lab1;

import javax.swing.JFrame;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.Queue;
import java.util.LinkedList;


public class FunT {
	static String strS;
	static Vector<String> str;
	static void showGra(Graph gra) {
		//Graph a = new Graph();
		//a.readFile("/home/hhk/Desktop/1.txt");
		JFrame frame = new JFrame("展示有向图");
        MyG ll=new MyG(gra);
        frame.add(ll);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,700);
        frame.setVisible(true);
	}
	static int bridge(Graph g, String s1, String s2) {
		//s1 = s1.toLowerCase();
		//s2 = s2.toLowerCase();
		if(g.words.containsKey(s1) == false || g.words.containsKey(s2) == false) {
			strS = "No word1 or word2 in the graph";
			return -1;
		}
		
		int xid = g.words.get(s1);
		int yid = g.words.get(s2);
		HashMap<Integer, Integer> tpm = g.vec.elementAt(xid);
		str = new Vector<String>();
		for (HashMap.Entry<Integer, Integer> entry : tpm.entrySet()) { 
			if(g.vec.elementAt(entry.getKey()).containsKey(yid)) {
				str.addElement(g.wordlist.elementAt(entry.getKey()));
			}
		}
		if(str.isEmpty() == true) {
			strS = "no bridge";
			return -1;
		} else {
			strS = "bridge: ";
			for (int i = 0; i < str.size(); i++) {
				strS += ", " + str.elementAt(i);
			}
			return 0;
		}
	}
	static String newtext(Graph g, String s) {
		String[] div = s.split(" ");
		String ans = "";
		for(int i = 0; i < div.length - 1; i++) {
			ans = ans + " " + div[i];
			if(bridge(g, div[i].toLowerCase(), div[i + 1].toLowerCase()) == 0) {
				ans = ans + " " + str.elementAt((int)(Math.random() * 100) % str.size());
			}
		}
		ans = ans + " " + div[div.length - 1];
		return ans;
	}
	
    static int shortestpath(Graph g, String start, String end) {
    	str = new Vector<String>();
    	int[] d = new int[g.wordsid];
    	int[] nxt = new int[g.wordsid];
    	int[] path = new int[g.wordsid];
    	for(int i = 0; i < d.length; i++) {
    		d[i] = -1;
    		nxt[i] = -1;
    		path[i] = -1;
    	}
    	Queue<Integer> q = new LinkedList<Integer>();
    	int sid = g.words.get(start);
    	int eid = g.words.get(end);
    	q.offer(sid);
    	d[sid] = 0;
    	while (q.isEmpty() == false) {
    		int x = q.poll();
    		HashMap<Integer, Integer> tp = g.showEs(g.wordlist.elementAt(x));
    		for (HashMap.Entry<Integer, Integer> entry : tp.entrySet()) {
    			int v = entry.getKey();
    			int val = entry.getValue();
    		    if (d[v] == -1 || d[v] > d[x] + val) {
    		    	d[v] = d[x] + val;
    		    	nxt[v] = x;
    		    	q.offer(v);
    		    	/*
    		    	System.out.println("offer " + v + "    :");
    		    	for (int i = 0; i < d.length; i++) {
    		    		System.out.print(d[i] + " ");
    		    	}
    		    	System.out.println("  ");
    		    	*/
    		    }
    		} 
    	}
    	if (d[eid] == -1) {
    		return -1;
    	}
    	int e = eid;
    	while (nxt[e] != -1) {
    		path[e] = nxt[e];
    		e = nxt[e];
    	}
    	
    	JFrame frame = new JFrame("展示最短路");
        MyG ll=new MyG(g, path);
        frame.add(ll);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,700);
        frame.setVisible(true);
    	return d[eid];
	}
    static String random(Graph g) {
		/*String ans = "";
		
		Random ran = new Random();
		
		int sta = ran.nextInt(g.wordsid);
		boolean temp = false;
		HashMap<Integer, Integer> tpm = g.vec.elementAt(sta);
	
		
		ans = ans + g.wordlist.get(sta);
		
		while(g.vec.elementAt(sta).isEmpty() != true && temp != true) {
			int index = ran.nextInt(g.vec.elementAt(sta).size());
			int count = 0;
			tpm = g.vec.elementAt(sta);
			
			for (HashMap.Entry<Integer, Integer> entry : tpm.entrySet()) { 
				if(count == index) {
					ans = ans + g.wordlist.get(entry.getKey());
					if(g.mark.elementAt(sta).get(entry.getKey()) == true) { //contain this edge
						return ans;
					} else {
						g.mark.elementAt(sta).put(entry.getKey(), true);
						sta = entry.getKey();
					}
					break;
				}
				count += 1;
			} 
		}
		th1 t1 = new th1();
	    Thread tt1 = new Thread(t1);
	    tt1.start();
	    th2 t2 = new th2();
	    Thread tt2 = new Thread(t2);
	    tt2.start();
	    while(th1.Run != false) {
	    	System.out.println(th1.Run + ans);
	    }
	    System.out.println("1213413");*/
		
		Thr t = new Thr(g);
		//th2 tt = new th2();
		//Thread t2 = new Thread(tt);
		t.go.start();
		t.stop.start();
		//t2.start();
	
		try {  
	        t.stop.join();  
	        t.go.join();  
	    } catch (InterruptedException e) {  
	        e.printStackTrace(); 
	    } 
		System.out.println("ssssssssssssssssss");
		return t.ans;
    }
    static void ranGo(Graph gra) {
    	new ShowRWay(gra).show();
    }
}