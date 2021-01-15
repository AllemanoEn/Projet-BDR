package db;
public class Utilisateur {

    String pseudo;
    boolean admin;

    public Utilisateur(String pseudo, String password ,boolean admin){
        this.password = password;
        this.pseudo = pseudo;
        this.admin = admin;
    }

    public String getPseudo() {
        return pseudo;
    }


}
