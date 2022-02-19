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

public class PlayerUpdate extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JComboBox comBox1, comBox2;
    private String pid;
    //private JTextField textField_4;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ConnectMysql.init();
            PlayerUpdate pu = new PlayerUpdate();
            pu.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the frame.
     */
    public PlayerUpdate() {
        setTitle("球员更新");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(65,25,65));
        contentPane.setLayout(null);
        Vector<String> id = ConnectMysql.get_player(0,"all");


        JLabel Label = new JLabel("选择编号");
        Label.setBounds(10, 10, 63, 15);
        Label.setForeground(Color.white);
        contentPane.add(Label);

        comBox1 = new JComboBox<String>();
        comBox1.setBounds(80, 7, 100, 21);
        contentPane.add(comBox1);
        comBox1.addItem("-null-");
        for(int i = 0; i < id.size(); i++)
            comBox1.addItem(id.elementAt(i));

        JLabel label2 = new JLabel("球员姓名");
        label2.setBounds(10, 38, 63, 15);
        label2.setForeground(Color.white);
        contentPane.add(label2);

        textField_1 = new JTextField();
        textField_1.setBounds(80, 35, 300, 21);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel label3 = new JLabel("球员国籍");
        label3.setBounds(10, 66, 63, 15);
        label3.setForeground(Color.white);
        contentPane.add(label3);

        textField_2 = new JTextField();
        textField_2.setBounds(80, 63, 300, 21);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JLabel label4 = new JLabel("所属俱乐部");
        label4.setBounds(10, 94, 63, 15);
        label4.setForeground(Color.white);
        contentPane.add(label4);

        textField_3 = new JTextField();
        textField_3.setBounds(80, 91, 300, 21);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        JLabel label5 = new JLabel("球衣号码");
        label5.setBounds(10, 122, 63, 15);
        label5.setForeground(Color.white);
        contentPane.add(label5);

        textField_4 = new JTextField();
        textField_4.setBounds(80, 119, 300, 21);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        JLabel label6 = new JLabel("场上位置");
        label6.setBounds(10, 150, 63, 15);
        label6.setForeground(Color.white);
        contentPane.add(label6);

        comBox2 = new JComboBox<String>();
        comBox2.setBounds(80, 147, 100, 21);
        contentPane.add(comBox2);
        comBox2.addItem("-null-");
        comBox2.addItem("S");
        comBox2.addItem("M");
        comBox2.addItem("D");
        comBox2.addItem("G");

        JButton button = new JButton("选择");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pid = (String) comBox1.getSelectedItem();
                PlayerDetail detail = ConnectMysql.get_detail(pid);
                textField_1.setText(detail.name);
                textField_2.setText(detail.country);
                textField_3.setText(detail.club);
                textField_4.setText(detail.num);
                comBox1.setSelectedItem(detail.pos);
            }
        });
        button.setBounds(200, 8, 63, 21);
        contentPane.add(button);

        JButton button_1 = new JButton("更新");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textField_1.getText();
                String country = textField_2.getText();
                String club = textField_3.getText();
                String num = textField_4.getText();
                String pos = (String) comBox2.getSelectedItem();
//						System.out.println(data.toString());
                if(ConnectMysql.update_player(pid, name, country, club, num, pos)==true){
                    JOptionPane.showMessageDialog(getParent(), "更新成功", "更新结果", JOptionPane.INFORMATION_MESSAGE);
                    PlayerUpdate.this.dispose();
                }else{
                    JOptionPane.showMessageDialog(getParent(), "更新失败", "更新结果", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button_1.setBounds(100, 200, 63, 21);
        contentPane.add(button_1);

        JButton button_2 = new JButton("退出");

        button_2.setBounds(200, 200, 63, 23);
        contentPane.add(button_2);
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
}