/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Md. Rakibul Islam
 */
public class CreateAccount {
  Connection dbconnection;
  public Scanner scanner = new Scanner(System.in);
  String connectionUrl = "jdbc:mysql://127.0.0.1/bankcustomer";
  public void createAccount() {
    try {

      System.out.println("Enter name : ");
      String name = scanner.nextLine();
      System.out.println("Enter phone : ");
      String phone = scanner.next();
      System.out.println("Enter NID : ");
      String nid = scanner.next();
      System.out.println("Create new password");
      String password = scanner.next(); {
        try {
          dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

          String insertQuery = "insert into customer  (name,phone,nid,pass)" + " values (?,?,?,?)";
          PreparedStatement preparedStatement = dbconnection.prepareStatement(insertQuery);

          preparedStatement.setString(1, name);
          preparedStatement.setString(2, phone);
          preparedStatement.setString(3, nid);
          preparedStatement.setString(4, password);

          int result = preparedStatement.executeUpdate();
          preparedStatement.close();
          if (result == 1) {
            System.out.println("Registration Complete");
            System.out.println("Go to login Page");

          }
        } catch (SQLException throwables) {
          System.out.println("Can not create new customer database Error " + throwables);
        }
      }

    } catch (InputMismatchException e) {
      System.out.println("Sorry input mismatch !");
    }
  }

}
