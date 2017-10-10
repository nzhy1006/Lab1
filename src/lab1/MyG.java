package lab1;

import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JLabel;

public class MyG extends JLabel {
	Vector<WordImg> words = new Vector<WordImg>();
	
	MyG (Graph gra) {
		for (int i = 0; i < gra.wordsid; i++) {
			WordImg tmp = new WordImg(gra, i);
			words.addElement(tmp);
			//System.out.println(i + " ds");
		}
	}
	MyG(Graph gra, int[] flag) {
		WordImg.nxt = flag;
		for (int i = 0; i < gra.wordsid; i++) {
			WordImg tmp = new WordImg(gra, i);
			words.add(tmp);
		}
	}
	public void paint(Graphics g){
		for (int i = 0; i < words.size(); i++) {
			words.elementAt(i).myShow(g);
		}
	}
}
//tt