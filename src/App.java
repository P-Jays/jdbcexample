import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        PlayerDAO dao = new PlayerDAO();
        // Player p = dao.getPlayer(18);
        // System.out.println(p.name);

        Player p2 = new Player();
        p2.name = "Mamamia";
        p2.age = 188;
        dao.connect();
        dao.setPlayer(p2);

    }
}

class PlayerDAO {
    Connection con = null;

    public void connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "2444666668888888");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Player getPlayer(int age) {
        try {
            String query = "SELECT * FROM player where age =" + age;
            Player p = new Player();
            p.age = age;
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            rs.next();
            String name = rs.getString(1);
            p.name = name;
            return p;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public void setPlayer(Player p) {
        
        PreparedStatement ps;
        try {
            String query = "INSERT INTO player VALUES (?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, p.name);
            ps.setInt(2, p.age);
            int count =  ps.executeUpdate();
            System.out.println("success! "+ count+" row affected");
        } catch (Exception e) {
           System.out.println(e);
        }
    }
}

class Player {
    String name;
    int age;
}