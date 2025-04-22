package movierating.db.entities;

public class MovieActor {
    private final String name, role;

    public MovieActor(String name, String role) {
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
