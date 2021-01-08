import javax.xml.transform.Result;
import java.sql.*;

public interface IDBAccess {

    void startDB() throws SQLException;

    ResultSet createUser(String username, String password, String email, String orientation);

    ResultSet login(String username, String password);

    ResultSet getLeaderboard();

    ResultSet addDrink(String name, String type);

    ResultSet getDrink(String name);

    ResultSet addComment(String drinkName, String comment, int note);

    ResultSet getOrientationLeaderboard();


}
