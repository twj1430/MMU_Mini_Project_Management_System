import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/*
 * Adam
 * This is controller class for lecturer view
 */
public class LecturerController {
    private Lecturer lecturer;
    private LecturerView lecturerView;

    /*
     * Adam
     * this constructor initializes the controller with lecturer and lecturer view
     */
    public LecturerController(Lecturer lecturers, LecturerView lecturerView) {
        this.lecturer = lecturers;
        this.lecturerView = lecturerView;
        this.lecturerView.display();
        this.lecturerView.addSubmitListener(new SubmitListener());
        this.lecturerView.addCreateProjectListener(new CreateProjectListener());
        this.lecturerView.addViewProjectListener(new ViewProjectListener());
        this.lecturerView.addSpecializationCBListener(new specializationCBListener());
        this.lecturerView.addBackFromProjectDetailsListener(new BackProjectListener());
        this.lecturerView.addBackFromProjectEditListener(new SaveProjectListener());

    }

    /*
     * Adam
     * this is Listener class for submit project button
     */
    private class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String projectDescription = lecturerView.getProjectDescription();
            String projectName = lecturerView.getProjectName();
            if ((projectDescription == null || projectDescription.equals("")) || (projectName == null
                    || projectName.equals(""))) {
                lecturerView.showSubmitError();
            } else {
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
                    if (student.get(i).contains(lecturerView.getStudentID())) {
                        List<String> checking = Arrays.asList(student.get(i).split(","));
                        Student currStudent = new Student(checking.get(0), checking.get(1),
                                checking.get(2),
                                checking.get(3), checking.get(4));
                        if (currStudent.getMiniProject() != null) {
                            JOptionPane.showMessageDialog(null,
                                    "This student already have project, please assign to another student!",
                                    "Failed", JOptionPane.ERROR_MESSAGE);
                            break;
                        } else {
                            MiniProject mp = new MiniProject();
                            mp.createMiniProject(lecturer.getId(),
                                    lecturerView.getStudentID(),
                                    lecturerView.getProjectName(),
                                    lecturerView.getProjectDescription(),
                                    lecturerView.getFaculty(),
                                    lecturerView.getSpecialization(),
                                    lecturerView.getStatus());
                            lecturer.addMiniProject(mp);
                            lecturerView.showSubmitSuccess();
                            lecturerView.showCreateProject();
                            break;
                        }
                    } else if (i == student.size() - 1) {
                        MiniProject mp = new MiniProject();
                        mp.createMiniProject(lecturer.getId(),
                                "unassigned",
                                lecturerView.getProjectName(),
                                lecturerView.getProjectDescription(),
                                lecturerView.getSpecialization(),
                                lecturerView.getFaculty(),
                                lecturerView.getStatus());
                        lecturer.addMiniProject(mp);
                        lecturerView.showSubmitSuccess();
                        lecturerView.showCreateProject();
                        break;
                    }
                }
            }

            lecturerView.showCreateProject();
        }
    }

    /*
     * Adam
     * this is a Listener class for createProject button
     */
    private class CreateProjectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            lecturerView.showCreateProject();
        }
    }

    /*
     * Adam
     * this is Listener class for backFromProjectButton
     */
    private class BackProjectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            lecturerView.showViewProject();
            String projectID;
            DefaultTableModel tableModel = new DefaultTableModel(
                    new Object[] { "ID", "Content", "Faculty", "Specialization", "Student ID & Name", "Score", "Status",
                            "Detail", "Edit" },
                    0) {
                public boolean isCellEditable(int row, int column) {
                    if (column == 7 || column == 8)
                        return true;
                    return false;
                }
            };
            for (int i = 0; i < lecturer.getMiniProjects().size(); i++) {
                projectID = lecturer.getMiniProjects().get(i).getProjectID();
                tableModel.addRow(new Object[] { projectID, lecturer.getMiniProjects().get(i).getName(),
                        lecturer.getMiniProjects().get(i).getFaculty(),
                        lecturer.getMiniProjects().get(i).getSpecialization(),
                        lecturer.getMiniProjects().get(i).getStudentIDName(),
                        lecturer.getMiniProjects().get(i).getScore(), lecturer.getMiniProjects().get(i).getStatus(),
                        projectID, projectID });
            }
            JTable table = new JTable();
            table.setModel(tableModel);
            TableCellRenderer buttonRenderer = new JTableButtonRenderer();

            table.getColumnModel().getColumn(8).setCellRenderer(buttonRenderer);
            table.getColumnModel().getColumn(7).setCellRenderer(buttonRenderer);
            table.getColumnModel().getColumn(0).setPreferredWidth(75);
            table.getColumnModel().getColumn(1).setPreferredWidth(75);
            table.getColumnModel().getColumn(2).setPreferredWidth(75);
            table.getColumnModel().getColumn(3).setPreferredWidth(150);
            table.getColumnModel().getColumn(4).setPreferredWidth(250);
            table.getColumnModel().getColumn(5).setPreferredWidth(75);
            table.getColumnModel().getColumn(6).setPreferredWidth(75);
            table.getColumnModel().getColumn(7).setPreferredWidth(100);
            table.getColumnModel().getColumn(8).setPreferredWidth(100);
            table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JTextField()));
            table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JTextField()));
            lecturerView.refreshTableView1(table, tableModel);
        }
    }

    /*
     * Adam
     * this is Listener Class for save project button after edit
     * Also change the project table after edit
     */
    private class SaveProjectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            lecturerView.showViewProject();

            for (MiniProject mp : lecturer.getMiniProjects()) {
                if (mp.getProjectID() == lecturerView.getThisProjectID()) {

                    mp.setScore(lecturerView.getScore());
                    if (lecturerView.getStatus1() == "hidden")
                        mp.hideProject();
                    else
                        mp.activateProject();
                    if (!lecturerView.getStudentID1().equals("-")) {

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
                            if (student.get(i).contains(lecturerView.getStudentID1())) {
                                List<String> checking = Arrays.asList(student.get(i).split(","));
                                Student currStudent = new Student(checking.get(0), checking.get(1),
                                        checking.get(2),
                                        checking.get(3), checking.get(4));
                                if (currStudent.getMiniProject() != null) {
                                    JOptionPane.showMessageDialog(null,
                                            "This student already have project, please assign to another student!",
                                            "Failed", JOptionPane.ERROR_MESSAGE);

                                } else {
                                    mp.assignStudent(lecturerView.getStudentID1());
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
            }
            String projectID;
            DefaultTableModel tableModel = new DefaultTableModel(
                    new Object[] { "ID", "Content", "Faculty", "Specialization", "Student ID & Name", "Score", "Status",
                            "Detail", "Edit" },
                    0) {
                public boolean isCellEditable(int row, int column) {
                    if (column == 7 || column == 8)
                        return true;
                    return false;
                }
            };
            for (int i = 0; i < lecturer.getMiniProjects().size(); i++) {
                projectID = lecturer.getMiniProjects().get(i).getProjectID();
                tableModel.addRow(new Object[] { projectID, lecturer.getMiniProjects().get(i).getName(),
                        lecturer.getMiniProjects().get(i).getFaculty(),
                        lecturer.getMiniProjects().get(i).getSpecialization(),
                        lecturer.getMiniProjects().get(i).getStudentIDName(),
                        lecturer.getMiniProjects().get(i).getScore(), lecturer.getMiniProjects().get(i).getStatus(),
                        projectID, projectID });
            }
            JTable table = new JTable();
            table.setModel(tableModel);
            TableCellRenderer buttonRenderer = new JTableButtonRenderer();

            table.getColumnModel().getColumn(8).setCellRenderer(buttonRenderer);
            table.getColumnModel().getColumn(7).setCellRenderer(buttonRenderer);
            table.getColumnModel().getColumn(0).setPreferredWidth(75);
            table.getColumnModel().getColumn(1).setPreferredWidth(75);
            table.getColumnModel().getColumn(2).setPreferredWidth(75);
            table.getColumnModel().getColumn(3).setPreferredWidth(150);
            table.getColumnModel().getColumn(4).setPreferredWidth(250);
            table.getColumnModel().getColumn(5).setPreferredWidth(75);
            table.getColumnModel().getColumn(6).setPreferredWidth(75);
            table.getColumnModel().getColumn(7).setPreferredWidth(100);
            table.getColumnModel().getColumn(8).setPreferredWidth(100);
            table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JTextField()));
            table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JTextField()));
            lecturerView.refreshTableView1(table, tableModel);
        }
    }

    /*
     * Adam
     * this is Listener class for view project.
     * It create table model and JTable for it
     */
    private class ViewProjectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            lecturerView.showViewProject();
            Object[][] tableData = new Object[lecturer.getMiniProjects().size()][9];
            String projectID;
            DefaultTableModel tableModel = new DefaultTableModel(
                    new Object[] { "ID", "Content", "Faculty", "Specialization", "Student ID & Name", "Score", "Status",
                            "Detail", "Edit" },
                    0) {
                public boolean isCellEditable(int row, int column) {
                    if (column == 7 || column == 8)
                        return true;
                    return false;
                }
            };
            for (int i = 0; i < lecturer.getMiniProjects().size(); i++) {
                projectID = lecturer.getMiniProjects().get(i).getProjectID();
                tableModel.addRow(new Object[] { projectID, lecturer.getMiniProjects().get(i).getName(),
                        lecturer.getMiniProjects().get(i).getFaculty(),
                        lecturer.getMiniProjects().get(i).getSpecialization(),
                        lecturer.getMiniProjects().get(i).getStudentIDName(),
                        lecturer.getMiniProjects().get(i).getScore(), lecturer.getMiniProjects().get(i).getStatus(),
                        projectID, projectID });
            }
            JTable table = new JTable();
            table.setModel(tableModel);
            TableCellRenderer buttonRenderer = new JTableButtonRenderer();

            table.getColumnModel().getColumn(8).setCellRenderer(buttonRenderer);
            table.getColumnModel().getColumn(7).setCellRenderer(buttonRenderer);
            table.getColumnModel().getColumn(0).setPreferredWidth(75);
            table.getColumnModel().getColumn(1).setPreferredWidth(75);
            table.getColumnModel().getColumn(2).setPreferredWidth(75);
            table.getColumnModel().getColumn(3).setPreferredWidth(150);
            table.getColumnModel().getColumn(4).setPreferredWidth(250);
            table.getColumnModel().getColumn(5).setPreferredWidth(75);
            table.getColumnModel().getColumn(6).setPreferredWidth(75);
            table.getColumnModel().getColumn(7).setPreferredWidth(100);
            table.getColumnModel().getColumn(8).setPreferredWidth(100);
            table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JTextField()));
            table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JTextField()));
            lecturerView.refreshTableView1(table, tableModel);
            // createProjectView.refreshTableView(tableModel);

        }
    }

    /*
     * Adam
     * this is Listener class for specialization combo box
     */
    private class specializationCBListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String specialization = lecturerView.getSpecialization();
            lecturerView.getStudentListFromFile(specialization);
        }
    }

    /*
     * Adam
     * this is a JTable renderer to add button for view project table
     */
    private static class JTableButtonRenderer extends JButton implements TableCellRenderer {
        public JTableButtonRenderer() {
            setOpaque(true);
        }

        /*
         * Adam
         * Render the button to for Details and Edit
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object obj,
                boolean selected, boolean focused, int row, int col) {

            if (col == 7)
                setText("Details");
            else
                setText("Edit");

            return this;
        }
    }

    /*
     * Adam
     * Button editor class for the Table
     * to handle the action for the button
     */
    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String buttonLabel;
        private String MPID;
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

            if (col == 7) {
                button.setText("Details");
                buttonLabel = "Details";
            } else {
                button.setText("Edit");
                buttonLabel = "Edit";
            }

            MPID = obj.toString();
            clicked = true;
            return button;
        }

        /*
         * manage action for Details and Edit button
         */
        @Override
        public Object getCellEditorValue() {
            String projectDescription = "";
            String projectSpecialization = "";
            if (clicked) {
                List<MiniProject> ProjectList = lecturer.getMiniProjects();
                for (MiniProject p : ProjectList) {
                    if (p.getProjectID() == MPID) {
                        projectDescription = p.getDescription();
                        projectSpecialization = p.getSpecialization();
                        break;
                    }
                }
                lecturerView.setThisProjectID(MPID);
                lecturerView.getStudentListFromFile(projectSpecialization);
                lecturerView.setProjectDescription(projectDescription);
                lecturerView.doTableButton(MPID, buttonLabel);
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
}
