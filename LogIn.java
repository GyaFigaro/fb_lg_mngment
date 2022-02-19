import java.awt.*;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;

import javax.swing.JLabel;

//启动函数
public class LogIn extends JFrame{
    private JPanel contentPane;
    private JTextField textField1, textField2;
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConnectMysql.init();
                    LogIn frame = new LogIn();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LogIn(){
        setTitle("足球联赛管理系统 V0.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 200, 450, 250);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(65,25,65));
        contentPane.setLayout(null);

        JLabel Label = new JLabel("用户名");
        Label.setFont(new Font("微软雅黑", Font.BOLD, 18));
        Label.setForeground(Color.white);
        Label.setBounds(50, 33, 100, 21);
        contentPane.add(Label);

        textField1 = new JTextField();
        textField1.setBounds(120, 34, 250, 21);
        contentPane.add(textField1);
        textField1.setColumns(10);

        JLabel label2 = new JLabel("密码");
        label2.setFont(new Font("微软雅黑", Font.BOLD, 18));
        label2.setForeground(Color.white);
        label2.setBounds(50, 78, 100, 21);
        contentPane.add(label2);

        textField2 = new JTextField();
        textField2.setBounds(120, 79, 250, 21);
        contentPane.add(textField2);
        textField2.setColumns(10);

        JButton button = new JButton("登录");
        button.setFont(new Font("微软雅黑", Font.BOLD ,13));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = textField2.getText();
                if(Objects.equals(username, "root") && Objects.equals(password, "root")){
                    JOptionPane.showMessageDialog(getParent(), "登录成功", "登录", JOptionPane.INFORMATION_MESSAGE);
                    Main mainframe = new Main();
                    mainframe.setVisible(true);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(getParent(), "登录失败", "登录", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button.setBounds(100, 150, 80, 30);
        contentPane.add(button);

        JButton button_1 = new JButton("退出");
        button_1.setFont(new Font("微软雅黑", Font.BOLD ,13));
        button_1.setBounds(250, 150, 80, 30);
        contentPane.add(button_1);
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}