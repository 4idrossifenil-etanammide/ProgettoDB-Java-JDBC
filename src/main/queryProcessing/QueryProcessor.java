package main.queryProcessing;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * La classe si occupa di processare le query e il corrispondente risultato.
 * @author stefa
 *
 */
public class QueryProcessor {

	/**
	 * Vengono definiti i campi per la creazione delle query
	 */
	Statement stmt;
	ResultSet rs;
	
	/**
	 * Il metodo setta i valori per stmt e rs che verrano utilizzati poi 
	 * per la produzione del risultato
	 * @param query - Query in formato stringa
	 * @return valore booleano che rappresenta il successo dell'operazione
	 */
	public String processa(String query) {
		try {
		    stmt = Connector.connection.createStatement();

		    if (stmt.execute(query)) {
		        rs = stmt.getResultSet();
		    }

		}
		catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    switch(ex.getSQLState()) {
		    case "42000":
		    	return "Errore nella sintassi SQL";
		    case "42S02":
		    	return getPartOfErrorMessage(ex.getMessage(), "Tabella ", " non esiste.");
		    case "42S22":
		    	return getPartOfErrorMessage(ex.getMessage(), "Colonna ", " non trovata.");
		    default:
		    	return "Errore sconosciuto";
		    }
		}
		
		return "";
	}
	
	private String getPartOfErrorMessage(String s, String first, String last) {
		String[] parti = s.split("'");
    	return first + parti[1].split("'")[0] + last;
	}
	
	/**
	 * Il metodo stampa il risultato.
	 * Vengono salvati il numero di colonne della tabella,
	 * successivamente, finchè ci sono righe da leggere vengono inserite in
	 * un dizionario con chiave uguale al nome della colonna e valori corrispondenti.
	 * Infine viene stampato il risultato sotto forma di tabella. 
	 */
	public String stampaRisultato() {
		
		String chiave;
		String valore;
		LinkedHashMap<String, ArrayList<String>> dizionario = new LinkedHashMap<>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int colonne = rsmd.getColumnCount();
			
			int righe = 0;
			while (rs.next()) {
				righe++;
			       for (int i = 1; i <= colonne; i++) {
			    	   chiave = rsmd.getColumnName(i);
			    	   valore = rs.getString(i);
			           dizionario.computeIfAbsent(chiave, k -> new ArrayList<String>());
			           //Vengono sostituiti i valori nulli con una stringa "NULL"
			           if(valore == null)
			        	   dizionario.get(chiave).add("NULL");
			           else
			        	   dizionario.get(chiave).add(valore);
			       }
			}
			
			if(righe == 0) {
				return "Risultato vuoto";
			}
			
			return stampaTabella(dizionario, righe);
			//System.out.println(dictionary.toString());
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + e.getSQLState());
		}
		return "";
		
	}
	
	/**
	 * La funziona stampa il risultato in formato di tabella.
	 * Prima di tutto vengono definite le massime lunghezze delle stringhe per orgni colonna e
	 * vengono inserite in una lista. Successivamente si stampa una riga vuota,
	 * la riga con i nomi delle colonne e i vari valori.
	 * @param dictionary - dizionario delle colonne e corrispondenti valori
	 * @param rows - numero di righe nella tabella
	 */
	private String stampaTabella(LinkedHashMap<String, ArrayList<String>> dizionario, int righe) {
		List<Integer> listaLunghezze = new ArrayList<>();
		dizionario.forEach((k,v) -> listaLunghezze.add(Math.max(k.length(), v.stream().mapToInt(String::length).max().orElseThrow())));
		
		String toReturn = "";
		
		toReturn += stampaRiga(listaLunghezze);
		
		int j = 0;
		for(String key : dizionario.keySet()) {
			toReturn += "| "+key;
			//System.out.print("| "+key);
			for(int k = 0; k < listaLunghezze.get(j)+1-key.length(); k++)
				toReturn += " ";
				//System.out.print(" ");
			j++;
		}
		toReturn += "| \n";
		//System.out.print("|");
		//System.out.println();
		
		toReturn += stampaRiga(listaLunghezze);
		
		int x = 0;
		int l = 0;
		for(int i = 0; i < righe; i++) {
			l = 0;
			for(List<String> value : dizionario.values()) {
				toReturn += "| "+value.get(x);
				//System.out.print("| "+value.get(x));
				for(int p = 0; p < listaLunghezze.get(l)+1-value.get(x).length(); p++)
					toReturn += " ";
					//System.out.print(" ");
				l++;
			}
			toReturn += "| \n";
			//System.out.print("|");
			//System.out.println();
			x++;
		}
		
		toReturn += stampaRiga(listaLunghezze);
		return toReturn;
	}
	
	/**
	 * Viene stampata una riga che funziona da separatore nella tabella
	 * @param listaLunghezze - lista delle lunghezze massime nelle colonne.
	 */
	private String stampaRiga(List<Integer> listaLunghezze) {
		
		String toReturn = "";
		
		for(Integer k : listaLunghezze) {
			toReturn += "+";
			//System.out.print("+");
			for(int i = 0; i < k+2; i++)
				toReturn += "-";
				//System.out.print("-");
		}
		toReturn += "+ \n";
		return toReturn;
		//System.out.print("+");
		//System.out.println();
	}
}
