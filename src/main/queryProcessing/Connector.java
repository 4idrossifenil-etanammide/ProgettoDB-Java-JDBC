package main.queryProcessing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	static Connection connection;
	
	public static String start(String database, String username, String password) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/" + database + "?" + "user=" + username + "&password=" + password);
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + e.getSQLState());
		    
		    String strError = e.getSQLState();
		    switch(strError) {
		    	case "42000":
		    		return "Database sconosciuto. Riprova.";
		    	case "28000":
		    		return "Accesso negato. Ricontrolla il tuo username o la tua password";
		    	default:
		    		return "Errore sconosciuto. Codice errore SQL : " + strError;
		    }
		}
		
		return "";
	}
}
