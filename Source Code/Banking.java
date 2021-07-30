/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankmanagement;
import java.io.Console;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author Md. Rakibul Islam
 */
public class Banking implements BankingServices {

  private String name;
  private String phone;
  private int balance;

  Connection dbconnection;

  public Scanner scanner = new Scanner(System.in);

  String connectionUrl = "jdbc:mysql://127.0.0.1/bankCustomer";

  public Banking(String name, String phone, int balance) {
    this.name = name;
    this.phone = phone;
    this.balance = balance;
    showMenu();
  }

  public void showMenu() {
    System.out.println("");
    System.out.println("");
    System.out.println("------------------------ ");
    System.out.println("------------------------ ");
    System.out.println("Welcome to Takawala");
    System.out.println("Name : " + name + "  ||  Balance " + balance);
    System.out.println("");
    System.out.println("------------------------ ");
    System.out.println("Select from the menu :  ");
    System.out.println("1 - Send Money");
    System.out.println("2 - Add Money");
    System.out.println("3 - Pay Bill.");
    System.out.println("4 - Mobile Recharge ");
    System.out.println("5 - Payment");
    System.out.println("6 - Money Transfer ");
    System.out.println("7 - Cashout");
    System.out.println("8 - Exit");
    System.out.println("------------------------ ");

    System.out.println("------------------------ ");
    System.out.println("Your select  : ");
    int option = scanner.nextInt();
    switch (option) {
    case 1:
      sendMoney();
      break;
    case 2:
      addMoney();
      break;
    case 3:
      payBill();
      break;
    case 4:
      mobileRecharge();
      break;
    case 5:
      payment();
      break;
    case 6:
      moneyTransfer();
      break;
    case 7:
      cashOut();
      break;
    case 8:
      System.exit(0);
      break;
    default:
      System.out.println("Sorry your select is not listed ");
    }
  }

  @Override
  public void addMoney() {
    System.out.println("");
    System.out.println("");
    System.out.println("Add money");
    System.out.println("1 - Bank to Takawala");
    System.out.println("2 - Mastercard to Takawala");
    System.out.println("3 - Visa to Takawala");
    System.out.println("4 - Back to Home");
    System.out.println("------------------------ ");
    System.out.println("Your select  : ");
    int option = scanner.nextInt();
    switch (option) {
    case 1:

      System.out.println("Enter Bank Name : ");
      String bankName = scanner.next();

      System.out.println("Enter Bank Account number : ");
      int bankAccountNumber = scanner.nextInt();

      System.out.println("Enver Verification Code : ");
      int code = scanner.nextInt();

      System.out.println("Enter Amount : ");
      int amount = scanner.nextInt();

      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String updateQuery = "update customer set balance = ? where phone = ? ";
        PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery);

        int newBalance = balance + amount;
        preparedStatement.setInt(1, newBalance);
        preparedStatement.setString(2, phone);

        int updateResult = preparedStatement.executeUpdate();

        if (updateResult == 1) {
          System.out.println("Money added on your account ");
          System.out.println("Your new Balance =  " + newBalance);
          balance = newBalance;
          showMenu();
        }
      } catch (SQLException throwables) {
        System.out.println("error from database from update deposit  ");
      }

      break;
    case 2:

      System.out.println("Enter Card number : ");
      int mCard = scanner.nextInt();

      System.out.println("Enver Pin : ");
      int mPin = scanner.nextInt();

