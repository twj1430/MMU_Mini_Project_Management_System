import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// Adam Bin Anuar
public class MiniProject {

    private String projectID;
    private String lecturerID;
    private String studentID;
    private String name;
    private String description;
    private String specialization;
    private String faculty;
    private float score;
    private String status;
    private static ArrayList<MiniProject> project_ArrayList = new ArrayList<MiniProject>();
    private ArrayList<Comment> commentList = new ArrayList<Comment>();

    public MiniProject() {
    }

    public MiniProject(String projectID, String lecturerID, String studentID, String name, String description,
    String faculty, String specialization, float score, String status) {
        this.projectID = projectID;
        this.lecturerID = lecturerID;
        this.studentID = studentID;
        this.name = name;
        this.description = description;
        this.specialization = specialization;
        this.faculty = faculty;
        this.score = score;
        this.status = status;
        getCMFromFile();
    }

     /*
     * createMiniProject
     * 1. Generate projectID by locating the last projectID from last line of
     * Mini_Project.txt and add 1 to it.
     * 2. Append the mini project to Mini_Project.txt
     */
    private void getCMFromFile() {
        try {
            File file = new File("comment.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<String>();
            String line;
            while ((line = br.readLine()) != "") {
                if (line == null)
                    break;
                else if (line.contains("," + projectID + ",")) {
                    lines.add(line);
                }
            }

            if (!lines.isEmpty()) {
                List<String> CM;
                for (String s : lines) {
                    CM = Arrays.asList(s.split(","));
                    commentList.add(new Comment(CM.get(0), CM.get(1), CM.get(2)));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * createMiniProject
     * 1. Generate projectID by locating the last projectID from last line of
     * Mini_Project.txt and add 1 to it.
     * 2. Append the mini project to Mini_Project.txt
     */
    public void createMiniProject(String lecturerID, String studentID, String name, String description,String faculty,
            String specialization, String status) {
        this.lecturerID = lecturerID;
        this.studentID = studentID;
        this.name = name;
        this.description = description;
        this.faculty = faculty;
        this.specialization = specialization;
        this.status = status;
        try {

            FileReader fileReader = new FileReader("Mini_Project.txt");
            String lastLine = "", currentLine;
            BufferedReader br = new BufferedReader(fileReader);

            while ((currentLine = br.readLine()) != null) {
                lastLine = currentLine;
            }

            String[] lines = lastLine.split("[,]", 2);
            int nextInt = (Integer.parseInt(lines[0].substring(2, 8)) + 1);
            String MP = String.format("%s%06d", "MP", nextInt);
            fileReader.close();
            br.close();
            this.projectID = MP;

            appendMPInFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * appendMPInFile()
     * This method will append mini project to Mini_Project.txt.
     * Should only be called in this class.
     */
    private void appendMPInFile() {
        try {
            File file = new File("Mini_Project.txt");
            FileWriter fileWriter = new FileWriter(file, true);

            String outputLine = String.format("\r\n%s,%s,%s,%s,%s,%s,%s,%s,%s",
                    projectID,
                    lecturerID,
                    studentID,
                    name,
                    description,
                    faculty,
                    specialization,
                    Float.toString(score),
                    status);
            fileWriter.write(outputLine);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * replaceMPInFile()
     * This method will replace the entire line in Mini_Project.txt.
     * The replaced data will correspond to the exact data fields of this object.
     */
    private void replaceMPInFile() {
        try {
            File file = new File("Mini_Project.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            List<String> lines = new ArrayList<String>();
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            br.close();
            String toReplace = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                    projectID,
                    lecturerID,
                    studentID,
                    name,
                    description,
                    faculty,
                    specialization,
                    Float.toString(score),
                    status);

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith(projectID)) {
                    lines.set(i, toReplace);
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
    }

    public static ArrayList<MiniProject> getMPFromFile(){
        try {
            // get all the mini project information from txt file
            FileReader fileReader = new FileReader("Mini_Project.txt");
            String currentLine = "";
            BufferedReader br = new BufferedReader(fileReader);
            project_ArrayList.removeAll(project_ArrayList);
            
            // if the txt paragraph line is not empty
            while ((currentLine = br.readLine()) != null) {
                // split all the "," and insert into array. Example: 1234,4321 => [1234],[4321]
                String[] lines = currentLine.split(",");
                project_ArrayList.add(new MiniProject(lines[0], lines[1], lines[2], lines[3], lines[4], lines[5],
                        lines[6], Float.parseFloat(lines[7]), lines[8]));
            }
            // close the FileReder and BufferedReader
            fileReader.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return project_ArrayList;
    }

    /*
     * activateProject method
     * set the status in the datafield and txt file to "activated".
     */
    public void activateProject() {
        status = "activated";
        replaceMPInFile();
    }

    /*
     * hideProject()
     * set the status in the datafield and txt file to "hidden".
     */
    public void hideProject() {
        this.status = "hidden";
        replaceMPInFile();
    }

    public void setScore(float score) {
        this.score = score;
        replaceMPInFile();
    }

    public void assignStudent(String studentID) {
        this.studentID = studentID;
        replaceMPInFile();
    }

    public void unassignStudent() {
        studentID = "unassigned";
        replaceMPInFile();
    }

    public void setDescription(String description) {
        this.description = description;
        replaceMPInFile();
    }

    public String getProjectID() {
        return projectID;
    }

    public String getLecturerID() {
        return lecturerID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setName(String name) {
        this.name = name;
        replaceMPInFile();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
        replaceMPInFile();
    }

    public String getSpecialization() {
        return specialization;
    }

    public float getScore() {
        return score;
    }

    public String getStatus() {
        return status;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setCommentList(ArrayList<Comment> comment) {
        this.commentList = comment;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public String getStudentIDName() {
        String studentIDName = "";
        try {
            File file = new File("Student.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != "") {
                if (line == null)
                    break;
                else if (line.contains(studentID)) {
                    break;
                }
            }
            if (line != null) {
                List<String> studentLine = Arrays.asList(line.split(","));
                studentIDName = studentID + "-" + studentLine.get(1);
                
            }else{
                studentIDName = "unassigned";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentIDName;
    }

    public String toString() {
        // return String.format("%s%s%s%s%s%s%s%s%s", this.studentID, this.lecturerID,
        // this.studentID, this.name,
        // this.description, this.specialization,this.faculty, this.score, this.status);
        String toString = "Project ID: " + this.projectID
                + "\nLecturer ID: " + this.lecturerID
                + "\nStudent ID: " + this.studentID
                + "\nProject Name: " + this.name
                + "\nProject Description: " + this.description
                + "\nFaculty :" + this.faculty
                + "\nSpecialization: " + this.specialization
                + "\nScore :" + this.score
                + "\nStatus :" + this.status;
        return toString;
    }

    public static void main(String[] args) {
        // MiniProject mp = new MiniProject();
        // mp.createMiniProject("L0004", "S1200003", "Project A", "Project Description
        // A",
        // "Software Egineering", "FCI", "hidden");
        // mp.activateProject();
        // mp.unassignStudent();
    }
}
