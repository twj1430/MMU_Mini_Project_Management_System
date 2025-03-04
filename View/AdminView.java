import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Vector;

//author: Tan Wei Chun , ONG Shi Yi , Soon 

class AdminView extends JFrame {
    private ArrayList<MiniProject> project_ArrayList = new ArrayList<MiniProject>();
    private ArrayList<Comment> CommentList = new ArrayList<Comment>();
    // Do not touch
    // ----------------------------------------------------------------------------------------
    private int bounds = 80;
    private Border borderLine = BorderFactory.createLineBorder(new Color(216, 216, 216));
    // private Border borderLine1 = BorderFactory.createLineBorder(Color.BLACK);
    // Create container
    private Container container = getContentPane();
    // ----------------------------------------------------------------------------------------

    // change page button
    private JButton registerViewBtn = new JButton("Register Account");
    // Soon
    // Main screen Panel side button
    private JButton viewProjectBtn = new JButton("View Project List");
    private JButton viewCommentedProjectBtn = new JButton("View Comments");
    private JButton manageProjectBtn = new JButton("Manage Project");
    private JButton addCommentViewBtn = new JButton("Add Comment");

    // Do not touch
    // ----------------------------------------------------------------------------------------
    // Create navigation, sidebar and main page JPanel
    private JPanel nav = new JPanel();
    private JPanel sidebar = new JPanel();
    private JPanel mainPG = new JPanel();
    private JLabel navTitle = new JLabel("");
    // ----------------------------------------------------------------------------------------
    private JLabel username = new JLabel("Username");
    private JLabel username1 = new JLabel("Username");
    private JLabel username2 = new JLabel("Username");
    private JTextField usernameText = new JTextField(30);
    private JTextField usernameText1 = new JTextField(30);
    private JTextField usernameText2 = new JTextField(30);

    private JLabel facultyLabel = new JLabel("Faculty");
    private JLabel facultyLabel1 = new JLabel("Faculty");
    private String facultyList[] = { "FCI", "FOE", "FCM", "FOM", "FAC" };
    private JComboBox facultyCB = new JComboBox<>(facultyList);
    private JComboBox facultyCB1 = new JComboBox<>(facultyList);

    private JLabel courseLabel = new JLabel("Course");
    private String courseList[] = { " " };
    private JComboBox<String> courseCB = new JComboBox<>(courseList);

    private DefaultComboBoxModel<String> FCICourse = new DefaultComboBoxModel(new String[] {
            "Software Engineering",
            "Game Development",
            "Data Science",
            "Cyber Security" });
    private DefaultComboBoxModel<String> FOECourse = new DefaultComboBoxModel(
            new String[] { "Intelligent Robotics",
                    "Electrical", "Electronics",
                    "Electronics majoring in Telecommunications",
                    "Electronics  majoring in Computer" });
    private DefaultComboBoxModel<String> FCMCourse = new DefaultComboBoxModel(new String[] {
            "Advertising Design", "Animation",
            "Interface Design", "Media Arts",
            "Virtual Reality", "Visual Effects" });
    private DefaultComboBoxModel<String> FOMCourse = new DefaultComboBoxModel(
            new String[] { "Accounting", "Financial Engineering",
                    "Finance", "Business Management", "Marketing",
                    "Analytical Economics", "Enterprise Management System" });
    private DefaultComboBoxModel<String> FACCourse = new DefaultComboBoxModel(
            new String[] { "Communication (Strategic Communication)" });

    // shiyi
    // JPanel for View
    private SpringLayout sprLayout = new SpringLayout();
    private JPanel registerViewPn = new JPanel();

    // shi yi
    // For register
    private String identityType;
    private JLabel regisLabel = new JLabel("Register Account");

    private JButton registerAdminButton = new JButton("Register");
    private JButton registerLecButton = new JButton("Register");
    private JButton registerStuButton = new JButton("Register");

    private JButton adminButton = new JButton("Admin");
    private JButton lecturerButton = new JButton("Lecturer");
    private JButton studentButton = new JButton("Student");

    private JButton resetButton = new JButton("Reset");
    private JButton resetButton1 = new JButton("Reset");
    private JButton resetButton2 = new JButton("Reset");

    // For register admin
    private JLabel adminRegisLabel = new JLabel("Register Account for Admin");
    private SpringLayout sprLayout1 = new SpringLayout();
    private JPanel adminRegisterPn = new JPanel();

    // For register lecturer
    private JLabel lecRegisLabel = new JLabel("Register Account for Lecturer");
    private SpringLayout sprLayout2 = new SpringLayout();
    private JPanel lecturerRegisterPn = new JPanel();

    // For register student
    private JLabel studentRegisLabel = new JLabel("Register Account for Student");
    private SpringLayout sprLayout3 = new SpringLayout();
    private JPanel studentRegisterPn = new JPanel();

    // add, edit and delete button
    private JButton addProjectBtn = new JButton("Add");
    private JButton deleteProjectBtn = new JButton("Delete");

    // Ze Feng
    // Project Label for add, edit and delete project
    private JLabel ProjectLecIDLB = new JLabel("Lecturer ID: ");
    private JLabel ProjectNameLB = new JLabel("Project Name: ");
    private JLabel ProjectDescripLB = new JLabel("Project Description: ");
    private JLabel ProjectFacLB = new JLabel("Faculty: ");
    private JLabel ProjectSpecLB = new JLabel("Specialization: ");
    private JLabel ProjectStuLB = new JLabel("Student ID: ");
    private JLabel ProjectStatusLB = new JLabel("Status: ");

    private JLabel deleteProjectIDLB = new JLabel("Project ID :");
    private JLabel deleteProjectLecIDLB = new JLabel("Lecturer ID: ");
    private JLabel deleteProjectNameLB = new JLabel("Project Name: ");
    private JLabel deleteProjectDescripLB = new JLabel("Project Description: ");
    private JLabel deleteProjectFacLB = new JLabel("Faculty: ");
    private JLabel deleteProjectSpecLB = new JLabel("Specialization: ");
    private JLabel deleteProjectStuLB = new JLabel("Student ID: ");
    private JLabel deleteProjectScoreLB = new JLabel("Score: ");
    private JLabel deleteProjectStatusLB = new JLabel("Status: ");

    private JComboBox deleteProjectIDCB = new JComboBox();
    private JTextField deleteProjectLecIDTF = new JTextField(15);
    private JTextField deleteProjectNameTF = new JTextField(15);
    private JTextField deleteProjectDescripTF = new JTextField(15);
    private JTextField deleteProjectFacLTF = new JTextField(15);
    private JTextField deleteProjectSpecTF = new JTextField(15);
    private JTextField deleteProjectStuTF = new JTextField(15);
    private JTextField deleteProjectScoreTF = new JTextField(15);
    private JTextField deleteProjectStatusTF = new JTextField(15);

    private JButton deleteProjectBtn2 = new JButton("Delete");

