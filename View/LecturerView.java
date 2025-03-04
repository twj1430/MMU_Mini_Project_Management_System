import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

//author: Adam

public class LecturerView extends JFrame {

    private Icon icon = new ImageIcon("icon/logout.PNG");
    private JButton logoutBtn = new JButton("logout", icon);

    private Container container;

    private JPanel mainPgPanel = new JPanel();

    private JPanel sideBarPanel = new JPanel();
    private JButton createProjectButton = new JButton("Create Project");
    private JButton viewProjectButton = new JButton("View Project");

    private JPanel navPanel = new JPanel();
    private JLabel navTitle = new JLabel("");
    private JPanel createProjectPanel = new JPanel();
    private JPanel viewProjectPanel = new JPanel();
    private JPanel viewProjectDetailPanel = new JPanel();
    private JPanel editProjectPanel = new JPanel();

    private String studentIDName[] = { "-" };
    private JLabel studentIDLabel = new JLabel("Student ID:");
    private JComboBox studentIDNameCB = new JComboBox(studentIDName);

    private String studentIDName1[] = { "-" };
    private JLabel studentIDLabel1 = new JLabel("Student ID:");
    private JComboBox studentIDNameCB1 = new JComboBox(studentIDName);

    private JLabel projectNameLabel = new JLabel("Project Name:");
    private JTextField projectNameTF = new JTextField(15);

    private JLabel projectDescriptionLabel = new JLabel("Project Description:");
    private JTextArea projectDescriptionTA = new JTextArea();
    private JScrollPane projectDescriptiScrollPane = new JScrollPane(projectDescriptionTA);

    private String faculty[] = { "FCI" };
    private JLabel facultyLabel = new JLabel("Faculty:");
    private JComboBox facultyCB = new JComboBox(faculty);

    private String specialization[] = { "Data Science", "Software Engineering", "Game Development", "Cyber Security" };
    private JLabel specializationLabel = new JLabel("Specialization:");
    private JComboBox specializationCB = new JComboBox(specialization);

    private String status[] = { "hidden", "activated" };
    private JLabel statusLabel = new JLabel("Status:");
    private JComboBox statusCB = new JComboBox<>(status);

    private JLabel statusLabel1 = new JLabel("Status:");
    private JComboBox statusCB1 = new JComboBox<>(status);

    private JLabel scoreLabel = new JLabel("Score:");
    private JLabel scoreLabel1 = new JLabel();
    private JTextField scoreTextField = new JTextField();

    private JButton submitProjectButton = new JButton("Submit");
    private JButton backFromDetailsButton = new JButton("Back");
    private JButton backFromEditButton = new JButton("Save");

    private String thisProjectID;
    private JOptionPane jOptionPane = new JOptionPane();

    private JLabel projectDescriptionLabel1 = new JLabel("Project Description:");
    private JTextArea projectDescriptionTA1 = new JTextArea();
    private JScrollPane projectDescriptiScrollPane1 = new JScrollPane(projectDescriptionTA1);
    private String projectDescription;

    private DefaultTableModel MPTableModel = new DefaultTableModel(
            new Object[] { "ID", "Content", "Faculty", "Specialization", "Student ID & Name", "Score", "Status",
                    "Detail", "Edit" },
            0);
    private JTable MPJTable = new JTable();
    private JScrollPane MPTableScrollPane = new JScrollPane(MPJTable);

    // Dimension 1200 x780

