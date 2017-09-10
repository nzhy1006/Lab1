package lab1;

import java.awt.*;
import java.util.HashMap;

class WordImg {
	public static final int x_rad = 500;
	public static final int y_rad = 300;
	public int id2x(int id) {
		return (int)(x_rad * Math.cos(2 * Math.PI * id / totalnum) + x_rad + 50);
	}
	public int id2y(int id) {
		return (int)(y_rad * Math.sin(2 * Math.PI * id / totalnum) + y_rad + 30);
	}
	void drawArrow(Graphics g, int x, int y, int nx, int ny, int value, boolean bold) {
		if (bold == true) {
			g.setColor(Color.green);
		}
		double len = Math.sqrt((nx - x) * (nx - x) + (ny - y) * (ny - y));
		double cosTheta = (nx - x) / len;
		double sinTheta = (ny - y) / len;
		// cut 30 
		x = (int)(x + 30 * cosTheta);
		y = (int)(y + 30 * sinTheta);
		nx = (int)(nx - 30 * cosTheta);
		ny = (int)(ny - 30 * sinTheta);
		//translation 10
		x = (int)(x - 10 * sinTheta);
		y = (int)(y + 10 * cosTheta);
		nx = (int)(nx - 10 * sinTheta);
		ny = (int)(ny + 10 * sinTheta);
		
		g.drawLine(x, y, nx, ny);
		int y1 = (int)(ny - 20 * (0.866 * sinTheta - 0.5 * cosTheta));
		int x1 = (int)(nx - 20 * (0.866 * cosTheta + 0.5 * sinTheta));
		g.drawLine(x1, y1, nx, ny);
		int y2 = (int)(ny - 20 * (0.866 * sinTheta + 0.5 * cosTheta));
		int x2 = (int)(nx - 20 * (0.866 * cosTheta - 0.5 * sinTheta));
		g.drawLine(x2, y2, nx, ny);
		g.setColor(Color.red);
		g.drawString(""+value, (x + nx) / 2, (y + ny) / 2);
		g.setColor(Color.black);
	}
	void mdArc(Graphics g, int value){
		g.setColor(Color.blue);
		g.drawArc(x + name.length() - 3, y - 30, 60, 60, 225, 270);
		int bx = x + name.length() + 57;
		int by = y;
		int x1 = bx - 5;
		int y1 = by - 8;
		g.drawLine(x1, y1, bx, by);
		int x2 = bx + 5;
		int y2 = by - 8;
		g.drawLine(x2, y2, bx, by);
		g.setColor(Color.red);
		g.drawString(""+value, bx + 7, by);
		g.setColor(Color.black);
	}
	
	static int[] nxt;
	String name;
	int x;
	int y;
	int id;
	int totalnum;
	HashMap<Integer, Integer> neighbors;   //neighbors' id -> value
	WordImg(Graph gra, int i) {
		//System.out.println("aa a");
		totalnum = gra.wordsid;
		name = gra.wordlist.elementAt(i);
		x = id2x(i);
		y = id2y(i);
		id = i;
		neighbors = gra.showEs(name);
		//System.out.println("aa abb");
	}
	void myShow(Graphics g){
		g.setFont(new Font("Courier", Font.PLAIN, 15));
		g.drawString(name, x - 5 * name.length(), y + 5);
		g.drawOval(x - 5 * name.length() - 10, y - 15, name.length() * 10 + 15, 30);
		
		//drawLine
		for (HashMap.Entry<Integer, Integer> entry : neighbors.entrySet()) {
			int n_id = entry.getKey();
			int nx = id2x(n_id);
			int ny = id2y(n_id);
			if (n_id == id) {
				mdArc(g, entry.getValue());
			} else {
				drawArrow(g, x, y, nx, ny, entry.getValue(), nxt == null ? false : id == nxt[n_id]);
			}
		    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
		} 
	}
}