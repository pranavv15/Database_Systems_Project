package search;

import java.awt.*; // for Dimension
import javax.swing.*; // for GUI components
import java.awt.event.*; // for action events
import java.sql.*;
import java.text.NumberFormat;
import java.time.*;

public class search implements ActionListener {
    final static String driverClass = "org.sqlite.JDBC";
	final static String url = "jdbc:sqlite:dbs_project/autosDB.sqlite";

    private JLabel label;
    private JTextField min_date;
    private JTextField max_date;
    private JTextField min_avg;
    private JTextField max_avg;
    private JTextField min_tot;
    private JTextField max_tot;
    

    private JButton button;
    
    JFrame frame = new JFrame();
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    // private JButton button;
    // private int acc_id;


    public search() {
        frame.setForeground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(new Point(550, 200));
        frame.setSize(new Dimension(600, 400));
        frame.setTitle("An insert frame");

        button = new JButton();
        button.setText("Search");
        button.addActionListener(this);

        min_date = new JTextField();
        max_date = new JTextField();
        min_avg = new JTextField();
        max_avg = new JTextField();
        min_tot = new JTextField();
        max_tot = new JTextField();

        label1 = new JLabel("Enter the min date range or leave empty");
        label2 = new JLabel("Enter the max date range or leave empty");
        label3 = new JLabel("Enter the min average damages or leave empty");
        label4 = new JLabel("Enter the max average damages or leave empty");
        label5 = new JLabel("Enter the min total damages or leave empty");
        label6 = new JLabel("Enter the max total damages or leave empty");

        frame.add(min_date);
        frame.add(label1);

        frame.add(max_date);
        frame.add(label2);

        frame.add(min_avg);
        frame.add(label3);

        frame.add(max_avg);
        frame.add(label4);

        frame.add(min_tot);
        frame.add(label5);

        frame.add(max_tot);
        frame.add(label6);

        frame.add(button);

        frame.setLayout(new GridLayout(7,2));
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource()==button){
            // String t = text.getText();
            String a = min_date.getText();
            String b = max_date.getText();
            String c = min_avg.getText();
            String d = max_avg.getText();
            String f = min_tot.getText();
            String g = max_tot.getText();

           
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
                String dmin="";
                String dmax="";
                int amin=0;
                int amax=0;
                int tmin=0;
                int tmax=0;


                if(min_avg.getText().isEmpty() && min_date.getText().isEmpty() && min_tot.getText().isBlank()){
                     dmin = "0000-01-01";
                     dmax = "9999-12-31";
                     amin = 0;
                     amax = 999999;
                     tmin = 0;
                     tmax = 999999;
                }

                else if(min_date.getText().isEmpty() && min_avg.getText().isEmpty()==false && min_tot.getText().isEmpty()==false){
                    dmin = "0000-01-01";
                    dmax = "9999-12-31";
                    amin = Integer.parseInt(c);
                    amax = Integer.parseInt(d);
                    tmin = Integer.parseInt(f);
                    tmax = Integer.parseInt(g);
                }

                else if(min_date.getText().isEmpty()==false && min_avg.getText().isEmpty() && min_tot.getText().isEmpty()==false){
                    dmin = "0000-01-01";
                    dmax = "9999-12-31";
                    amin = 0;
                    amax = 999999;
                    tmin = Integer.parseInt(f);
                    tmax = Integer.parseInt(g);
                }

                else if(min_date.getText().isEmpty()==false && min_avg.getText().isEmpty()==false && min_tot.getText().isEmpty()) {
                    dmin = a;
                    dmax = b;
                    amin = Integer.parseInt(c);
                    amax = Integer.parseInt(d);
                    tmin = 0;
                    tmax = 999999;

                }

                else if(min_date.getText().isEmpty() && min_avg.getText().isEmpty()){
                    dmin = "0000-01-01";
                    dmax = "9999-12-31";
                    amin = 0;
                    amax = 999999;
                    tmin = Integer.parseInt(f);
                    tmax = Integer.parseInt(g);

                }

                else if(min_avg.getText().isEmpty() && min_tot.getText().isEmpty()){
                    dmin = a;
                    dmax = b;
                    amin = 0;
                    amax = 999999;
                    tmin = 0;
                    tmax = 999999;
                }
                else if(min_date.getText().isEmpty()  && min_tot.getText().isEmpty()){
                    dmin = "0000-01-01";
                    dmax = "9999-12-31";
                    amin = Integer.parseInt(c);
                    amax = Integer.parseInt(d);
                    tmin = 0;
                    tmax = 999999;
                }

                else{
                    System.out.println(a);
                    System.out.println(b);
                    System.out.println(c);
                    System.out.println(d);
                    System.out.println(f);
                    System.out.println(g);

                    dmin = a;
                    dmax = b;
                    amin = Integer.parseInt(c);
                    amax = Integer.parseInt(d);
                    tmin = Integer.parseInt(f);
                    tmax = Integer.parseInt(g);
                }

                query = "select * from (select * from accidents where aid in(select aid from (select aid,SUM(damages) as tdamages, AVG(damages) as adamages from involvements GROUP BY aid) where tdamages > "+tmin+" AND tdamages < "+tmax+" and adamages > "+amin+" and adamages < "+amax+")) where accident_date > '"+dmin+"' and accident_date < '"+dmax+"' ";

                ResultSet result = stmnt.executeQuery(query);
			    while(result.next()){
				int ac_id = result.getInt(1);
				String date = result.getString(2);
				String city = result.getString(3);
				String state = result.getString(4);

                JTextArea t = new JTextArea("Accident id is: " + ac_id +
                "\r\n" + "Date is :  " + date +
                "\r\n" + "City is : " + city +
                "\r\n" +  "State is : " + state + 
                "\r\n\r\n");

                frame.add(new JScrollPane(t));
                frame.pack();
			}
			result.close();




            }catch (Exception e) {
                System.out.println(query);
                JFrame frame2 = new JFrame();
                    frame2.setForeground(Color.WHITE);
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame2.setLocation(new Point(550, 200));
                    frame2.setSize(new Dimension(600, 400));
                    frame2.setTitle("An search frame");

                    JLabel lab1 = new JLabel();
                    lab1.setText("Invalid entry! Please enter correct value");

                    frame2.add(lab1);
                    frame2.setVisible(true);
                e.printStackTrace();
            } 
            finally {
                try {
                    connection.close();
                } catch (Exception e) {
            }
    }}   
}
}
