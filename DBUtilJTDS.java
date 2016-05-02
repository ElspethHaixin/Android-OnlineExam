package db;

import net.sourceforge.jtds.jdbc.Driver;
import java.sql.*;

public class DBUtilJTDS {
	
	String driverName = "net.sourceforge.jtds.jdbc.Driver";
	String user = "sa";
	String pwd = "123456";
	String url ="jdbc:jtds:sqlserver://192.40.38.241:1433;DatabaseName=sp_akmdbs;user=" + user + ";password=" + pwd;
	
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;

	public Connection getConn() {
		try {
			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Statement getStmt() {
		try {
			stmt = getConn().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}

	public PreparedStatement getPstmt(String sql) {
		try {
			pstmt = getConn().prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
}