    private JLabel viewProjectIDLB = new JLabel("Project ID :");
    private JLabel viewProjectLecIDLB = new JLabel("Lecturer ID: ");
    private JLabel viewProjectNameLB = new JLabel("Project Name: ");
    private JLabel viewProjectDescripLB = new JLabel("Project Description: ");
    private JLabel viewProjectFacLB = new JLabel("Faculty: ");
    private JLabel viewProjectSpecLB = new JLabel("Specialization: ");
    private JLabel viewProjectStuLB = new JLabel("Student ID: ");
    private JLabel viewProjectScoreLB = new JLabel("Score: ");
    private JLabel viewProjectStatusLB = new JLabel("Status: ");

    private JComboBox viewProjectIDCB = new JComboBox();
    private JTextField viewProjectLecIDTF = new JTextField(15);
    private JTextField viewProjectNameTF = new JTextField(15);
    private JTextField viewProjectDescripTF = new JTextField(15);
    private JTextField viewProjectFacLTF = new JTextField(15);
    private JTextField viewProjectSpecTF = new JTextField(15);
    private JTextField viewProjectStuTF = new JTextField(15);
    private JTextField viewProjectScoreTF = new JTextField(15);
    private JTextField viewProjectStatusTF = new JTextField(15);

    // button for adding comments
    private JButton addCommentBtn = new JButton("Add");
    private JTextArea commentTA = new JTextArea(5, 25);
    private JScrollPane projectCommentScrollPane = new JScrollPane(commentTA);

    // Project textfield and button for add project
    private JTextField ProjectNameTF = new JTextField(15);
    private String lecturerIDName[] = { "-" };
    private JComboBox createLecIDCB = new JComboBox(lecturerIDName);
    private JTextArea ProjectDescripTA = new JTextArea();
    private JScrollPane projectDescriptiScrollPane = new JScrollPane(ProjectDescripTA);

    private String studentIDName[] = { "-" };
    private JComboBox createtStuCB = new JComboBox(studentIDName);
    private JButton createProjectBtn = new JButton("Create");

    private JComboBox ProjectFacCB = new JComboBox(facultyList);
    // create specialization combo box for add and edit project page
    private JComboBox ProjectSpecCB = new JComboBox();
    // create project status combo box for add and edit project page
    private String ProjectStatus[] = { "activated", "hidden" };
    private JComboBox ProjectStatusCB = new JComboBox(ProjectStatus);

    // Create Screen for project comments
    private JLabel projectCommentsLabel = new JLabel("Comments:");

    private DefaultTableModel projectCommentTablemodel = new DefaultTableModel();
    private JTable projectCommentTable = new JTable(projectCommentTablemodel);
    private JScrollPane projectCommentScrollPanel = new JScrollPane(projectCommentTable);

    private JPanel viewProjectCommentPanel = new JPanel();
    private JButton backFromCommentsButton = new JButton("Back");

    // Create JTable and add into ScrollPane
    private JTable jt;
    private DefaultTableModel model;
    private JScrollPane sp;

    private Icon icon = new ImageIcon("icon/logout.PNG");
    private JButton logoutBtn = new JButton("logout", icon);

