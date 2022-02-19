import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by qcl on 2017/11/18.
 * 数据库连接
 */
public class ConnectMysql {
    private static Statement stmt;
    private static Connection con = null;


    public static void init(){
        String driver="com.mysql.cj.jdbc.Driver";
        //这里我的数据库是qcl
        String url="jdbc:mysql://localhost:3306/management_of_league";
        String user="root";
        String password="123456";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            stmt = con.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
        }
    }

    public static  Vector<String> get_game(){
        String sql0;
        Vector<String> gamelist = new Vector<String>();
        sql0 = "select game_id from game";
        try {
            ResultSet result0 = stmt.executeQuery(sql0);
            while (result0.next()) {
                String gm = result0.getString("game_id");
                gamelist.add(gm);
            }
            return gamelist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector<String> get_player(int code, String club){
        String sql0;
        Vector<String> gamelist = new Vector<String>();
        try {
            if (code == 1){
                sql0 = "select player_name from player";
                if(club != "all")
                    sql0 = sql0 + ",club where club.club_id = player.club_id and club_name =\"" + club + "\"";
                ResultSet result0 = stmt.executeQuery(sql0);
                while (result0.next()) {
                    String gm = result0.getString("player_name");
                    gamelist.add(gm);
                }
            }
            else{
                sql0 = "select player_id from player";
                if(club != "all")
                    sql0 = sql0 + " where club_id =\"" + club + "\"";
                ResultSet result0 = stmt.executeQuery(sql0);
                while (result0.next()) {
                    String gm = result0.getString("player_id");
                    gamelist.add(gm);
                }
            }
            return gamelist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean insert_player(String name, String country, String club, String num, String pos){
        String sql0, sql1, sql;
        String club_id = null, country_id = null;
        sql0 = "select club_id from club where club_name=\"";
        sql0 = sql0 + club + "\"";
        System.out.println(sql0);
        sql1 = "select country_id from country where country_name=\"";
        sql1 = sql1 + country + "\"";
        System.out.println(sql1);
        try{
            ResultSet resultSet0 = stmt.executeQuery(sql0);
            System.out.println("get "+ club);
            while (resultSet0.next()) {
                club_id = resultSet0.getString("club_id");
            }
            ResultSet resultSet1 = stmt.executeQuery(sql1);
            System.out.println("get "+ country);
            while (resultSet1.next()) {
                country_id = resultSet1.getString("country_id");
            }
            sql = "insert into player values(\"" + club_id + num + "\",\"" + country_id + "\",\"" + club_id + "\",\"" + name;
            sql = sql + "\",\"" + pos + "\",\"" + num + "\",\"0\")";
            System.out.println(sql);
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update_player(String pno, String name, String country, String club, String num, String pos){
        String sql0, sql1, sql;
        String club_id = null, country_id = null;
        sql0 = "select club_id from club where club_name=\"";
        sql0 = sql0 + club + "\"";
        System.out.println(sql0);
        sql1 = "select country_id from country where country_name=\"";
        sql1 = sql1 + country + "\"";
        System.out.println(sql1);
        try{
            ResultSet resultSet0 = stmt.executeQuery(sql0);
            System.out.println("get "+ club);
            while (resultSet0.next()) {
                club_id = resultSet0.getString("club_id");
            }
            ResultSet resultSet1 = stmt.executeQuery(sql1);
            System.out.println("get "+ country);
            while (resultSet1.next()) {
                country_id = resultSet1.getString("country_id");
            }
            resultSet0.close();
            resultSet1.close();
            sql = "update player set country_id=\"" + country_id + "\",club_id=\"" + club_id;
            sql = sql + "\",player_name=\"" + name + "\",position=\"" + pos + "\",num_of_shirt=\"" + num + "\",num_of_goal=\"0\" ";
            sql = sql + "where player_id=\"" + pno + "\"";
            System.out.println(sql);
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update_score(){
        String hostgoals = null, guestgoals = null;
        String sql0, sql1, sql2, sql;
        String club_id = null, country_id = null;
        Vector<String> gamelist = new Vector<String>();
        sql0 = "select game_id from game";
        try{
            gamelist = get_game();
            for(int i = 0; i < gamelist.size(); i++){
                String gmid = gamelist.elementAt(i);
                hostgoals = null; guestgoals = null;
                sql1 = "select count(goal_id) from game,goal,player,club where game.game_id = \"" + gmid+"\"" +
                        " and player.club_id = club.club_id and hostname = club.club_name" +
                        " and goal.player_id = player.player_id and game.game_id = goal.game_id";
                System.out.println(sql1);
                ResultSet resultSet0 = stmt.executeQuery(sql1);
                while (resultSet0.next()) {
                    hostgoals = resultSet0.getString("count(goal_id)");
                    System.out.println(hostgoals);
                }
                sql2 = "select count(goal_id) from game,goal,player,club where game.game_id = \"" + gmid+"\"" +
                        " and player.club_id = club.club_id and guestname = club.club_name" +
                        " and goal.player_id = player.player_id and game.game_id = goal.game_id";
                System.out.println(sql2);
                ResultSet resultSet1 = stmt.executeQuery(sql2);
                while (resultSet1.next()) {
                    guestgoals = resultSet1.getString("count(goal_id)");
                    System.out.println(guestgoals);
                }
                sql = "update game set host_score = \"" + hostgoals + "\", guest_score = \"" + guestgoals;
                sql = sql + "\" where game_id = \"" + gmid + "\"";
                System.out.println(sql);
                stmt.execute(sql);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete_player(String col, String data){
        String sql;
        try{
            sql = "delete from player where " + col + "=\"" + data + "\"";
            System.out.println(sql);
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static PlayerDetail get_detail(String pid){
        String sql = "select player_name,country_name,club_name,num_of_shirt,position from player,country,club " +
                "where player.country_id = country.country_id and player.club_id = club.club_id and " +
                "player_id = \"" + pid + "\"";
        System.out.println(sql);
        PlayerDetail detail = new PlayerDetail();
        try{
            ResultSet result = stmt.executeQuery(sql);
            while(result.next()){
                detail.name= result.getString("player_name");
                detail.country = result.getString("country_name");
                detail.club = result.getString("club_name");
                detail.num= result.getString("num_of_shirt");
                detail.pos = result.getString("position");
            }
            return detail;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector<String> Get_team_list(){
        Vector<String> list = new Vector<String>();
        String sql = "select club_name from club";
        try{
            ResultSet result = stmt.executeQuery(sql);
            while(result.next()){
                String s = result.getString("club_name");
                System.out.println(s);
                list.add(s);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector<ScoreInfo> Get_score_list(String gmid, String team){
        Vector<ScoreInfo> list = new Vector<ScoreInfo>();
        String sql = "select score_min,player_name from goal,game,player,club where goal.game_id = \""+ gmid + "\"" +
                " and game.game_id = goal.game_id and player.player_id = goal.player_id and " +
                "player.club_id = club.club_id and club.club_name = \"" + team + "\"";
        System.out.println(sql);
        try{
            ResultSet result = stmt.executeQuery(sql);
            while(result.next()){
                ScoreInfo sif = new ScoreInfo();
                sif.time = result.getString("score_min");
                sif.name = result.getString("player_name");
                list.add(sif);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector<String> Get_Country_list(){
        Vector<String> list = new Vector<String>();
        String sql = "select country_name from country";
        try{
            ResultSet result = stmt.executeQuery(sql);
            while(result.next()){
                String s = result.getString("country_name");
                System.out.println(s);
                list.add(s);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector<Vector> Get_team(String club){
        String sql0, sql;
        String club_id = null;
        sql0 = "select club_id from club where club_name=\""+ club + "\"";
        System.out.println(sql0);
        try{
            ResultSet resultSet0 = stmt.executeQuery(sql0);
            System.out.println("get "+ club);
            while (resultSet0.next()) {
                club_id = resultSet0.getString("club_id");
            }
            sql = "select player_name, num_of_shirt, position, country_name" +
                    " from player, country where player.country_id = country.country_id " +
                    "and club_id = \"" + club_id + "\"";
            System.out.println(sql);
            ResultSet result = stmt.executeQuery(sql);
            Vector<Vector> ans = new Vector<>();
            while (result.next()) {
                Vector s = new Vector<String>();
                s.add(false);
                for (int i = 1; i <= 4; i++) {
                    s.add(result.getString(i));
					System.out.println(result.getString(i));
                    if(s.lastElement()==null)
                        s.set(i, "NULL");
                }
                ans.add(s);
            }
            return ans;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean insert_goal(String gmno, String time, String scorer){
        String sql0, sql;
        String scorer_id = null;
        sql0 = "select player_id from player where player_name=\"";
        sql0 = sql0 + scorer + "\"";
        System.out.println(sql0);
        try{
            ResultSet resultSet0 = stmt.executeQuery(sql0);
            System.out.println("get "+ scorer);
            while (resultSet0.next()) {
                scorer_id = resultSet0.getString("player_id");
            }
            resultSet0.close();
            sql = "insert into goal values(\"" + gmno + time + "\",\"" + scorer_id;
            sql = sql + "\",\"" + gmno + "\",\"" + time + "\")";
            System.out.println(sql);
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static ClubInfo Get_Point_Info(String club){
        String sql0, sql1, sql2, sql3;
        ClubInfo cinfo = new ClubInfo();
        int host = 0, guest = 0;
        //win = 0, lose = 0, draw = 0;
        //get = 0, got = 0;
        try{
            sql0 = "select game_id, host_score, guest_score from game where hostname = \"" + club + "\"";
            ResultSet result = stmt.executeQuery(sql0);
            while (result.next()) {
                String gm = result.getString("game_id");
                host = result.getInt("host_score");
                guest = result.getInt("guest_score");
                cinfo.get_in += host;
                cinfo.get_lost += guest;
                if (host > guest) cinfo.win++;
                else if (host == guest) cinfo.draw++;
                else cinfo.lose++;
            }
            sql1 = "select game_id, host_score, guest_score from game where guestname = \"" + club + "\"";
            ResultSet result0 = stmt.executeQuery(sql1);
            while (result0.next()) {
                String gm = result0.getString("game_id");
                host = result0.getInt("host_score");
                guest = result0.getInt("guest_score");
                cinfo.get_in += guest;
                cinfo.get_lost += host;
                if (host < guest) cinfo.win++;
                else if (host == guest) cinfo.draw++;
                else cinfo.lose++;
            }
            cinfo.over_goal = cinfo.get_in - cinfo.get_lost;
            cinfo.point = 3 * cinfo.win + cinfo.draw;
            return cinfo;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector<String> Get_Match_Team(String gmid){
        String sql;
        try{
            sql = "select hostname,guestname from game where game_id = \"" + gmid + "\"";
            ResultSet result = stmt.executeQuery(sql);
            Vector<String> ans = new Vector<String>();
            while (result.next()) {
                ans.add(result.getString(1));
                ans.add(result.getString(2));
            }
            return ans;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector<ScorePlayer> Get_Scorer_Table(){
        String sql;
        Vector<ScorePlayer> ans = new Vector<ScorePlayer>();
        try{
            sql = "select player_name,num_of_goal from player where num_of_goal>0 order by num_of_goal desc";
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                ScorePlayer s = new ScorePlayer();
                s.num = result.getInt(2);
                s.name = result.getString(1);
                ans.add(s);
            }
            return ans;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector<Vector> Get_Recent_Game(int code, String index){
        String sql;
        try{
            Vector<Vector> ans = new Vector<>();
            if(code == 1){
                sql = "select round,day_of_match,time_of_match,hostname,guestname " +
                        "from game where day_of_match=\"" + index + "\"";
                ResultSet result = stmt.executeQuery(sql);
                while (result.next()) {
                    Vector s = new Vector<String>();
                    s.add(false);
                    for (int i = 1; i <= 5; i++) {
                        s.add(result.getString(i));
                        if (s.lastElement() == null)
                            s.set(i, "NULL");
                    }
                    ans.add(s);
                }
            }
            else{
                sql = "select round,day_of_match,time_of_match,hostname,guestname " +
                        "from game where round=\"" + index + "\"";
                ResultSet result = stmt.executeQuery(sql);
                while (result.next()) {
                    Vector s = new Vector<String>();
                    s.add(false);
                    for (int i = 1; i <= 5; i++) {
                        s.add(result.getString(i));
                        if (s.lastElement() == null)
                            s.set(i, "NULL");
                    }
                    ans.add(s);
                }
            }
            System.out.println(ans);
            return ans;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        init();
    }
}