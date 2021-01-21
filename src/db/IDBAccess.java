/*
 * Fichier  : IDBAccess.java
 * Auteurs  : Allemano Enzo, Paulus Yohann, Merk Melvin
 * But      : Défini toutes les fonctions nécessaire à l'affiche de la DB
 * Date     : 21.01.2021
 */
package db;

import java.sql.*;

public interface IDBAccess {

    void startDB() throws SQLException;

    //User function

    boolean createUser(String username, String password, String email, int orientation, boolean isAdmin) throws SQLException;

    void setUser(Utilisateur u);

    Utilisateur[] getUsers() throws SQLException;

    String[] getAllUsers() throws SQLException;

    boolean login(Utilisateur utilisateur) throws SQLException;

    //Drink function

    void addDrink(String name, int type, int quantite, double prixvente, double prixachat) throws SQLException;

    void addBeer(String name, int type, int quantite, double prixvente, double prixachat, double pourcentage, double contenance, String pays, String region, String brasserie, String style) throws SQLException;

    Boisson getDrink(String name) throws SQLException;

    void updateDrink(String drinkName, int quantite, double prixVente, double prixAchat) throws SQLException;

    void updateBeer(String drinkName, int quantite, double prixVente, double prixAchat, double contenance, double pourcentage) throws SQLException;

    Biere[] getBeers() throws SQLException;

    Boisson[] getSoftDrinks() throws SQLException;

    String[] getAllBoissons() throws SQLException;

    int getBoisson(String name) throws SQLException;

    int getStyle(String name) throws SQLException;

    //Comment function

    void addComment(int note, String commentBy, Utilisateur user, String drinkName) throws SQLException;

    Commentaire[] getComments(String drinkName) throws SQLException;

    //Event function

    boolean createEvent(String nom, Timestamp date, Boisson boisson, int quantite, int table) throws SQLException;

    int getEvent(String name) throws SQLException;

    //Orientation function

    /**
     * Retourne toutes
     * @return
     * @throws SQLException
     */
    String[] getOrientation() throws SQLException;

    /**
     * Permet de retourner les orientations classées par bières bues
     * @return Un tableau de String qui contient le nom des orientations
     * @throws SQLException
     */
    String[] getOrientationLeaderboard() throws SQLException;

    //Other function

    /**
     * Permet de retourner la liste des tables disponibles à une certaine date
     * @param date
     * @return Tableau de int qui correspond à l'id des tables
     * @throws SQLException
     */
    Integer[] getTables(Timestamp date) throws SQLException;

    /**
     * Permet d'ajouter une transaction
     * @param u Utilisateur qui a efféctué la transaction
     * @param b Boisson achetée
     * @param quantite Quantité de boisson achetée
     * @throws SQLException
     */
    void addTransaction(String u, String b, int quantite) throws SQLException;
}
