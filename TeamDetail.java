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

public class TeamDetail extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JComboBox comBox;
    private String []names = new String[]{"姓名","号码","场上位置","国籍"};
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConnectMysql.init();
                    TeamDetail td = new TeamDetail();
                    td.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TeamDetail() {
        setTitle("球队信息");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(65,25,65));
        contentPane.setLayout(null);

        JLabel Label = new JLabel("选择球队");
        Label.setBounds(10, 10, 60, 25);
        Label.setForeground(Color.white);
        contentPane.add(Label);

        comBox = new JComboBox<String>();
        comBox.setBounds(100, 13, 90, 21);
        contentPane.add(comBox);
        comBox.addItem("--NULL--");



        JButton button1 = new JButton("获取球队列表");
        button1.setBounds(10,50,120,30);
        contentPane.add(button1);

        JButton button2 = new JButton("查询");
        button2.setBounds(140,50,70,30);
        contentPane.add(button2);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Vector<String> clublist = ConnectMysql.Get_team_list();
                for(int i = 0; i < clublist.size(); i++)
                    comBox.addItem(clublist.elementAt(i));
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String club = (String) comBox.getSelectedItem();
                System.out.println(club);
                Vector<Vector> in = ConnectMysql.Get_team(club);
                String[][] out = new String[30][5];
                for(int i = 0; i < in.size(); i++){
                    Vector v = in.elementAt(i);
                    System.out.println(v);
                    for(int j = 1; j < v.size(); j++){
                        out[i][j-1] = (String) v.elementAt(j);
                    }
                }
                JTable table = new JTable(out, names);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(220, 10, 200, 250);
                contentPane.add(scrollPane);
            }
        });
    }
}