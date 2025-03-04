
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/*
 * Tan Wei Chun, Ong Shi Yi, Soon Tee Herr, Lim Ze Feng
 * This is controller class for admin view
 */
public class AdminController {
    private Admin admin;
    private AdminView adminView;
    private ArrayList<MiniProject> project_ArrayList = MiniProject.getMPFromFile();
    private ArrayList<Comment> commentList = Comment.getCMFromFile();

    /*
     * Tan Wei Chun, Ong Shi Yi, Soon Tee Herr, Lim Ze Feng
     * this constructor initializes the controller with admin and admin view
     */
    AdminController(Admin admin, AdminView adminView) {
        this.admin = admin;
        this.adminView = adminView;
        this.adminView.display();

        // Change View
        this.adminView.registerViewListener(new registerViewListener());
        this.adminView.regAdminViewListener(new regAdminViewListener());
        this.adminView.regLecViewListener(new regLecturerViewListener());
        this.adminView.regStuViewListener(new regStudentViewListener());
        this.adminView.manageProjectListener(new manageProjectListener());
        this.adminView.addProjectViewListener(new addProjectViewListener());
        this.adminView.deleteProjectViewListener(new deleteProjectViewListener());
        this.adminView.addCommentViewListener(new addCommentViewListener());
        this.adminView.changeViewProjectDetailListener(new changeViewProjectDetailListener());

        // Register account
        this.adminView.registerAdminListener(new registerAdminListener());
        this.adminView.registerLecListener(new registerLecListener());
        this.adminView.registerStuListener(new registerStuListener());

        // Create and Delete Project
        this.adminView.createProjectListener(new createProjectListener());
        this.adminView.changeDeleteDetailListener(new changeDeleteDetailListener());
        this.adminView.deleteProjectListener(new deleteProjectListener());

        // Change Faculty and Specialization
        this.adminView.changeSpecializationListener(new changeSpecializationListener());
        this.adminView.changeProjectFacultyListener(new changeProjectSpecializationListener());
        this.adminView.changeStudentListener(new changeStudentListener());

        // Reset the text field
        this.adminView.resetListener(new resetListener());
        this.adminView.reset1Listener(new resetListener());
        this.adminView.reset2Listener(new resetListener());

        // add comment
        this.adminView.addCommentListener(new addCommentListener());

        // Soon change screen Listener
        this.adminView.addChangeScreenListener(new ChangeScreenListener());

    }

