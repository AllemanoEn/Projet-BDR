public class Utilisateur {

    private String pseudo;
    private String password;
    boolean admin;

    Utilisateur (String pseudo, boolean admin){
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
