import java.sql.*;

public interface IDBAccess {

    void startDB() throws SQLException;

    ResultSet createUser(String username, String password, String email, String orientation);

    ResultSet login(String username, String password);

    ResultSet getLeaderboard();

}
