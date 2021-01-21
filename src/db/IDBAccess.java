package db;

import java.sql.*;


public interface IDBAccess {

    void startDB() throws SQLException;

    boolean createUser(String username, String password, String email, int orientation) throws SQLException;

    boolean login(Utilisateur utilisateur) throws SQLException;

    void addDrink(String name, int type, int quantite, int prixvente, int prixachat) throws SQLException;


    public void deleteDrink(String name) throws SQLException;

    Boisson getDrink(String name) throws SQLException;

    Biere[] getBeers() throws SQLException;

    Boisson[] getSoftDrinks() throws SQLException;

    Integer[] getTables(Timestamp date) throws SQLException;

    void addComment(int note, String commentBy, Utilisateur user, String drinkName) throws SQLException;

    Commentaire[] getComments(String drinkName) throws SQLException;

    String[] getOrientationLeaderboard() throws SQLException;

    boolean createEvent(String nom, Timestamp date, Boisson boisson, int quantite, int table) throws SQLException;

    int getEvent(String name) throws SQLException;

    int getBoisson(String name) throws SQLException;

    void setUser(Utilisateur u);

    Utilisateur[] getUsers() throws SQLException;

    String[] getOrientation() throws SQLException;

    boolean isConnected();

    boolean isConnectedAsAdmin();

}
