package toby.springbook.user.dao;//package springbook.user.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DUserDao extends UserDao {
//    public Connection getConnection() throws ClassNotFoundException, SQLException{
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection c = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/springbook?useSSL=false", "root", "1234");
//        return c;
//    }
//}
