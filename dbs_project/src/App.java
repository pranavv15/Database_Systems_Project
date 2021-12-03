import java.sql.*;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.print.PrintService;

import java.time.*;

public class App {
	final static String driverClass = "org.sqlite.JDBC";
	final static String url = "jdbc:sqlite:dbs_project/autosDB.sqlite";


	public static void main(String[] argv)
	{
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		Connection connection = null;
		String query = null;

		try {
			// Load the JDBC drivers
			Class.forName(driverClass);

			// Open a DB Connection
			//connection = DriverManager.getConnection(url, username, password);
			connection = DriverManager.getConnection(url);

			// Create a Statement
			Statement stmnt = connection.createStatement();
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO accidents(aid, accident_date, city, state) VALUES(?, ?, ?, ?)"); 

			// 1. Execute an update
			Scanner m1 = new Scanner(System.in);
			// System.out.println("Enter the year of the accident");
			// String year = m1.nextLine();
			// System.out.println("Enter the month of the accident");
			// String month = m1.nextLine();
			// System.out.println("Enter the day of the accident");
			// String day = m1.nextLine();
			
			// int y = Integer.parseInt(year);
			// int m = Integer.parseInt(month);
			// int d = Integer.parseInt(day);

			// LocalDate id = LocalDate.of(y, m, d);

			// System.out.println("Enter the city of the accident");
			// String City = m1.nextLine();
			// City = City.toUpperCase();

			// System.out.println("Enter the state of the accident");
			// String state = m1.nextLine();
			// state = state.toUpperCase();

			// int aid = 101;

			// pstmt.setInt(1, aid);
			// pstmt.setObject(2, id);
			// pstmt.setString(3, City);
			// pstmt.setString(4, state);
			
			// pstmt.executeUpdate();
			// System.out.println("Success!");

			// //  Insert into involvements
			// PreparedStatement pst = connection.prepareStatement("INSERT INTO involvements(aid, vin, damages, driver_ssn) VALUES(?, ?, ?, ?)");

			// System.out.println("Enter the aid of the accident: ");
			// int ac = m1.nextInt();

			// System.out.println("Enter the vin of the vehicle: ");
			// String vin = m1.next();

			// System.out.println("Enter the damages in the accident: ");
			// int damage = m1.nextInt();

			// System.out.println("Enter the driver ssn: ");
			// String ssn = m1.next();

			// pst.setInt(1, ac);
			// pst.setString(2, vin);
			// pst.setInt(3, damage);
			// pst.setString(4, ssn);

			// pst.executeUpdate();
			// System.out.println("Success!");

			

			// // PreparedStatement st = connection.prepareStatement("DELETE FROM involvements WHERE aid > " + 100 + ";");
        	// // st.executeUpdate();


			// 2. Execute query
			System.out.println("Enter the accident id number: ");
			int a_id = m1.nextInt();
			query = "select * from involvements, accidents where accidents.aid = "+a_id+" and involvements.aid = "+a_id+"";
			ResultSet rslt = stmnt.executeQuery(query);
			while (rslt.next()) {
				int accid = rslt.getInt(1);
				String v = rslt.getString(2);
				int da = rslt.getInt(3);
				String Driver_vin = rslt.getString(4);
				String date = rslt.getString(5);
				String city = rslt.getString(6);
				String State = rslt.getString(7);
				


				System.out.println("Acc id: " + accid);
				System.out.println("Vin: " + v);
				System.out.println("Damages: " + da);
				System.out.println("Driver SSN: " + Driver_vin);
				System.out.println("Date: " + date);
				System.out.println("City: " + city);
				System.out.println("State: " + State);


				System.out.println();
			}
		} 
		catch (Exception e) {
			System.out.println(query);
			e.printStackTrace();
		} 
		finally {
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

}