    AdminView() {

        project_ArrayList = MiniProject.getMPFromFile();

        try {
            // get all the mini project information from txt file
            FileReader fileReader = new FileReader("comment.txt");
            String currentLine = "";
            BufferedReader br = new BufferedReader(fileReader);

            // if the txt paragraph line is not empty
            while ((currentLine = br.readLine()) != null) {
                // split all the "," and insert into array. Example: 1234,4321 => [1234],[4321]
                String[] lines = currentLine.split(",");
                CommentList.add(new Comment(lines[0], lines[1], lines[2]));
            }
            // close the FileReder and BufferedReader
            fileReader.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // add all the project id into the delete project combo box
        deleteProjectIDCB.addItem("");
        viewProjectIDCB.addItem("");
        for (int i = 0; i < project_ArrayList.size(); i++) {
            deleteProjectIDCB.addItem(project_ArrayList.get(i).getProjectID());
            viewProjectIDCB.addItem(project_ArrayList.get(i).getProjectID());
        }

        // Do not touch
        // ----------------------------------------------------------------------------------------
        setTitle("Mini-project Management System");
        setLayout(null);
        // set container layout as white background
        container.setBackground(Color.white);

        // setting the panel layout as null (compulsory to add it if you create more
        // JPanel)
        nav.setLayout(null);
        sidebar.setLayout(null);

        nav.setLayout(null);
        sidebar.setLayout(null);

        // Navigation (top navigation)
        nav.setBackground(Color.WHITE);
        nav.setBounds(200, 0, 1000, 80);
        // Panel border
        nav.setBorder(borderLine);

        logoutBtn.setBounds(900, 15, 50, 40);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nav.add(logoutBtn);
        nav.add(navTitle);

        // SideBar
        sidebar.setBackground(new Color(78, 115, 223));
        sidebar.setBounds(0, 0, 200, 700);
        // Panel border
        sidebar.setBorder(borderLine);

        // Main page
        mainPG.setBackground(Color.white);
        mainPG.setBounds(200, 80, 1000, 700);
        mainPG.setBorder(borderLine);

        // ----------------------------------------------------------------------------------------

        registerViewBtn.setBounds(0, bounds, 200, 80);
        registerViewBtn.setFocusPainted(false);
        registerViewBtn.setContentAreaFilled(false);
        registerViewBtn.setForeground(Color.white);
        registerViewBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        registerViewBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebar.add(registerViewBtn);
        bounds += 80;

        viewProjectBtn.setBounds(0, bounds, 200, 80);
        viewProjectBtn.setFocusPainted(false);
        viewProjectBtn.setContentAreaFilled(false);
        viewProjectBtn.setForeground(Color.white);
        viewProjectBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        viewProjectBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebar.add(viewProjectBtn);
        bounds += 80;

        viewCommentedProjectBtn.setBounds(0, bounds, 200, 80);
        viewCommentedProjectBtn.setFocusPainted(false);
        viewCommentedProjectBtn.setContentAreaFilled(false);
        viewCommentedProjectBtn.setForeground(Color.white);
        viewCommentedProjectBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        viewCommentedProjectBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebar.add(viewCommentedProjectBtn);
        bounds += 80;

        manageProjectBtn.setBounds(0, bounds, 200, 80);
        manageProjectBtn.setFocusPainted(false);
        manageProjectBtn.setContentAreaFilled(false);
        manageProjectBtn.setForeground(Color.white);
        manageProjectBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        manageProjectBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebar.add(manageProjectBtn);
        bounds += 80;

        addCommentViewBtn.setBounds(0, bounds, 200, 80);
        addCommentViewBtn.setFocusPainted(false);
        addCommentViewBtn.setContentAreaFilled(false);
        addCommentViewBtn.setForeground(Color.white);
        addCommentViewBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        addCommentViewBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebar.add(addCommentViewBtn);
        bounds += 80;

        // Do not touch
        // ----------------------------------------------------------------------------------------
        // adding the panel to the Container of the JFrame
        container.add(nav);
        container.add(sidebar);
        container.add(mainPG);

        // ----------------------------------------------------------------------------------------
        // Register Page
        registerViewPn.setLayout(null);
        registerViewPn.setBounds(200, 80, 1000, 700);
        registerViewPn.setBorder(borderLine);
        registerViewPn.setBackground(Color.white);

        regisLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        regisLabel.setBounds(450, 30, 200, 40);
        registerViewPn.add(regisLabel);

        registerViewPn.setLayout(sprLayout);
        adminButton.setPreferredSize(new Dimension(800, 80));
        lecturerButton.setPreferredSize(new Dimension(800, 80));
        studentButton.setPreferredSize(new Dimension(800, 80));
        sprLayout.putConstraint(SpringLayout.WEST, regisLabel, 380, SpringLayout.NORTH, registerViewPn);
        sprLayout.putConstraint(SpringLayout.NORTH, regisLabel, 50, SpringLayout.NORTH, registerViewPn); // height
        sprLayout.putConstraint(SpringLayout.WEST, adminButton, 80, SpringLayout.NORTH, registerViewPn);
        sprLayout.putConstraint(SpringLayout.NORTH, adminButton, 120, SpringLayout.NORTH, registerViewPn);
        sprLayout.putConstraint(SpringLayout.WEST, lecturerButton, 80, SpringLayout.NORTH, registerViewPn);
        sprLayout.putConstraint(SpringLayout.NORTH, lecturerButton, 220, SpringLayout.NORTH, registerViewPn);
        sprLayout.putConstraint(SpringLayout.WEST, studentButton, 80, SpringLayout.NORTH, registerViewPn);
        sprLayout.putConstraint(SpringLayout.NORTH, studentButton, 320, SpringLayout.NORTH, registerViewPn);

        registerViewPn.add(adminButton);
        registerViewPn.add(lecturerButton);
        registerViewPn.add(studentButton);

        // ----------------------------------------------------------------------------------------
        // Register admin Page

        adminRegisterPn.setLayout(null);
        adminRegisterPn.setBounds(200, 80, 1000, 700);
        adminRegisterPn.setBorder(borderLine);
        adminRegisterPn.setBackground(Color.white);

        adminRegisterPn.setLayout(sprLayout1);

        adminRegisLabel.setFont(new Font("Arial", Font.BOLD, 50));
        sprLayout1.putConstraint(SpringLayout.WEST, adminRegisLabel, 120, SpringLayout.NORTH, adminRegisterPn);
        sprLayout1.putConstraint(SpringLayout.NORTH, adminRegisLabel, 50, SpringLayout.NORTH, adminRegisterPn); // height

        sprLayout1.putConstraint(SpringLayout.WEST, username, 120, SpringLayout.NORTH, adminRegisterPn);
        sprLayout1.putConstraint(SpringLayout.NORTH, username, 200, SpringLayout.NORTH, adminRegisterPn);
        sprLayout1.putConstraint(SpringLayout.WEST, usernameText, 120, SpringLayout.NORTH, username);
        sprLayout1.putConstraint(SpringLayout.NORTH, usernameText, 200, SpringLayout.WEST, adminRegisterPn);

        sprLayout1.putConstraint(SpringLayout.WEST, resetButton, 120, SpringLayout.NORTH, username);
        sprLayout1.putConstraint(SpringLayout.NORTH, resetButton, 350, SpringLayout.WEST, adminRegisterPn);
        sprLayout1.putConstraint(SpringLayout.WEST, registerAdminButton, 120, SpringLayout.NORTH, resetButton);
        sprLayout1.putConstraint(SpringLayout.NORTH, registerAdminButton, 350, SpringLayout.NORTH, adminRegisterPn);

        adminRegisterPn.add(adminRegisLabel);
        adminRegisterPn.add(username);
        adminRegisterPn.add(usernameText);

        adminRegisterPn.add(resetButton);
        adminRegisterPn.add(registerAdminButton);

        // ----------------------------------------------------------------------------------------
        // Register Lecturer Page

        lecturerRegisterPn.setLayout(null);
        lecturerRegisterPn.setBounds(200, 80, 1000, 700);
        lecturerRegisterPn.setBackground(Color.white);
        lecturerRegisterPn.setLayout(sprLayout2);

        lecRegisLabel.setFont(new Font("Arial", Font.BOLD, 50));

        sprLayout2.putConstraint(SpringLayout.WEST, lecRegisLabel, 120, SpringLayout.NORTH, lecturerRegisterPn);
        sprLayout2.putConstraint(SpringLayout.NORTH, lecRegisLabel, 50, SpringLayout.NORTH, lecturerRegisterPn); // height
        sprLayout2.putConstraint(SpringLayout.WEST, username1, 120, SpringLayout.NORTH, lecturerRegisterPn);
        sprLayout2.putConstraint(SpringLayout.NORTH, username1, 150, SpringLayout.NORTH, lecturerRegisterPn);
        sprLayout2.putConstraint(SpringLayout.WEST, usernameText1, 120, SpringLayout.NORTH, username1);
        sprLayout2.putConstraint(SpringLayout.NORTH, usernameText1, 150, SpringLayout.WEST, lecturerRegisterPn);
        sprLayout2.putConstraint(SpringLayout.WEST, facultyLabel, 120, SpringLayout.NORTH, lecturerRegisterPn);
        sprLayout2.putConstraint(SpringLayout.NORTH, facultyLabel, 200, SpringLayout.NORTH, lecturerRegisterPn);
        sprLayout2.putConstraint(SpringLayout.WEST, facultyCB, 120, SpringLayout.NORTH, username1);
        sprLayout2.putConstraint(SpringLayout.NORTH, facultyCB, 200, SpringLayout.WEST, lecturerRegisterPn);
        sprLayout2.putConstraint(SpringLayout.WEST, resetButton1, 120, SpringLayout.NORTH, username1);
        sprLayout2.putConstraint(SpringLayout.NORTH, resetButton1, 350, SpringLayout.WEST, lecturerRegisterPn);
        sprLayout2.putConstraint(SpringLayout.WEST, registerLecButton, 120, SpringLayout.NORTH, resetButton1);
        sprLayout2.putConstraint(SpringLayout.NORTH, registerLecButton, 350, SpringLayout.NORTH, lecturerRegisterPn);

        lecturerRegisterPn.add(lecRegisLabel);
        lecturerRegisterPn.add(username1);
        lecturerRegisterPn.add(usernameText1);
        lecturerRegisterPn.add(facultyLabel);
        lecturerRegisterPn.add(facultyCB);
        lecturerRegisterPn.add(resetButton1);
        lecturerRegisterPn.add(registerLecButton);

        facultyCB.setSelectedIndex(0);

        // //
        // ----------------------------------------------------------------------------------------
        // // Register Student Page

        studentRegisterPn.setLayout(null);
        studentRegisterPn.setBounds(200, 80, 1000, 700);
        studentRegisterPn.setBorder(borderLine);
        // ----------------------------------------------------------------------------------------
        // You can change the background color that you want
        studentRegisterPn.setBackground(Color.white);
        // After create a JPanel, you can start to add something inside this JPanel
        // ----------------------------------------------------------------------------------------

        studentRegisterPn.setLayout(sprLayout3);

        studentRegisLabel.setFont(new Font("Arial", Font.BOLD, 50));

        /////////////////
        sprLayout3.putConstraint(SpringLayout.WEST, studentRegisLabel, 120, SpringLayout.NORTH, studentRegisterPn);
        sprLayout3.putConstraint(SpringLayout.NORTH, studentRegisLabel, 50, SpringLayout.NORTH, studentRegisterPn); // height
        //////////////

        sprLayout3.putConstraint(SpringLayout.WEST, username2, 120, SpringLayout.NORTH, studentRegisterPn);
        sprLayout3.putConstraint(SpringLayout.NORTH, username2, 150, SpringLayout.NORTH, studentRegisterPn);
        sprLayout3.putConstraint(SpringLayout.WEST, usernameText2, 120, SpringLayout.NORTH, username2);
        sprLayout3.putConstraint(SpringLayout.NORTH, usernameText2, 150, SpringLayout.WEST, studentRegisterPn);

        sprLayout3.putConstraint(SpringLayout.WEST, facultyLabel1, 120, SpringLayout.NORTH, studentRegisterPn);
        sprLayout3.putConstraint(SpringLayout.NORTH, facultyLabel1, 200, SpringLayout.NORTH, studentRegisterPn);
        sprLayout3.putConstraint(SpringLayout.WEST, facultyCB1, 120, SpringLayout.NORTH, username2);
        sprLayout3.putConstraint(SpringLayout.NORTH, facultyCB1, 200, SpringLayout.WEST, studentRegisterPn);

        sprLayout3.putConstraint(SpringLayout.WEST, courseLabel, 120, SpringLayout.NORTH, studentRegisterPn);
        sprLayout3.putConstraint(SpringLayout.NORTH, courseLabel, 250, SpringLayout.NORTH, studentRegisterPn);
        sprLayout3.putConstraint(SpringLayout.WEST, courseCB, 120, SpringLayout.NORTH, username2);
        sprLayout3.putConstraint(SpringLayout.NORTH, courseCB, 250, SpringLayout.WEST, studentRegisterPn);

        sprLayout3.putConstraint(SpringLayout.WEST, resetButton2, 120, SpringLayout.NORTH, username2);
        sprLayout3.putConstraint(SpringLayout.NORTH, resetButton2, 350, SpringLayout.WEST, studentRegisterPn);

        sprLayout3.putConstraint(SpringLayout.WEST, registerStuButton, 120, SpringLayout.NORTH, resetButton2);
        sprLayout3.putConstraint(SpringLayout.NORTH, registerStuButton, 350, SpringLayout.NORTH, studentRegisterPn);

        studentRegisterPn.add(studentRegisLabel);
        studentRegisterPn.add(username2);
        studentRegisterPn.add(usernameText2);
        studentRegisterPn.add(facultyLabel1);
        studentRegisterPn.add(facultyCB1);
        studentRegisterPn.add(courseLabel);
        studentRegisterPn.add(courseCB);
        studentRegisterPn.add(resetButton2);
        studentRegisterPn.add(registerStuButton);

        facultyCB1.setSelectedIndex(0);

        // ------------------------------------------------------------------------------

        this.setVisible(false);
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // ----------------------------------------------------------------------------------------
    }

    // this method is to show the manage project home page (able to select add, edit
    // or delete project)
    public void manageProject() {
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();

        JPanel manageProPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        manageProPanel.setLayout(new GridBagLayout());
        manageProPanel.setBounds(200, 100, 1000, 400);
        // createProjectPanel.setBorder(borderLine);
        manageProPanel.setBackground(Color.white);

        navTitle.setText("Manage Project");
        navTitle.setBounds(450, 20, 250, 50);
        navTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));

