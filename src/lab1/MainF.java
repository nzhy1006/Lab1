package lab1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import java.io.File;

public class MainF extends JFrame {
	static String s;
	static String echo = "no text";
	public void go() {
		String instruction1 = "read a file : input the path, and read a text file";
		String instruction2 = "show graph : trans the file to a graph";
		String instruction3 = "bridge words : show the bridge words between to words"; 
		String instruction4 = "new text : trans a sentence to a new sentence";
		String instruction5 = "shortest path : show the shortest path between to words"; 
		String instruction6 = "random go : traval in the graph randomly";
		
		JPanel jp = new JPanel(new FlowLayout());
        this.add(jp);

        JLabel echol = new JLabel(echo);
        JLabel label1 = new JLabel(instruction1);
        JLabel label2 = new JLabel(instruction2);
        JLabel label3 = new JLabel(instruction3);
        JLabel label4 = new JLabel(instruction4);
        JLabel label5 = new JLabel(instruction5);
        JLabel label6 = new JLabel(instruction6);
        JButton readFile = new JButton("read a file");  
        JButton showGraph = new JButton("show graph");  
        JButton bridgeWords = new JButton("bridge words");
        JButton newText = new JButton("new text");
        JButton shortestPath = new JButton("shortest path");
        JButton randomGo = new JButton("random go");
        jp.add(readFile);  
        jp.add(showGraph);
        jp.add(bridgeWords);
        jp.add(newText);
        jp.add(shortestPath);
        jp.add(randomGo);
        jp.add(label1);
        jp.add(label2);
        jp.add(label3);
        jp.add(label4);
        jp.add(label5);
        jp.add(label6);
        
        this.setTitle("实验一");
        this.add(BorderLayout.SOUTH,echol);
        this.setSize(400, 300);
        this.setVisible(true);  
        this.setResizable(false);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        //set buttons
        readFile.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            	JFileChooser ch = new JFileChooser();
            	ch.setCurrentDirectory(new File("."));
            	int result = ch.showOpenDialog(jp);
            	String filename = ch.getSelectedFile().getPath();
            	Lab1.gra = Lab1.createDirectedGraph(filename);
            	echol.setText(filename);
            	/*
            	g = new Graph();
            	JButton button = new JButton("submit");
                JTextField field = new JTextField("/home/hhk/Desktop/1.txt");
                JLabel label = new JLabel("在上面的框中输入路径");
            	JFrame frame = new JFrame("read a file");
            	
                frame.getContentPane().add(BorderLayout.CENTER, label);
                frame.getContentPane().add(BorderLayout.NORTH,field);
                frame.getContentPane().add(BorderLayout.SOUTH, button);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(210,100);
                frame.setVisible(true);
                
                button.addActionListener(new ActionListener() {  
                    public void actionPerformed(ActionEvent e) {
                    	s = field.getText();
                    	
                        g.readFile(s);
                        label.setText("Finish reading!");
                    }  
                }); 
                */
            }  
        });  
        showGraph.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	if (Lab1.gra == null) {
            		echol.setText("read a file first!");
            		return;
            	}
            	Lab1.showDirectedGraph(Lab1.gra);
            }  
        }); 
        bridgeWords.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	if (Lab1.gra == null) {
            		echol.setText("read a file first!");
            		return;
            	}
            	JButton button = new JButton("submit");
            	JLabel word1 = new JLabel("word 1");
            	JLabel word2 = new JLabel("word 2");
                JTextField field1 = new JTextField();
                field1.setColumns(30);
                JTextField field2 = new JTextField();
                field2.setColumns(30);
                JLabel label = new JLabel();
            	JFrame frame = new JFrame("bridgeWords");
            	JPanel panel = new JPanel(new FlowLayout()); 
            	frame.add(panel);
            	panel.add(word1);
            	panel.add(field1);
            	panel.add(word2);
            	panel.add(field2);
            	panel.add(button);
            	frame.add(BorderLayout.SOUTH, label);

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(400,220);
                frame.setVisible(true);
                button.addActionListener(new ActionListener() {  
                    public void actionPerformed(ActionEvent e) {
                    	String s1 = field1.getText();
                        String s2 = field2.getText();
                        label.setText(Lab1.queryBridgeWords(Lab1.gra, s1, s2));
                    }  
                }); 
            }  
        });
        newText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (Lab1.gra == null) {
            		echol.setText("read a file first!");
            		return;
            	}
            	JButton button = new JButton("submit");
            	JLabel word1 = new JLabel("sentence");
                JTextField field1 = new JTextField();
                field1.setColumns(30);
                JLabel label = new JLabel();
            	JFrame frame = new JFrame("newText");
            	JPanel panel = new JPanel(new FlowLayout()); 
            	frame.add(panel);
            	panel.add(word1);
            	panel.add(field1);
            	panel.add(button);
            	frame.add(BorderLayout.SOUTH, label);

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(400,220);
                frame.setVisible(true);
                button.addActionListener(new ActionListener() {  
                    public void actionPerformed(ActionEvent e) {
                    	String s1 = field1.getText();
                        label.setText(Lab1.generateNewText(Lab1.gra, s1));
                    }  
                }); 
            }  
        });  
        shortestPath.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            	if (Lab1.gra == null) {
            		echol.setText("read a file first!");
            		return;
            	}
            	JButton button = new JButton("submit");
            	JLabel word1 = new JLabel("word 1");
            	JLabel word2 = new JLabel("word 2");
                JTextField field1 = new JTextField();
                field1.setColumns(30);
                JTextField field2 = new JTextField();
                field2.setColumns(30);
                JLabel label = new JLabel();
            	JFrame frame = new JFrame("shortestPath");
            	JPanel panel = new JPanel(new FlowLayout()); 
            	frame.add(panel);
            	panel.add(word1);
            	panel.add(field1);
            	panel.add(word2);
            	panel.add(field2);
            	panel.add(button);
            	frame.add(BorderLayout.SOUTH, label);

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(400,220);
                frame.setVisible(true);
                button.addActionListener(new ActionListener() {  
                    public void actionPerformed(ActionEvent e) {
                    	String s1 = field1.getText();
                    	String s2 = field2.getText();
                        label.setText(Lab1.calcShortestPath(Lab1.gra, s1, s2));
                    }  
                }); 
            }  
        });  
        randomGo.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	if (Lab1.gra == null) {
            		echol.setText("read a file first!");
            		return;
            	}
            	Lab1.randomWalk(Lab1.gra);
            } 
        });  
	}
}

//b2