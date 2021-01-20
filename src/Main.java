import db.DB;
import db.IDBAccess;

import java.sql.SQLException;
import gui.MainView;

public class Main {
    public static void main(String ... args) throws SQLException {

        IDBAccess DBprojet = new DB();

        MainView mainView = new MainView(DBprojet);

    }
}
