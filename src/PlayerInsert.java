import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class PlayerInsert extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JComboBox comBox, comBox1, comBox2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ConnectMysql.init();
            PlayerInsert pi = new PlayerInsert();
            pi.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the frame.
     */
    public PlayerInsert() {
        setTitle("球员注册");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(65,25,65));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel Label = new JLabel("球员姓名");
        Label.setBounds(10, 10, 63, 15);
        Label.setForeground(Color.white);
        contentPane.add(Label);

        textField = new JTextField();
        textField.setBounds(80, 7, 300, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel label2 = new JLabel("球员国籍");
        label2.setBounds(10, 38, 63, 15);
        label2.setForeground(Color.white);
        contentPane.add(label2);

        comBox1 = new JComboBox<String>();
        comBox1.setBounds(80, 35, 300, 21);
        contentPane.add(comBox1);
        comBox1.addItem("---NULL---");
        Vector<String> list = ConnectMysql.Get_Country_list();
        for(int i = 0; i < list.size(); i++)
            comBox1.addItem(list.elementAt(i));



        JLabel label3 = new JLabel("所属俱乐部");
        label3.setBounds(10, 66, 63, 15);
        label3.setForeground(Color.white);
        contentPane.add(label3);

        comBox2 = new JComboBox<String>();
        comBox2.setBounds(80, 63, 300, 21);
        contentPane.add(comBox2);
        comBox2.addItem("---NULL---");
        Vector<String> clist = ConnectMysql.Get_team_list();
        for(int i = 0; i < clist.size(); i++)
            comBox2.addItem(clist.elementAt(i));

        JLabel label4 = new JLabel("球衣号码");
        label4.setBounds(10, 94, 63, 15);
        label4.setForeground(Color.white);
        contentPane.add(label4);

        textField_3 = new JTextField();
        textField_3.setBounds(80, 91, 300, 21);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        JLabel label5 = new JLabel("场上位置");
        label5.setBounds(10, 122, 63, 15);
        label5.setForeground(Color.white);
        contentPane.add(label5);

        comBox = new JComboBox<String>();
        comBox.setBounds(80, 119, 100, 21);
        contentPane.add(comBox);
        comBox.addItem("---NULL---");
        comBox.addItem("S");
        comBox.addItem("M");
        comBox.addItem("D");
        comBox.addItem("G");


        JButton button = new JButton("注册");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                String country = (String) comBox1.getSelectedItem();
                String club = (String) comBox2.getSelectedItem();
                String num = textField_3.getText();
                String pos = (String) comBox.getSelectedItem();
                System.out.println(pos);
//						System.out.println(data.toString());
                if(ConnectMysql.insert_player(name, country, club, num, pos)==true){
                    JOptionPane.showMessageDialog(getParent(), "注册成功", "注册结果", JOptionPane.INFORMATION_MESSAGE);
                    //PlayerInsert.this.dispose();
                    textField.setText("");
                    textField_3.setText("");
                }else{
                    JOptionPane.showMessageDialog(getParent(), "注册失败", "注册结果", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button.setBounds(120, 150, 63, 21);
        contentPane.add(button);

        JButton button_1 = new JButton("退出");

        button_1.setBounds(200, 150, 63, 23);
        contentPane.add(button_1);
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}