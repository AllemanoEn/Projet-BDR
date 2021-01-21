package db;

import java.sql.*;


public interface IDBAccess {

    void startDB() throws SQLException;

    boolean createUser(String username, String password, String email, int orientation, boolean isAdmin) throws SQLException;

    boolean login(Utilisateur utilisateur) throws SQLException;

    void addDrink(String name, int type, int quantite, double prixvente, double prixachat) throws SQLException;

    void addBeer(String name, int type, int quantite, double prixvente, double prixachat, double pourcentage,
                        double contenance, String pays, String region, String brasserie, String style) throws SQLException;

    Boisson getDrink(String name) throws SQLException;

    Biere[] getBeers() throws SQLException;

    Boisson[] getSoftDrinks() throws SQLException;

    String[] getAllBoissons() throws SQLException;

    Integer[] getTables(Timestamp date) throws SQLException;

    void addComment(int note, String commentBy, Utilisateur user, String drinkName) throws SQLException;

    void updateDrink(String drinkName, int quantite, double prixVente, double prixAchat) throws SQLException;
    void updateBeer(String drinkName, int quantite, double prixVente, double prixAchat, double contenance, double pourcentage) throws SQLException;

    Commentaire[] getComments(String drinkName) throws SQLException;

    String[] getOrientationLeaderboard() throws SQLException;

    boolean createEvent(String nom, Timestamp date, Boisson boisson, int quantite, int table) throws SQLException;

    int getEvent(String name) throws SQLException;

    int getBoisson(String name) throws SQLException;

    int getStyle(String name) throws SQLException;

    void setUser(Utilisateur u);

    Utilisateur[] getUsers() throws SQLException;

    String[] getAllUsers() throws SQLException;

    String[] getOrientation() throws SQLException;

    void addTransaction(String u, String b, int quantite) throws SQLException;

    boolean isConnected();

    boolean isConnectedAsAdmin();

}
