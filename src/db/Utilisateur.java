package db;
public class Utilisateur {

    String pseudo;
    boolean admin;

    Utilisateur (String pseudo, boolean admin){
        this.pseudo = pseudo;
        this.admin = admin;
    }

    public String getPseudo() {
        return pseudo;
    }


}