    /*
     * Tan Wei Chun
     * this is Listener class for logout button
     */
    private class closeViewListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            adminView.close();
        }
    }

    /*
     * Ong Shi Yi
     * this is Listener class for register View button
     */
    private class registerViewListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            adminView.changeRegisterView();
        }
    }

    /*
     * Ong Shi Yi
     * this is Listener class for register admin button
     */
    private class registerAdminListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            // if username format is wrong
            if (checkString(adminView.getUsername()) == false) {

                JOptionPane.showMessageDialog(null, "Please Try Again", "Message",
                        JOptionPane.ERROR_MESSAGE);
                // if username is empty
            } else if (adminView.getUsername().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Field name cannot be blank", "Message",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                createAdmin(adminView.getUsername());
            }
        }
    }

    /*
     * Ong Shi Yi
     * this is Listener class for register lecturer button
     */
    private class registerLecListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            // if username format is wrong
            if (checkString(adminView.getUsername1()) == false) {
                JOptionPane.showMessageDialog(null, "Please Try Again", "Message",
                        JOptionPane.ERROR_MESSAGE);
                // if username is empty
            } else if (adminView.getUsername1().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Field name cannot be blank", "Message",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                createLecturer(adminView.getUsername1(), adminView.getFaculty());
            }

        }
    }

    /*
     * Ong Shi Yi
     * this is Listener class for register student button
     */
    private class registerStuListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            // if username format is wrong
            if (checkString(adminView.getUsername2()) == false) {
                JOptionPane.showMessageDialog(null, "Please Try Again", "Message",
                        JOptionPane.ERROR_MESSAGE);
                // if username is empty
            } else if (adminView.getUsername2().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Field name cannot be blank", "Message",
                        JOptionPane.ERROR_MESSAGE);
            } else if (adminView.getCourse().equals(" ")) {
                JOptionPane.showMessageDialog(null, "You have to select the specialization", "Message",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                createStudent(adminView.getUsername2(), adminView.getFaculty1(), adminView.getCourse());
            }

        }
    }

    /*
     * Ong Shi Yi
     * this is Listener class for admin button to display the admin registration
     * view
     */
    private class regAdminViewListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            adminView.changeAdminRegView();
        }
    }

    /*
     * Ong Shi Yi
     * this is Listener class for admin button to display the lecturer registration
     * view
     */
    private class regLecturerViewListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            adminView.changeLecturerRegView();
        }
    }

    /*
     * Ong Shi Yi
     * this is Listener class for admin button to display the student registration
     * view
     */
    private class regStudentViewListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            adminView.changeStudentRegView();
        }
    }

    /*
     * Ong Shi Yi
     * this is Listener class for resetButton button to reset the registration form
     */
    private class resetListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            adminView.setReset();
        }
    }

    /*
     * Ong Shi Yi
     * this is Listener class for faculty combo box button to change the
     * specialization selection when doing the registration
     */
    private class changeSpecializationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            adminView.changeSpecialization();
        }
    }

    /*
     * Lim Ze Feng
     * this is Listener class for project faculty combo box button to change the
     * specialization selection when doing the project creation
     * 
     */
    private class changeProjectSpecializationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            adminView.getLecListFromFile(adminView.getProjectFac());
            adminView.changeProjectSpecialization();
        }
    }

    /*
     * Lim Ze Feng
     * this is Listener class for project faculty combo box button to change the
     * specialization selection when doing the project creation
     * view
     */
    private  class manageProjectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            adminView.manageProject();
        }
    }

    /*
     * Lim Ze Feng
     * this is Listener class for project faculty combo box button to change the
     * specialization selection when doing the project creation
     */
    private class changeStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            adminView.getStudentListFromFile(adminView.getProjectSpec());
        }
    }

    /*
     * Lim Ze Feng
     * this is Listener class for add Project button to display the Project Creation
     * View
     */
    private class addProjectViewListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            adminView.createProjectView();
        }
    }

    /*
     * Lim Ze Feng
     * this is Listener class for delete Project button to display the Project
     * Delete
     * View
     */
    private class deleteProjectViewListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            adminView.deleteProjectView();
        }
    }

    /*
     * Lim Ze Feng
     * this is Listener class for add Comment View button to display the Project
     * Detail with comments view
     */
    private class addCommentViewListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            adminView.projectCommentsView();
        }
    }

    /*
     * Lim Ze Feng
     * this is Listener class for view ProjectID combo box button to display the
     * specific project detail with comments View
     */
    private class changeViewProjectDetailListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String id = adminView.getViewProjectIDCB();
            adminView.changeViewDetail(id);
        }
    }

    /*
     * Lim Ze Feng
     * this is Listener class for add Comment button to add a new comment on
     * specific project id
     */
    private class addCommentListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if (adminView.getViewProjectIDCB().equals("")) {
                JOptionPane.showMessageDialog(null, "Please select project id!",
                        "Failed", JOptionPane.ERROR_MESSAGE);
            } else {
                if (adminView.getComment().equals("") || adminView.getComment() == null) {
                    JOptionPane.showMessageDialog(null, "Please enter something before you add comment!",
                            "Failed", JOptionPane.ERROR_MESSAGE);
                } else {
                    Comment cm = new Comment(adminView.getViewProjectIDCB(), adminView.getComment());
                    cm.createComment();
                    adminView.showAddCommentSuccess();
                    JOptionPane.showMessageDialog(null, cm.toString(), "Successfully added",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
    }

    /*
     * Ong Shi Yi
     * this method is to create a new admin account
     */
    private void createAdmin(String username) {
        try {
            // get the admin file
            FileReader fileReader = new FileReader("admin.txt");
            String lastLine = "", currentLine;
            BufferedReader br = new BufferedReader(fileReader);

            // get the last value of the file
            while ((currentLine = br.readLine()) != null) {
                lastLine = currentLine;
            }

            // split the word into array example: example1,example2 -> [example1],[example2]

            String[] lines = lastLine.split("[,]", 2);
            int nextInt = (Integer.parseInt(lines[0].substring(1, 5)) + 1);
            String adminID = String.format("%s%04d", "a", nextInt);

            String[] result = username.split(" ");
            String firstName = result[0];
            String new_password = String.format("%s%04d", firstName, nextInt);

            // create new admmin
            appendAdminInFile(new Admin(adminID, username, new_password));

            fileReader.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Ong Shi Yi
     * this method is to append the new admin account into the admin.txt
     */
    private void appendAdminInFile(Admin newAdmin) {
        try {
            File file = new File("admin.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            if (file.length() == 0) {
                String outputLine = String.format("%s,%s,%s", newAdmin.getId(), newAdmin.getName(),
                        newAdmin.getPassword());
                fileWriter.write(outputLine);
            } else {
                String outputLine = String.format("\r\n%s,%s,%s", newAdmin.getId(), newAdmin.getName(),
                        newAdmin.getPassword());
                fileWriter.write(outputLine);
            }
            fileWriter.close();

            JOptionPane.showMessageDialog(null, newAdmin.toString(), "Registration Sucessful",
                    JOptionPane.INFORMATION_MESSAGE);
            adminView.setReset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Ong Shi Yi
     * this method is to create a new lecturer account
     */
    private void createLecturer(String username, String faculty) {
        try {
            // get the admin file
            FileReader fileReader = new FileReader("lecturer.txt");
            String lastLine = "", currentLine;
            BufferedReader br = new BufferedReader(fileReader);

            // get the last value of the file
            while ((currentLine = br.readLine()) != null) {
                lastLine = currentLine;
            }

            // split the word into array example: example1,example2 -> [example1],[example2]
            String[] lines = lastLine.split("[,]", 2);
            int nextInt = (Integer.parseInt(lines[0].substring(1, 5)) + 1);
            String lecturerID = String.format("%s%04d", "l", nextInt);

            String[] result = username.split(" ");
            String firstName = result[0];
            String new_password = String.format("%s%04d", firstName, nextInt);

            fileReader.close();
            br.close();

            // create new admmin
            appendLecturerInFile(new Lecturer(lecturerID, username, new_password, faculty));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Ong Shi Yi
     * this method is to append the new lecturer account into the lecturer.txt
     */
    private void appendLecturerInFile(Lecturer newlecturer) {
        try {
            File file = new File("lecturer.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            if (file.length() == 0) {
                String outputLine = String.format("%s,%s,%s,%s", newlecturer.getId(), newlecturer.getName(),
                        newlecturer.getPassword(), newlecturer.getFaculty());
                fileWriter.write(outputLine);
            } else {
                String outputLine = String.format("\r\n%s,%s,%s,%s", newlecturer.getId(), newlecturer.getName(),
                        newlecturer.getPassword(), newlecturer.getFaculty());
                fileWriter.write(outputLine);
            }
            fileWriter.close();

            JOptionPane.showMessageDialog(null, newlecturer.toString(), "Registration Sucessful",
                    JOptionPane.INFORMATION_MESSAGE);
            adminView.setReset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Ong Shi Yi
     * this method is to create a new student account
     */
    private void createStudent(String username, String faculty, String specialization) {
        try {
            // get the admin file
            FileReader fileReader = new FileReader("student.txt");
            String lastLine = "", currentLine;
            BufferedReader br = new BufferedReader(fileReader);

            // get the last value of the file
            while ((currentLine = br.readLine()) != null) {
                lastLine = currentLine;
            }

            // split the word into array example: example1,example2 -> [example1],[example2]
            String[] lines = lastLine.split("[,]", 2);
            int nextInt = (Integer.parseInt(lines[0].substring(1, 5)) + 1);
            String studentID = String.format("%s%04d", "s", nextInt);

            String[] result = username.split(" ");
            String firstName = result[0];
            String new_password = String.format("%s%04d", firstName, nextInt);

            fileReader.close();
            br.close();

            // create new admmin
            appendStudentInFile(new Student(studentID, username, new_password, faculty, specialization));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Ong Shi Yi
     * this method is to append the new student account into the student.txt
     */
    private void appendStudentInFile(Student newstudent) {
        try {
            File file = new File("student.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            if (file.length() == 0) {
                String outputLine = String.format("%s,%s,%s,%s,%s", newstudent.getId(), newstudent.getName(),
                        newstudent.getPassword(), newstudent.getFaculty(), newstudent.getSpecialization());
                fileWriter.write(outputLine);
            } else {
                String outputLine = String.format("\r\n%s,%s,%s,%s,%s", newstudent.getId(), newstudent.getName(),
                        newstudent.getPassword(), newstudent.getFaculty(), newstudent.getSpecialization());
                fileWriter.write(outputLine);
            }
            fileWriter.close();

            JOptionPane.showMessageDialog(null, newstudent.toString(), "Registration Sucessful",
                    JOptionPane.INFORMATION_MESSAGE);
            adminView.setReset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Ong Shi Yi
     * this method is to check the the username text field
     * If the character is not a letter and not a whitespace, return false.
     */
    private boolean checkString(String name) {
        for (int i = 0; i < name.length(); ++i) {
            char ch = name.charAt(i);

            // If the character is not a letter and not a whitespace, return false.
            if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Tan Wei Chun , Lim Ze Feng
     * this is Listener class for create project button to create a new project
     */
    private class createProjectListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if ((adminView.getProjectName().equals("") || adminView.getProjectName() == null)
                    || (adminView.getProjectDes().equals("") || adminView.getProjectDes() == null)) {
                adminView.showSubmitFail();
            } else {
                if (adminView.getProjectFac().equals("-") || adminView.getProjectSpec().equals("-")) {
                    adminView.showSubmitFail2();
                } else {
                    if (adminView.getProjectLecturer().equals("-")) {
                        adminView.showSubmitFail3();
                    } else {
                        if (!adminView.getProjectStu().equals("-")) {
                            List<String> student = new ArrayList<String>();
                            try {
                                File file = new File("student.txt");
                                BufferedReader br = new BufferedReader(new FileReader(file));

                                String line;

                                while ((line = br.readLine()) != null) {
                                    student.add(line);
                                }
                                br.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            for (int i = 0; i < student.size(); i++) {
                                if (student.get(i).contains(adminView.getProjectStu())) {
                                    List<String> checking = Arrays.asList(student.get(i).split(","));
                                    Student currStudent = new Student(checking.get(0), checking.get(1),
                                            checking.get(2),
                                            checking.get(3), checking.get(4));
                                    if (currStudent.getMiniProject() != null) {
                                        JOptionPane.showMessageDialog(null,
                                                "This student already have project, please assign to another student!",
                                                "Failed", JOptionPane.ERROR_MESSAGE);

                                    } else {
                                        MiniProject mp = new MiniProject();
                                        mp.createMiniProject(adminView.getProjectLecturer(),
                                                adminView.getProjectStu(),
                                                adminView.getProjectName(),
                                                adminView.getProjectDes(),
                                                adminView.getProjectFac(),
                                                adminView.getProjectSpec(),
                                                adminView.getProjectStatus());

                                        adminView.showCreateSuccess();
                                        JOptionPane.showMessageDialog(null, mp.toString(),
                                                "Successfully Created",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    }
                                }
                            }

                        } else {
                            MiniProject mp = new MiniProject();
                            mp.createMiniProject(adminView.getProjectLecturer(),
                                    "unassigned",
                                    adminView.getProjectName(),
                                    adminView.getProjectDes(),
                                    adminView.getProjectFac(),
                                    adminView.getProjectSpec(),
                                    adminView.getProjectStatus());
                            adminView.showCreateSuccess();
                            JOptionPane.showMessageDialog(null, mp.toString(),
                                    "Successfully Created",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        }
    }

    /*
     * Lim Ze Feng
     * this is Listener class for delete project id combo box button to change the
     * specific project detail on
     * project delete view
     */
    private class changeDeleteDetailListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String id = adminView.getDeleteProjectID();
            adminView.changeDeleteDetail(id);
        }
    }

    /*
     * Lim Ze Feng
     * this is Listener class for delete project button to delete the specific
     * project
     */
    private class deleteProjectListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {

            if (!adminView.getDeleteProjectID().equals("")) {
                try {
                    File file = new File("Mini_Project.txt");
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    List<String> lines = new ArrayList<String>();
                    String line;

                    while ((line = br.readLine()) != null) {
                        lines.add(line);
                    }

                    br.close();
                    for (int i = 0; i < lines.size(); i++) {
                        if (lines.get(i).startsWith(adminView.getDeleteProjectID())) {
                            lines.remove(i);
                        }
                    }

                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write("");

                    fileWriter.close();
                    fileWriter = new FileWriter(file);

                    for (String s : lines) {
                        if (!s.startsWith("MP000001"))
                            fileWriter.write("\r\n" + s);
                        else
                            fileWriter.write(s);
                    }
                    fileWriter.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                adminView.showDeleteSuccess();
            } else {
                JOptionPane.showMessageDialog(null, "Please select project to delete!",
                        "Failed", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    /*
     * Soon Tee Herr
     * this method is for view Project list, view comment and back from
     * comment buttons to change
     * the view
     */
    private class ChangeScreenListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String btn = evt.getActionCommand();
            if (btn.equals("View Project List")) {
                adminView.projectTableView();
            } else {
                adminView.projectTableCommentsView();
                projectShowCommentButton();
            }
        }
    }

    /*
     * Soon Tee Herr
     * this method is to show the project comment list
     */
    private void projectShowCommentButton() {

        // set all the mini project attributes in each column
        String column[] = { "Project ID", "Comments" };
        // set the Table not editable
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1)
                    return true;
                return false;
            }
        };
        // add the attribute column into table
        model.setColumnIdentifiers(column);

        for (int i = 0; i < project_ArrayList.size(); i++) {
            Vector row = new Vector(1);
            row.add(project_ArrayList.get(i).getProjectID());
            row.add("-");
            model.addRow(row);
        }
        // model add into JTable
        JTable jt = new JTable(model);
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();

        jt.getColumnModel().getColumn(1).setCellRenderer(buttonRenderer);
        jt.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor(new JTextField()));

        // JTable add into JScrollPane
        adminView.refreshTableView(jt, model);
    }

    // Soon Tee Herr
    // JTable renderer to add button for view project table
    private static class JTableButtonRenderer extends JButton implements TableCellRenderer {
        public JTableButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused,
                int row, int col) {
            setText("View");
            return this;
        }
    }

    // Soon Tee Herr
    // Button editor class for the Table
    // to handle the action for the view button
    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String buttonLabel;
        private String ProjectID;
        private Boolean clicked;

        public ButtonEditor(JTextField text) {
            super(text);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });

        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object obj,
                boolean selected, int row, int col) {

            button.setText("View");
            buttonLabel = "View";

            ProjectID = project_ArrayList.get(row).getProjectID();
            // System.out.println(ProjectID);

            clicked = true;
            return button;
        }

        // Soon Tee Herr
        // manage action for view button

        @Override
        public Object getCellEditorValue() {

            if (clicked) {
                Vector projectComments = new Vector(2, 1);
                // Check the project ID and put in the description
                for (int i = 0; i < commentList.size(); i++) {
                    if (commentList.get(i).getProjectID().equals(ProjectID)) {
                        projectComments.add(commentList.get(i).getComment());
                    }
                }
                if (projectComments.size() == 0) {
                    projectComments.add("No Comment");
                }

                // show table of detail
                adminView.doCommentButton(ProjectID, buttonLabel, projectComments);
                adminView.showViewProjectComment(projectComments);
            }
            // SET IT TO FALSE NOW THAT ITS CLICKED
            clicked = false;
            return new String(buttonLabel);
        }

        @Override
        public boolean stopCellEditing() {

            // SET CLICKED TO FALSE FIRST
            clicked = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {

            super.fireEditingStopped();
        }
    }

    /*
     * Tan Wei Chun
     * this method is to close the whole system interface after user click the
     * logout button
     */
    public void close() {
        adminView.close();
    }

    /*
     * Tan Wei Chun
     * this method is to display the whole system interface after user login
     * successful
     */
    public void display() {
        adminView.display();
    }

}