package com.soft.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.soft.entity.Orientation;
import com.soft.entity.OrientationAvg;

public class DbOperations {

	private boolean checkTableExists(String tableName) {
		 String sql = "SELECT * FROM sqlite_master WHERE name ='"+tableName+"' and type='table';";
		 Connection c = null;
		 Statement stmt = null;
		 boolean result = false;

		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:orientations.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      result = rs.next();

		      rs.close();
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    }

		    return result;
	}

	private void createTableOrients() {
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:orientations.db");
	      stmt = c.createStatement();
	      String sql = "CREATE TABLE ORIENTS " +
	                   "(ID INTEGER PRIMARY KEY   AUTOINCREMENT," +
	                   " ORIENTATION            INT     NOT NULL, " +
	                   " DATEANDTIME        DATETIME NOT NULL)";

	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}

	private void createTableOrientsAvg() {
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:orientations.db");
	      stmt = c.createStatement();
	      String sql = "CREATE TABLE ORIENTSAVG " +
	                   "(ID INTEGER PRIMARY KEY   AUTOINCREMENT," +
	                   " AVG            INT     NOT NULL, " +
	                   " DATEANDTIME        DATETIME NOT NULL)";

	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}

	public void add(Orientation orient) {

			if(!checkTableExists("ORIENTS")) {
				createTableOrients();
			}

		 	Connection c = null;
		    Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:orientations.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      String sql = "INSERT INTO ORIENTS(ORIENTATION, DATEANDTIME)  " +
		                   "VALUES ('" + Integer.toString(orient.getOrientation()) + "', '" + orient.getDateTime()  +"');";

		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.commit();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    }
	}

	public void add(OrientationAvg avg) {

		if(!checkTableExists("ORIENTSAVG")) {
			createTableOrientsAvg();
		}

	 	Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:orientations.db");
	      c.setAutoCommit(false);
	      stmt = c.createStatement();
	      String sql = "INSERT INTO ORIENTSAVG(AVG, DATEANDTIME)  " +
	                   "VALUES ('" + Integer.toString(avg.getAvg()) + "', '" + avg.getDateTime()  +"');";

	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
}

	public void addAll(List<Orientation> orients) {

		if(!checkTableExists("ORIENTS")) {
			createTableOrients();
		}

	 	Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:orientations.db");
	      c.setAutoCommit(false);
	      stmt = c.createStatement();

	      for(Orientation orient: orients) {
		      String sql = "INSERT INTO ORIENTS(ORIENTATION, DATEANDTIME)  " +
		                   "VALUES ('" + new String(Integer.toString(orient.getOrientation())) + "', '" + orient.getDateTime()  +"');";

	      	  stmt.executeUpdate(sql);
	      }
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
}

	public List<Orientation> getOrientations() {
		List<Orientation> list = new ArrayList<Orientation>();

		 Connection c = null;
		 Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:orientations.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM ORIENTS;" );
		      while ( rs.next() ) {
		    	  Orientation orient = new Orientation();
		    	  orient.setId(rs.getInt("id"));
		    	  orient.setOrientation(rs.getInt("orientation"));
		    	  orient.setDateTime(rs.getString("dateandtime"));
		    	  list.add(orient);
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    }

		    return list;
	}

	public List<OrientationAvg> getAvgs() {
		List<OrientationAvg> list = new ArrayList<OrientationAvg>();

		 Connection c = null;
		 Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:orientations.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM ORIENTSAVG;" );
		      while ( rs.next() ) {
		    	  OrientationAvg avg = new OrientationAvg();
		    	  avg.setId(rs.getInt("id"));
		    	  avg.setAvg(rs.getInt("avg"));
		    	  avg.setDateTime(rs.getString("dateandtime"));
		    	  list.add(avg);
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    }

		    return list;
	}
}
