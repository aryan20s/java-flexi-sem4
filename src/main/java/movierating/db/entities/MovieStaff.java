package movierating.db.entities;

public class MovieStaff {
    private final String name, role;

    public MovieStaff(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
