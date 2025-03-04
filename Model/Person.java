public abstract class Person {
    private String id;
    private String username, password;
    public Person() {
    }

    public Person(String username, String password) {
        setName(username);
        setPassword(password);
    }
    
    public Person(String id, String username, String password) {
        setId(id);
        setName(username);
        setPassword(password);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    // public String toString() {
    // return nm + gen;
    // };

    public abstract String toString();
}