import javax.swing.*;
import java.awt.*;

public class StudentGUI extends JFrame {

    public StudentGUI(){
        JPanel mainPanel = new JPanel(new GridLayout(4,2,5,5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JLabel t1 = new JLabel("Student ID: ");
        mainPanel.add(t1);
        JTextField f1 = new JTextField(30);
        mainPanel.add(f1);
        JLabel t2 = new JLabel("Student Name: ");
        mainPanel.add(t2);
        JTextField f2 = new JTextField(30);
        mainPanel.add(f2);
        JLabel t3 = new JLabel("Program: ");
        mainPanel.add(t3);
        JTextField f3 = new JTextField(30);
        mainPanel.add(f3);

        JButton btn1 = new JButton("Next");
        Panel empty = new Panel();
        mainPanel.add(empty);
        mainPanel.add(btn1);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        add(mainPanel);

    }


    public static void main(String[] args) {
        StudentGUI frame = new StudentGUI();
        frame.setTitle("Add Student");
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
