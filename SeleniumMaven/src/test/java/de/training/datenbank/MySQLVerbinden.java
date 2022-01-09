package de.training.datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class MySQLVerbinden {

	@Test
	public void datenbankTesten() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");// driver laden
		System.out.println("Driver geladen");

		Connection datenbankVerbindung = DriverManager.getConnection("jdbc:mysql://localhost:3306/selenium", "root",
				"Qwertz987.,!"); // mit der Datenbank verbinden

		System.out.println("Mit MySQL-Datenbank verbunden");

		Statement feststellung = datenbankVerbindung.createStatement(); // Dass man Querys nutzen kann
		ResultSet ergebnismenge = feststellung.executeQuery("select * from selenium_user;");

		while (ergebnismenge.next()) {
			String vorname = ergebnismenge.getString("first_name");
			//String vorname = ergebnismenge.getString(0); holt auch den Vornamen aber weil es die erste Spalte von der Tabelle waere
			System.out.println("Vorname: " + vorname);
			String email = ergebnismenge.getString("email");
			//String email = ergebnismenge.getString(1); holt auch die E-mail-Adrese weil es die zweite Spalte von der Tabelle waere 
			System.out.println("Email-Adresse: " + email);
		}

		// EINFUEGEN
//		String erstellenString = "insert into selenium_user values('Ele', 'ele@gmail.com');";
//		PreparedStatement feststellungFuersErstellen = datenbankverbindung.prepareStatement(erstellenString);
//		int feststellungsausfuehrung = feststellungFuersErstellen.executeUpdate();
//		if (feststellungsausfuehrung > 0) {
//			System.out.println("Reihe hinzugefuedgt");
//		} else {
//			System.out.println("FEHLER");
//		}

		// DATENSATZ VON TABELLE AKTUALISIEREN
//		String aktualisierenString = "update selenium_user set first_name='Ele' where first_name='Elejj';";
//		PreparedStatement feststellungFuersAktualisieren = datenbankverbindung.prepareStatement(aktualisierenString);
//		int feststellungsausfuehrung = feststellungFuersAktualisieren.executeUpdate();
//		if (feststellungsausfuehrung > 0) {
//			System.out.println("Reihe aktualisiert");
//		} else {
//			System.out.println("FEHLER");
//		}

		// REIHE VON TABELLE LOESCHEN
//		String loeschenString = "delete from selenium_user where first_name='Ele';";
//		PreparedStatement feststellungFuersLoeschen = datenbankverbindung.prepareStatement(loeschenString);
//		int feststellungsausfuehrung = feststellungFuersLoeschen.executeUpdate();
//		if (feststellungsausfuehrung == 0) {
//			System.out.println("FEHLER");
//		} else {
//			System.out.println("Reihe geloescht");
//		}
	}

}
