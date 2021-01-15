import db.DB;
import db.IDBAccess;
import db.Utilisateur;
import gui.GUI;
import java.sql.SQLException;

import java.sql.SQLException;

public class Main {
    public static void main(String ... args) throws SQLException {
        GUI gui = new GUI();

        gui.show();

        IDBAccess oui = new DB();

        oui.startDB();

        Utilisateur u1 = new Utilisateur("Paul", "Issier",false);
        Utilisateur u2 = new Utilisateur("Paul", "mypassword",false);

        if(oui.login(u1)){
            System.out.println("L'utilisateur 1 est connecter");
        }
        else {
            System.out.println("L'utilisateur 1 n'est pas connecter");
        }

        if(oui.login(u2)){
            System.out.println("L'utilisateur 2 est connecter");
        }
        else {
            System.out.println("L'utilisateur 2 n'est pas connecter");
        }

    }
}
