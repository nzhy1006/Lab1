import java.awt.*;
import java.util.HashMap;

class WordImg {
    public static final int X_RAD = 500;
    public static final int Y_RAD = 300;
    public int id2x(final int id) {
        return (int) (X_RAD * Math.cos(2 * Math.PI * id / totalnum)
                       + X_RAD + 50);
    }
    public int id2y(final int id) {
        return (int) (Y_RAD * Math.sin(2 * Math.PI * id / totalnum)
                       + Y_RAD + 30);
    }
    void drawArrow(final Graphics g, final int x, final int y, final int nx,
                   final int ny, final int value, final boolean bold) {
        if (bold) {
            g.setColor(Color.green);
        }
        double len = Math.sqrt((nx - x) * (nx - x) + (ny - y) * (ny - y));
        double cosTheta = (nx - x) / len;
        double sinTheta = (ny - y) / len;
        // cut 30
        int xx = (int) (x + 30 * cosTheta);
        int yy = (int) (y + 30 * sinTheta);
        int nxnx = (int) (nx - 30 * cosTheta);
        int nyny = (int) (ny - 30 * sinTheta);
        //translation 10
        xx = (int) (xx - 10 * sinTheta);
        yy = (int) (yy + 10 * cosTheta);
        nxnx = (int) (nxnx - 10 * sinTheta);
        nyny = (int) (nyny + 10 * sinTheta);
        g.drawLine(xx, yy, nxnx, nyny);
        int y1 = (int) (nyny - 20 * (0.866 * sinTheta - 0.5 * cosTheta));
        int x1 = (int) (nxnx - 20 * (0.866 * cosTheta + 0.5 * sinTheta));
        g.drawLine(x1, y1, nxnx, nyny);
        int y2 = (int) (nyny - 20 * (0.866 * sinTheta + 0.5 * cosTheta));
        int x2 = (int) (nxnx - 20 * (0.866 * cosTheta - 0.5 * sinTheta));
        g.drawLine(x2, y2, nxnx, nyny);
        g.setColor(Color.red);
        g.drawString("" + value, (xx + nxnx) / 2, (yy + nyny) / 2);
        g.setColor(Color.black);
    }
    void mdArc(final Graphics g, final int value) {
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
        g.drawString("" + value, bx + 7, by);
        g.setColor(Color.black);
    }
    private static int[] nxt;
    private String name;
    private int x;
    private int y;
    private int id;
    private int totalnum;
    private HashMap<Integer, Integer> neighbors;   //neighbors' id -> value

    public static int[] getNxt() {
        return nxt;
    }

    public static void setNxt(final int[] nxt) {
        WordImg.nxt = nxt;
    }

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public int getX() {
        return x;
    }
    public void setX(final int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(final int y) {
        this.y = y;
    }
    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(final int totalnum) {
        this.totalnum = totalnum;
    }

    public HashMap<Integer, Integer> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(final HashMap<Integer, Integer> neighbors) {
        this.neighbors = neighbors;
    }
    WordImg(final Graph gra, final int i) {
        //System.out.println("aa a");
        totalnum = gra.getWordsid();
        name = gra.getWordlist().elementAt(i);
        x = id2x(i);
        y = id2y(i);
        id = i;
        neighbors = gra.showEs(name);
        //System.out.println("aa abb");
    }
    void myShow(final Graphics g) {
        g.setFont(new Font("Courier", Font.PLAIN, 15));
        g.drawString(name, x - 5 * name.length(), y + 5);
        g.drawOval(x - 5 * name.length() - 10, y - 15,
                name.length() * 10 + 15, 30);
        //drawLine
        for (HashMap.Entry<Integer, Integer> entry : neighbors.entrySet()) {
            int nid = entry.getKey();
            int nx = id2x(nid);
            int ny = id2y(nid);
            if (nid == id) {
                mdArc(g, entry.getValue());
            } else {
                if (nxt ==  null) {
                    drawArrow(g, x, y, nx, ny, entry.getValue(), false);
                } else {
                    drawArrow(g, x, y, nx, ny, entry.getValue(),
                            id == nxt[nid]);
                }
            }
            //System.out.println("Key = " + entry.getKey() + ",
            // Value = " + entry.getValue());
        }
    }
}

