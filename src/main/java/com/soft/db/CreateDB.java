package com.soft.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDB {

	public static void main(String[] args) {
		Connection c = null;
	    Statement stmt = null;
        System.out.println("aaa");
        try {
		Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:orientations.db");
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE ORIENTS " +
	                   "(ID INTEGER PRIMARY KEY   AUTOINCREMENT," +
	                   " ORIENTATION            INT     NOT NULL, " +
	                   " DATEANDTIME        DATETIME NOT NULL)";
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println("aaaaaaaaaa" + e.getClass().getName() + ": " + e.getMessage() );
	    }
	    System.out.println("Table created successfully");

	}

}
