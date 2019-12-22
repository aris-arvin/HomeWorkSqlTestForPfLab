package ru.pflb.sql.education;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
                System.out.println("Test Connection");
                return true;
        } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
        }
    }
    // Enter name of table
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
        String name = resultSet.getMetaData().getTableName(columns);
        String columnName = resultSet.getMetaData().getColumnName(columns);
    // Output data to the console
        System.out.println("| "+name+" |");
        while(resultSet.next()){
            for (int i = 1; i <= columns; i++){
                System.out.print("| " + resultSet.getString(i) + "\t ");
            }
            System.out.println();
        }
        System.out.println();

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

