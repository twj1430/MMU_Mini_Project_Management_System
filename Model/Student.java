import java.io.*;
import java.util.*;

public class Student extends Person {

    private String faculty, specialization;
    private MiniProject miniProject;

    public Student() {
    }

    public Student(String username, String password, String faculty, String specialization) {
        super(username, password);
        setFaculty(faculty);
        setSpecialization(specialization);
    }

    public Student(String id, String username, String password, String faculty, String specialization) {
        super(id, username, password);
        setFaculty(faculty);
        setSpecialization(specialization);
        getMPFromFile();
    }

        /*
     * this method get lecturer's mini project from Mini-Project.txt 
     * and add to the list of miniProjects
     */
    public void getMPFromFile() {
        try{
        File file = new File("Mini_Project.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<String>();
        String line;
        while( (line = br.readLine()) != "") {
            if(line == null)
                break;
            else if(line.contains("," + super.getId() + ",")) {
                    lines.add(line);
                }
        }
        if (lines.isEmpty()) {
            return;
        }
        List<String> MP;
        for (String s : lines) {
            MP = Arrays.asList(s.split(","));
            miniProject = new MiniProject (MP.get(0), MP.get(1), MP.get(2),
                                              MP.get(3), MP.get(4), MP.get(5), 
                                              MP.get(6),
                                              Float.valueOf(MP.get(7)), MP.get(8));
        }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setMiniProject(MiniProject miniProject) {
        this.miniProject = miniProject;
    }

    public MiniProject getMiniProject() {
        return miniProject;
    }

    @Override
    public String toString() {
        String toString = "ID: " + super.getId()
                + "\nUsename: " + super.getName()
                + "\nPassword: " + super.getPassword()
                + "\nFaculty: " + this.getFaculty()
                + "\nSpecialization: " + this.getSpecialization();
        return toString;
    }
}
