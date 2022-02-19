import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class Main extends JFrame{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConnectMysql.init();
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private JPanel contentPane;

    public Main() {
        setTitle("足球联赛管理系统 V0.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(65,25,65));
        contentPane.setLayout(new GridLayout(0, 3, 0, 0));
        //final GPL g=new GPL();
        JButton button = new JButton("信息查询");
        button.setFont(new Font("微软雅黑", Font.BOLD ,25));
        button.setBackground(new Color(200,200,205));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InfoSearch is=new InfoSearch();
                is.setVisible(true);
            }
        });

        JButton button_6 = new JButton("球员注册");
        button_6.setFont(new Font("微软雅黑", Font.BOLD ,25));
        button_6.setBackground(new Color(200,200,205));
        button_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayerInsert pi = new PlayerInsert();
                pi.setVisible(true);
            }
        });

        JButton button_8 = new JButton("球员注销");
        button_8.setFont(new Font("微软雅黑", Font.BOLD ,25));
        button_8.setBackground(new Color(200,200,205));
        button_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayerDelete pd = new PlayerDelete();
                pd.setVisible(true);
            }
        });

        JButton button_5 = new JButton("积分榜");
        button_5.setFont(new Font("微软雅黑", Font.BOLD ,25));
        button_5.setBackground(new Color(200,200,205));
        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MakeTable mt = new MakeTable();
                mt.setVisible(true);
            }
        });

        JButton button_3 = new JButton("球员更新");
        button_3.setFont(new Font("微软雅黑", Font.BOLD ,25));
        button_3.setBackground(new Color(200,200,205));
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayerUpdate pu=new PlayerUpdate();
                pu.setVisible(true);
            }
        });

        JButton button_1 = new JButton("射手榜");
        button_1.setFont(new Font("微软雅黑", Font.BOLD ,25));
        button_1.setBackground(new Color(200,200,205));
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MakeList ml = new MakeList();
                ml.setVisible(true);
            }
        });

        contentPane.add(button);
        contentPane.add(button_5);
        contentPane.add(button_1);
        contentPane.add(button_3);
        contentPane.add(button_6);
        contentPane.add(button_8);

        JButton button_9 = new JButton("赛况录入");
        button_9.setFont(new Font("微软雅黑", Font.BOLD ,25));
        button_9.setBackground(new Color(200,200,205));
        contentPane.add(button_9);
        button_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResultInsert ri = new ResultInsert();
                ri.setVisible(true);
            }
        });


        JPanel panel = new JPanel();
        contentPane.add(panel);

        JLabel lblNewLabel = new JLabel("190111026" + "\r\n" + "19级计算机10班郭毅安" + "\r\n" + "2021.10");
        panel.add(lblNewLabel);


        JButton button_10 = new JButton("退出登录");
        button_10.setFont(new Font("微软雅黑", Font.BOLD ,25));
        button_10.setBackground(new Color(200,200,205));
        button_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        contentPane.add(button_10);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
}