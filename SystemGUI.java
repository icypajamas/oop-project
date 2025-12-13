import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;

public class SystemGUI extends JFrame {
    JPanel mainPanel;
    CardLayout card;
    // SystemManager system;

    public SystemGUI(/* SystemManager system */) {
        // this.system = system;
        setTitle("Student Management System");
        setSize(690, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main Panel to manage Card Layout
        card = new CardLayout();
        mainPanel = new JPanel(card);
        JPanel menu = createMenuPanel();
        JPanel studentManagePanel = createStudentManage();
        JPanel addStudentPanel = createAddStudentPanel();
        JPanel deleteStudentPanel = createDeleteStudentPanel();
        JPanel addTranscriptPanel = createAddTranscriptPanel();
        JPanel manageCoursesPanel = createCourseManagePanel();
        JPanel assignCoursesPanel = createAssignCoursesPanel();
        JPanel deleteCoursesPanel = createDeleteCoursesPanel();
        JPanel resultManagePanel = createResultManagementPanel();
        JPanel viewReportPanel = createViewReportPanel();
        JPanel viewReportStudentPanel = createViewReportStudentPanel();
        JPanel viewReportCoursesPanel = createViewReportCoursesPanel();
        JPanel globalStatsPanel = createGlobalStatsPanel();
        JPanel recordResultPanel = createRecordResultPanel();
        JPanel[] allPanels = { menu, studentManagePanel, addStudentPanel, deleteStudentPanel, addTranscriptPanel,
                manageCoursesPanel, assignCoursesPanel, deleteCoursesPanel, resultManagePanel, viewReportPanel,
                viewReportStudentPanel, viewReportCoursesPanel, globalStatsPanel, recordResultPanel };
        String[] tags = { "Menu", "StudentManage", "AddStudent", "DeleteStudent", "AddTranscript", "CourseManage",
                "AssignCourse", "DeleteCourse", "ResultManage",
                "ViewReport", "ViewReportStudent",
                "ViewReportCourse", "GlobalStats", "RecordResult" };

        for (int i = 0; i < allPanels.length; i++) {
            mainPanel.add(allPanels[i], tags[i]);
        }

        add(mainPanel);
        setVisible(true);
    }

    // MAIN MENU PANEL

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
        String[] btnLabels = { "Student Management", "Course Management", "Result Management", "Global Statistics" };
        String[] tags = { "StudentManage", "CourseManage", "ResultManage", "GlobalStats" };
        JPanel buttonGrid = new JPanel(new GridLayout(5, 1, 10, 10));
        for (int i = 0; i < 4; i++) {
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

    // STUDENT MANAGE PANEL

    public JPanel createStudentManage() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("Manage Students");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Central Panel
        JPanel centerButtons = new JPanel(new GridLayout(2, 1));
        centerButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(e -> {
            card.show(mainPanel, "AddStudent");
        });
        JButton deleteStudentButton = new JButton("Delete Student");
        deleteStudentButton.addActionListener(e -> {
            card.show(mainPanel, "DeleteStudent");
        });
        centerButtons.add(addStudentButton);
        centerButtons.add(deleteStudentButton);
        cover.add(centerButtons, BorderLayout.CENTER);

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

    // ADD STUDENT PANEL

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
            card.show(mainPanel, "StudentManage");
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

    // DELETE STUDENT PANEL

    public JPanel createDeleteStudentPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("Delete Student");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Fields
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel textPanel = new JPanel(new GridLayout(1, 2));
        JLabel idLabel = new JLabel("Enter Student ID:");
        JTextField idField = new JTextField(10);
        textPanel.add(idLabel);
        textPanel.add(idField);
        JButton delete = new JButton("Delete");
        delete.addActionListener(e -> {
            // Delete Function Call
        });
        centerPanel.add(delete, BorderLayout.SOUTH);
        centerPanel.add(textPanel, BorderLayout.CENTER);
        cover.add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            card.show(mainPanel, "StudentManage");
        });
        buttonPanel.add(back);
        cover.add(buttonPanel, BorderLayout.SOUTH);
        return cover;
    }

    // ADD TRANSCRIPT PANEL

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

    // COURSE MANAGEMENT PANEL
    public JPanel createCourseManagePanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("Manage Students");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Central Panel
        JPanel centerButtons = new JPanel(new GridLayout(2, 1));
        centerButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JButton assignCourseButton = new JButton("Assign Course to Instructor");
        assignCourseButton.addActionListener(e -> {
            card.show(mainPanel, "AssignCourse");
        });
        JButton deleteCourseButton = new JButton("Delete Course");
        deleteCourseButton.addActionListener(e -> {
            card.show(mainPanel, "DeleteCourse");
        });
        centerButtons.add(assignCourseButton);
        centerButtons.add(deleteCourseButton);
        cover.add(centerButtons, BorderLayout.CENTER);

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

    // COURSE ASSIGNMENT PANEL

    public JPanel createAssignCoursesPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("Assign Course to Instructor");
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
            card.show(mainPanel, "CourseManage");
        });
        buttonPanel.add(back);
        buttonPanel.add(assign);
        cover.add(buttonPanel, BorderLayout.SOUTH);

        return cover;
    }

    // COURSE DELETE PANEL

    public JPanel createDeleteCoursesPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("Delete Course");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Fields
        JPanel fields = new JPanel(new GridLayout(2, 2));
        JLabel courseLabel = new JLabel("Course Code:");
        fields.add(courseLabel);
        JTextField courseField = new JTextField(10);
        fields.add(courseField);
        cover.add(fields);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        JButton delete = new JButton("Delete");
        back.addActionListener(e -> {
            card.show(mainPanel, "CourseManage");
        });
        buttonPanel.add(back);
        buttonPanel.add(delete);
        cover.add(buttonPanel, BorderLayout.SOUTH);

        return cover;
    }

    // RESULT MANAGEMENT
    public JPanel createResultManagementPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel("Result Management");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        cover.add(titlePanel, BorderLayout.NORTH);

        // Option Buttons
        JPanel optionPanel = new JPanel(new GridLayout(2, 1));
        JButton recordResultButton = new JButton("Record Result");
        recordResultButton.addActionListener(e -> {
            card.show(mainPanel, "RecordResult");
        });
        JButton viewReportButton = new JButton("View Report");
        viewReportButton.addActionListener(e -> {
            card.show(mainPanel, "ViewReport");
        });
        optionPanel.add(recordResultButton);
        optionPanel.add(viewReportButton);
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

    // VIEW REPORT PANEL

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
        optionStudent.addActionListener(e -> {
            card.show(mainPanel, "ViewReportStudent");
        });
        JButton optionCourse = new JButton("View by Course");
        optionCourse.addActionListener(e -> {
            card.show(mainPanel, "ViewReportCourses");
        });
        optionPanel.add(optionStudent);
        optionPanel.add(optionCourse);
        cover.add(optionPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            card.show(mainPanel, "ResultManage");
        });
        buttonPanel.add(back);
        cover.add(buttonPanel, BorderLayout.SOUTH);

        return cover;
    }

    // VIEW REPORT BY STUDENT

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

        // Fields
        JPanel fields = new JPanel(new GridLayout(1, 2));
        JLabel idLabel = new JLabel("Enter Student ID:");
        fields.add(idLabel);
        JTextField idField = new JTextField(10);
        fields.add(idField);
        cover.add(fields, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            card.show(mainPanel, "ViewReport");
        });
        buttonPanel.add(back);
        cover.add(buttonPanel, BorderLayout.SOUTH);

        return cover;
    }

    // VIEW REPORT BY COURSE

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

        // Fields
        JPanel fields = new JPanel(new GridLayout(1, 2));
        JLabel codeLabel = new JLabel("Enter Course Code:");
        fields.add(codeLabel);
        JTextField codeField = new JTextField(10);
        fields.add(codeField);
        cover.add(fields, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            card.show(mainPanel, "ViewReport");
        });
        buttonPanel.add(back);
        cover.add(buttonPanel, BorderLayout.SOUTH);
        return cover;
    }

    // GLOBAL STATISTICS PANEL

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

    // RECORD RESULT PANEL

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
        // SystemGUI systemGUI = new SystemGUI(new SystemManager());
        SystemGUI systemGUI = new SystemGUI();
    }
}
