import java.io.*;
import java.util.*;

public class Admin extends Person {
    public Admin() {
    }

    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin(String id, String username, String password) {
        super(id,username, password);

    }

    @Override
    public String toString() {
        String toString = "ID: " + super.getId()
                + "\nUsename: " + super.getName()
                + "\nPassword: " + super.getPassword();
        return toString;
    }
}
