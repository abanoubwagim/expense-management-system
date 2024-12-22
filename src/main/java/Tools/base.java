package Tools;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class base {



    public static boolean addUser(String name , String email ,String password){

        if (!userCheck(email)) {
            String query = "INSERT INTO users (name,email,password)values (?,?,?)";
            try (
                    Connection connection = DataBase.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ){
                if (connection == null){
                    throw new SQLException("Failed to connect to the database.");
                }

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();

                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    public static boolean userCheck(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try(
                Connection connection = DataBase.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){

            preparedStatement.setString(1,email);
            ResultSet rs =  preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static String getUser(String email){
        String query = "SELECT *FROM users WHERE email = ?";
        try(
                Connection connection = DataBase.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ){
            statement.setString(1,email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return rs.getString("name");
            }
        }catch (SQLException e ){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return null;
    }
    public static int getId(String email ){
        String query = "SELECT id FROM users WHERE email = ?";
        try(
                Connection connection = DataBase.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ){
            statement.setString(1,email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return rs.getInt("id");
            }
        }catch (SQLException e ){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return 0;
    }
}
