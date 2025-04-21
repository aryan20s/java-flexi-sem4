package movierating.db.entities;

public class User {
    private final String username;
    private final int personID;

    public User(String username, int personID) {
        this.username = username;
        this.personID = personID;
    }

    public String getUsername() {
        return username;
    }

    public int getPersonID() {
        return personID;
    }
}
