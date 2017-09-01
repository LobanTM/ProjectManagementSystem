import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/module01";
        String user = "root";
        String password = "root";
        Statement st = null;
        ResultSet rs = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM projects");
            System.out.println("OK");

            while (rs.next()){
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("нет драйвера");
        }
    }
}
