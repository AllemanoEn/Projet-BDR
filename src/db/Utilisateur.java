package db;
public class Utilisateur {

    private String pseudo;
    private String email;
    private String password;
    private String orientation;
    boolean admin;

    public Utilisateur(String pseudo, String password, boolean admin){
        this.pseudo = pseudo;
        this.password = password;
        this.admin = admin;
    }

    public Utilisateur(String pseudo, String email, String password, String orientation, boolean admin){
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.orientation = orientation;
        this.admin = admin;
    }

    public Utilisateur(String pseudo, String password){
        this.pseudo = pseudo;
        this.password = password;
        this.admin = false;
    }

    public String getPseudo(){
        return pseudo;
    }

    public String getPassword(){
        return password;
    }

    public String getOrientation() {
        return orientation;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return pseudo;
    }
}
