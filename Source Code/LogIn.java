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
import java.util.Scanner;

/**
 *
 * @author Md. Rakibul Islam
 */
public class LogIn {
  Connection dbconnection;
  public Scanner scanner = new Scanner(System.in);
  String connectionUrl = "jdbc:mysql://127.0.0.1/bankcustomer";

  void log() throws ClassNotFoundException, SQLException {
    try {

      System.out.println("Enter Phone : ");
      String phone = scanner.next();
      System.out.println("Enter Password : ");
      String password = scanner.next();
      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String checkQuery = "select * from customer where phone = ? AND pass= ?  ";

        try (PreparedStatement preparedStatement = dbconnection.prepareStatement(checkQuery)) {

          preparedStatement.setString(1, phone);
          preparedStatement.setString(2, password);

          ResultSet result = preparedStatement.executeQuery();

          if (result.next()) {
            String name = result.getString("name");
            int balance = result.getInt("balance");
            Banking bank = new Banking(name, phone, balance);

          } else {
            System.out.println("Password incorrect ");

          }
        }
      } catch (SQLException throwables) {
        System.out.println("error from database   ");
      }
    } catch (Exception e) {
      System.out.println("Sorry Input error ");
    }
  }

}
