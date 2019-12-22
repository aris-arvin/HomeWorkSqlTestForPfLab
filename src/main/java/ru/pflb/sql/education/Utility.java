package ru.pflb.sql.education;

import java.sql.*;
import java.util.Scanner;

public class Utility {
    public static void main(String[] args) {
        Utility utility = new Utility();
        if (utility.open()){
        utility.selectTable();
        utility.close();
        }
    }
    Connection connect;
    // Open connection
    public boolean open() {
        try {
                Class.forName("org.sqlite.JDBC");
                connect = DriverManager.getConnection("JDBC:sqlite:c:\\SQLight\\first_database.db");
                System.out.println("Connection is ready");
                return true;
        } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
        }
    }
    // Enter name of table (names: "users", "cars")
    public void selectTable(){
        try{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Connected with database SQLight");
        System.out.println("Enter name of table: ");
        String nameOfTable = scanner.nextLine();

        String sqlQuery = "SELECT * FROM " + nameOfTable +"";

        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        int columns = resultSet.getMetaData().getColumnCount();
        String tableName = resultSet.getMetaData().getTableName(columns);

        // Output data to the console
        System.out.println("| "+tableName+" |");
        for (int a = 1; a <=columns; a++){
            System.out.printf("|" + resultSet.getMetaData().getColumnName(a) + "\t");
        }
            System.out.println("|");

        while(resultSet.next()){
            for (int i = 1; i <= columns; i++){
                System.out.printf("|" + resultSet.getString(i) + "\t");
            }
            System.out.println("|");
        }

        statement.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    // Close utility
    public void close(){
        try{
            connect.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

