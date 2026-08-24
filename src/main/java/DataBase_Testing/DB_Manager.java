package DataBase_Testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Manager {

	public static void main(String[] args) {

		manageDB_Access();
	}

	public static void manageDB_Access() {
		String databaseURL = "jdbc:ucanaccess://src/main/resources/Database_MS_Access/DATABASE_FOR DB_TESTING.accdb";
		/*
		 * String sql = "SELECT * "
		 * + "From PLAYER_DATA";
		 */

		try {
			// Load drivers
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			// create connection
			Connection connection = DriverManager.getConnection(databaseURL);

			// create statement
			Statement statement = connection.createStatement();

			// execute query
			// ResultSet result = statement.executeQuery(sql);

			ResultSet result = statement.executeQuery("Select * from PLAYER_INFO where (Salary > 0)");
			while (result.next()) {
				System.out.println(result.getString(1) + " " + result.getString(2));
			}
			connection.close();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

	}

	public static void manageDB_MySQL() {

	}

	public static void manageDB_Oracle() {

	}
}
