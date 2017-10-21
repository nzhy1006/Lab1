import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
public class ShowRWay extends JFrame {
    private MyThr thread = null;
    private Graph g;
    private static String words;
    private static JTextArea txtA = new JTextArea("", 20, 43);
    private static JLabel echo = new JLabel();
    public MyThr getThread() {
        return thread;
    }

    public void setThread(final MyThr thread) {
        this.thread = thread;
    }
    public Graph getG() {
        return g;
    }

    public void setG(final Graph g) {
        this.g = g;
    }
    public static String getWords() {
        return words;
    }
    public static void setWords(final String words) {
        ShowRWay.words = words;
    }
    public static JTextArea getTxtA() {
        return txtA;
    }
    public static void setTxtA(final JTextArea txtA) {
        ShowRWay.txtA = txtA;
    }
    public static JLabel getEcho() {
        return echo;
    }
    public static void setEcho(final JLabel echo) {
        ShowRWay.echo = echo;
    }
    public ShowRWay(final Graph g) {
        try {
            createFrame();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        this.g = g;
    }
    private void createFrame() {
        this.setTitle("random go");
        JPanel jp = new JPanel(new FlowLayout());
        this.add(jp);
        this.add(BorderLayout.SOUTH, echo);
        JButton jbStart = new JButton("start/continue");
        JButton jbEnd = new JButton("pause");
        jp.add(jbStart);
        jp.add(jbEnd);
        txtA.setLineWrap(true);        //激活自动换行功能
        txtA.setWrapStyleWord(true);
        jp.add(new JScrollPane(txtA));
        txtA.setText("");
        echo.setText("click start ~");
        this.setSize(600, 450);
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jbStart.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (thread != null) {
                    thread.stop();
                }
                thread = new MyThr(g);
                thread.start();
            }
        });
        jbEnd.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (thread != null) {
                    echo.setText("paused");
                    thread.stop();
                }
                thread = null;
            }
        });
    }
}

