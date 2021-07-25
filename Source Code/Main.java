package bankmanagement;

import java.io.Console;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main<control> {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        // Create  Your Bank
       Scanner scanner = new Scanner(System.in);
       /* Connection dbconnection;
      
        String url = "jdbc:mysql;//127.0.0.1/datanaseName";
        Class.forName("com.mysql.jdbc.Driver");
        Connction connction = (Connction) DriverManager.getConnection(url,"root","");
        Statement statement = connction.createStatement();
        ResultSet result;
        String query = "SELECT * FROM Customer WHERE id=2";
        result = statement.executeQuery(query);
        result.next();
*/
  
       
        System.out.println("1 - Login");
        System.out.println("2 - Registration");

        System.out.println("------------------------ ");
        System.out.println("Your select  : ");
        
        int  option = scanner.nextInt();
        switch (option)
        {
            case 1 :
                 LogIn obj = new LogIn();
                 obj.log();
                   
                break;
            case 2 :
                 CreateAccount ob = new CreateAccount();
                 ob.createAccount();
                break;
          
            default:
                System.out.println("Sorry your select is not listed ");
        }
    }
        
        
        
        
    
        
       /* Bank bd = new Bank("Takawala") ;
        // Show Welcoming screen
        ConsoleController.showMenu(bd.getBankName());
        // Get what customer need
        int option =  ConsoleController.getOption();
         // Run Bank all operations depend on what customer Need
        bd.operations(option);
     
*/
}
