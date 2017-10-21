import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class NewF implements ActionListener {
    private JButton button = new JButton("转换成英文");
    private JTextField field = new JTextField();
    private JLabel label = new JLabel("请在最上面的框中输入数字");
    private String s;
    public JButton getButton() {
        return button;
    }
    public void setButton(final JButton button) {
        this.button = button;
    }
    public JTextField getField() {
        return field;
    }
    public void setField(final JTextField field) {
        this.field = field;
    }
    public JLabel getLabel() {
        return label;
    }
    public void setLabel(final JLabel label) {
        this.label = label;
    }

    public String getS() {
        return s;
    }

    public void setS(final String s) {
        this.s = s;
    }

    public void go() {
        JFrame frame = new JFrame("单个数字转换成英文:");
        button.addActionListener(this);
        frame.getContentPane().add(BorderLayout.CENTER, button);
        frame.getContentPane().add(BorderLayout.NORTH, field);
        frame.getContentPane().add(BorderLayout.SOUTH, label);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
    public void actionPerformed(final ActionEvent event) {
        s = field.getText();
        if (s.length() == 0) {
            label.setText("请输入^");
        } else {
            int x = s.charAt(0) - '0';
            if (x >= 0 && x <= 9) {
                label.setText(MYOUT[x]);
            } else {
                label.setText("请输入合法的数字^");
            }
        }
    }
    public static final String[] MYOUT  = {"zero", "one", "two", "three",
            "four", "five", "six", "seven", "eight", "nine"};
}

