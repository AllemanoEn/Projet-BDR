package db;
public class Utilisateur {

    private String pseudo;
    private String password;
    boolean admin;

    public Utilisateur(String pseudo, String password, boolean admin){
        this.pseudo = pseudo;
        this.password = password;
        this.admin = admin;
    }

    public String getPseudo(){
        return pseudo;
    }

    public String getPassword(){
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }
}
