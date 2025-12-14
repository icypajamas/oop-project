import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.util.ArrayList;

public class SystemGUI extends JFrame {
    JPanel mainPanel;
    CardLayout card;
    JComboBox<Course> courseList;
    SystemManager system;

    public SystemGUI(SystemManager system) {
        this.system = system;
        setTitle("Student Management System");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main Panel to manage Card Layout
        card = new CardLayout();
        mainPanel = new JPanel(card);
        JPanel menu = createMenuPanel();
        JPanel studentManagePanel = createStudentManage();
        JPanel addStudentPanel = createAddStudentPanel();
        JPanel deleteStudentPanel = createDeleteStudentPanel();
        JPanel viewAllStudents = createAllStudentsPanel();
        JPanel manageCoursesPanel = createCourseManagePanel();
        JPanel addCoursePanel = createAddCoursePanel();
        JPanel assignCoursesPanel = createAssignCoursesPanel();
        JPanel deleteCoursesPanel = createDeleteCoursesPanel();
        JPanel viewAllCourses = createAllCoursesPanel();
        JPanel viewReportPanel = createViewReportPanel();
        JPanel viewReportStudentPanel = createViewReportStudentPanel();
        JPanel viewReportCoursesPanel = createViewReportCoursesPanel();
        JPanel globalStatsPanel = createGlobalStatsPanel();
        JPanel recordResultPanel = createRecordResultPanel();
        JPanel[] allPanels = { menu, studentManagePanel, addStudentPanel, deleteStudentPanel, viewAllStudents,
                manageCoursesPanel, addCoursePanel, assignCoursesPanel, deleteCoursesPanel, viewAllCourses,
                viewReportPanel,
                viewReportStudentPanel, viewReportCoursesPanel, globalStatsPanel, recordResultPanel };
        String[] tags = { "Menu", "StudentManage", "AddStudent", "DeleteStudent", "ViewAllStudent", "CourseManage",
                "AddCourse",
                "AssignCourse", "DeleteCourse", "ViewAllCourse",
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
        JPanel titlePanel = createTitlePanel("Student Management System");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Button Grid
        String[] btnLabels = { "Student Management", "Course Management", "View Report", "Record Result",
                "Global Statistics" };
        String[] tags = { "StudentManage", "CourseManage", "ViewReport", "RecordResult", "GlobalStats" };
        JPanel buttonGrid = new JPanel(new GridLayout(6, 1, 10, 10));
        for (int i = 0; i < 5; i++) {
            int index = i;
            JButton btn = new JButton(btnLabels[i]);
            btn.setFocusable(false);
            btn.addActionListener(e -> {
                refreshCourseCombo(courseList);
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
        JPanel titlePanel = createTitlePanel("Student Management");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Central Panel
        JPanel centerButtons = new JPanel(new GridLayout(3, 1));
        centerButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(e -> {
            card.show(mainPanel, "AddStudent");
        });
        JButton deleteStudentButton = new JButton("Delete Student");
        deleteStudentButton.addActionListener(e -> {
            card.show(mainPanel, "DeleteStudent");
        });
        JButton allStudentButton = new JButton("View All Students");
        allStudentButton.addActionListener(e -> {
            card.show(mainPanel, "ViewAllStudent");
        });
        centerButtons.add(addStudentButton);
        centerButtons.add(deleteStudentButton);
        centerButtons.add(allStudentButton);
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
        JPanel titlePanel = createTitlePanel("Add Student");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Fields
        JPanel textPanel = new JPanel(new GridLayout(4, 2));
        JLabel idLabel = new JLabel("Enter Student ID:");
        JTextField idField = new JTextField(10);
        JLabel nameLabel = new JLabel("Enter Student Name:");
        JTextField nameField = new JTextField(10);
        JLabel programLabel = new JLabel("Enter Student Program:");
        JTextField programField = new JTextField(10);
        JLabel typeLabel = new JLabel("Select Student Department: ");
        String[] studentTypes = { "Science", "Engineering", "Arts" };
        JComboBox<String> typeList = new JComboBox<>(studentTypes);
        textPanel.add(idLabel);
        textPanel.add(idField);
        textPanel.add(nameLabel);
        textPanel.add(nameField);
        textPanel.add(programLabel);
        textPanel.add(programField);
        textPanel.add(typeLabel);
        textPanel.add(typeList);
        cover.add(textPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        JButton next = new JButton("Next");
        back.setFocusable(false);
        next.setFocusable(false);
        back.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            programField.setText("");
            card.show(mainPanel, "StudentManage");
        });
        next.addActionListener(e -> {
            StringBuilder errors = new StringBuilder();

            if (!isValidName(nameField.getText())) {
                errors.append("- Invalid name\n");
            }

            if (!isValidID(idField.getText())) {
                errors.append("- Invalid student ID\n");
            }

            if (!isValidProgram(programField.getText())) {
                errors.append("- Invalid program\n");
            }

            if (errors.length() > 0) {
                JOptionPane.showMessageDialog(cover, errors.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String program = programField.getText().trim();
            String type = (String) typeList.getSelectedItem();
            if (system.getStudents().search(id) != null) {
                JOptionPane.showMessageDialog(cover, "Student ID already exists", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student;
            switch (type) {
                case "Science":
                    student = new ScienceStudent(id, name, program);
                    break;

                case "Engineering":
                    student = new EngineeringStudent(id, name, program);
                    break;

                case "Arts":
                    student = new ArtsStudent(id, name, program);
                    break;
                default:
                    return;
            }
            idField.setText("");
            nameField.setText("");
            programField.setText("");
            system.addStudent(student);
            int choice = JOptionPane.showConfirmDialog(this,
                    "Student added successfully\nWould you like to add a Result Entry?", "Success",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                card.show(mainPanel, "RecordResult");
            }

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
        JPanel titlePanel = createTitlePanel("Delete Student");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Fields
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel textPanel = new JPanel(new GridLayout(1, 2));
        JLabel idLabel = new JLabel("Enter Student ID:");
        JTextField idField = new JTextField(10);
        textPanel.add(idLabel);
        textPanel.add(idField);
        centerPanel.add(textPanel, BorderLayout.CENTER);
        cover.add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.setFocusable(false);
        back.addActionListener(e -> {
            idField.setText("");
            card.show(mainPanel, "StudentManage");
        });
        JButton delete = new JButton("Delete");
        delete.setFocusable(false);
        delete.addActionListener(e -> {
            if (system.deleteStudent(idField.getText())) {
                JOptionPane.showMessageDialog(cover, "Student Deleted Successfully", "RIP",
                        JOptionPane.INFORMATION_MESSAGE);
            } else
                JOptionPane.showMessageDialog(cover, "Student does not exist", "Error", JOptionPane.ERROR_MESSAGE);

            idField.setText("");
        });

        buttonPanel.add(back);
        buttonPanel.add(delete);
        cover.add(buttonPanel, BorderLayout.SOUTH);
        return cover;
    }

    // VIEW ALL STUDENTS PANEL

    public JPanel createAllStudentsPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = createTitlePanel("Student List");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Table
        String[] columns = { "ID", "Name", "Program", "Department" };
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable studentTable = new JTable(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        studentTable.setRowSorter(sorter);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        loadStudentTableData(model);
        cover.add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        JButton update = new JButton("Update Table");
        back.setFocusable(false);
        update.setFocusable(false);
        update.addActionListener(e -> {
            loadStudentTableData(model);
        });
        back.addActionListener(e -> {
            card.show(mainPanel, "StudentManage");
        });
        buttonPanel.add(back);
        buttonPanel.add(update);
        cover.add(buttonPanel, BorderLayout.SOUTH);
        return cover;
    }

    // COURSE MANAGEMENT PANEL

    public JPanel createCourseManagePanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = createTitlePanel("Course Management");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Central Panel
        JPanel centerButtons = new JPanel(new GridLayout(4, 1));
        centerButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JButton addCourseButton = new JButton("Add Course");
        addCourseButton.setFocusable(false);
        addCourseButton.addActionListener(e -> {
            card.show(mainPanel, "AddCourse");
        });
        JButton assignCourseButton = new JButton("Assign Course - Instructor");
        assignCourseButton.setFocusable(false);
        assignCourseButton.addActionListener(e -> {
            refreshCourseCombo(courseList);
            card.show(mainPanel, "AssignCourse");
        });
        JButton deleteCourseButton = new JButton("Delete Course");
        deleteCourseButton.setFocusable(false);
        deleteCourseButton.addActionListener(e -> {
            card.show(mainPanel, "DeleteCourse");
        });
        JButton allCoursesButton = new JButton("View All Courses");
        allCoursesButton.setFocusable(false);
        allCoursesButton.addActionListener(e -> {
            card.show(mainPanel, "ViewAllCourse");
        });
        centerButtons.add(addCourseButton);
        centerButtons.add(assignCourseButton);
        centerButtons.add(deleteCourseButton);
        centerButtons.add(allCoursesButton);
        cover.add(centerButtons, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.setFocusable(false);
        back.addActionListener(e -> {
            goBacktoMenu();
        });
        buttonPanel.add(back);
        cover.add(buttonPanel, BorderLayout.SOUTH);

        return cover;
    }

    // COURSE ADDITION PANEL

    public JPanel createAddCoursePanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = createTitlePanel("Add Course");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Fields
        JPanel fields = new JPanel(new GridLayout(5, 2));
        JLabel codeLabel = new JLabel("Course Code:");
        fields.add(codeLabel);
        JTextField codeField = new JTextField(10);
        fields.add(codeField);
        JLabel courseLabel = new JLabel("Course Title:");
        fields.add(courseLabel);
        JTextField courseField = new JTextField(10);
        fields.add(courseField);
        JLabel chLabel = new JLabel("Credit Hours: ");
        fields.add(chLabel);
        JTextField chField = new JTextField(10);
        fields.add(chField);
        JLabel instructorLabel = new JLabel("Instructor Name:");
        fields.add(instructorLabel);
        JTextField instructorField = new JTextField(10);
        fields.add(instructorField);
        JLabel qualLabel = new JLabel("Instructor Qualification:");
        fields.add(qualLabel);
        JTextField qualField = new JTextField(10);
        fields.add(qualField);
        cover.add(fields);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        JButton addButton = new JButton("Add Course");
        back.setFocusable(false);
        addButton.setFocusable(false);
        back.addActionListener(e -> {
            codeField.setText("");
            courseField.setText("");
            chField.setText("");
            instructorField.setText("");
            qualField.setText("");
            card.show(mainPanel, "CourseManage");
        });
        addButton.addActionListener(e -> {

            if (system.getCourses().search(codeField.getText().trim()) != null) {
                JOptionPane.showMessageDialog(cover, "Course Code already exists", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            StringBuilder errors = new StringBuilder();

            if (!isValidCourseCode(codeField.getText())) {
                errors.append("- Invalid code\n");
            }

            if (!isValidProgram(courseField.getText())) {
                errors.append("- Invalid Title\n");
            }

            if (!isValidInstructorName(instructorField.getText())) {
                errors.append("- Invalid Instructor Name\n");
            }
            if (!isOnlyNum(chField.getText())) {
                errors.append("- Invalid Credit Hours\n- Credit hours must be positive\n");
            }
            if (!isValidQualification(qualField.getText())) {
                errors.append("- Invalid Qualification");
            }

            if (errors.length() > 0) {
                JOptionPane.showMessageDialog(cover, errors.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String name = instructorField.getText().trim();
            String qualification = qualField.getText().trim();
            CourseInstructor instructor = new CourseInstructor(name, qualification);
            String code = codeField.getText().trim();
            String title = courseField.getText().trim();
            int ch = Integer.parseInt(chField.getText());
            Course course = new Course(code, title, ch, instructor);
            system.addCourse(course);
            JOptionPane.showMessageDialog(cover, "Course added successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            codeField.setText("");
            courseField.setText("");
            chField.setText("");
            instructorField.setText("");
            qualField.setText("");
        });

        buttonPanel.add(back);
        buttonPanel.add(addButton);
        cover.add(buttonPanel, BorderLayout.SOUTH);

        return cover;
    }

    // COURSE ASSIGNMENT PANEL

    public JPanel createAssignCoursesPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = createTitlePanel("Assign Course to Instructor");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Fields
        JPanel fields = new JPanel(new GridLayout(4, 2));
        JLabel courseLabel = new JLabel("Course: ");
        fields.add(courseLabel);
        courseList = new JComboBox<>();
        ArrayList<Course> courseArray = system.getCourses().getItems();
        for (Course course : courseArray) {
            courseList.addItem(course);
        }
        fields.add(courseList);
        JLabel instructorLabel = new JLabel("Instructor Name:");
        fields.add(instructorLabel);
        JTextField instructorField = new JTextField(10);
        fields.add(instructorField);
        JLabel qualLabel = new JLabel("Instructor Qualification:");
        fields.add(qualLabel);
        JTextField qualField = new JTextField(10);
        fields.add(qualField);
        cover.add(fields);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        JButton assign = new JButton("Assign");
        back.setFocusable(false);
        assign.setFocusable(false);
        back.addActionListener(e -> {
            instructorField.setText("");
            qualField.setText("");
            card.show(mainPanel, "CourseManage");
        });

        assign.addActionListener(e -> {
            Course selectedCourse = (Course) courseList.getSelectedItem();
            if (courseList.getItemCount() == 0) {
                JOptionPane.showMessageDialog(
                        cover,
                        "No courses available.\nPlease add a course first.",
                        "No Courses",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            StringBuilder errors = new StringBuilder();
            if (!isValidInstructorName(instructorField.getText())) {
                errors.append("- Invalid Instructor Name\n");
            }
            if (!isValidQualification(qualField.getText())) {
                errors.append("- Invalid Qualification");
            }

            if (errors.length() > 0) {
                JOptionPane.showMessageDialog(cover, errors.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String name = instructorField.getText().trim();
            String qualification = qualField.getText().trim();
            CourseInstructor instructor = new CourseInstructor(name, qualification);
            system.assignInstructor(selectedCourse, instructor);
            JOptionPane.showMessageDialog(cover, "Course assigned to instructor successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        instructorField.setText("");
        qualField.setText("");
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
        JPanel titlePanel = createTitlePanel("Delete Course");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Fields
        JPanel fields = new JPanel(new GridLayout(1, 2));
        JLabel courseLabel = new JLabel("Course Code:");
        fields.add(courseLabel);
        JTextField courseField = new JTextField(10);
        fields.add(courseField);
        cover.add(fields);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        JButton delete = new JButton("Delete");
        back.setFocusable(false);
        delete.setFocusable(false);
        delete.addActionListener(e -> {
            if (system.deleteCourse(courseField.getText().trim())) {
                JOptionPane.showMessageDialog(cover, "Course deleted successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else
                JOptionPane.showMessageDialog(cover, "Course does not exist", "Error", JOptionPane.ERROR_MESSAGE);

        });
        back.addActionListener(e -> {
            courseField.setText("");
            card.show(mainPanel, "CourseManage");
        });
        buttonPanel.add(back);
        buttonPanel.add(delete);
        cover.add(buttonPanel, BorderLayout.SOUTH);

        return cover;
    }

    // VIEW ALL COURSES PANEL

    public JPanel createAllCoursesPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = createTitlePanel("Course List");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Table
        String[] columns = { "Code", "Title", "Credit Hours", "Instructor" };
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable courseTable = new JTable(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        courseTable.setRowSorter(sorter);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        loadCourseTableData(model);
        cover.add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        JButton update = new JButton("Update Table");
        update.setFocusable(false);
        back.setFocusable(false);
        update.addActionListener(e -> {
            loadCourseTableData(model);
        });
        back.addActionListener(e -> {
            card.show(mainPanel, "CourseManage");
        });
        buttonPanel.add(back);
        buttonPanel.add(update);
        cover.add(buttonPanel, BorderLayout.SOUTH);
        return cover;
    }

    // VIEW REPORT PANEL

    public JPanel createViewReportPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = createTitlePanel("View Report");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Option Buttons
        JPanel optionPanel = new JPanel();
        JButton optionStudent = new JButton("View by Student");
        optionStudent.setFocusable(false);
        optionStudent.addActionListener(e -> {
            card.show(mainPanel, "ViewReportStudent");
        });
        JButton optionCourse = new JButton("View by Course");
        optionCourse.setFocusable(false);
        optionCourse.addActionListener(e -> {
            refreshCourseCombo(courseList);
            card.show(mainPanel, "ViewReportCourse");
        });
        optionPanel.add(optionStudent);
        optionPanel.add(optionCourse);
        cover.add(optionPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.setFocusable(false);
        back.addActionListener(e -> {
            goBacktoMenu();
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
        JPanel titlePanel = createTitlePanel("View Report");
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
        JButton viewButton = new JButton("View Report");
        back.setFocusable(false);
        viewButton.setFocusable(false);
        viewButton.addActionListener(e -> {
            String id = idField.getText().trim();
            if (isValidID(id)) {
                Student s = (Student) system.getStudents().search(id);
                if (s != null) {
                    String report = system.createReportString(id);
                    JOptionPane.showMessageDialog(cover, report, "Report", JOptionPane.INFORMATION_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(cover, "Student not found", "Error", JOptionPane.ERROR_MESSAGE);
            } else
                JOptionPane.showMessageDialog(cover, "Please enter a Valid ID", "Error", JOptionPane.ERROR_MESSAGE);
            idField.setText("");
        });
        back.addActionListener(e -> {
            idField.setText("");
            card.show(mainPanel, "ViewReport");
        });
        buttonPanel.add(back);
        buttonPanel.add(viewButton);
        cover.add(buttonPanel, BorderLayout.SOUTH);

        return cover;
    }

    // VIEW REPORT COURSE PANEL

    public JPanel createViewReportCoursesPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = createTitlePanel("View Report by Course Code");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new BorderLayout(0, 20));

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Enter Course Code: ");
        JTextField courseCodeField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchLabel);
        searchPanel.add(courseCodeField);
        searchPanel.add(searchButton);

        centerPanel.add(searchPanel, BorderLayout.NORTH);

        // Table
        String[] columns = { "StudentID", "Name", "Obtained Marks", "GPA", "Grade" };
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable courseTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        cover.add(centerPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.setFocusable(false);
        back.addActionListener(e -> card.show(mainPanel, "ViewReport"));
        buttonPanel.add(back);
        cover.add(buttonPanel, BorderLayout.SOUTH);

        // Search action
        searchButton.setFocusable(false);
        searchButton.addActionListener(e -> {
            String code = courseCodeField.getText().trim();
            model.setRowCount(0); 
            if (!code.isEmpty()) {
                Course found = null;
                ArrayList<Course> courses = system.getCourses().getItems();
                for (Course c : courses) {
                    if (c.getCourseCode().equalsIgnoreCase(code)) {
                        found = c;
                        break;
                    }
                }
                if (found != null) {
                    loadCourseReportData(model, found);
                } else {
                    JOptionPane.showMessageDialog(cover, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return cover;
    }

    // GLOBAL STATISTICS PANEL

    public JPanel createGlobalStatsPanel() {
        JPanel cover = new JPanel(new BorderLayout());
        cover.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Panel
        JPanel titlePanel = createTitlePanel("Global Statistics");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Central Panel
        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        JLabel totalStudents = new JLabel("Total Students: " + Student.getTotalStudents());
        JLabel totalPassStudents = new JLabel("Passing Students: " + system.totalPassingStudents());
        JLabel totalFailStudents = new JLabel("Fail Students: " + system.totalFailStudents());
        JLabel passFailRatio = new JLabel("Percentage of Passing Students: " + system.passStudentPercentage() + "%");
        JLabel totalCourses = new JLabel("Total Courses: " + Course.getTotalCourses());
        JLabel[] labelArray = { totalStudents, totalPassStudents, totalFailStudents, passFailRatio, totalCourses };
        for (JLabel label : labelArray) {
            label.setHorizontalAlignment(SwingConstants.LEFT);
            centerPanel.add(label);
        }
        cover.add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        back.setFocusable(false);
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
        JPanel titlePanel = createTitlePanel("Record Result");
        cover.add(titlePanel, BorderLayout.NORTH);

        // Central Panel
        JPanel fields = new JPanel(new GridLayout(3, 2));
        JLabel idLabel = new JLabel("Enter Student ID: ");
        JTextField idField = new JTextField(10);
        fields.add(idLabel);
        fields.add(idField);
        courseList = new JComboBox<>();
        ArrayList<Course> courseArray = system.getCourses().getItems();
        for (Course course : courseArray) {
            courseList.addItem(course);
        }
        JLabel listLabel = new JLabel("Select course: ");
        fields.add(listLabel);
        fields.add(courseList);
        JLabel markLabel = new JLabel("Enter Obtained Marks:");
        fields.add(markLabel);
        JTextField markField = new JTextField(10);
        fields.add(markField);
        cover.add(fields, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton back = new JButton("Back");
        JButton addButton = new JButton("Add Entry");
        addButton.setFocusable(false);
        back.setFocusable(false);
        addButton.addActionListener(e -> {
            StringBuilder errors = new StringBuilder();
            if (!isValidID(idField.getText().trim())) {
                errors.append("- Invalid ID\n");
            }
            if (!isValidMarks(markField.getText().trim())) {
                errors.append("- Invalid marks\n");
            }

            if (errors.length() > 0) {
                JOptionPane.showMessageDialog(cover, errors.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Course selectedCourse = (Course) courseList.getSelectedItem();
            Student student = (Student) system.getStudents().search(idField.getText().trim());
            if (student == null) {
                JOptionPane.showMessageDialog(
                        cover,
                        "Student not found",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean alreadyExists = system.studentHasResultForCourse(idField.getText(), selectedCourse);
            if (alreadyExists) {
                JOptionPane.showMessageDialog(
                        cover,
                        "Marks for this course already exist",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            double marks = Double.parseDouble(markField.getText().trim());
            ResultEntry result = new ResultEntry(marks, selectedCourse);
            student.getTranscript().addResultEntry(result);
            system.saveData();
            JOptionPane.showMessageDialog(cover, "Result added successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            idField.setText("");
            markField.setText("");
        });
        back.addActionListener(e -> {
            idField.setText("");
            markField.setText("");
            goBacktoMenu();
        });
        buttonPanel.add(back);
        buttonPanel.add(addButton);
        cover.add(buttonPanel, BorderLayout.SOUTH);
        return cover;
    }

    // Helper Methods
    private void goBacktoMenu() {
        card.show(mainPanel, "Menu");
    }

    public boolean isNotEmpty(String text) {
        if (text.trim().isEmpty()) {
            return false;
        } else
            return true;
    }

    public boolean isOnlyText(String text) {
        if (text.matches("[a-zA-Z ]+")) {
            return true;
        } else
            return false;
    }

    public boolean isOnlyNum(String text) {
        if (text.matches("\\d+")) {
            return true;
        } else
            return false;
    }

    public boolean isAlphaNumeric(String text) {
        if (text.matches("[a-zA-Z0-9 ]+")) {
            return true;
        } else
            return false;
    }

    public boolean isValidName(String text) {
        return isNotEmpty(text) && isOnlyText(text);
    }

    public boolean isValidID(String text) {
        return isNotEmpty(text) && isOnlyNum(text);
    }

    public boolean isValidProgram(String text) {
        return isNotEmpty(text) && isAlphaNumeric(text);
    }

    public boolean isValidCourseCode(String text) {
        return isNotEmpty(text) && isAlphaNumeric(text);
    }

    public boolean isValidInstructorName(String text) {
        return isNotEmpty(text) && isOnlyText(text);
    }

    public boolean isValidQualification(String text) {
        return isNotEmpty(text) && isAlphaNumeric(text);
    }

    public boolean isValidMarks(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }

        try {
            double marks = Double.parseDouble(text.trim());
            return marks >= 0 && marks <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void loadStudentTableData(DefaultTableModel model) {
        model.setRowCount(0);
        ArrayList<Student> students = system.getStudents().getItems();
        for (Student student : students) {
            model.addRow(new Object[] {
                    student.getStudentID(),
                    student.getName(),
                    student.getProgram(),
                    student.getDepartment()
            });
        }

    }

    private void loadCourseTableData(DefaultTableModel model) {
        model.setRowCount(0);
        ArrayList<Course> courses = system.getCourses().getItems();
        for (Course course : courses) {
            model.addRow(new Object[] {
                    course.getCourseCode(),
                    course.getTitle(),
                    course.getCreditHours(),
                    course.getCourseInstructor().getName()
            });
        }

    }

    private void loadCourseReportData(DefaultTableModel model, Course selectedCourse) {
        model.setRowCount(0);

        ArrayList<Student> students = system.getStudents().getItems();

        for (Student student : students) {
            Transcript transcript = student.getTranscript();
            for (ResultEntry result : transcript.getResults()) {
                if (result.getCourse().getCourseCode().equals(selectedCourse.getCourseCode())) {
                    double marks = result.getMarksObtained();
                    double gpa = transcript.marksToGradePoints(marks);
                    String grade = student.calculateGrade();
                    model.addRow(new Object[] {
                            student.getStudentID(),
                            student.getName(),
                            marks,
                            gpa,
                            grade
                    });
                }
            }
        }
    }

    private JPanel createTitlePanel(String t) {
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JLabel title = new JLabel(t);
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        titlePanel.add(title);
        return titlePanel;
    }

    private void refreshCourseCombo(JComboBox<Course> box) {
        box.removeAllItems();
        ArrayList<Course> courses = system.getCourses().getItems();
        for (Course c : courses) {
            box.addItem(c);
        }
    }

    public static void main(String[] args) {
        SystemGUI systemGUI = new SystemGUI(new SystemManager());
        // SystemGUI systemGUI = new SystemGUI();
    }
}
