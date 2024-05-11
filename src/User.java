import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.Connection;


public class User {
    private Connection connection;

    private Scanner scanner;

    public User(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void register(){
        scanner.nextLine();
        System.out.println("Full Name: ");
        String full_name = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        if(user_exist(email)){
            System.out.println("User Already Exists for this Email Address.");
            return;
        }
        String register_query = "INSERT INTO User(full_name, email, password) VALUES(?, ?, ?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(register_query);
            preparedStatement.setString(1,full_name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);
            int affectedRows = preparedStatement.executeUpdate(); //when we inset data executeUpdate
            if(affectedRows>0){
                System.out.println("Register Successfully");
            }else System.out.println("Registration Failed");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public String login(){
        scanner.nextLine();
        System.out.println("Email: ");
        String  email = scanner.nextLine();
        System.out.println("Password: ");
        String  password = scanner.nextLine();
        String query = "SELECT * FROM User WHERE email = ? AND password = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return email;
            }else
                return null;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean user_exist(String email){
        String query = "SELECT * FROM User WHERE email = ? ";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else
                return
                    false;

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
