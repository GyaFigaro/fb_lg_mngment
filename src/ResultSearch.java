import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

@SuppressWarnings("serial")
public class ResultSearch extends JFrame {

    private JPanel contentPane;
    private JComboBox comBox1, comBox2, comBox3, comBox4;
    private String []names = new String[]{"进球时间","进球球员"};

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConnectMysql.init();
                    ResultSearch frame = new ResultSearch();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ResultSearch() {
        setTitle("赛况查询");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(65,25,65));
        contentPane.setLayout(null);

        JLabel Label1 = new JLabel("轮次:");
        Label1.setBounds(10, 10, 40, 25);
        Label1.setForeground(Color.white);
        contentPane.add(Label1);

        comBox1 = new JComboBox<String>();
        comBox1.setBounds(60, 13, 50, 21);
        contentPane.add(comBox1);
        comBox1.addItem("0000");
        for(int i = 1; i <= 9; i++){
            String s = "" + i;
            comBox1.addItem(s);
        }

        JLabel Label2 = new JLabel("场次:");
        Label2.setBounds(140, 10, 50, 25);
        Label2.setForeground(Color.white);
        contentPane.add(Label2);

        comBox2 = new JComboBox<String>();
        comBox2.setBounds(200, 13, 50, 21);
        contentPane.add(comBox2);
        comBox2.addItem("00");
        for(int i = 1; i <= 9; i++){
            String s = "0" + i;
            comBox2.addItem(s);
        }

        JButton button1 = new JButton("查询");
        button1.setBounds(300,13,80,21);
        contentPane.add(button1);

        JLabel Label = new JLabel("XX超级联赛");
        Label.setFont(new Font("微软雅黑", Font.BOLD, 20));
        Label.setBounds(160, 40, 220, 40);
        Label.setForeground(Color.white);
        contentPane.add(Label);

        JLabel Label3 = new JLabel();
        Label3.setFont(new Font("微软雅黑", Font.BOLD, 18));
        Label3.setBounds(185, 70, 100, 50);
        Label3.setForeground(Color.white);
        contentPane.add(Label3);

        JLabel Label4 = new JLabel();
        Label4.setFont(new Font("微软雅黑", Font.BOLD, 18));
        Label4.setBounds(50, 100, 120, 80);
        Label4.setForeground(Color.white);
        contentPane.add(Label4);

        JLabel Label5 = new JLabel();
        Label5.setFont(new Font("微软雅黑", Font.BOLD, 18));
        Label5.setBounds(190, 100, 120, 80);
        Label5.setForeground(Color.white);
        contentPane.add(Label5);

        JLabel Label6 = new JLabel();
        Label6.setFont(new Font("微软雅黑", Font.BOLD, 18));
        Label6.setBounds(290, 100, 120, 80);
        Label6.setForeground(Color.white);
        contentPane.add(Label6);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String r =  (String) comBox1.getSelectedItem();
                String g =  (String) comBox2.getSelectedItem();
                Label3.setText("第" + r + "轮");
                String gmid = r + g ;
                Vector<String> teams = ConnectMysql.Get_Match_Team(gmid);
                String host = teams.elementAt(0);
                String guest = teams.elementAt(1);
                Label4.setText(host);
                Label6.setText(guest);
                Vector<ScoreInfo> hostlist = ConnectMysql.Get_score_list(gmid,host);
                Vector<ScoreInfo> guestlist = ConnectMysql.Get_score_list(gmid,guest);
                Label5.setText("" + hostlist.size() + " : " + guestlist.size());
                String[][] hostscore = new String[10][5];
                String[][] guestscore = new String[10][5];
                for (int i = 0; i < hostlist.size(); i++){
                    ScoreInfo sif = hostlist.elementAt(i);
                    hostscore[i][0] = sif.time;
                    hostscore[i][1] = sif.name;
                }
                for (int i = 0; i < guestlist.size(); i++){
                    ScoreInfo sif = guestlist.elementAt(i);
                    guestscore[i][0] = sif.time;
                    guestscore[i][1] = sif.name;
                }

                System.out.println(host);
                System.out.println(guest);
                JTable table1 = new JTable(hostscore,names);
                JScrollPane scrollPane1 = new JScrollPane(table1);
                scrollPane1.setBounds(20, 200, 150, 150);
                contentPane.add(scrollPane1);

                JTable table2 = new JTable(guestscore,names);
                JScrollPane scrollPane2 = new JScrollPane(table2);
                scrollPane2.setBounds(250, 200, 150, 150);
                contentPane.add(scrollPane2);
            }
        });

    }
}