        // add project button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 20);
        addProjectBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        addProjectBtn.setPreferredSize(new Dimension(80, 50));
        addProjectBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        manageProPanel.add(addProjectBtn, gbc);

        // delete project button
        gbc.gridx = 1;
        gbc.gridy = 0;
        deleteProjectBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        deleteProjectBtn.setPreferredSize(new Dimension(80, 50));
        deleteProjectBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        manageProPanel.add(deleteProjectBtn, gbc);

        container.add(manageProPanel);
    }

    // this method is to add the mini project
    public void createProjectView() {
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();

        JPanel createProPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        createProPanel.setLayout(new GridBagLayout());
        createProPanel.setBounds(200, 100, 1000, 400);
        // createProjectPanel.setBorder(borderLine);
        createProPanel.setBackground(Color.white);

        navTitle.setText("Create new Project");
        navTitle.setBounds(450, 20, 250, 50);
        navTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));

        // project lecturer ID Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        createProPanel.add(ProjectLecIDLB, gbc);

        // project lecturer ID Text Field
        gbc.gridx = 1;
        gbc.gridy = 0;
        createProPanel.add(createLecIDCB, gbc);

        // projec tName Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        createProPanel.add(ProjectNameLB, gbc);

        // project Name Text Field
        gbc.gridx = 1;
        gbc.gridy = 1;
        createProPanel.add(ProjectNameTF, gbc);

        // project Description Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        createProPanel.add(ProjectDescripLB, gbc);

        // project Description Text Field
        gbc.gridx = 1;
        gbc.gridy = 2;
        createProPanel.add(projectDescriptiScrollPane, gbc);
        projectDescriptiScrollPane.setPreferredSize(new Dimension(300, 100));


        // project faculty Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        createProPanel.add(ProjectFacLB, gbc);

        // project faculty JCombo Box (faculty selection)
        gbc.gridx = 1;
        gbc.gridy = 3;
        createProPanel.add(ProjectFacCB, gbc);

        // project specialization Label
        gbc.gridx = 0;
        gbc.gridy = 4;
        createProPanel.add(ProjectSpecLB, gbc);

        // project specialization JCombo Box (specialization selection)
        gbc.gridx = 1;
        gbc.gridy = 4;
        createProPanel.add(ProjectSpecCB, gbc);

        // project Student ID Label
        gbc.gridx = 0;
        gbc.gridy = 5;
        createProPanel.add(ProjectStuLB, gbc);

        // project Student ID Text Field
        gbc.gridx = 1;
        gbc.gridy = 5;
        createProPanel.add(createtStuCB, gbc);

        // project Status Label
        gbc.gridx = 0;
        gbc.gridy = 6;
        createProPanel.add(ProjectStatusLB, gbc);

        // project Status JCombo Box (status selection)
        gbc.gridx = 1;
        gbc.gridy = 6;
        createProPanel.add(ProjectStatusCB, gbc);

        // add Project Button
        gbc.gridx = 1;
        gbc.gridy = 7;
        createProjectBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createProPanel.add(createProjectBtn, gbc);

        container.add(createProPanel);
    }

    // this method is to delete the mini project
    public void deleteProjectView() {
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();

        JPanel deleteProPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        deleteProPanel.setLayout(new GridBagLayout());
        deleteProPanel.setBounds(200, 100, 1000, 400);
        // createProjectPanel.setBorder(borderLine);
        deleteProPanel.setBackground(Color.white);

        navTitle.setText("Delete Project");
        navTitle.setBounds(450, 20, 250, 50);
        navTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));

        // project ID Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        deleteProPanel.add(deleteProjectIDLB, gbc);

        // project ID JCombo Box (project id selection)
        gbc.gridx = 1;
        gbc.gridy = 0;
        deleteProPanel.add(deleteProjectIDCB, gbc);

        // project lecturer ID Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        deleteProPanel.add(deleteProjectLecIDLB, gbc);

        // project lecturer ID Text Field
        gbc.gridx = 1;
        gbc.gridy = 1;
        deleteProPanel.add(deleteProjectLecIDTF, gbc);
        deleteProjectLecIDTF.setEditable(false);

        // project name Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        deleteProPanel.add(deleteProjectNameLB, gbc);

        // project lecturer ID Text Field
        gbc.gridx = 1;
        gbc.gridy = 2;
        deleteProPanel.add(deleteProjectNameTF, gbc);
        deleteProjectNameTF.setEditable(false);

        // project description Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        deleteProPanel.add(deleteProjectDescripLB, gbc);

        // project description Text Field
        gbc.gridx = 1;
        gbc.gridy = 3;
        deleteProPanel.add(deleteProjectDescripTF, gbc);
        deleteProjectDescripTF.setEditable(false);

        // project faculty Label
        gbc.gridx = 0;
        gbc.gridy = 4;
        deleteProPanel.add(deleteProjectFacLB, gbc);

        // project faculty Text Field
        gbc.gridx = 1;
        gbc.gridy = 4;
        deleteProPanel.add(deleteProjectFacLTF, gbc);
        deleteProjectFacLTF.setEditable(false);

        // project specialization Label
        gbc.gridx = 0;
        gbc.gridy = 5;
        deleteProPanel.add(deleteProjectSpecLB, gbc);

        // project specialization Text Field
        gbc.gridx = 1;
        gbc.gridy = 5;
        deleteProPanel.add(deleteProjectSpecTF, gbc);
        deleteProjectSpecTF.setEditable(false);

        // project student ID Label
        gbc.gridx = 0;
        gbc.gridy = 6;
        deleteProPanel.add(deleteProjectStuLB, gbc);

        // project student ID Text Field
        gbc.gridx = 1;
        gbc.gridy = 6;
        deleteProPanel.add(deleteProjectStuTF, gbc);
        deleteProjectStuTF.setEditable(false);

        // project score Label
        gbc.gridx = 0;
        gbc.gridy = 7;
        deleteProPanel.add(deleteProjectScoreLB, gbc);

        // project score Text Field
        gbc.gridx = 1;
        gbc.gridy = 7;
        deleteProPanel.add(deleteProjectScoreTF, gbc);
        deleteProjectScoreTF.setEditable(false);

        // project status Label
        gbc.gridx = 0;
        gbc.gridy = 8;
        deleteProPanel.add(deleteProjectStatusLB, gbc);

        // project status Text Field
        gbc.gridx = 1;
        gbc.gridy = 8;
        deleteProPanel.add(deleteProjectStatusTF, gbc);
        deleteProjectStatusTF.setEditable(false);

        // delete Project Button
        gbc.gridx = 1;
        gbc.gridy = 9;
        deleteProjectBtn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteProPanel.add(deleteProjectBtn2, gbc);

        container.add(deleteProPanel);
    }

    // this method is to show the project list page
    public void projectTableView() {
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        navTitle.setText("Project List");
        navTitle.setBounds(400, 20, 250, 50);
        navTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));

        // set all the mini project attributes in each column
        String column[] = { "Project ID", "Lecturer ID", "Student ID", "Content", "Description", "Faculty",
                "Specialization",
                "Score", "Status" };

        // set the Table not editable
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // add the attribute column into table
        model.setColumnIdentifiers(column);

        for (int i = 0; i < project_ArrayList.size(); i++) {
            Vector row = new Vector(8);

            row.add(project_ArrayList.get(i).getProjectID()); // add the id
            row.add(project_ArrayList.get(i).getLecturerID());
            row.add(project_ArrayList.get(i).getStudentID());
            row.add(project_ArrayList.get(i).getName());
            row.add(project_ArrayList.get(i).getDescription());
            row.add(project_ArrayList.get(i).getFaculty());
            row.add(project_ArrayList.get(i).getSpecialization());
            row.add(project_ArrayList.get(i).getScore());
            row.add(project_ArrayList.get(i).getStatus());
            model.addRow(row);
        }

        // model add into JTable
        jt = new JTable(model);

        // JTable add into JScrollPane
        sp = new JScrollPane(jt);
        sp.setBounds(200, 80, 1000, 600);

        container.add(sp);
    }

    public void projectTableCommentsView() {
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        navTitle.setText("Commented Project List");
        navTitle.setBounds(450, 20, 250, 50);
        navTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        jt = new JTable(model);

        // JTable add into JScrollPane
        sp = new JScrollPane(jt);
        sp.setBounds(200, 80, 1000, 600);

        container.add(sp);
    }

    // Method of add comment panel to container
    public void showViewProject(JScrollPane sp) {
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        container.add(sp);
    }

    // Soon
    // Show the project comment list
    public void showViewProjectComment(Vector projectComments) {
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        String column[] = { "" };
        // view project Detail Panel
        viewProjectCommentPanel = new JPanel();
        viewProjectCommentPanel.setLayout(null);
        viewProjectCommentPanel.setBounds(450, 80, 887, 700);
        viewProjectCommentPanel.setBorder(borderLine);
        viewProjectCommentPanel.setBackground(Color.white);

        // backFromCommentsButton
        backFromCommentsButton.setBounds(300, 500, 150, 30);
        viewProjectCommentPanel.add(backFromCommentsButton);

        // viewProjectCommentPanels
        projectCommentsLabel.setBounds(10, 90, 150, 30);
        viewProjectCommentPanel.add(projectCommentsLabel);

        // viewProjectCommentPanels
        DefaultTableModel projectCommentTablemodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        projectCommentTablemodel = getprojectCommentTablemodel();
        projectCommentTablemodel.setColumnIdentifiers(column);

        for (int i = 0; i < projectComments.size(); i++) {
            projectCommentTablemodel.setValueAt(projectComments.get(i), i, 0);
        }

        projectCommentTable = new JTable(projectCommentTablemodel);
        projectCommentTable.setBounds(30, 40, 200, 300);
        projectCommentScrollPanel = new JScrollPane(projectCommentTable);
        projectCommentScrollPanel.setBounds(160, 90, 500, 300);

        viewProjectCommentPanel.add(projectCommentScrollPanel);
        container.add(viewProjectCommentPanel);

    }

    // this method is to delete the mini project
    public void projectCommentsView() {
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();

        JPanel commentProPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbcProject = new GridBagConstraints();
        commentProPanel.setLayout(new GridBagLayout());
        commentProPanel.setBounds(200, 80, 1000, 600);
        navTitle.setText("Project Comments");
        navTitle.setBounds(450, 20, 250, 50);
        navTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));

        JPanel projectPanel = new JPanel();
        projectPanel.setLayout(new GridBagLayout());

        // project ID Label
        gbcProject.gridx = 0;
        gbcProject.gridy = 0;
        gbcProject.insets = new Insets(0, 0, 10, 0);
        projectPanel.add(viewProjectIDLB, gbcProject);

        // project ID JCombo Box (project id selection)
        gbcProject.gridx = 1;
        gbcProject.gridy = 0;
        projectPanel.add(viewProjectIDCB, gbcProject);

        // project lecturer ID Label
        gbcProject.gridx = 0;
        gbcProject.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        projectPanel.add(viewProjectLecIDLB, gbcProject);

        // project lecturer ID Text Field
        gbcProject.gridx = 1;
        gbcProject.gridy = 1;
        projectPanel.add(viewProjectLecIDTF, gbcProject);
        viewProjectLecIDTF.setEditable(false);

        // project name Label
        gbcProject.gridx = 0;
        gbcProject.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        projectPanel.add(viewProjectNameLB, gbcProject);

        // project lecturer ID Text Field
        gbcProject.gridx = 1;
        gbcProject.gridy = 2;
        projectPanel.add(viewProjectNameTF, gbcProject);
        viewProjectNameTF.setEditable(false);

        // project description Label
        gbcProject.gridx = 0;
        gbcProject.gridy = 3;
        projectPanel.add(viewProjectDescripLB, gbcProject);

        // project description Text Field
        gbcProject.gridx = 1;
        gbcProject.gridy = 3;
        projectPanel.add(viewProjectDescripTF, gbcProject);
        viewProjectDescripTF.setEditable(false);

        // project faculty Label
        gbcProject.gridx = 0;
        gbcProject.gridy = 4;
        projectPanel.add(viewProjectFacLB, gbcProject);

        // project faculty Text Field
        gbcProject.gridx = 1;
        gbcProject.gridy = 4;
        projectPanel.add(viewProjectFacLTF, gbcProject);
        viewProjectFacLTF.setEditable(false);

        // project specialization Label
        gbcProject.gridx = 0;
        gbcProject.gridy = 5;
        projectPanel.add(viewProjectSpecLB, gbcProject);

        // project specialization Text Field
        gbcProject.gridx = 1;
        gbcProject.gridy = 5;
        projectPanel.add(viewProjectSpecTF, gbcProject);
        viewProjectSpecTF.setEditable(false);

        // project student ID Label
        gbcProject.gridx = 0;
        gbcProject.gridy = 6;
        projectPanel.add(viewProjectStuLB, gbcProject);

        // project student ID Text Field
        gbcProject.gridx = 1;
        gbcProject.gridy = 6;
        projectPanel.add(viewProjectStuTF, gbcProject);
        viewProjectStuTF.setEditable(false);

        // project score Label
        gbcProject.gridx = 0;
        gbcProject.gridy = 7;
        projectPanel.add(viewProjectScoreLB, gbcProject);

        // project score Text Field
        gbcProject.gridx = 1;
        gbcProject.gridy = 7;
        projectPanel.add(viewProjectScoreTF, gbcProject);
        viewProjectScoreTF.setEditable(false);

        // project status Label
        gbcProject.gridx = 0;
        gbcProject.gridy = 8;
        projectPanel.add(viewProjectStatusLB, gbcProject);

        // project status Text Field
        gbcProject.gridx = 1;
        gbcProject.gridy = 8;
        projectPanel.add(viewProjectStatusTF, gbcProject);
        viewProjectStatusTF.setEditable(false);

        // set the Table column
        String column[] = { "Admin Comments" };

        // set Table not editable
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // set column into Table
        model.setColumnIdentifiers(column);

        // add all the project's comment in row
        for (int i = 0; i < project_ArrayList.size(); i++) {
            if (project_ArrayList.get(i).getProjectID().equals(getViewProjectIDCB())) {
                for (int m = 0; m < project_ArrayList.get(i).getCommentList().size(); m++) {
                    Vector row = new Vector(1);
                    row.add(project_ArrayList.get(i).getCommentList().get(m).getComment()); // add the id
                    model.addRow(row);
                    break;
                }
            }
        }

        // add model into JTable
        jt = new JTable(model);
        // add JTable into JScrollPane
        sp = new JScrollPane(jt);
        sp.setPreferredSize(new Dimension(300, 250));

        // projectPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 10);
        commentProPanel.add(projectPanel, gbc);

        // sp
        gbc.gridx = 1;
        gbc.gridy = 0;
        commentProPanel.add(sp, gbc);

        // comment text area
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        commentProPanel.add(projectCommentScrollPane, gbc);
        projectCommentScrollPane.setPreferredSize(new Dimension(300, 100));

        // add comment button
        gbc.gridx = 1;
        gbc.gridy = 2;
        commentProPanel.add(addCommentBtn, gbc);

        container.add(commentProPanel);
    }

    // Soon
    // this method will move to project detail from button of column

    public void doCommentButton(String ProjectID, String action, Vector commentList) {
        DefaultTableModel projectCommentTablemodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (int i = 0; i < commentList.size(); i++) {
            Vector row = new Vector(1);
            row.add(commentList.get(i));
            projectCommentTablemodel.addRow(row);
        }

        setprojectCommentTablemodel(projectCommentTablemodel);

    }

    // get the register type (admin,lecturer or student)
    public String getIdentityType() {
        return identityType;
    }

    // get the register account username
    public String getUsername() {
        return usernameText.getText();
    }

    // get the register account username
    public String getUsername1() {
        return usernameText1.getText();
    }

    // get the register account username
    public String getUsername2() {
        return usernameText2.getText();
    }

    // get the register faculty type from JCombo box
    public String getFaculty() {
        return facultyCB.getSelectedItem().toString();
    }

    // get the register faculty type from JCombo box
    public String getFaculty1() {
        return facultyCB1.getSelectedItem().toString();
    }

    // get the register course from JCombo box
    public String getCourse() {
        return courseCB.getSelectedItem().toString();
    }

    // get the create project name text field
    public String getProjectName() {
        return ProjectNameTF.getText();
    }

    // get the create project lecturer Jcombo
    public String getProjectLecturer() {
        if (createLecIDCB.getSelectedItem().toString().equals("-")) {
            return "-";
        } else {
            return createLecIDCB.getSelectedItem().toString().substring(0, 5);
        }

    }

    public String getProjectDes() {
        return ProjectDescripTA.getText();
    }

    public String getProjectFac() {
        return ProjectFacCB.getSelectedItem().toString();
    }

    public String getProjectSpec() {
        return ProjectSpecCB.getSelectedItem().toString();
    }

    public String getProjectStu() {
        if (createtStuCB.getSelectedItem().toString().equals("-")) {
            return "-";
        } else {
            return createtStuCB.getSelectedItem().toString().substring(0, 5);
        }
    }

    public String getProjectStatus() {
        return ProjectStatusCB.getSelectedItem().toString();
    }

    public String getDeleteProjectID() {
        return deleteProjectIDCB.getSelectedItem().toString();
    }

    public String getViewProjectIDCB() {
        return viewProjectIDCB.getSelectedItem().toString();
    }

    public String getComment() {
        return commentTA.getText();
    }

    public void showSubmitFail() {
        JOptionPane.showMessageDialog(null, "Please fill up all the text field",
                "Failed", JOptionPane.ERROR_MESSAGE);
    }

    public void showSubmitFail2() {
        JOptionPane.showMessageDialog(null, "Please select faculty and specialization!",
                "Failed", JOptionPane.ERROR_MESSAGE);
    }

    public void showSubmitFail3() {
        JOptionPane.showMessageDialog(null, "Please select lecturer!",
                "Failed", JOptionPane.ERROR_MESSAGE);
    }

    public void showCreateSuccess() {
        JOptionPane.showMessageDialog(null, "Succesfull!", "Message", JOptionPane.INFORMATION_MESSAGE);

        project_ArrayList.clear();
        try {
            FileReader fileReader = new FileReader("Mini_Project.txt");
            String currentLine = "";
            BufferedReader br = new BufferedReader(fileReader);

            while ((currentLine = br.readLine()) != null) {
                String[] lines = currentLine.split(",");
                project_ArrayList.add(new MiniProject(lines[0], lines[1], lines[2], lines[3], lines[4], lines[5],
                        lines[6], Float.parseFloat(lines[7]), lines[8]));
            }
            fileReader.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object[] projectIDList = new String[project_ArrayList.size() + 1];
        for (int i = 0; i < projectIDList.length; i++) {
            if (i == 0) {
                projectIDList[i] = "";
            } else {
                projectIDList[i] = project_ArrayList.get(i - 1).getProjectID();
            }
        }
        DefaultComboBoxModel<String> newProjectArray = new DefaultComboBoxModel(projectIDList);
        deleteProjectIDCB.setModel(newProjectArray);
        viewProjectIDCB.setModel(newProjectArray);
    }

    public void showDeleteSuccess() {
        JOptionPane.showMessageDialog(null, "Succesfull!", "Message", JOptionPane.INFORMATION_MESSAGE);

        project_ArrayList.clear();
        try {
            FileReader fileReader = new FileReader("Mini_Project.txt");
            String currentLine = "";
            BufferedReader br = new BufferedReader(fileReader);

            while ((currentLine = br.readLine()) != null) {
                String[] lines = currentLine.split(",");
                project_ArrayList.add(new MiniProject(lines[0], lines[1], lines[2], lines[3], lines[4], lines[5],
                        lines[6], Float.parseFloat(lines[7]), lines[8]));
            }
            fileReader.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object[] projectIDList = new String[project_ArrayList.size() + 1];
        for (int i = 0; i < projectIDList.length; i++) {
            if (i == 0) {
                projectIDList[i] = "";
            } else {
                projectIDList[i] = project_ArrayList.get(i - 1).getProjectID();
            }
        }
        DefaultComboBoxModel<String> newProjectArray = new DefaultComboBoxModel(projectIDList);
        deleteProjectIDCB.setModel(newProjectArray);
        viewProjectIDCB.setModel(newProjectArray);

        deleteProjectNameTF.setText("");

        deleteProjectDescripTF.setText("");

        deleteProjectLecIDTF.setText("");

        deleteProjectFacLTF.setText("");

        deleteProjectSpecTF.setText("");

        deleteProjectStuTF.setText("");

        deleteProjectScoreTF.setText("");

        deleteProjectStatusTF.setText("");

        // remove the last panel from container
        // container.remove(getComponentCount() + 1);
        // container.revalidate();
        // container.repaint();
        // add the new panel into container
    }

    public void showAddCommentSuccess() {


        JOptionPane.showMessageDialog(null, "Succesfull!", "Message", JOptionPane.INFORMATION_MESSAGE);

        String currentProject = getViewProjectIDCB();
        commentTA.setText("");
        project_ArrayList.clear();
        try {
            FileReader fileReader = new FileReader("Mini_Project.txt");
            String currentLine = "";
            BufferedReader br = new BufferedReader(fileReader);

            while ((currentLine = br.readLine()) != null) {
                String[] lines = currentLine.split(",");
                project_ArrayList.add(new MiniProject(lines[0], lines[1], lines[2], lines[3], lines[4], lines[5],
                        lines[6], Float.parseFloat(lines[7]), lines[8]));
            }
            fileReader.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object[] projectIDList = new String[project_ArrayList.size() + 1];
        for (int i = 0; i < projectIDList.length; i++) {
            if (i == 0) {
                projectIDList[i] = "";
            } else {
                projectIDList[i] = project_ArrayList.get(i - 1).getProjectID();
            }
        }
        DefaultComboBoxModel<String> newProjectArray = new DefaultComboBoxModel(projectIDList);
        deleteProjectIDCB.setModel(newProjectArray);
        viewProjectIDCB.setModel(newProjectArray);

        viewProjectIDCB.setSelectedItem(0);
        viewProjectLecIDTF.setText("");
        viewProjectNameTF.setText("");
        viewProjectDescripTF.setText("");
        viewProjectFacLTF.setText("");
        viewProjectSpecTF.setText("");
        viewProjectStuTF.setText("");
        viewProjectScoreTF.setText("");
        viewProjectStatusTF.setText("");
        model.setRowCount(0);
    }

    // clear the username
    public void setReset() {
        usernameText.setText(null);
        usernameText1.setText(null);
        usernameText2.setText(null);
        facultyCB.setSelectedIndex(0);
        facultyCB1.setSelectedIndex(0);
        courseCB.setModel(new DefaultComboBoxModel(
                new String[] { " " }));
    }

    public void changeRegisterView() {
        navTitle.setText("Register");
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        container.add(registerViewPn);
    }

    public void changeAdminRegView() {
        navTitle.setText("Register As Admin");
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        container.add(adminRegisterPn);
    }

    public void changeLecturerRegView() {
        navTitle.setText("Rgister As Lecturer");
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        container.add(lecturerRegisterPn);
    }

    public void changeStudentRegView() {
        navTitle.setText("Register As Student");
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        container.add(studentRegisterPn);
    }

    public void changeSpecialization() {
        if ("FCI".equals(getFaculty1())) {
            courseCB.setModel(FCICourse);
        } else if ("FOE".equals(getFaculty1())) {
            courseCB.setModel(FOECourse);
        } else if ("FCM".equals(getFaculty1())) {
            courseCB.setModel(FCMCourse);
        } else if ("FOM".equals(getFaculty1())) {
            courseCB.setModel(FOMCourse);
        } else if ("FAC".equals(getFaculty1())) {
            courseCB.setModel(FACCourse);
        }
    }

    public void changeProjectSpecialization() {
        if ("FCI".equals(getProjectFac())) {
            ProjectSpecCB.setModel(FCICourse);
        } else if ("FOE".equals(getProjectFac())) {
            ProjectSpecCB.setModel(FOECourse);
        } else if ("FCM".equals(getProjectFac())) {
            ProjectSpecCB.setModel(FCMCourse);
        } else if ("FOM".equals(getProjectFac())) {
            ProjectSpecCB.setModel(FOMCourse);
        } else if ("FAC".equals(getProjectFac())) {
            ProjectSpecCB.setModel(FACCourse);
        }
    }

    public void changeDeleteDetail(String id) {
        for (int i = 0; i < project_ArrayList.size(); i++) {
            if (project_ArrayList.get(i).getProjectID().equals(id)) {

                deleteProjectNameTF.setText(project_ArrayList.get(i).getName());

                deleteProjectDescripTF.setText(project_ArrayList.get(i).getDescription());

                deleteProjectLecIDTF.setText(project_ArrayList.get(i).getLecturerID());

                deleteProjectFacLTF.setText(project_ArrayList.get(i).getFaculty());

                deleteProjectSpecTF.setText(project_ArrayList.get(i).getSpecialization());

                deleteProjectStuTF.setText(project_ArrayList.get(i).getStudentID());

                deleteProjectScoreTF.setText(String.valueOf(project_ArrayList.get(i).getScore()));

                deleteProjectStatusTF.setText(project_ArrayList.get(i).getStatus());
            }
        }
    }

    public void changeViewDetail(String id) {
        model.setRowCount(0);
        for (int i = 0; i < project_ArrayList.size(); i++) {
            if (project_ArrayList.get(i).getProjectID().equals(id)) {

                viewProjectNameTF.setText(project_ArrayList.get(i).getName());

                viewProjectDescripTF.setText(project_ArrayList.get(i).getDescription());

                viewProjectLecIDTF.setText(project_ArrayList.get(i).getLecturerID());

                viewProjectFacLTF.setText(project_ArrayList.get(i).getFaculty());

                viewProjectSpecTF.setText(project_ArrayList.get(i).getSpecialization());

                viewProjectStuTF.setText(project_ArrayList.get(i).getStudentID());

                viewProjectScoreTF.setText(String.valueOf(project_ArrayList.get(i).getScore()));

                viewProjectStatusTF.setText(project_ArrayList.get(i).getStatus());
                model.setRowCount(0);
                for (int m = 0; m < project_ArrayList.get(i).getCommentList().size(); m++) {
                    Vector row = new Vector(1);
                    row.add(project_ArrayList.get(i).getCommentList().get(m).getComment()); // add the
                    // id
                    model.addRow(row);
                }

                jt = new JTable(model);
                sp = new JScrollPane(jt);
                break;
            }
        }
    }

    public void getLecListFromFile(String faculty) {

        try {
            File file = new File("lecturer.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<String>();
            String line;
            while ((line = br.readLine()) != "") {
                if (line == null)
                    break;
                else if (line.contains("," + faculty)) {
                    lines.add(line);
                }
            }
            // if (lines.isEmpty()) {
            // return;
            // }

            List<String> lecturerLine;
            lecturerIDName = new String[lines.size() + 1];
            lecturerIDName[0] = "-";
            for (int i = 1; i < lines.size() + 1; i++) {
                lecturerLine = Arrays.asList(lines.get(i - 1).split(","));
                lecturerIDName[i] = lecturerLine.get(0) + "-" + lecturerLine.get(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        createLecIDCB.removeAllItems();
        for (String s : lecturerIDName) {
            createLecIDCB.addItem(s);
        }
        createLecIDCB.revalidate();
        createLecIDCB.repaint();
    }

    public void getStudentListFromFile(String specialization) {

        try {
            File file = new File("student.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<String>();
            String line;
            while ((line = br.readLine()) != "") {
                if (line == null)
                    break;
                else if (line.contains("," + specialization)) {
                    lines.add(line);
                }
            }
            // if (lines.isEmpty()) {
            // return;
            // }
            List<String> studentLine;
            studentIDName = new String[lines.size() + 1];
            studentIDName[0] = "-";
            for (int i = 1; i < lines.size() + 1; i++) {
                studentLine = Arrays.asList(lines.get(i - 1).split(","));
                studentIDName[i] = studentLine.get(0) + "-" + studentLine.get(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        createtStuCB.removeAllItems();
        for (String s : studentIDName) {
            createtStuCB.addItem(s);
        }
        createtStuCB.revalidate();
        createtStuCB.repaint();
    }

    public void close() {
        this.setVisible(false);
    }

    public void display() {
        this.setVisible(true);
    }

    // register admin,lecturer or student account action
    public void registerViewListener(ActionListener view) {
        registerViewBtn.addActionListener(view);
    }

    // register admin,lecturer or student account action
    public void regAdminViewListener(ActionListener view) {
        adminButton.addActionListener(view);
    }

    // register admin,lecturer or student account action
    public void regLecViewListener(ActionListener view) {
        lecturerButton.addActionListener(view);
    }

    // register admin,lecturer or student account action
    public void regStuViewListener(ActionListener view) {
        studentButton.addActionListener(view);
    }

    // register admin,lecturer or student account action
    public void registerAdminListener(ActionListener register) {
        registerAdminButton.addActionListener(register);
    }

    // register admin,lecturer or student account action
    public void registerLecListener(ActionListener register) {
        registerLecButton.addActionListener(register);
    }

    // register admin,lecturer or student account action
    public void registerStuListener(ActionListener register) {
        registerStuButton.addActionListener(register);
    }

    // reset all the text field and combo box
    public void resetListener(ActionListener reset) {
        resetButton.addActionListener(reset);
    }

    // reset all the text field and combo box
    public void reset1Listener(ActionListener reset) {
        resetButton1.addActionListener(reset);
    }

    // reset all the text field and combo box
    public void reset2Listener(ActionListener reset) {
        resetButton2.addActionListener(reset);
    }

    public void manageProjectListener(ActionListener manage) {
        manageProjectBtn.addActionListener(manage);
    }

    // reset all the text field and combo box
    public void addProjectViewListener(ActionListener add) {
        addProjectBtn.addActionListener(add);
    }

    // reset all the text field and combo box
    public void deleteProjectViewListener(ActionListener add) {
        deleteProjectBtn.addActionListener(add);
    }

    // register admin,lecturer or student account action
    public void changeSpecializationListener(ActionListener change) {
        facultyCB1.addActionListener(change);
    }

    // register admin,lecturer or student account action
    public void changeProjectFacultyListener(ActionListener change) {
        ProjectFacCB.addActionListener(change);
    }

    public void changeStudentListener(ActionListener change) {
        ProjectSpecCB.addActionListener(change);
    }

    public void createProjectListener(ActionListener create) {
        createProjectBtn.addActionListener(create);
    }

    public void changeDeleteDetailListener(ActionListener change) {
        deleteProjectIDCB.addActionListener(change);
    }

    public void deleteProjectListener(ActionListener delete) {
        deleteProjectBtn2.addActionListener(delete);
    }

    public void addCommentViewListener(ActionListener change) {
        addCommentViewBtn.addActionListener(change);
    }

    public void changeViewProjectDetailListener(ActionListener change) {
        viewProjectIDCB.addActionListener(change);
    }

    public void addCommentListener(ActionListener add) {
        addCommentBtn.addActionListener(add);
    }

    public void closedViewListener(ActionListener close) {
        logoutBtn.addActionListener(close);
    }

    // Soon
    // For table model of comment
    public void setprojectCommentTablemodel(DefaultTableModel projectCommentTablemodel) {
        this.projectCommentTablemodel = projectCommentTablemodel;
    }

    public DefaultTableModel getprojectCommentTablemodel() {
        return projectCommentTablemodel;
    }

    public void addChangeScreenListener(ActionListener listener) {
        viewProjectBtn.addActionListener(listener);
        viewCommentedProjectBtn.addActionListener(listener);
        backFromCommentsButton.addActionListener(listener);
    }

    public void refreshTableView(JTable table, DefaultTableModel model) {
        this.model = model;
        this.jt = table;
        this.sp.setViewportView(table);
        sp.setBounds(200, 80, 1000, 600);
        jt.repaint();
        jt.revalidate();
        container.add(sp);
    }

    public static void main(String[] args) {
        new AdminView();
    }

}
