import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    Border borderLine = BorderFactory.createLineBorder(Color.black);
    JLabel loginLb = new JLabel("Login");
    int bounds = 80;

    private StudentView studentView = new StudentView();
    private StudentController studentController;

    private LecturerView lecturerView = new LecturerView();
    private LecturerController lecturerController;

    private AdminView adminView = new AdminView();
    private AdminController adminController;

    private JFrame frame = new JFrame();
    private JPanel loginJP = new JPanel();
    private JPanel studentJP = new JPanel();
    private JPanel lecturerJP = new JPanel();
    private JPanel adminJP = new JPanel();

    Main() {
        loginLb.setFont(new Font("Times New Roman", Font.BOLD, 30));
        loginLb.setBounds(550, 50, 400, 40);

        // ---------------------------------------------------------------------------

        // Student Login Page
        // ------------------------------------------------------------------------
        GridBagConstraints gbc = new GridBagConstraints();
        studentJP.setLayout(new GridBagLayout());
        studentJP.setBounds(200, 100, 1000, 400);
        studentJP.setBackground(Color.white);

        // username label and textfield
        JLabel studentNameLb = new JLabel("id");
        JTextField studentTF = new JTextField(15);

        // password label and textfield
        JLabel studentPasswordLb = new JLabel("Password");
        JTextField studentPasswordTF = new JPasswordField(15);

        // username label and textfield
        JButton backBtn1 = new JButton("Back");
        backBtn1.setPreferredSize(new Dimension(70, 40));
        backBtn1.setFocusPainted(false);
        backBtn1.setBorder(borderLine);
        backBtn1.setForeground(Color.black);
        backBtn1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        backBtn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(loginJP);
                frame.validate();
                frame.repaint();
            }
        });
        JButton stuloginBtn = new JButton("Login");
        stuloginBtn.setPreferredSize(new Dimension(70, 40));
        stuloginBtn.setFocusPainted(false);
        stuloginBtn.setBorder(borderLine);
        stuloginBtn.setForeground(Color.black);
        stuloginBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        stuloginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stuloginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Boolean success = false;
                Boolean accountExists = false;

                List<String> lines = new ArrayList<String>();
                String line;

                try {
                    File file = new File("student.txt");
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != "") {
                        if (line == null) {
                            break;
                        } else {
                            lines.add(line);
                        }
                    }
                    br.close();
                } catch (IOException evt) {
                    evt.printStackTrace();
                }

                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).startsWith(studentTF.getText())) {
                        List<String> checking = Arrays.asList(lines.get(i).split(","));
                        if (checking.get(0).equals(studentTF.getText())) {
                            accountExists = true;
                            break;
                        }
                    }
                }

                if (!accountExists) {
                    JOptionPane.showMessageDialog(null, "Account does not exists", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    studentTF.setText("");
                    studentPasswordTF.setText("");
                } else {
                    for (int i = 0; i < lines.size(); i++) {
                        if (lines.get(i).startsWith(studentTF.getText())) {
                            List<String> checking = Arrays.asList(lines.get(i).split(","));
                            if (checking.get(2).equals(studentPasswordTF.getText())) {
                                Student student = new Student(checking.get(0), checking.get(1), checking.get(2),
                                        checking.get(3), checking.get(4));
                                studentController = new StudentController(student, studentView);

                                studentView.closedViewListener(new logoutListener());
                                success = true;
                                close();
                                frame.setContentPane(loginJP);
                                frame.validate();
                                frame.repaint();
                                break;
                            }
                        }
                    }
                    if (!success) {
                        JOptionPane.showMessageDialog(null, "Wrong password, please try again", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                        studentPasswordTF.setText("");
                    }
                }
            }
        });

        // admin username Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 20);
        studentJP.add(studentNameLb, gbc);

        // admin username Text Field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        studentJP.add(studentTF, gbc);

        // admin password Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 20);
        studentJP.add(studentPasswordLb, gbc);

        // admin password TextField
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        studentJP.add(studentPasswordTF, gbc);

        // admin password TextField
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 20);
        studentJP.add(backBtn1, gbc);

        // admin password TextField
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        studentJP.add(stuloginBtn, gbc);

        // ---------------------------------------------------------------------------

        // Lecturer Login Page
        // ----------------------------------------------------------------------

        GridBagConstraints gbc1 = new GridBagConstraints();
        lecturerJP.setLayout(new GridBagLayout());
        lecturerJP.setBounds(200, 100, 1000, 400);
        lecturerJP.setBackground(Color.white);

        // username label and textfield
        JLabel lecNameLb = new JLabel("id");
        JTextField lecTF = new JTextField(15);

        // password label and textfield
        JLabel lecPasswordLb = new JLabel("Password");
        JTextField lecPasswordTF = new JPasswordField(15);

        // username label and textfield
        JButton backBtn2 = new JButton("Back");
        backBtn2.setPreferredSize(new Dimension(70, 40));
        backBtn2.setFocusPainted(false);
        backBtn2.setBorder(borderLine);
        backBtn2.setForeground(Color.black);
        backBtn2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        backBtn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(loginJP);
                frame.validate();
                frame.repaint();
            }
        });
        JButton lecloginBtn = new JButton("Login");
        lecloginBtn.setPreferredSize(new Dimension(70, 40));
        lecloginBtn.setFocusPainted(false);
        lecloginBtn.setBorder(borderLine);
        lecloginBtn.setForeground(Color.black);
        lecloginBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lecloginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lecloginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Boolean success = false;
                Boolean accountExists = false;

                List<String> lines = new ArrayList<String>();
                String line;

                try {
                    File file = new File("lecturer.txt");
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != "") {
                        if (line == null) {
                            break;
                        } else {
                            lines.add(line);
                        }
                    }
                    br.close();
                } catch (IOException evt) {
                    evt.printStackTrace();
                }

                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).startsWith(lecTF.getText())) {
                        List<String> checking = Arrays.asList(lines.get(i).split(","));
                        if (checking.get(0).equals(lecTF.getText())) {
                            accountExists = true;
                            break;
                        }
                    }
                }

                if (!accountExists) {
                    JOptionPane.showMessageDialog(null, "Account does not exists", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    lecTF.setText("");
                    lecPasswordTF.setText("");
                } else {
                    for (int i = 0; i < lines.size(); i++) {
                        if (lines.get(i).startsWith(lecTF.getText())) {
                            List<String> checking = Arrays.asList(lines.get(i).split(","));
                            if (checking.get(2).equals(lecPasswordTF.getText())) {
                                Lecturer lecturer = new Lecturer(checking.get(0), checking.get(1), checking.get(2),
                                        checking.get(3));
                                lecturerController = new LecturerController(lecturer, lecturerView);

                                lecturerView.closedViewListener(new logoutListener());
                                success = true;
                                close();
                                frame.setContentPane(loginJP);
                                frame.validate();
                                frame.repaint();
                                break;
                            }
                        }
                    }
                    if (!success) {
                        JOptionPane.showMessageDialog(null, "Wrong password, please try again", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                        lecPasswordTF.setText("");
                    }
                }
            }
        });

        // admin username Label
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(0, 0, 10, 20);
        lecturerJP.add(lecNameLb, gbc1);

        // admin username Text Field
        gbc1.gridx = 1;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(0, 0, 10, 0);
        lecturerJP.add(lecTF, gbc1);

        // admin password Label
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        gbc1.insets = new Insets(0, 0, 10, 20);
        lecturerJP.add(lecPasswordLb, gbc1);

        // admin password TextField
        gbc1.gridx = 1;
        gbc1.gridy = 1;
        gbc1.insets = new Insets(0, 0, 10, 0);
        lecturerJP.add(lecPasswordTF, gbc1);

        // admin password TextField
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        gbc1.insets = new Insets(0, 0, 0, 20);
        lecturerJP.add(backBtn2, gbc1);

        // admin password TextField
        gbc1.gridx = 1;
        gbc1.gridy = 2;
        gbc1.insets = new Insets(0, 0, 0, 0);
        lecturerJP.add(lecloginBtn, gbc1);

        // --------------------------------------------------------------------------------------

        // Admin Login
        // --------------------------------------------------------------------------------------
        GridBagConstraints gbc2 = new GridBagConstraints();
        adminJP.setLayout(new GridBagLayout());
        adminJP.setBounds(200, 100, 1000, 400);
        adminJP.setBackground(Color.white);

        // username label and textfield
        JLabel adminNameLb = new JLabel("id");
        JTextField adminTF = new JTextField(15);

        // password label and textfield
        JLabel adminPasswordLb = new JLabel("Password");
        JTextField adminPasswordTF = new JPasswordField(15);

        // username label and textfield
        JButton backBtn3 = new JButton("Back");
        backBtn3.setPreferredSize(new Dimension(70, 40));
        backBtn3.setFocusPainted(false);
        backBtn3.setBorder(borderLine);
        backBtn3.setForeground(Color.black);
        backBtn3.setFont(new Font("Times New Roman", Font.BOLD, 15));
        backBtn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(loginJP);
                frame.validate();
                frame.repaint();
            }
        });
        JButton adminloginBtn = new JButton("Login");
        adminloginBtn.setPreferredSize(new Dimension(70, 40));
        adminloginBtn.setFocusPainted(false);
        adminloginBtn.setBorder(borderLine);
        adminloginBtn.setForeground(Color.black);
        adminloginBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        adminloginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        adminloginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Boolean success = false;
                Boolean accountExists = false;

                List<String> lines = new ArrayList<String>();
                String line;

                try {
                    File file = new File("admin.txt");
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != "") {
                        if (line == null) {
                            break;
                        } else {
                            lines.add(line);
                        }
                    }
                    br.close();
                } catch (IOException evt) {
                    evt.printStackTrace();
                }

                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).startsWith(adminTF.getText())) {
                        List<String> checking = Arrays.asList(lines.get(i).split(","));
                        if (checking.get(0).equals(adminTF.getText())) {
                            accountExists = true;
                            break;
                        }
                    }
                }

                if (!accountExists) {
                    JOptionPane.showMessageDialog(null, "Account does not exists", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    adminTF.setText("");
                    adminPasswordTF.setText("");
                } else {
                    for (int i = 0; i < lines.size(); i++) {
                        if (lines.get(i).startsWith(adminTF.getText())) {
                            List<String> checking = Arrays.asList(lines.get(i).split(","));
                            if (checking.get(2).equals(adminPasswordTF.getText())) {
                                Admin admin = new Admin(checking.get(0), checking.get(1), checking.get(2));
                                adminController = new AdminController(admin, adminView);

                                adminView.closedViewListener(new logoutListener());
                                success = true;
                                close();
                                frame.setContentPane(loginJP);
                                frame.validate();
                                frame.repaint();
                                break;
                            }
                        }
                    }
                    if (!success) {
                        JOptionPane.showMessageDialog(null, "Wrong password, please try again", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                        adminPasswordTF.setText("");
                    }
                }
            }
        });

        // admin username Label
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.insets = new Insets(0, 0, 10, 20);
        adminJP.add(adminNameLb, gbc2);

        // admin username Text Field
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.insets = new Insets(0, 0, 10, 0);
        adminJP.add(adminTF, gbc2);

        // admin password Label
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.insets = new Insets(0, 0, 10, 20);
        adminJP.add(adminPasswordLb, gbc2);

        // admin password TextField
        gbc2.gridx = 1;
        gbc2.gridy = 1;
        gbc2.insets = new Insets(0, 0, 10, 0);
        adminJP.add(adminPasswordTF, gbc2);

        // admin password TextField
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.insets = new Insets(0, 0, 0, 20);
        adminJP.add(backBtn3, gbc2);

        // admin password TextField
        gbc2.gridx = 1;
        gbc2.gridy = 2;
        gbc2.insets = new Insets(0, 0, 0, 0);
        adminJP.add(adminloginBtn, gbc2);

        // -----------------------------------------------------------------------

        // Login Page
        // ---------------------------------------------------------------------

        loginJP.setLayout(null);
        loginJP.setSize(1200, 700);

        loginJP.add(loginLb);

        String[] btnName = { "Student", "Lecturer", "Admin" };
        for (int i = 0; i < btnName.length; i++) {
            JButton list = new JButton(btnName[i]);
            list.setBounds(bounds, 170, 320, 300);
            list.setFocusPainted(false);
            // list.setContentAreaFilled(false);
            list.setBorder(borderLine);
            list.setForeground(Color.black);
            list.setFont(new Font("Times New Roman", Font.BOLD, 18));
            list.setCursor(new Cursor(Cursor.HAND_CURSOR));
            list.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String btn = e.getActionCommand();
                    if (btn.equals("Student")) {
                        frame.setContentPane(studentJP);
                        frame.validate();
                        frame.repaint();
                    } else if (btn.equals("Lecturer")) {
                        frame.setContentPane(lecturerJP);
                        frame.validate();
                        frame.repaint();
                    } else {
                        frame.setContentPane(adminJP);
                        frame.validate();
                        frame.repaint();
                    }
                }
            });
            bounds += 350;

            loginJP.add(list);
        }
        bounds = 80;

        frame.setContentPane(loginJP);

        frame.setTitle("Mini-project Management System");
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
    }

    public void display() {
        frame.setVisible(true);
    }

    public void close() {
        frame.setVisible(false);
    }

    class logoutListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            studentView.close();
            studentView = new StudentView();
            lecturerView.close();
            lecturerView = new LecturerView();
            adminView.close();
            adminView = new AdminView();
            display();
        }
    }

    public void logout(Object controller) {
        if (controller instanceof StudentController) {

        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
