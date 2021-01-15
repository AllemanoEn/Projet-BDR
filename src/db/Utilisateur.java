package db;
public class Utilisateur {

    private String pseudo;
    private String password;
    boolean admin;

    public Utilisateur(String pseudo, String password ,boolean admin){
        this.password = password;
        this.pseudo = pseudo;
        this.admin = admin;
    }

    public String getPseudo(){
        return pseudo;
    }

    public String getPassword(){
        return password;
    }

}
