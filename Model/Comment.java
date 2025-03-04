import java.io.*;
import java.util.ArrayList;

// (Tan Wei Chun)
public class Comment {
    private String commentID, projectID, comment;
    private static ArrayList<Comment> commentList = new ArrayList<Comment>();

    public Comment() {
    }

    public Comment(String projectID, String comment) {
        this.projectID = projectID;
        this.comment = comment;
    }

    public Comment(String commentID, String projectID, String comment) {
        this.commentID = commentID;
        this.projectID = projectID;
        this.comment = comment;
    }

    public void createComment() {
        try {

            FileReader fileReader = new FileReader("comment.txt");
            String lastLine = "", currentLine;
            BufferedReader br = new BufferedReader(fileReader);

            while ((currentLine = br.readLine()) != null) {
                lastLine = currentLine;
            }

            String[] lines = lastLine.split("[,]", 2);
            int nextInt = (Integer.parseInt(lines[0].substring(2, 8)) + 1);
            String CM = String.format("%s%06d", "CM", nextInt);
            fileReader.close();
            br.close();
            this.commentID = CM;

            appendCMInFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Comment> getCMFromFile() {
        try {
            // get all the mini project information from txt file
            FileReader fileReader = new FileReader("comment.txt");
            String currentLine = "";
            BufferedReader br = new BufferedReader(fileReader);

            // if the txt paragraph line is not empty
            while ((currentLine = br.readLine()) != null) {
                // split all the "," and insert into array. Example: 1234,4321 => [1234],[4321]
                String[] lines = currentLine.split(",");
                commentList.add(new Comment(lines[0], lines[1], lines[2]));
            }
            // close the FileReder and BufferedReader
            fileReader.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return commentList;

    }

    private void appendCMInFile() {
        try {
            File file = new File("comment.txt");
            FileWriter fileWriter = new FileWriter(file, true);

            String outputLine = String.format("\r\n%s,%s,%s",
                    commentID,
                    projectID,
                    comment);
            fileWriter.write(outputLine);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public String toString() {
        String toString = "Comment ID: " + getCommentID()
                + "\nProject ID: " + getProjectID()
                + "\nComment: " + getComment();
        return toString;
    }

}
