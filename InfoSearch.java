import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;

public class InfoSearch extends JFrame{
    private JPanel contentPane;

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InfoSearch frame = new InfoSearch();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public InfoSearch(){
        setTitle("信息查询");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(65,25,65));
        contentPane.setLayout(null);

        JLabel Label = new JLabel("请选择查询内容");
        Label.setFont(new Font("微软雅黑", Font.BOLD, 18));
        Label.setForeground(Color.white);
        Label.setBounds(150, 50, 200, 60);
        contentPane.add(Label);

        JButton button = new JButton("球队查询");
        button.setBounds(30, 160, 90, 50);
        button.setFont(new Font("微软雅黑", Font.BOLD ,14));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TeamDetail td = new TeamDetail();
                td.setVisible(true);
            }
        });
        contentPane.add(button);

        JButton button_1 = new JButton("赛况查询");
        button_1.setBounds(170, 160, 90, 50);
        button_1.setFont(new Font("微软雅黑", Font.BOLD ,14));
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResultSearch rs = new ResultSearch();
                rs.setVisible(true);
            }
        });
        contentPane.add(button_1);

        JButton button_2 = new JButton("赛程查询");
        button_2.setBounds(310, 160, 90, 50);
        button_2.setFont(new Font("微软雅黑", Font.BOLD ,14));
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScheduleSearch ss=new ScheduleSearch();
                ss.setVisible(true);
            }
        });
        contentPane.add(button_2);
    }
}