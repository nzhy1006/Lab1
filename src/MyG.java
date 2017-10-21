import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JLabel;

public class MyG extends JLabel {
    private Vector<WordImg> words = new Vector<WordImg>();

    public Vector<WordImg> getWords() {
        return words;
    }

    public void setWords(final Vector<WordImg> words) {
        this.words = words;
    }

    MyG(final Graph gra) {
        for (int i = 0; i < gra.getWordsid(); i++) {
            WordImg tmp = new WordImg(gra, i);
            words.addElement(tmp);
            //System.out.println(i + " ds");
        }
    }
    MyG(final Graph gra, final int[] flag) {
        WordImg.setNxt(flag);
        for (int i = 0; i < gra.getWordsid(); i++) {
            WordImg tmp = new WordImg(gra, i);
            words.add(tmp);
        }
    }
    public void paint(final Graphics g) {
        for (int i = 0; i < words.size(); i++) {
            words.elementAt(i).myShow(g);
        }
    }
}
