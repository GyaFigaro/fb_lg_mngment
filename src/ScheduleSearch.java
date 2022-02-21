import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScheduleSearch extends JFrame{
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JComboBox comBox1, comBox2, comBox3, comBox4;
    private String []names = new String[]{"轮次","日期","时间","主队","客队"};

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConnectMysql.init();
                    ScheduleSearch ss = new ScheduleSearch();
                    ss.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ScheduleSearch(){
        setTitle("赛程查询");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(65,25,65));
        contentPane.setLayout(null);

        JLabel Label = new JLabel("按日期查询:");
        Label.setBounds(10, 10, 70, 25);
        Label.setForeground(Color.white);
        contentPane.add(Label);

        comBox1 = new JComboBox<String>();
        comBox1.setBounds(90, 13, 60, 21);
        contentPane.add(comBox1);
        comBox1.addItem("0000");
        for(int i = 2021; i <= 2023; i++){
            String s = "" + i;
            comBox1.addItem(s);
        }

        comBox2 = new JComboBox<String>();
        comBox2.setBounds(170, 13, 60, 21);
        contentPane.add(comBox2);
        comBox2.addItem("00");
        for(int i = 1; i <= 12; i++){
            String s;
            if (i < 10)
                s = "0" + i;
            else s = "" + i;
            comBox2.addItem(s);
        }

        comBox3 = new JComboBox<String>();
        comBox3.setBounds(250, 13, 60, 21);
        contentPane.add(comBox3);
        comBox3.addItem("00");
        for(int i = 1; i <= 31; i++){
            String s;
            if (i < 10)
                s = "0" + i;
            else s = "" + i;
            comBox3.addItem(s);
        }

        JButton button1 = new JButton("查询");
        button1.setBounds(330,13,80,21);
        contentPane.add(button1);

        JLabel Label1 = new JLabel("按轮次查询:");
        Label1.setBounds(10, 50, 70, 25);
        Label1.setForeground(Color.white);
        contentPane.add(Label1);

        comBox4 = new JComboBox<String>();
        comBox4.setBounds(90, 53, 100, 21);
        contentPane.add(comBox4);
        comBox4.addItem("0000");
        for(int i = 1; i <= 6; i++){
            String s = "" + i;
            comBox4.addItem(s);
        }

        JButton button2 = new JButton("查询");
        button2.setBounds(230,53,80,21);
        contentPane.add(button2);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String y =  (String) comBox1.getSelectedItem();
                String m =  (String) comBox2.getSelectedItem();
                String d =  (String) comBox3.getSelectedItem();
                String date = y + "-" + m + "-" + d;
                System.out.println(date);
                Vector<Vector> list = ConnectMysql.Get_Recent_Game(1, date);
                String[][] out = new String[10][5];
                for (int i = 0; i < list.size(); i++){
                    Vector<String> v = list.elementAt(i);
                    for (int j = 0; j < v.size() - 1; j++){
                        out[i][j] = v.elementAt(j + 1);
                    }
                }
                JTable table = new JTable(out,names);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(20, 100, 380, 150);
                contentPane.add(scrollPane);
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String r =  (String) comBox4.getSelectedItem();
                Vector<Vector> list = ConnectMysql.Get_Recent_Game(2, r);
                String[][] out = new String[10][5];
                for (int i = 0; i < list.size(); i++){
                    Vector<String> v = list.elementAt(i);
                    for (int j = 0; j < v.size() - 1; j++){
                        out[i][j] = v.elementAt(j + 1);
                    }
                }
                JTable table = new JTable(out,names);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(20, 100, 380, 150);
                contentPane.add(scrollPane);
            }
        });
    }
}