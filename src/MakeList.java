import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.util.Map;
import java.util.Vector;

public class MakeList extends JFrame{
    private JPanel contentPane;
    private String []names = {"名次","球员","进球数"};
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConnectMysql.init();
                    MakeList frame = new MakeList();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MakeList() {
        setTitle("射手榜");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 350, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(65,25,65));
        contentPane.setLayout(null);

        JLabel Label = new JLabel("XX超级联赛射手榜");
        Label.setBounds(120, 10, 250, 25);
        Label.setForeground(Color.white);
        contentPane.add(Label);

        Vector<ScorePlayer> numlist = ConnectMysql.Get_Scorer_Table();
        String[][] out = new String[20][5];
        for(int i = 0; i < numlist.size(); i++){
            out[i][0] = "" + (i + 1);
            out[i][1] = numlist.elementAt(i).name;
            out[i][2] = "" + numlist.elementAt(i).num;
        }
        JTable table = new JTable(out,names);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 40, 300, 300);
        contentPane.add(scrollPane);
    }

}
