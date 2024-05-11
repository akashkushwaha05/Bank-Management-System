import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {

    private static final String url = "jbdc:mysql://localhost:3306/banking_system";

    private static final String username = "root";

    private static final String password = "1998";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Scanner scanner = new Scanner(System.in);
            User user = new User(connection, scanner);
            AccountManager accountManager = new AccountManager(connection, scanner);
            Accounts accounts = new Accounts(connection, scanner);
        }catch()

    }
} 