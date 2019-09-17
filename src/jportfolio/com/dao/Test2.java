package jportfolio.com.dao;

import java.sql.Connection;

public class Test2 {
	
	public static void main(String [] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Connection c = ConnectorX.getConnectionCrawler();
            System.out.println(c.getMetaData().getDatabaseProductName());
            
        } catch (Exception ex) {
            // handle the error
        	System.out.println(ex);
        }
	}

}
