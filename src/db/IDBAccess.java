/*
 * Fichier  : IDBAccess.java
 * Auteurs  : Allemano Enzo, Paulus Yohann, Merk Melvin
 * But      : Défini toutes les fonctions nécessaire à l'affiche de la DB
 * Date     : 21.01.2021
 */
package db;

import java.sql.*;

public interface IDBAccess {

    /**
     * Permet de se connecter à la base de donnée
     * @throws SQLException
     */
    void startDB() throws SQLException;

    //User function

    /**
     * Permet de créer un nouvel utilisateur
     * @param username      nom de l'utilisateur
     * @param password      mot de passe de l'utilisateur
     * @param email         email de l'utilisateur
     * @param orientation   orientation de l'utilisateur
     * @param isAdmin       défini si l'utilisateur est admin
     * @return              true si l'ajout a fonctionné
     * @throws SQLException
     */
    boolean createUser(String username, String password, String email, int orientation, boolean isAdmin) throws SQLException;

    /**
     * Set l'utilisateur courant
     * @param u
     */
    void setUser(Utilisateur u);

    /**
     * Retourne un tableau de tout les utilisateurs
     * @return  le tableau des utilisateurs
     * @throws SQLException
     */
    Utilisateur[] getUsers() throws SQLException;

    /**
     * Retourne un tableau du nom des utilisateurs
     * @return              le tableau des noms des utilisateurs
     * @throws SQLException
     */
    String[] getAllUsers() throws SQLException;

    /**
     * Permet de se connecter
     * @param utilisateur   l'utilisateur
     * @return              true si la connexion a fonctionnée
     * @throws SQLException
     */
    boolean login(Utilisateur utilisateur) throws SQLException;

    //Drink function

    /**
     * Permet d'ajouter une boisson
     * @param name          nom boisson
     * @param type          type (alcool/soft)
     * @param quantite      quantite en stock
     * @param prixvente     prix de vente
     * @param prixachat     prix achat
     * @throws SQLException
     */
    void addDrink(String name, int type, int quantite, double prixvente, double prixachat) throws SQLException;

    /**
     * Permet d'ajouter une bière
     * @param name          nom biere
     * @param quantite      quantite en stock
     * @param prixvente     prix de vente
     * @param prixachat     prix d'achat
     * @param pourcentage   pourcentage d'alcool
     * @param contenance    contenance de la boisson
     * @param pays          pays d'origine
     * @param region        region
     * @param brasserie     brasserie
     * @param style         style(blonde, blanche, etc)
     * @throws SQLException
     */
    void addBeer(String name, int quantite, double prixvente, double prixachat, double pourcentage, double contenance, String pays, String region, String brasserie, String style) throws SQLException;

    /**
     * Permet de récupérer une boisson
     * @param name          nom de la boisson
     * @return              la boisson
     * @throws SQLException
     */
    Boisson getDrink(String name) throws SQLException;

    /**
     * Permet de mettre à jour une boisson
     * @param drinkName     nom de la boisson
     * @param quantite      quantite
     * @param prixVente     prix de vente
     * @param prixAchat     prix d'achat
     * @throws SQLException
     */
    void updateDrink(String drinkName, int quantite, double prixVente, double prixAchat) throws SQLException;

    /**
     * Permet de mettre à jour une bière
     * @param drinkName nom de la bière
     * @param quantite quantité de la bière
     * @param prixVente prix de vente de la bière
     * @param prixAchat prix d'achat de la bière
     * @param contenance contenance de la bière
     * @param pourcentage pourcentage de la bière
     * @throws SQLException
     */
    void updateBeer(String drinkName, int quantite, double prixVente, double prixAchat, double contenance, double pourcentage) throws SQLException;

    /**
     * Permet de récupérer toutes les bières
     * @return  une tableau de de bières
     * @throws SQLException
     */
    Biere[] getBeers() throws SQLException;

    /**
     * Permet de récupérer toutes les boissons non-alcoolisées
     * @return un tableau de boisson
     * @throws SQLException
     */
    Boisson[] getSoftDrinks() throws SQLException;

    /**
     * Permet de récupérer toutes les boissons
     * @return une liste de String contenant tout les noms des boissons
     * @throws SQLException
     */
    String[] getAllBoissons() throws SQLException;

    /**
     * Permet de récupérer l'id du boisson
     * @param name Nom de la boisson
     * @return l'id de la boisson
     * @throws SQLException
     */
    int getBoisson(String name) throws SQLException;

    /**
     * Permet de récupérer l'id d'un type de boisson
     * @param name type concerné
     * @return l'id du type
     * @throws SQLException
     */
    int getType(String name) throws SQLException;

    //Comment function

    void addComment(int note, String commentBy, Utilisateur user, String drinkName) throws SQLException;

    /**
     * Permet de récuper tout les commentaires d'une boisson
     * @param drinkName Boisson qui concerne les commentaires
     * @return Un tableau de commenatire
     * @throws SQLException
     */
    Commentaire[] getComments(String drinkName) throws SQLException;

    //Event function

    /**
     * Permet de créer un event
     * @param nom nom de l'event
     * @param date date de l'event
     * @param boisson Boisson de l'event
     * @param quantite Quantité de boisson
     * @param table Table reservé par l'event
     * @return true si la commande SQL c'est bien déroulé
     * @throws SQLException
     */
    boolean createEvent(String nom, Timestamp date, Boisson boisson, int quantite, int table) throws SQLException;

    /**
     * Retourne l'id d'un event
     * @param name Event à identifier
     * @return l'id de l'event identifier
     * @throws SQLException
     */
    int getEvent(String name) throws SQLException;

    //Orientation function

    /**
     * Retourne toutesles orientations
     * @return Un tableau de string avec le nom des orientations
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
