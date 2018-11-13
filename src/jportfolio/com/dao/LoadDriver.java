package jportfolio.com.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class LoadDriver {
    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
        		Connection c = getConnectionCrawler();
           // System.out.println(c.getMetaData().getDatabaseProductName());
           // System.out.println(c.getMetaData().getSchemaTerm());
            
            DatabaseMetaData md = c.getMetaData();
            String[] TYPE = {"TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM"};
            String[] TYPE1 = {"TABLE"};
            ResultSet rs = md.getTables(null, "public", "%", TYPE);
            while (rs.next()) {
              System.out.println(rs.getString(3));
            }
    
        } catch (Exception ex) {
            // handle the error
        	System.out.println(ex);
        }
    }
    
    public static Connection getConnectionCrawler() throws SQLException {
//
//		String driver = "org.postgresql.Driver";
//        String user   = "wizardme_wendel";
//        String senha  = "865410@Mkzj1584";
//        String url    = "jdbc:postgresql://162.253.125.196:5432/wizardme_mbi";
        
		String driver = "org.postgresql.Driver";
        String user   = "postgres";
        String senha  = "654321";
        String url    = "jdbc:postgresql://localhost:5433/db_finance";
        
        
        String user_jquine   = "hrddcefi_wendel";
        String senha_jquine  = "865410@Mkzj1584";
        String url_jquine    = "jdbc:postgresql://www.jquine.com:5432/hrddcefi_organon";
        //String url_jquine    = "jdbc:postgresql://162.253.126.108:5432/hrddcefi_organon";
       
		Connection conn = null;
		
		  try
	        {
	            Class.forName(driver);
	            //conn = (Connection) DriverManager.getConnection(url_jquine, user_jquine, senha_jquine);
	            conn = (Connection) DriverManager.getConnection(url, user, senha);
	        }
	        catch (ClassNotFoundException ex)
	        {
	            System.err.print(ex.getMessage());
	        } 
	        catch (SQLException e)
	        {
	            System.err.print(e.getMessage());
	        }

		return conn;

	}

}
