package lab1;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class NewF implements ActionListener {
    JButton button = new JButton("转换成英文");
    JTextField field = new JTextField();
    JLabel label = new JLabel("请在最上面的框中输入数字");
    String s;
    
    public void go() {
        JFrame frame = new JFrame("单个数字转换成英文:");
        button.addActionListener(this);
        
        frame.getContentPane().add(BorderLayout.CENTER, button);
        frame.getContentPane().add(BorderLayout.NORTH,field);
        frame.getContentPane().add(BorderLayout.SOUTH,label);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,600);
       
        frame.setVisible(true);
        
        
    }
   
    public void actionPerformed(ActionEvent event) {
        s = field.getText();
        if (s.length() == 0) {
            label.setText("请输入^");
        }
        else {
            int x = s.charAt(0) - '0';
           
            label.setText((x >= 0 && x <= 9) ? myOut[x] : "请输入合法的数字^");
        }
    }
   
    public static final String myOut[]= {"zero", "one", "two", "three",
        "four", "five", "six", "seven", "eight", "nine"};
}
//c4
