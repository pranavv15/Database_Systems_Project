package insert;

import java.awt.*; // for Dimension
import javax.swing.*; // for GUI components
import java.awt.event.*; // for action events
import java.sql.*;
import java.text.NumberFormat;

public class insert implements ActionListener {
    final static String driverClass = "org.sqlite.JDBC";
	final static String url = "jdbc:sqlite:dbs_project/autosDB.sqlite";

    private JLabel label;
    private JTextField year;
    private JTextField month;
    private JTextField day;
    private JTextField aid;
    private JTextField vin;
    private JTextField damages;
    private JTextField driver_ssn;


    private JButton button;
    
    JFrame frame = new JFrame();

    public insert() {
    frame.setForeground(Color.WHITE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(new Point(550, 200));
    frame.setSize(new Dimension(600, 400));
    frame.setTitle("A details frame");

    year = new JTextField();
    month = new JTextField();
    day = new JTextField();
    aid = new JTextField();
    vin = new JTextField();
    damages = new JTextField();
    driver_ssn = new JTextField();

    button = new JButton();
    button.setBackground(Color.red);
    button.setText("Find");
    button.addActionListener(this);
    label1 = new JLabel("Enter Year of accident");
    label2 = new JLabel("Enter Month of accident");
    label3 = new JLabel("Enter day of accident");
    label4 = new JLabel("Enter aid of accident");
    label5 = new JLabel("Enter vin of vehicles");
    label6 = new JLabel("Year of accident");
    label7 = new JLabel("Year of accident");



    
    frame.add(year);
    frame.add(month);
    frame.add(day);
    frame.add(aid);
    frame.add(vin);
    frame.add(damages);
    frame.add(driver_ssn);

    frame.add(button);
    frame.add(label);
    
    frame.setLayout(new GridLayout(4,1));
    frame.pack();
    frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource()==button){
            String a = year.getText();
            String b = month.getText();
            String c = day.getText();
            String d = aid.getText();
            String i = vin.getText();
            String f = damages.getText();
            String g = driver_ssn.getText();


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
            
			    // int a_id = Integer.parseInt(t);
                // query = "select * from involvements, accidents where accidents.aid = "+a_id+" and involvements.aid = "+a_id+"";
                // ResultSet rslt = stmnt.executeQuery(query);
                // int val = 0;
                // while (rslt.next()) {
                //     int accid = rslt.getInt(1);
                //     String v = rslt.getString(2);
                //     int da = rslt.getInt(3);
                //     String Driver_vin = rslt.getString(4);
                //     String date = rslt.getString(5);
                //     String city = rslt.getString(6);
                //     String State = rslt.getString(7);
				
                //     JTextArea k = new JTextArea("Acc id: " + accid + 
                //     "\r\n"+ "Vin: " + v + 
                //     "\r\n" + "Damages: " + da + 
                //     "\r\n" +  "Driver SSN: " + Driver_vin + 
                //     "\r\n" + "Date: " + date + 
                //     "\r\n" + "City: " + city + 
                //     "\r\n" + "State: " + State +
                //     "\r\n\r\n");

                //     val=val+1;
                //     frame.add(k);
                //     frame.setLayout(new GridLayout(6,1));
                //     frame.pack();

                    
                //     System.out.println();
                // }
                //     rslt.close();
                
                }catch (Exception e) {
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
}
