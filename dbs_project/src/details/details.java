package details;

import java.awt.*; // for Dimension
import javax.swing.*; // for GUI components

// import apple.laf.JRSUIConstants.FrameOnly;

import java.awt.event.*; // for action events

import java.sql.*;
import java.text.NumberFormat;


public class details implements ActionListener {

    final static String driverClass = "org.sqlite.JDBC";
	final static String url = "jdbc:sqlite:dbs_project/autosDB.sqlite";

    private JLabel label;
    private JTextField text;
    private JButton button;
    
    JFrame frame = new JFrame();

    public details() {
    frame.setForeground(Color.WHITE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(new Point(550, 200));
    frame.setSize(new Dimension(600, 400));
    frame.setTitle("A details frame");

    text = new JTextField();
    button = new JButton();
    button.setBackground(Color.red);
    button.setText("Find");
    button.addActionListener(this);
    label = new JLabel("Enter Accident ID");
    
    frame.add(text);
    frame.add(button);
    frame.add(label);
    
    frame.setLayout(new GridLayout(4,1));
    frame.pack();
    frame.setVisible(true);

    }
    // @Override
    public void actionPerformed(ActionEvent event){
        if (event.getSource()==button){
            String t = text.getText();


            // NumberFormat nf = NumberFormat.getCurrencyInstance();
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
            
			    int a_id = Integer.parseInt(t);
                query = "select involvements.aid,vin,damages,driver_ssn,accident_date,city,state from involvements, accidents where accidents.aid = "+a_id+" and involvements.aid = "+a_id+"";
                ResultSet rslt = stmnt.executeQuery(query);
                int val = 0;
                while (rslt.next()) {
                    int accid = rslt.getInt(1);
                    String v = rslt.getString(2);
                    int da = rslt.getInt(3);
                    String Driver_vin = rslt.getString(4);
                    String date = rslt.getString(5);
                    String city = rslt.getString(6);
                    String State = rslt.getString(7);
				
                    JTextArea k = new JTextArea("Acc id: " + accid + 
                    "\r\n"+ "Vin: " + v + 
                    "\r\n" + "Damages: " + da + 
                    "\r\n" +  "Driver SSN: " + Driver_vin + 
                    "\r\n" + "Date: " + date + 
                    "\r\n" + "City: " + city + 
                    "\r\n" + "State: " + State +
                    "\r\n\r\n");

                    val=val+1;
                    frame.add(k);
                    frame.setLayout(new GridLayout(6,1));
                    frame.pack();

                    
                    System.out.println();
                }
                    rslt.close();
                
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
} }