      System.out.println("Enter Amount : ");
      int mAmount = scanner.nextInt();

      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String updateQuery = "update customer set balance = ? where phone = ? ";
        PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery);

        int newBalance = balance + mAmount;
        preparedStatement.setInt(1, newBalance);
        preparedStatement.setString(2, phone);

        int updateResult = preparedStatement.executeUpdate();

        if (updateResult == 1) {
          System.out.println("Money added on your account ");
          System.out.println("Your new Balance =  " + newBalance);
          balance = newBalance;
          showMenu();
        }
      } catch (SQLException throwables) {
        System.out.println("error from database from update deposit  ");
      }

      break;
    case 3:
      System.out.println("Enter Card number : ");
      int vCard = scanner.nextInt();

      System.out.println("Enver Pin : ");
      int vPin = scanner.nextInt();

      System.out.println("Enter Amount : ");
      int vAmount = scanner.nextInt();

      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String updateQuery = "update customer set balance = ? where phone = ? ";
        PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery);

        int newBalance = balance + vAmount;
        preparedStatement.setInt(1, newBalance);
        preparedStatement.setString(2, phone);

        int updateResult = preparedStatement.executeUpdate();

        if (updateResult == 1) {
          System.out.println("Money added on your account ");
          System.out.println("Your new Balance =  " + newBalance);
          balance = newBalance;
          showMenu();
        }
      } catch (SQLException throwables) {
        System.out.println("error from database from update deposit  ");
      }

      break;
    case 4:
      showMenu();
      break;

    default:
      System.out.println("Sorry your select is not listed ");
    }

  }

  @Override
  public void sendMoney() {
    int receiverBalance = 0;

    System.out.println("Enter receiver account number : ");
    String receiverAccount = scanner.next();

    System.out.println("Enter Amount : ");
    int amount = scanner.nextInt();

    if (balance > amount) {

      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String checkQuery = "select * from customer where phone = ?";

        try (PreparedStatement preparedStatement = dbconnection.prepareStatement(checkQuery)) {

          preparedStatement.setString(1, receiverAccount);

          ResultSet result = preparedStatement.executeQuery();

          if (result.next()) {

            receiverBalance = result.getInt("balance");

          }
        }
      } catch (SQLException throwables) {
        System.out.println("error from database   ");
      }

      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String updateQuery = "update customer set balance = ? where phone = ? ";
        PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery);

        int newBalance = receiverBalance + amount;
        preparedStatement.setInt(1, newBalance);
        preparedStatement.setString(2, receiverAccount);

        int updateResult = preparedStatement.executeUpdate();

        if (updateResult == 1) {
          System.out.println("Money sent successfully  ");
          balance = balance - amount;
          System.out.println("Your new Balance =  " + balance);

          String Query = "update customer set balance = ? where phone = ? ";
          PreparedStatement Statement = dbconnection.prepareStatement(Query);

          Statement.setInt(1, balance);
          Statement.setString(2, phone);

          Statement.executeUpdate();

          showMenu();
        } else {
          System.out.println("Sorry! this account is not registered on Takawala.");
          showMenu();
        }
      } catch (SQLException throwables) {
        System.out.println("error from database from update deposit  ");
      }

    } else {
      System.out.println("your account have no enough money");
      showMenu();
    }

  }

  void Bidyut(String companyName) {

    String CompanyName = companyName;

    System.out.println(CompanyName);

    System.out.println("Enter Metter number : ");
    String metterNumber = scanner.next();
    System.out.println("Enter Mobile number : ");
    String mobile = scanner.next();
    System.out.println("Enter Month : ");
    String month = scanner.next();

    System.out.println("Bill Amount : ");
    int amount = scanner.nextInt();
    if (balance > amount) {

      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String updateQuery = "update customer set balance = ? where phone = ? ";
        PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery);

        balance = balance - amount;
        preparedStatement.setInt(1, balance);
        preparedStatement.setString(2, phone);

        int updateResult = preparedStatement.executeUpdate();

        if (updateResult == 1) {
          System.out.println("Pay Bill successfull ");
          System.out.println("Your new Balance =  " + balance);

          showMenu();
        }
      } catch (SQLException throwables) {
        System.out.println("error from database from update deposit  ");
      }
    } else {
      System.out.println("your account have no enough money");
      showMenu();

    }

  }

  void GasBill(String companyName) {

    String CompanyName = companyName;

    System.out.println(CompanyName);

    System.out.println("Enter Customer Code : ");
    String customerCode = scanner.next();
    System.out.println("Enter Mobile number : ");
    String mobile = scanner.next();
    System.out.println("Enter Bill month : ");
    String month = scanner.next();

    System.out.println("Bill Amount : ");
    int amount = scanner.nextInt();
    if (balance > amount) {

      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String updateQuery = "update customer set balance = ? where phone = ? ";
        PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery);

        balance = balance - amount;
        preparedStatement.setInt(1, balance);
        preparedStatement.setString(2, phone);

        int updateResult = preparedStatement.executeUpdate();

        if (updateResult == 1) {
          System.out.println("Pay Bill successfull ");
          System.out.println("Your new Balance =  " + balance);

          showMenu();
        }
      } catch (SQLException throwables) {
        System.out.println("error from database from update deposit  ");
      }
    } else {
      System.out.println("your account have no enough money");
      showMenu();

    }

  }

  void WaterBill(String companyName) {

    String CompanyName = companyName;

    System.out.println(CompanyName);

    System.out.println("Enter Bill Number : ");
    String billNumber = scanner.next();
    System.out.println("Enter Bill month : ");
    String month = scanner.next();

    System.out.println("Bill Amount : ");
    int amount = scanner.nextInt();
    if (balance > amount) {

      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String updateQuery = "update customer set balance = ? where phone = ? ";
        PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery);

        balance = balance - amount;
        preparedStatement.setInt(1, balance);
        preparedStatement.setString(2, phone);

        int updateResult = preparedStatement.executeUpdate();

        if (updateResult == 1) {
          System.out.println("Pay Bill successfull ");
          System.out.println("Your new Balance =  " + balance);

          showMenu();
        }
      } catch (SQLException throwables) {
        System.out.println("error from database from update deposit  ");
      }
    } else {
      System.out.println("your account have no enough money");
      showMenu();

    }

  }

  void InternetBill(String companyName) {

    String CompanyName = companyName;

    System.out.println(CompanyName);

    System.out.println("Enter Subscribr ID : ");
    String billNumber = scanner.next();

    System.out.println("Enter Contact number : ");
    String contact = scanner.next();

    System.out.println("Amount : ");
    int amount = scanner.nextInt();
    if (balance > amount) {

      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String updateQuery = "update customer set balance = ? where phone = ? ";
        PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery);

        balance = balance - amount;
        preparedStatement.setInt(1, balance);
        preparedStatement.setString(2, phone);

        int updateResult = preparedStatement.executeUpdate();

        if (updateResult == 1) {
          System.out.println("Pay Bill successfull ");
          System.out.println("Your new Balance =  " + balance);

          showMenu();
        }
      } catch (SQLException throwables) {
        System.out.println("error from database from update deposit  ");
      }
    } else {
      System.out.println("your account have no enough money");
      showMenu();

    }

  }

  @Override
  public void payBill() {

    System.out.println("");
    System.out.println("");
    System.out.println("Pay Bill");
    System.out.println("1 - Electricity Bill");
    System.out.println("2 - Gas Bill");
    System.out.println("3 - Water Bill");
    System.out.println("4 - Internet Bill");
    System.out.println("5 - Back to Home");
    System.out.println(" ------------------------ ");
    System.out.println(" Your select  : ");
    int option = scanner.nextInt();
    switch (option) {
    case 1:

      System.out.println("");
      System.out.println("");
      System.out.println("Electricity Bill");
      System.out.println("1 - Palli Bidyut");
      System.out.println("2 - DESCO");
      System.out.println("3 - NESCO");
      System.out.println("4 - DPDC");
      System.out.println("5 - Westzone");
      System.out.println("6 - Back to Home");
      System.out.println("------------------------ ");
      System.out.println("Your select  : ");
      int n = scanner.nextInt();
      switch (n) {
      case 1:
        Bidyut("Palli Bidyut");

        break;

      case 2:
        Bidyut("DESCO");

        break;

      case 3:
        Bidyut("NESCO");

        break;
      case 4:
        Bidyut("DPDC");

        break;
      case 5:
        Bidyut("Westzone");

        break;

      case 6:
        showMenu();
        break;

      default:
        System.out.println("Sorry your select is not listed ");
      }

      break;

    case 2:

      System.out.println("");
      System.out.println("");
      System.out.println("Gas Bill");
      System.out.println("1 - Titas Gas");
      System.out.println("2 - Karnaphuli Gas");
      System.out.println("3 - Sundarban Gas");

      System.out.println("4 - Back to Home");
      System.out.println("------------------------ ");
      System.out.println("Your select  : ");
      int a = scanner.nextInt();
      switch (a) {
      case 1:

        GasBill("Titas Gas");

        break;

      case 2:
        GasBill("Karnaphuli Gas");

        break;

      case 3:
        GasBill("Sundarban Gas");

        break;

      case 4:
        showMenu();
        break;

      default:
        System.out.println("Sorry your select is not listed ");
      }

      break;

    case 3:

      System.out.println("");
      System.out.println("");
      System.out.println("Water Bill");
      System.out.println("1 - Dhaka WASA");
      System.out.println("2 - Khulna WASA");
      System.out.println("3 - Chattogram WASA");
      System.out.println("4 - Rajshahi WASA");

      System.out.println("5 - Back to Home");
      System.out.println("------------------------ ");
      System.out.println("Your select  : ");
      int b = scanner.nextInt();
      switch (b) {
      case 1:
        WaterBill("Dhaka WASA");

        break;

      case 2:
        WaterBill("Khulna WASA");

        break;

      case 3:
        WaterBill("Chattogram WASA");

        break;
      case 4:
        WaterBill("Rajshahi WASA");

        break;

      case 5:
        showMenu();
        break;

      default:
        System.out.println("Sorry your select is not listed ");
      }

      break;

    case 4:

      System.out.println("");
      System.out.println("");
      System.out.println("Internet Bill");
      System.out.println("1 - Link3");
      System.out.println("2 - AmberIT");
      System.out.println("3 - Carnival");
      System.out.println("4 - SamOnline");
      System.out.println("5 - Dot Internet");
      System.out.println("6 - Back to Home");
      System.out.println("------------------------ ");
      System.out.println("Your select  : ");
      int c = scanner.nextInt();
      switch (c) {
      case 1:
        InternetBill("Link3");

        break;

      case 2:
        InternetBill("AmberIT");

        break;

      case 3:
        InternetBill("Carnival");

        break;
      case 4:
        InternetBill("SamOnline");

        break;
      case 5:
        InternetBill("Dot Internet");

        break;

      case 6:
        showMenu();
        break;

      default:
        System.out.println("Sorry your select is not listed ");
      }

      break;

    case 5:
      showMenu();
      break;

    default:
      System.out.println("Sorry your select is not listed ");
    }

    showMenu();
  }

  @Override
  public void mobileRecharge() {

    System.out.println("Enter mobile number : ");
    int mobileNumber = scanner.nextInt();

    System.out.println("Enter Amount : ");
    int amount = scanner.nextInt();
    if (balance > amount) {

      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String updateQuery = "update customer set balance = ? where phone = ? ";
        PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery);

        balance = balance - amount;
        preparedStatement.setInt(1, balance);
        preparedStatement.setString(2, phone);

        int updateResult = preparedStatement.executeUpdate();

        if (updateResult == 1) {
          System.out.println("Recharge successfull ");
          System.out.println("Your new Balance =  " + balance);

          showMenu();
        }
      } catch (SQLException throwables) {
        System.out.println("error from database from update deposit  ");
      }
    } else {
      System.out.println("your account have no enough money");
      showMenu();

    }

  }

  @Override
  public void payment() {

    System.out.println("Enter Merchant number or Name : ");
    String Merchant = scanner.next();

    System.out.println("Enter Amount : ");
    int amount = scanner.nextInt();
    if (balance > amount) {

      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String updateQuery = "update customer set balance = ? where phone = ? ";
        PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery);

        balance = balance - amount;
        preparedStatement.setInt(1, balance);
        preparedStatement.setString(2, phone);

        int updateResult = preparedStatement.executeUpdate();

        if (updateResult == 1) {
          System.out.println("Payment successfull ");
          System.out.println("Your new Balance =  " + balance);

          showMenu();
        }
      } catch (SQLException throwables) {
        System.out.println("error from database from update deposit  ");
      }
    } else {
      System.out.println("your account have no enough money");
      showMenu();

    }

  }

  void TakawalaToBank(String bankName) {

    String BankName = bankName;

    System.out.println("Enter " + BankName + " account number : ");
    int mobileNumber = scanner.nextInt();

    System.out.println("Enter Amount : ");
    int amount = scanner.nextInt();
    if (balance > amount) {

      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String updateQuery = "update customer set balance = ? where phone = ? ";
        PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery);

        balance = balance - amount;
        preparedStatement.setInt(1, balance);
        preparedStatement.setString(2, phone);

        int updateResult = preparedStatement.executeUpdate();

        if (updateResult == 1) {
          System.out.println("Money transfer successfull ");
          System.out.println("Your new Balance =  " + balance);

          showMenu();
        }
      } catch (SQLException throwables) {
        System.out.println("error from database from update deposit  ");
      }
    } else {
      System.out.println("your account have no enough money");
      showMenu();

    }

  }

  @Override
  public void moneyTransfer() {

    System.out.println("");
    System.out.println("");
    System.out.println("Money Transfer");
    System.out.println("1 - Takawala to Asia Bank");
    System.out.println("2 - Takawala to Islami Bank");
    System.out.println("3 - Takawala to City Bank");
    System.out.println("4 - Takawala to Dhaka Bank");
    System.out.println("5 - Takawala to AB Bank");
    System.out.println("6 - Back to Home");
    System.out.println("------------------------ ");
    System.out.println("Your select  : ");
    int option = scanner.nextInt();
    switch (option) {
    case 1:

      TakawalaToBank("Asia Bank");
      break;

    case 2:

      TakawalaToBank("Islami Bank");
      break;
    case 3:

      TakawalaToBank("City Bank");
      break;
    case 4:

      TakawalaToBank("Dhaka Bank");
      break;
    case 5:

      TakawalaToBank("AB Bank");
      break;

    case 6:
      showMenu();
      break;

    default:
      System.out.println("Sorry your select is not listed ");
    }

  }

  @Override
  public void cashOut() {

    System.out.println("Enter Agent number : ");
    int mobileNumber = scanner.nextInt();

    System.out.println("Enter Amount : ");
    int amount = scanner.nextInt();
    if (balance > amount) {

      try {
        dbconnection = DriverManager.getConnection(connectionUrl, "root", "");

        String updateQuery = "update customer set balance = ? where phone = ? ";
        PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery);
        balance = balance - amount;
        preparedStatement.setInt(1, balance);
        preparedStatement.setString(2, phone);

        int updateResult = preparedStatement.executeUpdate();

        if (updateResult == 1) {
          System.out.println("Cash Out successsfull! receive you money from Agent ");
          System.out.println("Your new Balance =  " + balance);

          showMenu();
        }
      } catch (SQLException throwables) {
        System.out.println("error from database from update deposit  ");
      }
    } else {
      System.out.println("your account have no enough money");
      showMenu();

    }

  }

}
