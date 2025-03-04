import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Lecturer extends Person{
    private String faculty;
    private List<MiniProject> miniProjects = new ArrayList<MiniProject>();

    public Lecturer () {}

    public Lecturer (String ID, String username,String password, String faculty) {
        super.setId(ID);
        super.setName(username);
        super.setPassword(password);
        this.faculty = faculty;
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
            miniProjects.add(new MiniProject (MP.get(0), MP.get(1), MP.get(2),
                                              MP.get(3), MP.get(4), MP.get(5), 
                                              MP.get(6),
                                              Float.valueOf(MP.get(7)), MP.get(8)));
        }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFaculty(String faculty){
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    public List<MiniProject> getMiniProjects () {
        return this.miniProjects;
    }

    public void addMiniProject (MiniProject project) {
        miniProjects.add(project);
    }

    @Override
    public String toString() {
        String toString = "ID: " + super.getId()
                + "\nUsename: " + super.getName()
                + "\nPassword: " + super.getPassword()
                + "\nFaculty: " + this.getFaculty();
        return toString;
    }
    
    // public static void main (String[] args) {
    //     Lecturer lecturer1 = new Lecturer ("L0004","Adam", "FCI");
    //     System.out.println(lecturer1.getID());
    //     lecturer1.getMPFromFile();
    //     for (MiniProject MP : lecturer1.getMiniProjects()) {
    //         System.out.println(MP);
    //     }
    // }
}