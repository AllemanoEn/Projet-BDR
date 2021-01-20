package db;

import java.sql.*;
import java.util.ArrayList;

public class DB implements IDBAccess {
    PreparedStatement preparedStatement;
    Connection connection;
    Utilisateur utilisateurCourant = null;

    public void setUser(Utilisateur u){
        utilisateurCourant = u;
    }

    public boolean isConnected(){
        return utilisateurCourant == null;
    }

    public boolean isConnectedAsAdmin(){
        return utilisateurCourant == null && utilisateurCourant.isAdmin();
    }

    @Override
    public void startDB() throws SQLException {

        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdr?currentSchema=projet", "bdr", "bdr");
    }

    @Override
    public boolean createUser(String username, String password, String email, int orientation) throws SQLException {

        preparedStatement = connection.prepareStatement("INSERT INTO utilisateur VALUES (?,?,?,?,?);");
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        preparedStatement.setString(3,email);
        preparedStatement.setInt(4,orientation);
        preparedStatement.setBoolean(5,false);

        try{
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.out.print(e);
            return false;
        }
    }

    @Override
    public boolean login(Utilisateur utilisateur) throws SQLException {

        preparedStatement = connection.prepareStatement("SELECT count(pseudo) FROM utilisateur WHERE pseudo = ? AND password = ? AND admin = ?;");
        preparedStatement.setString(1,utilisateur.getPseudo());
        preparedStatement.setString(2,utilisateur.getPassword());
        preparedStatement.setBoolean(3,utilisateur.isAdmin());
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean exist = false;
        while (resultSet.next()) {
             exist = resultSet.getBoolean(1);
        }
        if (exist) {
            return true;
        }
        return false;
    }

    @Override
    public Biere[] getBeers() throws SQLException{
        preparedStatement = connection.prepareStatement("SELECT * FROM boisson_alcolise");
        ArrayList<Biere> arrayList = new ArrayList<>();

        try{
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                arrayList.add(new Biere(resultSet.getString(1), resultSet.getInt(2), resultSet.getDouble(3),
                        resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getInt(6), resultSet.getDouble(7),
                        resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11)));

            }

           return arrayList.toArray(new Biere[0]);
        }
        catch (SQLException e){
            throw e;
        }
    }

    @Override
    public Boisson[] getSoftDrinks() throws SQLException{
        preparedStatement = connection.prepareStatement("SELECT * FROM boisson_non_alcolise");
        ArrayList<Boisson> arrayList = new ArrayList<>();
        try{
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                arrayList.add(new Boisson(resultSet.getString(1), resultSet.getInt(2), resultSet.getDouble(3),
                        resultSet.getDouble(4)));

            }
//test
            return arrayList.toArray(new Boisson[0]);
        }
        catch (SQLException e){
            throw e;
        }
    }




    @Override
    public void addDrink(String name, int type, int quantite, int prixvente, int prixachat) throws SQLException{

        preparedStatement = connection.prepareStatement("INSERT INTO boissons (nom, type, quantitestock, prixvente, prixachat) VALUES (?,?,?,?,?);");
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,type);
        preparedStatement.setInt(3,quantite);
        preparedStatement.setInt(4,prixvente);
        preparedStatement.setInt(5,prixachat);

        try{
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw e;
        }
    }


    @Override
    public Boisson getDrink(String name) throws SQLException {

        Boisson boisson = null;
        Boisson[] boissons = getSoftDrinks();

        for(Boisson boisson1 : boissons) {
            if(boisson1.getName().equals(name)){
                boisson = boisson1;
            }
        }

        if(boisson == null) {
            Biere[] bieres = getBeers();

            for(Biere biere1 : bieres) {
                if(biere1.getName().equals(name)){
                    boisson = biere1;
                }
            }
        }

        return boisson;
    }

    @Override
    public void addComment(int note, String comment, Utilisateur user, int boisson) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO commentaire (nom, type, quantitestock, prixvente, prixachat) VALUES (?,?,?,?);");
        preparedStatement.setInt(1,note);
        preparedStatement.setString(2,comment);
        preparedStatement.setString(3,user.getPseudo());
        preparedStatement.setInt(4,boisson);

        try{
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw e;
        }
    }

    @Override
    public String[] getOrientationLeaderboard() throws SQLException {

        preparedStatement = connection.prepareStatement("SELECT * FROM classement_orientation ");

        ArrayList<String> arrayList = new ArrayList<>();


        try{
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                arrayList.add(resultSet.getString(1) + "  :  " + resultSet.getString(2));
            }

            return arrayList.toArray(new String[0]);
        }
        catch (SQLException e){
            throw e;
        }

    }

    @Override
    public boolean createEvent(String nom, String date, Utilisateur utilisateur,Boisson boisson, int quantite, int table) throws SQLException {

        preparedStatement = connection.prepareStatement("INSERT INTO evenement(nom, date, organisateur) VALUES (?,?,?);");

        preparedStatement.setString(1,nom);
        preparedStatement.setString(2,date);
        preparedStatement.setString(3,utilisateur.getPseudo());

        try{ // TODO :  controler que l'utilisateur veuille réserver des boissons + tables
            if (preparedStatement.executeUpdate() == 1){

                if (boisson != null && quantite > 0 ){
                    preparedStatement = connection.prepareStatement("INSERT INTO reservation VALUES (?,?,?);");
                    preparedStatement.setInt(1,getBoisson(boisson.getName()));
                    preparedStatement.setInt(2,getEvent(nom));
                    preparedStatement.setInt(3,quantite);
                    preparedStatement.executeUpdate();
                }
                if (table > 0){
                    preparedStatement = connection.prepareStatement("INSERT INTO table_event VALUES (?,?);");
                    preparedStatement.setInt(1,table);
                    preparedStatement.setInt(2,getEvent(nom));
                    preparedStatement.executeUpdate();
                }

                return true;
            }

        }

        catch (SQLException e){
            throw e;
        }
        return false;
    }

    @Override
    public int getEvent(String name) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT id FROM evenement WHERE nom = ?");
        preparedStatement.setString(1,name);

        try{
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt(1);
        }

        catch (SQLException e){
            throw e;
        }

    }
    @Override
    public int getBoisson(String name) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT id FROM boissons WHERE nom = ?");
        preparedStatement.setString(1,name);

        try{
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt(1);
        }

        catch (SQLException e){
            throw e;
        }

    }

}


