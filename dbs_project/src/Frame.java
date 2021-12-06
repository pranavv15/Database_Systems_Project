import java.awt.*; // for Dimension
import javax.swing.*; // for GUI components
import java.awt.event.*; // for action events
import details.details;
import insert.insert;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.*;

import javax.print.PrintService;

import java.time.*;


public class Frame {

    private static JTextField field;
    private static JLabel lab;
    private static JButton button1;
    private static JButton button2;
    private static JButton button3;

    Frame() {
    
                JFrame frame = new JFrame();
                frame.setForeground(Color.WHITE);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocation(new Point(550, 200));
                frame.setSize(new Dimension(600, 400));
                frame.setTitle("A frame");
    
                button1 = new JButton();
                button1.setBackground(Color.RED);
                button1.setText("Add Accident");
                button1.addActionListener(new Message());
                frame.add(button1);
                
                button2 = new JButton();
                button2.setBackground(Color.PINK);
                button2.setText("Find Details");
                button2.addActionListener(new Message());
                frame.add(button2);
                
                button3 = new JButton();
                button3.setBackground(Color.red);
                button3.setText("Search Accidents");
                button3.addActionListener(new Message());
                frame.add(button3);


                

                lab = new JLabel("Choose required option");
                frame.add(lab);
                

                frame.setLayout(new GridLayout(5,1));
                frame.pack();

                frame.setVisible(true);
             }
            public class Message implements ActionListener {
            public void actionPerformed(ActionEvent event) {
            
                if (event.getSource()==button2){
                    details det = new details();
                //    det.details();
                }
                
                else if (event.getSource()== button1){
                        insert ins = new insert();
                }
                    }
            }

           
            
            public static void main (String[] args){
                Frame fr = new Frame();
                  
}
}
   