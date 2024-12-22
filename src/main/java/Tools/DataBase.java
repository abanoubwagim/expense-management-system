package Tools;

import java.sql.*;

public class DataBase {


    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "JDBC:mysql://localhost/expense_management_system";
    private static final String userName = "root";
    private static final String password = "";
    private static Connection connection;




    public static Connection getConnection(){
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(driverName);
                connection = DriverManager.getConnection(url, userName, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }










}
