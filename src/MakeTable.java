import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.util.Vector;

public class MakeTable extends JFrame{
    private JPanel contentPane;
    private String []names = {"名次","球队","胜","平","负","进球","丢球","净胜球","积分"};
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConnectMysql.init();
                    MakeTable frame = new MakeTable();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MakeTable() {
        setTitle("积分榜");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(65,25,65));
        contentPane.setLayout(null);

        JLabel Label = new JLabel("XX超级联赛积分榜");
        Label.setBounds(240, 10, 220, 25);
        Label.setForeground(Color.white);
        contentPane.add(Label);

        Vector<ClubInfo> pointlist = new Vector<ClubInfo>();
        Vector<String> clublist = ConnectMysql.Get_team_list();
        int []mark = new int[25];
        int[][] out = new int[25][10];
        String[][] in = new String[25][10];
        int temp;
        ConnectMysql.update_score();
        for(int i = 0; i < clublist.size(); i++){
            ClubInfo cif = ConnectMysql.Get_Point_Info(clublist.elementAt(i));
            pointlist.add(cif);
            mark[i] = i;
        }
        for(int i = 0; i < pointlist.size(); i++){
            out[i][1] = pointlist.elementAt(i).win;
            out[i][2] = pointlist.elementAt(i).draw;
            out[i][3] = pointlist.elementAt(i).lose;
            out[i][4] = pointlist.elementAt(i).get_in;
            out[i][5] = pointlist.elementAt(i).get_lost;
            out[i][6] = pointlist.elementAt(i).over_goal;
            out[i][7] = pointlist.elementAt(i).point;

        }
        for (int i = 0; i < pointlist.size() - 1; i++){
            for (int j = 0; j < pointlist.size() - i - 1; j++){
                if(out[j][7] < out[j+1][7]){
                    temp = mark[j+1];
                    mark[j+1] = mark[j];
                    mark[j] = temp;
                }
                else if(out[j][7] == out[j+1][7]){
                    if(out[j][6] < out[j+1][6]){
                        temp = mark[j+1];
                        mark[j+1] = mark[j];
                        mark[j] = temp;
                    }
                    else continue;
                }
                else continue;
            }
        }

        for(int i = 0; i < pointlist.size(); i++){
            System.out.println(mark[i]);
            in[i][0] = ""+(i+1);
            in[i][1] = clublist.elementAt(mark[i]);
            for(int j = 1; j <= 7; j++){
                in[i][j+1] = "" + out[mark[i]][j];
            }
        }

        JTable table = new JTable(in,names);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 40, 550, 300);
        contentPane.add(scrollPane);
    }

}
