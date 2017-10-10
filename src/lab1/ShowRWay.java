package lab1;

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

public class ShowRWay extends JFrame{
	MyThr thread = null;  
    Graph g;
    static String words;
    static JTextArea txtA = new JTextArea("",20,43);
    static JLabel echo = new JLabel();
    public ShowRWay(Graph g) {  
        try {  
            createFrame();  
        }  catch(Exception e) {  
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
            public void actionPerformed(ActionEvent e) {  
                if (thread != null)  
                    thread.stop();  
                thread = new MyThr(g);  
                thread.start();
            }  
        });  
        jbEnd.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                if (thread != null) {
                	echo.setText("paused");
                	thread.stop(); 
                }
                thread = null;  
            }  
        });  
    }  
}

//b1