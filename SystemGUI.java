import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SystemGUI extends JFrame {
    JPanel mainPanel;
    CardLayout card;

    public SystemGUI() {
        setTitle("Student Management System");
        setSize(650, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main Panel
        card = new CardLayout();
        mainPanel = new JPanel(card);
        JPanel menu = createMenuPanel();
        JPanel addStudentPanel = createAddStudentPanel();
        JPanel addTranscriptPanel = createAddTranscriptPanel();
        JPanel assignCoursesPanel = createAssignCoursesPanel();
        JPanel viewReportPanel = createViewReportPanel();
        JPanel viewReportStudentPanel = createViewReportStudentPanel();
        JPanel viewReportCoursesPanel = createViewReportCoursesPanel();
        JPanel globalStatsPanel = createGlobalStatsPanel();
        JPanel recordResultsPanel = createRecordResultPanel();
        JPanel[] allPanels = { menu, addStudentPanel, addTranscriptPanel, assignCoursesPanel, viewReportPanel,
                viewReportStudentPanel, viewReportCoursesPanel, globalStatsPanel, recordResultsPanel };
        String[] tags = { "Menu", "AddStudent", "AddTranscript", "AssignCourses", "ViewReport", "ViewReportStudent",
                "ViewReportCourses", "GlobalStats", "RecordResults" };

        for (int i = 0; i < allPanels.length; i++) {
            mainPanel.add(allPanels[i], tags[i]);
        }

        add(mainPanel);
        setVisible(true);
    }

    public JPanel createMenuPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("Student Management System");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Button Grid
        String[] btnLabels = { "Add Student", "Assign Course", "View Report", "Global Stats", "Record Result" };
        String[] tags = { "AddStudent", "AssignCourses", "ViewReport", "GlobalStats", "RecordResults" };
        JPanel buttonGrid = new JPanel(new GridLayout(5, 1, 10, 10));
        for (int i = 0; i < 5; i++) {
            int index = i;
            JButton btn = new JButton(btnLabels[i]);
            btn.setFocusable(false);
            btn.addActionListener(e -> {
                CardLayout c = (CardLayout) (mainPanel.getLayout());
                c.show(mainPanel, tags[index]);
            });
            buttonGrid.add(btn);
        }

        // Exit Button
        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));
        exit.setFocusable(false);

        cover.add(buttonGrid, BorderLayout.CENTER);
        cover.add(exit, BorderLayout.SOUTH);
        return cover;
    }

    public JPanel createAddStudentPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("Add Student");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Fields
        JPanel textPanel = new JPanel(new GridLayout(3, 2));
        JLabel idLabel = new JLabel("Enter Student ID:");
        JTextField idField = new JTextField(10);
        JLabel nameLabel = new JLabel("Enter Student Name:");
        JTextField nameField = new JTextField(10);
        JLabel programLabel = new JLabel("Enter Student Program:");
        JTextField programField = new JTextField(10);
        textPanel.add(idLabel);
        textPanel.add(idField);
        textPanel.add(nameLabel);
        textPanel.add(nameField);
        textPanel.add(programLabel);
        textPanel.add(programField);
        cover.add(textPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        JButton next = new JButton("Next");
        back.addActionListener(e -> {
            goBacktoMenu();
        });
        next.addActionListener(e -> {
            CardLayout c = (CardLayout) mainPanel.getLayout();
            c.show(mainPanel, "AddTranscript");
        });

        buttonPanel.add(back);
        buttonPanel.add(next);
        cover.add(buttonPanel, BorderLayout.SOUTH);
        return cover;
    }

    public JPanel createAddTranscriptPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("Add Transcript");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Fields

        return cover;
    }

    public JPanel createAssignCoursesPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("Assign Course - Instructor");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Fields
        JPanel fields = new JPanel(new GridLayout(2, 2));
        JLabel courseLabel = new JLabel("Course Name:");
        fields.add(courseLabel);
        JTextField courseField = new JTextField(10);
        fields.add(courseField);
        JLabel instructorLabel = new JLabel("Instructor Name:");
        fields.add(instructorLabel);
        JTextField instructorField = new JTextField(10);
        fields.add(instructorField);
        cover.add(fields);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        JButton assign = new JButton("Assign");
        back.addActionListener(e -> {
            goBacktoMenu();
        });
        buttonPanel.add(back);
        buttonPanel.add(assign);
        cover.add(buttonPanel, BorderLayout.SOUTH);

        return cover;
    }

    public JPanel createViewReportPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("View Report");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Option Buttons
        JPanel optionPanel = new JPanel();
        JButton optionStudent = new JButton("View by Student");
        JButton optionCourse = new JButton("View by Course");
        optionPanel.add(optionStudent);
        optionPanel.add(optionCourse);
        cover.add(optionPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            goBacktoMenu();
        });
        buttonPanel.add(back);
        cover.add(buttonPanel, BorderLayout.SOUTH);

        return cover;
    }

    public JPanel createViewReportStudentPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("View Report");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            goBacktoMenu();
        });
        buttonPanel.add(back);
        cover.add(buttonPanel, BorderLayout.SOUTH);

        return cover;
    }

    public JPanel createViewReportCoursesPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("View Report");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            goBacktoMenu();
        });
        buttonPanel.add(back);
        cover.add(buttonPanel, BorderLayout.SOUTH);
        return cover;
    }

    public JPanel createGlobalStatsPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("Global Stats");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            goBacktoMenu();
        });
        buttonPanel.add(back);
        cover.add(buttonPanel, BorderLayout.SOUTH);
        return cover;
    }

    public JPanel createRecordResultPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("Record Result");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            goBacktoMenu();
        });
        buttonPanel.add(back);
        cover.add(buttonPanel, BorderLayout.SOUTH);
        return cover;
    }

    // Helper Methods
    private void goBacktoMenu() {
        card.show(mainPanel, "Menu");
    }

    public static void main(String[] args) {
        SystemGUI systemGUI = new SystemGUI();
    }
}
