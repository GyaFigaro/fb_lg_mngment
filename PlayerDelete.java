import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerDelete extends JFrame {

    private JPanel contentPane;
    private JComboBox comBox1;
    private JComboBox comBox2;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ConnectMysql.init();
            PlayerDelete pd = new PlayerDelete();
            pd.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the frame.
     */
    public PlayerDelete() {
        setTitle("球员注销");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(65,25,65));
        contentPane.setLayout(null);
        Vector<String> name = ConnectMysql.get_player(1,"all");
        Vector<String> id = ConnectMysql.get_player(0,"all");

        JLabel Label = new JLabel("球员名字");
        Label.setBounds(30, 30, 63, 15);
        Label.setForeground(Color.white);
        contentPane.add(Label);

        comBox1 = new JComboBox<String>();
        comBox1.setBounds(110, 30, 100, 21);
        contentPane.add(comBox1);
        comBox1.addItem("-NULL-");
        for(int i = 0; i < name.size(); i++)
            comBox1.addItem(name.elementAt(i));

        JLabel label2 = new JLabel("球员编号");
        label2.setBounds(30, 80, 63, 15);
        label2.setForeground(Color.white);
        contentPane.add(label2);

        comBox2 = new JComboBox<String>();
        comBox2.setBounds(110, 80, 100, 21);
        contentPane.add(comBox2);
        comBox2.addItem("-NULL-");
        for(int i = 0; i < id.size(); i++)
            comBox2.addItem(id.elementAt(i));


        JButton button = new JButton("注销");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(ConnectMysql.delete_player("player_name",(String) comBox1.getSelectedItem())==true){
                    JOptionPane.showMessageDialog(getParent(), "注销成功", "注销结果", JOptionPane.INFORMATION_MESSAGE);
                    PlayerDelete.this.dispose();
                }else{
                    JOptionPane.showMessageDialog(getParent(), "注销失败", "注销结果", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button.setBounds(250, 30, 63, 21);
        contentPane.add(button);

        JButton button1 = new JButton("注销");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(ConnectMysql.delete_player("player_id",(String) comBox2.getSelectedItem())==true){
                    JOptionPane.showMessageDialog(getParent(), "注册成功", "注册结果", JOptionPane.INFORMATION_MESSAGE);
                    PlayerDelete.this.dispose();
                }else{
                    JOptionPane.showMessageDialog(getParent(), "注册失败", "注册结果", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button1.setBounds(250, 80, 63, 23);
        contentPane.add(button1);

        JButton button2 = new JButton("退出");

        button2.setBounds(170, 200, 63, 23);
        contentPane.add(button2);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
}