import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultInsert extends JFrame {
    private JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comBox;

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConnectMysql.init();
                    ResultInsert ri = new ResultInsert();
                    ri.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ResultInsert(){
        setTitle("赛况录入");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(65,25,65));
        contentPane.setLayout(null);

        JLabel Label = new JLabel("输入比赛编号");
        Label.setBounds(10, 10, 100, 15);
        Label.setForeground(Color.white);
        contentPane.add(Label);

        textField1 = new JTextField();
        textField1.setBounds(120, 7, 300, 21);
        contentPane.add(textField1);
        textField1.setColumns(10);

        JLabel label2 = new JLabel("输入进球时间");
        label2.setBounds(10, 38, 100, 15);
        label2.setForeground(Color.white);
        contentPane.add(label2);

        textField2 = new JTextField();
        textField2.setBounds(120, 35, 300, 21);
        contentPane.add(textField2);
        textField2.setColumns(10);

        JLabel label3 = new JLabel("选择球员");
        label3.setBounds(10, 66, 100, 15);
        label3.setForeground(Color.white);
        contentPane.add(label3);

        comBox = new JComboBox<String>();
        comBox.setBounds(120, 63, 100, 21);
        contentPane.add(comBox);
        comBox.addItem("NULL");

        JButton button = new JButton("获得球员选项");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String gmid = textField1.getText();
                Vector<String> teams = ConnectMysql.Get_Match_Team(gmid);
                String team1 = teams.elementAt(0);
                String team2 = teams.elementAt(1);
                Vector<String> list1 = ConnectMysql.get_player(1,team1);
                Vector<String> list2 = ConnectMysql.get_player(1,team2);
                for(int i = 0; i < list1.size(); i++)
                    comBox.addItem(list1.elementAt(i));
                for(int i = 0; i < list2.size(); i++)
                    comBox.addItem(list2.elementAt(i));
            }
        });
        button.setBounds(280, 63, 120, 21);
        contentPane.add(button);

        JButton button1 = new JButton("录入");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String gmid = textField1.getText();
                String time = textField2.getText();
                String name = (String) comBox.getSelectedItem();
                if(ConnectMysql.insert_goal(gmid,time,name)){
                    JOptionPane.showMessageDialog(getParent(), "录入成功", "录入结果", JOptionPane.INFORMATION_MESSAGE);
                    //ResultInsert.this.dispose();
                    textField1.setText("");
                    textField2.setText("");
                }else{
                    JOptionPane.showMessageDialog(getParent(), "录入失败", "录入结果", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button1.setBounds(170, 120, 63, 23);
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