package insert;

import java.awt.*; // for Dimension
import javax.swing.*; // for GUI components
import java.awt.event.*; // for action events
import java.sql.*;
import java.text.NumberFormat;
import java.time.*;


public class involvements_insert implements ActionListener {

    final static String driverClass = "org.sqlite.JDBC";
	final static String url = "jdbc:sqlite:dbs_project/autosDB.sqlite";

    private JTextField vin;
    private JTextField damages;
    private JTextField driver_ssn;

    private JButton button;
    
    JFrame frame = new JFrame();

    private JLabel label5;
    private JLabel label6;
    private JLabel label7;

    public involvements_insert() {
        frame.setForeground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(new Point(550, 200));
        frame.setSize(new Dimension(600, 400));
        frame.setTitle("An insert frame");

        vin = new JTextField();
        damages = new JTextField();
        driver_ssn = new JTextField();

        button = new JButton();
        button.setBackground(Color.red);
        button.setText("Insert");
        button.addActionListener(this);

        label5 = new JLabel("Enter vin of vehicle");
        label6 = new JLabel("Enter damages of vehicle");
        label7 = new JLabel("Enter driver ssn of driver");

        frame.add(vin);
        frame.add(label5);

        frame.add(damages);
        frame.add(label6);

        frame.add(driver_ssn);
        frame.add(label7);

        frame.add(button);
    // frame.add(label7);
    
        frame.setLayout(new GridLayout(10,2));
    // frame.pack();
        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource()==button){
           
            String h = vin.getText();
            String i = damages.getText();
            String j = driver_ssn.getText();


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
                // PreparedStatement pstmt = connection.prepareStatement("INSERT INTO accidents(aid, accident_date, city, state) VALUES(?, ?, ?, ?)");
                PreparedStatement pst = connection.prepareStatement("INSERT INTO involvements(aid, vin, damages, driver_ssn) VALUES(?, ?, ?, ?)");
 
                query = "select count(aid) from  accidents ";
                ResultSet rslt = stmnt.executeQuery(query);
                while(rslt.next()){
                    int accid = rslt.getInt(1);
                    System.out.println(accid);
                    // accid = accid+1;

                    // pstmt.setInt(1, accid);
                    pst.setInt(1, accid);

                }
                
                int dam = Integer.parseInt(i);
                // pst.setInt(1, acc_id);
                pst.setString(2, h);
                pst.setInt(3, dam);
                pst.setString(4, j);

                pst.executeUpdate();
                System.out.println("Involvements Update Successful!");

                frame.setVisible(false);
                frame.dispose();
                    
              
                
                }catch (Exception e) {
                    System.out.println(query);
                    JFrame frame2 = new JFrame();
                    frame2.setForeground(Color.WHITE);
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame2.setLocation(new Point(550, 200));
                    frame2.setSize(new Dimension(600, 400));
                    frame2.setTitle("An insert frame");

                    JLabel lab1 = new JLabel();
                    lab1.setText("Invalid entry! Please enter correct value");

                    frame2.add(lab1);
                    frame2.setVisible(true);
                    // frame2.pack();

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
