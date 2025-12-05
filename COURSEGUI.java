import javax.swing.*;
import java.awt.*;

public class COURSEGUI extends JFrame {
    public COURSEGUI(){
        //setLayout(new GridLayout(2 , 1 , 5 , 5));

        JPanel Cover = new JPanel(new GridLayout(3 , 2));
        Cover.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        Cover.add(new JLabel("COURSE NAME:"));
        // Cover.add(new JTextField(8));
        JTextField field = new JTextField(8);
        field.setPreferredSize(new Dimension(100,30));
        Cover.add(field);
        Cover.add(new JLabel("INSTRUCTOR NAME:"));
        //Cover.add(new JTextField(8));
        JTextField field2 = new JTextField(8);
        field.setPreferredSize(new Dimension(100,30));
        Cover.add(field2);
        Cover.setBackground(Color.LIGHT_GRAY);
        add(Cover);
        Panel p = new Panel();
        Cover.add(p);
        JButton jb = new JButton("Assign");
        Cover.add(jb);
    }
    public static void main(String[] args) {
        COURSEGUI C = new COURSEGUI();
        C.setTitle("Course and instructor name");
        C.setVisible(true);
        C.setSize(300 , 150);
        C.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        C.setLocationRelativeTo(null);
    }
}
