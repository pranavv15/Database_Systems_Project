package insert;

import java.awt.*; // for Dimension
import javax.swing.*; // for GUI components
import java.awt.event.*; // for action events
import java.sql.*;
import java.text.NumberFormat;
import java.time.*;


public class insert implements ActionListener {
    final static String driverClass = "org.sqlite.JDBC";
	final static String url = "jdbc:sqlite:dbs_project/autosDB.sqlite";

    private JLabel label;
    private JTextField year;
    private JTextField month;
    private JTextField day;
    private JTextField city;
    private JTextField state;
    private JTextField num_vehicles;
    

    private JButton button;
    
    JFrame frame = new JFrame();
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label8;
    private JLabel label9;
    private int acc_id;


    public insert() {
    frame.setForeground(Color.WHITE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(new Point(550, 200));
    frame.setSize(new Dimension(600, 400));
    frame.setTitle("An insert frame");

    year = new JTextField();
    month = new JTextField();
    day = new JTextField();
    num_vehicles = new JTextField();
    city = new JTextField();
    state = new JTextField();

    button = new JButton();
    button.setBackground(Color.red);
    button.setText("Insert");
    button.addActionListener(this);

    label1 = new JLabel("Enter Year of accident");
    label2 = new JLabel("Enter Month of accident");
    label3 = new JLabel("Enter day of accident");
    label4 = new JLabel("Enter number of vehicles in accident");
    label8 = new JLabel("Enter city of accident");
    label9 = new JLabel("Enter state of accident");

    frame.add(year);
    frame.add(label1);
   
    frame.add(month);
    frame.add(label2);

    frame.add(day);
    frame.add(label3);

    frame.add(city);
    frame.add(label8);
    
    frame.add(state);
    frame.add(label9);

    frame.add(num_vehicles);
    frame.add(label4);

  
    frame.add(button);
    
    frame.setLayout(new GridLayout(7,2));
    // frame.pack();
    frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource()==button){
            String a = year.getText();
            String b = month.getText();
            String c = day.getText();
            String d = num_vehicles.getText();
            String f = city.getText();
            String g = state.getText();
            
            
		    Connection connection = null;
		    String query = null;

		    try {
			// Load the JDBC drivers
			    Class.forName(driverClass);

			// Open a DB Connection
			    connection = DriverManager.getConnection(url);

			// Create a Statement
			    Statement stmnt = connection.createStatement();
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO accidents(aid, accident_date, city, state) VALUES(?, ?, ?, ?)");
 
                query = "select count(aid) from  accidents ";
                ResultSet rslt = stmnt.executeQuery(query);
                while(rslt.next()){
                    int accid = rslt.getInt(1);
                    System.out.println(accid);
                    accid = accid+1;
                    pstmt.setInt(1, accid);

                }
                int y = Integer.parseInt(a);
                int m = Integer.parseInt(b);
                int da = Integer.parseInt(c);
                int num_veh = Integer.parseInt(d);



                LocalDate id = LocalDate.of(y, m, da);
                f = f.toUpperCase();
                g = g.toUpperCase();

                
			    pstmt.setObject(2, id);
			    pstmt.setString(3, f);
			    pstmt.setString(4, g);
			
			    pstmt.executeUpdate();
                System.out.println(" Accident Update Successful!");

                frame.setVisible(false);

                if(num_veh ==1){
                    involvements_insert inv = new involvements_insert();
                }

                if(num_veh ==2){
                    involvements_insert inv1 = new involvements_insert();
                    involvements_insert inv2 = new involvements_insert();

                }

                if(num_veh ==3){
                    involvements_insert inv1 = new involvements_insert();
                    involvements_insert inv2 = new involvements_insert();
                    involvements_insert inv3 = new involvements_insert();

                }

                if(num_veh ==4){
                    involvements_insert inv1 = new involvements_insert();
                    involvements_insert inv2 = new involvements_insert();
                    involvements_insert inv3 = new involvements_insert();
                    involvements_insert inv4 = new involvements_insert();
                }

                if(num_veh ==5){
                    involvements_insert inv1 = new involvements_insert();
                    involvements_insert inv2 = new involvements_insert();
                    involvements_insert inv3 = new involvements_insert();
                    involvements_insert inv4 = new involvements_insert();
                    involvements_insert inv5 = new involvements_insert();
                }

                
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
