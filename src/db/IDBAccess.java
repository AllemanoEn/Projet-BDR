package db;

import java.sql.*;


public interface IDBAccess {

    void startDB() throws SQLException;

    boolean createUser(String username, String password, String email, int orientation) throws SQLException;

    boolean login(Utilisateur utilisateur) throws SQLException;

    void addDrink(String name, int type, int quantite, int prixvente, int prixachat) throws SQLException;

    Boisson getDrink(String name) throws SQLException;

    Biere[] getBeers() throws SQLException;

    Boisson[] getSoftDrinks() throws SQLException;

    void addComment(int note, String commentBy,Utilisateur user, int boisson) throws SQLException;

    String[] getOrientationLeaderboard() throws SQLException;

    void setUser(Utilisateur u);

    boolean isConnected();

    boolean isConnectedAsAdmin();

}
