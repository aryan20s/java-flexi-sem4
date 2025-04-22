package movierating.db.entities;

import java.sql.SQLException;

public interface WriteableDAO {
    boolean insert() throws SQLException;
}
