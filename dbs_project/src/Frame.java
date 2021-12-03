import java.awt.*; // for Dimension
import javax.swing.*; // for GUI components
// import App.java;
public class Frame {
    // import javax.swing.*; // for GUI components
    public static void main(String[] args) {
    
                JFrame frame = new JFrame();
                frame.setForeground(Color.WHITE);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocation(new Point(550, 200));
                frame.setSize(new Dimension(600, 400));
                frame.setTitle("A frame");

                JButton button1 = new JButton();
                button1.setBackground(Color.YELLOW);
                button1.setText("This is a button");
                frame.add(button1);

                JButton button2 = new JButton();
                button2.setBackground(Color.red);
                button2.setText("This is a button");
                frame.add(button2);

                frame.setLayout(new FlowLayout());
                frame.pack();

                frame.setVisible(true);
             }
}
   