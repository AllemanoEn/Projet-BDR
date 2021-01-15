import javax.xml.transform.Result;
import java.sql.*;

public interface IDBAccess {

    void startDB() throws SQLException;

    boolean createUser(String username, String password, String email, int orientation) throws SQLException;

    boolean login(String username, String password) throws SQLException;

    ResultSet getLeaderboard() throws SQLException;

    void addDrink(String name, int type, int quantite, int prixvente, int prixachat) throws SQLException;

    ResultSet getDrink(String name) throws SQLException;

    ResultSet addComment(String drinkName, String comment, int note);

    ResultSet getOrientationLeaderboard() throws SQLException;


}