    // author: Adam
    // this constructor initializes the view for Lecturer
    public LecturerView() {

        navTitle.setText("Create Project");
        navTitle.setBounds(400, 20, 250, 50);
        navTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));

        Border borderLine = BorderFactory.createLineBorder(new Color(216, 216, 216));

        container = getContentPane();

        setTitle("Mini-project Management System");
        setLayout(null);

        container.setBackground(Color.white);
        // initialize the data

        navPanel.setLayout(null);
        sideBarPanel.setLayout(null);

        // navigation
        navPanel.setBackground(Color.white);
        navPanel.setBounds(200, 0, 1000, 80);
        navPanel.setBorder(borderLine);

        // sidebar
        sideBarPanel.setBackground(new Color(78, 115, 223));
        sideBarPanel.setBounds(0, 0, 200, 700);
        navPanel.setBorder(borderLine);

        logoutBtn.setBounds(900, 15, 50, 40);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navPanel.add(logoutBtn);
        navPanel.add(navTitle);

        // main page
        mainPgPanel.setBackground(Color.yellow);
        mainPgPanel.setBounds(200, 80, 1000, 700);
        mainPgPanel.setBorder(borderLine);

        // sidebar create project button

        createProjectButton.setBounds(0, 80, 200, 80);
        createProjectButton.setFocusPainted(false);
        createProjectButton.setContentAreaFilled(false);
        createProjectButton.setForeground(Color.white);
        createProjectButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        createProjectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sideBarPanel.add(createProjectButton);

        // sidebar view project button
        viewProjectButton.setBounds(0, 160, 200, 80);
        viewProjectButton.setFocusPainted(false);
        viewProjectButton.setContentAreaFilled(false);
        viewProjectButton.setForeground(Color.white);
        viewProjectButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        viewProjectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sideBarPanel.add(viewProjectButton);

        // add the panel to container
        container.add(navPanel);
        container.add(sideBarPanel);
        // container.add(mainPgPanel);

        // create project panel
        // GridBagConstraints gbc = new GridBagConstraints();
        createProjectPanel.setLayout(null);
        createProjectPanel.setBounds(200, 80, 1000, 700);
        createProjectPanel.setBorder(borderLine);
        createProjectPanel.setBackground(Color.white);

        // facultyLabel
        facultyLabel.setBounds(10, 10, 100, 30);
        createProjectPanel.add(facultyLabel);

        // facultyCB
        facultyCB.setBounds(110, 10, 100, 30);
        createProjectPanel.add(facultyCB);

        // specializationLabel

        specializationLabel.setBounds(250, 10, 70, 30);
        createProjectPanel.add(specializationLabel);

        // specializationCB;
        specializationCB.setBounds(320, 10, 150, 30);
        createProjectPanel.add(specializationCB);

        // studentIDLabel

        studentIDLabel.setBounds(500, 10, 100, 30);
        createProjectPanel.add(studentIDLabel);

        // studentIDNameCB

        studentIDNameCB.setBounds(600, 10, 250, 30);
        createProjectPanel.add(studentIDNameCB);

        // projectNameLabel

        projectNameLabel.setBounds(10, 50, 100, 30);
        createProjectPanel.add(projectNameLabel);

        // projectNameTF

        projectNameTF.setBounds(110, 50, 800, 30);
        createProjectPanel.add(projectNameTF);

        // projectDescriptionLabel

        projectDescriptionLabel.setBounds(10, 90, 150, 30);
        createProjectPanel.add(projectDescriptionLabel);

        // projectDescriptionTA
        projectDescriptiScrollPane.setBounds(160, 90, 800, 300);

        createProjectPanel.add(projectDescriptiScrollPane);

        // statusLabel

        statusLabel.setBounds(10, 400, 150, 30);
        createProjectPanel.add(statusLabel);

        // statusCB

        statusCB.setBounds(160, 400, 150, 30);
        createProjectPanel.add(statusCB);

        // submitProjectButton

        submitProjectButton.setBounds(450, 500, 150, 30);
        createProjectPanel.add(submitProjectButton);

        container.add(createProjectPanel);

        // view project panel
        viewProjectPanel.setLayout(null);
        viewProjectPanel.setBounds(200, 80, 1000, 700);
        viewProjectPanel.setBorder(borderLine);
        viewProjectPanel.setBackground(Color.white);

        // MPTableScrollPane
        MPJTable.setModel(MPTableModel);
        MPTableScrollPane.setBounds(10, 10, 900, 600);
        viewProjectPanel.add(MPTableScrollPane);

        // view project Detail Panel
        viewProjectDetailPanel.setLayout(null);
        viewProjectDetailPanel.setBounds(200, 80, 1000, 700);
        viewProjectDetailPanel.setBorder(borderLine);
        viewProjectDetailPanel.setBackground(Color.white);

        // backFromDetailsButton
        backFromDetailsButton.setBounds(450, 500, 150, 30);
        viewProjectDetailPanel.add(backFromDetailsButton);

        // viewprojectDetails DescriptionLabel
        projectDescriptionLabel1.setBounds(10, 90, 150, 30);
        viewProjectDetailPanel.add(projectDescriptionLabel1);

        // viewprojectDetails DescriptionTextArea
        projectDescriptionTA1.setEditable(false);
        projectDescriptiScrollPane1.setBounds(160, 90, 800, 300);
        viewProjectDetailPanel.add(projectDescriptiScrollPane1);

        // view editProjectPanel
        editProjectPanel.setLayout(null);
        editProjectPanel.setBounds(200, 80, 1000, 700);
        editProjectPanel.setBorder(borderLine);
        editProjectPanel.setBackground(Color.white);

        // studentIDLabel1

        studentIDLabel1.setBounds(500, 10, 100, 30);
        editProjectPanel.add(studentIDLabel1);

        // studentIDNameCB1

        studentIDNameCB1.setBounds(600, 10, 250, 30);
        editProjectPanel.add(studentIDNameCB1);

        // statusLabel1
        statusLabel1.setBounds(10, 10, 150, 30);
        editProjectPanel.add(statusLabel1);

        // statusCB1
        statusCB1.setBounds(160, 10, 150, 30);
        editProjectPanel.add(statusCB1);

        // scoreLabl
        scoreLabel.setBounds(10, 50, 150, 30);
        editProjectPanel.add(scoreLabel);

        // scoreLabel2
        scoreLabel1.setBounds(320, 50, 250, 30);
        editProjectPanel.add(scoreLabel1);

        // scoreTextField
        scoreTextField.setBounds(160, 50, 150, 30);
        scoreTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = scoreTextField.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '.'
                        || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    scoreTextField.setEditable(true);
                    scoreLabel1.setText("");
                } else {
                    scoreTextField.setEditable(false);
                    scoreLabel1.setText("* Enter only numeric digits(0-9)and");
                }
            }
        });
        editProjectPanel.add(scoreTextField);

        // backFromDetailsButton
        backFromEditButton.setBounds(450, 500, 150, 30);
        editProjectPanel.add(backFromEditButton);

        this.setVisible(false);
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    /*
     * Adam
     * this method return the selection of student from create project
     */
    public String getStudentID() {
        if (studentIDNameCB.getSelectedItem().toString() == "-")
            return "unassigned";
        else
            return studentIDNameCB.getSelectedItem().toString().substring(0, 5);
    }

    /*
     * Adam
     * this method return project name from create project
     */
    public String getProjectName() {
        if (projectNameTF.getText().length() == 0)
            return null;
        else
            return projectNameTF.getText();
    }

    /*
     * Adam
     * this method return project description from create project
     */
    public String getProjectDescription() {
        if (projectDescriptionTA.getText().length() == 0)
            return null;
        else
            return projectDescriptionTA.getText();
    }

    /*
     * Adam
     * this method return student ID from edit project
     */
    public String getStudentID1() {
        if (!studentIDNameCB1.getSelectedItem().toString().equals("-")) {
            return studentIDNameCB1.getSelectedItem().toString().substring(0, 5);
        } else {
            return studentIDNameCB1.getSelectedItem().toString();
        }

    }

    /*
     * Adam
     * this method return score from create project
     */
    public Float getScore() {
        if (scoreTextField.getText().equals(""))
            return 0.0f;
        else
            return Float.parseFloat(scoreTextField.getText());
    }

    /*
     * Adam
     * this method return specialization from create project
     */
    public String getSpecialization() {
        return specializationCB.getSelectedItem().toString();
    }

    /*
     * Adam
     * this method return project's faculty from create project
     */
    public String getFaculty() {
        return facultyCB.getSelectedItem().toString();
    }

    /*
     * Adam
     * this method return project's status from create project
     */
    public String getStatus() {
        return statusCB.getSelectedItem().toString();
    }

    /*
     * Adam
     * this method return project's status from edit project
     */
    public String getStatus1() {
        return statusCB1.getSelectedItem().toString();
    }

    /*
     * Adam
     * this method return project's ID of specific row from view projects
     */
    public String getThisProjectID() {
        return this.thisProjectID;
    }

    /*
     * Adam
     * setter fro projectDescription
     */
    public void setProjectDescription(String description) {
        this.projectDescription = description;
    }

    /*
     * Adam
     * setter for projectID
     */
    public void setThisProjectID(String thisProjectID) {
        this.thisProjectID = thisProjectID;
    }

    /*
     * Adam
     * this method allows to add listener for submitProjectButton
     */
    public void addSubmitListener(ActionListener listenForSubmit) {
        submitProjectButton.addActionListener(listenForSubmit);
    }

    /*
     * Adam
     * this method allows to add listener for createProjectButton
     */
    public void addCreateProjectListener(ActionListener listenForCreateProject) {
        createProjectButton.addActionListener(listenForCreateProject);
    }

    /*
     * Adam
     * this method allows to add listener for viewProjectButton
     */
    public void addViewProjectListener(ActionListener listenForViewProject) {
        viewProjectButton.addActionListener(listenForViewProject);
    }

    /*
     * Adam
     * this method allows to add listener for specialization combo box
     */
    public void addSpecializationCBListener(ActionListener listenForSpecializationCB) {
        specializationCB.addActionListener(listenForSpecializationCB);
    }

    /*
     * Adam
     * this method allows to add listener for backFromDetailButton
     */
    public void addBackFromProjectDetailsListener(ActionListener listenForBackFromDetail) {
        backFromDetailsButton.addActionListener(listenForBackFromDetail);
    }

    /*
     * Adam
     * this method allows to add listener for backFromEditProjectButton
     */
    public void addBackFromProjectEditListener(ActionListener listenForBackFromEdit) {
        backFromEditButton.addActionListener(listenForBackFromEdit);
    }

    /*
     * Adam
     * this method show message dialog if project is successfully created
     */
    public void showSubmitSuccess() {
        jOptionPane.showMessageDialog(createProjectPanel, "Succesfully Submitted.", "Message",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * Adam
     * this method show message dialog if create project name or description is
     * empty
     */
    public void showSubmitError() {
        jOptionPane.showMessageDialog(createProjectPanel, "Project name and  description shouldn't be empty .",
                "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * Adam
     * this method add createProjectPanel to container
     */
    public void showCreateProject() {
        navTitle.setText("Create Project");
        navTitle.setBounds(400, 20, 250, 50);
        navTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        projectDescriptionTA.setText(null);
        projectNameTF.setText(null);

        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        container.add(createProjectPanel);
    }

    /*
     * Adam
     * this method add viewProjectPanel to container
     */
    public void showViewProject() {
        navTitle.setText("View Project");
        navTitle.setBounds(400, 20, 250, 50);
        navTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));

        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        container.add(viewProjectPanel);

    }

    /*
     * Adam
     * this method add viewProjectPanel to container
     */
    public void showViewProjectDetails() {
        navTitle.setText("View Project Detail");
        navTitle.setBounds(400, 20, 250, 50);
        navTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        projectDescriptionTA1.setText(this.projectDescription);
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        container.add(viewProjectDetailPanel);
    }

    /*
     * Adam
     * this method add editProjectPanel to container
     */
    public void showEditProjectDetails() {
        navTitle.setText("Edit Project Detail");
        navTitle.setBounds(400, 20, 250, 50);
        navTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        container.add(editProjectPanel);
    }

    /*
     * Adam
     * this method change the MPTable and MPTableMOdel and refresh the table
     */
    public void refreshTableView1(JTable table, DefaultTableModel model) {
        MPTableModel = model;
        MPJTable = table;
        MPTableScrollPane.setViewportView(table);
        MPJTable.repaint();
        MPJTable.revalidate();
    }

    /*
     * Adam
     * this method redirect the button from view mini project table
     */
    public void doTableButton(String MPID, String action) {
        if (action == "Details") {
            showViewProjectDetails();
        } else {
            showEditProjectDetails();
        }
    }

    /*
     * Adam
     * this method get student list for specific specialization from file and return
     * in format "studentID-studentName"
     */
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
            if (lines.isEmpty()) {
                return;
            }
            List<String> studentLine;
            studentIDName = new String[lines.size() + 1];
            studentIDName[0] = "-";
            studentIDName1 = new String[lines.size() + 1];
            studentIDName1[0] = "-";
            for (int i = 1; i < lines.size() + 1; i++) {
                studentLine = Arrays.asList(lines.get(i - 1).split(","));
                studentIDName[i] = studentLine.get(0) + "-" + studentLine.get(1);
                studentIDName1[i] = studentLine.get(0) + "-" + studentLine.get(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        studentIDNameCB.removeAllItems();
        studentIDNameCB1.removeAllItems();
        for (String s : studentIDName) {
            studentIDNameCB.addItem(s);
            studentIDNameCB1.addItem(s);
        }
        studentIDNameCB.revalidate();
        studentIDNameCB.repaint();
        studentIDNameCB1.revalidate();
        studentIDNameCB1.repaint();
    }

    public void display() {
        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
    }

    public void closedViewListener(ActionListener close) {
        logoutBtn.addActionListener(close);
    }

    public static void main(String[] args) {
        // new CreateProjectView();
    }
}