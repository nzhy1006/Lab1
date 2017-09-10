package lab1;

import java.awt.FlowLayout;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JPanel;

public class ShowRWay extends JFrame{
	MyThr thread = null;  
    Graph g;
    public ShowRWay(Graph g) {  
        try {  
            createFrame();  
        }  catch(Exception e) {  
            e.printStackTrace();  
        } 
        this.g = g;
    }  
  
    private void createFrame() {  
        JPanel jp = new JPanel(new FlowLayout());  
        this.add(jp);  
  
        JButton jbStart = new JButton("start ");  
        JButton jbEnd = new JButton("stop");  
        jp.add(jbStart);  
        jp.add(jbEnd);  
  
        this.setSize(300, 100);  
        this.setVisible(true);  
        this.setResizable(false);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  
        jbStart.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                if (thread != null)  
                    thread.stop();  
                thread = new MyThr(g);  
                thread.start();  
            }  
        });  
        jbEnd.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                if (thread != null)  
                    thread.stop();  
                thread = null;  
            }  
        });  
    }  
}
