import javax.xml.transform.Result;
import java.sql.*;

public interface IDBAccess {

    void startDB() throws SQLException;

    boolean createUser(String username, String password, String email, int orientation) throws SQLException;

    ResultSet login(String username, String password);

    ResultSet getLeaderboard();

    ResultSet addDrink(String name, String type);

    ResultSet getDrink(String name) throws SQLException;

    ResultSet addComment(String drinkName, String comment, int note);

    ResultSet getOrientationLeaderboard() throws SQLException;


}
