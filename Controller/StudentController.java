import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * Tan Wei Chun
 * This is controller class for student view
 */
public class StudentController {
    private Student student;
    private StudentView studentView;

    /*
     * Tan Wei Chun
     * this constructor initializes the controller with student and student view
     */
    public StudentController(Student student, StudentView studentView) {
        this.student = student;
        this.studentView = studentView;
        this.studentView.display();
        this.studentView.ShowViewProjectListener(new showProjectListener());
    }

    /*
     * Tan Wei Chun
     * This method is to show student's project details and comments
     * if student dont' have project then system will display "You dont have any
     * project right now"
     */

    private class showProjectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (student.getMiniProject() == null) {
                studentView.showProjectEmpty();
            } else {
                if (student.getMiniProject().getStatus().equals("hidden")) {
                    studentView.showProjectEmpty();
                } else {
                    String lecturer;
                    try {
                        File file = new File("lecturer.txt");
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        List<String> lines = new ArrayList<String>();
                        String line;
                        while ((line = br.readLine()) != "") {
                            if (line == null)
                                break;
                            else if (line.contains(student.getMiniProject().getLecturerID() + ",")) {
                                lines.add(line);
                            }
                        }
                        if (lines.isEmpty()) {
                            return;
                        }
                        List<String> lec;
                        for (String s : lines) {
                            lec = Arrays.asList(s.split(","));
                            lecturer = lec.get(1);

                            DefaultTableModel model;
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
                            for (int i = 0; i < student.getMiniProject().getCommentList().size(); i++) {
                                Vector row = new Vector(1);
                                row.add(student.getMiniProject().getCommentList().get(i).getComment()); // add the id
                                model.addRow(row);
                            }

                            JTable commentTable = new JTable(model);
                            studentView.showProjectDetail(lecturer,
                                    student.getMiniProject().getName(),
                                    student.getMiniProject().getDescription(),
                                    String.valueOf(student.getMiniProject().getScore()),
                                    commentTable);
                        }

                    } catch (IOException evt) {
                        evt.printStackTrace();
                    }

                }

            }
        }
    }
}
