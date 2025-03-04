import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

//author: Tan Wei Chun

class StudentView extends JFrame {
    // MiniProject studentProject = new MiniProject();

    // Do not touch
    // ----------------------------------------------------------------------------------------
    private Border borderLine = BorderFactory.createLineBorder(new Color(216, 216, 216));
    // JPanel createProPanel = new JPanel();
    // Create container
    private Container container = getContentPane();

    private Icon icon = new ImageIcon("icon/logout.PNG");
    private JButton logoutBtn = new JButton("logout", icon);
    // ----------------------------------------------------------------------------------------

    // Do not touch
    // ----------------------------------------------------------------------------------------
    // Create navigation, sidebar and main page JPanel
    private JPanel nav = new JPanel();
    private JPanel sidebar = new JPanel();
    private JPanel mainPG = new JPanel();

    private JLabel lecturerLB = new JLabel("Lecturer :");
    private JLabel showLecturerLB = new JLabel("");
    private JLabel projectTitleLB = new JLabel("Title :");
    private JLabel showProjectTitleLB = new JLabel("");
    private JLabel projectDesLB = new JLabel("Description :");
    private JLabel showProjectDesLB = new JLabel("");
    private JLabel projectScoreLB = new JLabel("Score :");
    private JLabel showProjectScoreLB = new JLabel("");

    private JButton showViewProjectBtn = new JButton("View Project");

    private JPanel projectViewPanel = new JPanel();
    private JPanel projectPanel = new JPanel();
    private GridBagConstraints gbcProject = new GridBagConstraints();

    // Create ScrollPane
    private JScrollPane sp;

    /*
     * Tan Wei Chun
     * this constructor initializes the view for student
     */
    public StudentView() {
        setTitle("Mini-project Management System");
        setLayout(null);

        // Do not touch
        // ----------------------------------------------------------------------------------------
        int bounds = 80;
        // set container layout as white background
        container.setBackground(Color.white);

        // setting the panel layout as null (compulsory to add it if you create more
        // JPanel)
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

        // SideBar
        sidebar.setBackground(new Color(29, 140, 248));
        sidebar.setBounds(0, 0, 200, 700);
        // Panel border
        sidebar.setBorder(borderLine);

        // Main page
        mainPG.setBackground(Color.white);
        mainPG.setBounds(200, 80, 1000, 700);
        mainPG.setBorder(borderLine);

        showViewProjectBtn.setBounds(0, bounds, 200, 80);
        showViewProjectBtn.setFocusPainted(false);
        showViewProjectBtn.setContentAreaFilled(false);
        showViewProjectBtn.setForeground(Color.white);
        showViewProjectBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        showViewProjectBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebar.add(showViewProjectBtn);
        bounds += 80;

        // Do not touch
        // ----------------------------------------------------------------------------------------
        // adding the panel to the Container of the JFrame
        container.add(nav);
        container.add(sidebar);
        container.add(mainPG);
        // ------------------------------------------------------------------------------------------

        projectViewPanel.setLayout(null);
        projectViewPanel.setBounds(200, 50, 1000, 700);
        projectViewPanel.setBackground(Color.white);

        projectPanel.setLayout(new GridBagLayout());

        this.setVisible(false);
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // ----------------------------------------------------------------------------------------
    }

    /*
     * Tan Wei Chun
     * this method allows to add listener for showViewProjectBtn
     */
    public void ShowViewProjectListener(ActionListener listenForViewProject) {
        showViewProjectBtn.addActionListener(listenForViewProject);
    }

    /*
     * Tan Wei Chun
     * this method is to display the project title if student have project
     */
    public void setProjectTitleLB(String projectTitle) {
        showProjectTitleLB.setText(projectTitle);
    }

    /*
     * Tan Wei Chun
     * this method is to display the project description if student have project
     */
    public void setProjectDesLB(String projectDesription) {
        showProjectDesLB.setText(projectDesription);
    }

    /*
     * Tan Wei Chun
     * this method is to display the project score if student have project
     */
    public void setProjectScoreLB(String projectScore) {
        showProjectScoreLB.setText(projectScore + "/100");
    }

    /*
     * Tan Wei Chun
     * this method is to display the information if student dont have project
     */
    public void showProjectEmpty() {
        JLabel empty = new JLabel("You dont have any project right now");
        empty.setBounds(400, 100, 500, 300);
        projectViewPanel.add(empty);
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        container.add(projectViewPanel);
    }

    /*
     * Tan Wei Chun
     * this method is to display the project information with the comments if
     * student have project
     */
    public void showProjectDetail(String lecturer, String projectTitle, String projectDesription, String projectScore,
            JTable commentTable) {

        showLecturerLB.setText(lecturer);

        showProjectTitleLB.setText(projectTitle);

        showProjectDesLB.setText(projectDesription);

        showProjectScoreLB.setText(projectScore + "/100");

        gbcProject.gridx = 0;
        gbcProject.gridy = 0;
        gbcProject.insets = new Insets(10, 0, 10, 10);
        projectPanel.add(lecturerLB, gbcProject);

        gbcProject.gridx = 1;
        gbcProject.gridy = 0;
        gbcProject.insets = new Insets(10, 0, 10, 10);
        projectPanel.add(showLecturerLB, gbcProject);

        gbcProject.gridx = 0;
        gbcProject.gridy = 1;
        gbcProject.insets = new Insets(10, 0, 10, 10);
        projectPanel.add(projectTitleLB, gbcProject);

        gbcProject.gridx = 1;
        gbcProject.gridy = 1;
        gbcProject.insets = new Insets(10, 0, 10, 10);
        projectPanel.add(showProjectTitleLB, gbcProject);

        // studentIDTF
        gbcProject.gridx = 0;
        gbcProject.gridy = 2;
        gbcProject.insets = new Insets(0, 0, 10, 10);
        projectPanel.add(projectDesLB, gbcProject);

        // projectNameLabel
        gbcProject.gridx = 1;
        gbcProject.gridy = 2;
        gbcProject.insets = new Insets(0, 0, 10, 10);
        projectPanel.add(showProjectDesLB, gbcProject);

        // projectNameTF
        gbcProject.gridx = 0;
        gbcProject.gridy = 3;
        gbcProject.insets = new Insets(0, 0, 10, 10);
        projectPanel.add(projectScoreLB, gbcProject);

        // projectDescriptionLabel
        gbcProject.gridx = 1;
        gbcProject.gridy = 3;
        gbcProject.insets = new Insets(0, 0, 10, 10);
        projectPanel.add(showProjectScoreLB, gbcProject);
        projectPanel.setBounds(0, 0, 990, 300);
        // projectPanel.setBackground(Color.white);

        // add JTable into JScrollPane
        sp = new JScrollPane(commentTable);
        sp.setBounds(0, 305, 990, 320);
        // sp.setPreferredSize(new Dimension(500, 250));

        projectViewPanel.add(sp);

        projectViewPanel.add(projectPanel);

        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        container.add(projectViewPanel);
    }

    /*
     * Tan Wei Chun
     * this method allows to add listener for logoutBtn
     */
    public void closedViewListener(ActionListener close) {
        logoutBtn.addActionListener(close);
    }

    /*
     * Tan Wei Chun
     * this method is to close the whole system
     */
    public void close() {
        container.remove(getComponentCount() + 1);
        container.revalidate();
        container.repaint();
        container.add(mainPG);
        this.setVisible(false);
    }

    /*
     * Tan Wei Chun
     * this method is to display the whole system
     */
    public void display() {
        this.setVisible(true);
    }

    public static void main(String[] args) {
        // new StudentView();
    }
}