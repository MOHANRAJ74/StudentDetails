package com.silicon.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*Created Connection class and implemented variable and methods which 
 * is declared in IJdbc Interface.Connected with Database using JDBC and return connection*/
public class JDBCConnection implements IJdbc{
		 
	    public Connection getConnection() throws ClassNotFoundException, SQLException {    
	        
	        	Class.forName("com.mysql.jdbc.Driver");
	        	Connection con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	        
	        return con;
	    }
	}
