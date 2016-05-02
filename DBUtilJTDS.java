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

	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
        String questionId = "253";
        
        String sql = "select * from ak_stk where id='" + questionId + "';";
        
        Statement s = new DBUtilJTDS().getStmt();
        
        try {
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{
				String[] choices;
				String name = rs.getString("st_names").trim();
				String answer = rs.getString("st_anwer").trim();
				String choice = rs.getString("st_wt").trim();
				int type = rs.getInt("ischeckbox");
				if (type == 2)
				{
					answer = choice;
					System.out.println(answer);
				}
				else
				{
					String[] aux = choice.split("#");
					int j = 0;
					for (; j < aux.length; j++)
						if (aux[j].equals(""))
							break;
					choices = new String[j];
					for (int k = 0; k < j; k++)
						choices[k] = aux[k];
					for (int i = 0; i < choices.length; i++)
						System.out.println(choices[i]);
				}
			}
		} catch (SQLException e) {
		}
	}
	
}
