package bankmanagement;

import java.io.Console;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main < control > {

  public static void main(String[] args) throws ClassNotFoundException,
  SQLException {

    Scanner scanner = new Scanner(System.in);

    System.out.println("1 - Login");
    System.out.println("2 - Registration");

    System.out.println("------------------------ ");
    System.out.println("Your select  : ");

    int option = scanner.nextInt();
    switch (option) {
    case 1:
      LogIn obj = new LogIn();
      obj.log();

      break;
    case 2:
      CreateAccount ob = new CreateAccount();
      ob.createAccount();
      break;

    default:
      System.out.println("Sorry your select is not listed ");
    }
  }

}
