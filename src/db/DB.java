package db;

import java.sql.*;
import java.util.ArrayList;

public class DB implements IDBAccess {

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

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO utilisateur VALUES (?,?,?,?,?);");
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
    public Utilisateur[] getUsers() throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM list_usr");
        ArrayList<Utilisateur> arrayList = new ArrayList<>();

        try{
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                arrayList.add(new Utilisateur(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5)));

            }

            return arrayList.toArray(new Utilisateur[0]);
        }
        catch (SQLException e){
            throw e;
        }
    }

    @Override
    public boolean login(Utilisateur utilisateur) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("select * from login(?,?);");
        preparedStatement.setString(1,utilisateur.getPseudo());
        preparedStatement.setString(2,utilisateur.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean exist = false;
        while (resultSet.next()) {
            utilisateur.setEmail(resultSet.getString(2));
            utilisateur.setOrientation(resultSet.getString(4));
            utilisateur.setAdmin(resultSet.getBoolean(5));
            exist = true;
        }
        if (exist) {
            return true;
        }
        return false;
    }

    @Override
    public Biere[] getBeers() throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM boisson_alcolise");
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
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM boisson_non_alcolise");
        ArrayList<Boisson> arrayList = new ArrayList<>();
        try{
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                arrayList.add(new Boisson(resultSet.getString(1), resultSet.getInt(2), resultSet.getDouble(3),
                        resultSet.getDouble(4)));

            }
            return arrayList.toArray(new Boisson[0]);
        }
        catch (SQLException e){
            throw e;
        }
    }

     public Integer[] getTables(Timestamp date) throws SQLException{
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM table_libre(?)");
        preparedStatement.setObject(1,date);
        ArrayList<Integer> arrayList = new ArrayList<>();
        try{
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                arrayList.add(resultSet.getInt(1));

            }
            return arrayList.toArray(new Integer[0]);
        }
        catch (SQLException e){
            throw e;
        }
    }

    @Override
    public String[] getOrientation() throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT nom FROM orientation");
        ArrayList<String> arrayList = new ArrayList<>();
        try{
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                arrayList.add(resultSet.getString(1));
            }
            return arrayList.toArray(new String[0]);
        }
        catch (SQLException e){
            throw e;
        }
    }

    @Override
    public void addDrink(String name, int type, int quantite, int prixvente, int prixachat) throws SQLException{

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO boissons (nom, type, quantitestock, prixvente, prixachat) VALUES (?,?,?,?,?);");
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
    public void addComment(int note, String comment, Utilisateur user, String drinkName) throws SQLException {
        CallableStatement callableStatement  = connection.prepareCall("CALL add_comment(?,?,?,?);");
        callableStatement.setInt(1,note);
        callableStatement.setString(2,comment);
        callableStatement.setString(3,user.getPseudo());
        callableStatement.setString(4,drinkName);

        try{
            callableStatement.executeUpdate();
        }
        catch (SQLException e){
            throw e;
        }

    }

    @Override
    public String[] getOrientationLeaderboard() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM classement_orientation ");

        ArrayList<String> arrayList = new ArrayList<>();


        try{
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                arrayList.add(resultSet.getString(1) + "," + resultSet.getString(2));
            }

            return arrayList.toArray(new String[0]);
        }
        catch (SQLException e){
            throw e;
        }

    }

    @Override
    public boolean createEvent(String nom, Timestamp date, Boisson boisson, int quantite, int table) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO evenement(nom, date, organisateur) VALUES (?,?,?);");

        preparedStatement.setString(1,nom);
        preparedStatement.setTimestamp(2,date);
        preparedStatement.setString(3,utilisateurCourant.getPseudo());

        try{
            if (preparedStatement.executeUpdate() == 1){
                preparedStatement.clearParameters();

                if (boisson != null && quantite > 0 ){
                    preparedStatement = connection.prepareStatement("INSERT INTO reservation VALUES (?,?,?);");
                    preparedStatement.setInt(1,getBoisson(boisson.getName()));
                    preparedStatement.setInt(2,getEvent(nom));
                    preparedStatement.setInt(3,quantite);
                    preparedStatement.executeUpdate();
                }
                preparedStatement.clearParameters();
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
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM evenement WHERE nom = ?");
        preparedStatement.setString(1,name);

        try{
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.clearParameters();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        }

        catch (SQLException e){
            throw e;
        }

    }
    @Override
    public int getBoisson(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM boissons WHERE nom = ?");
        preparedStatement.setString(1,name);

        try{
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.clearParameters();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
            return 0;
        }

        catch (SQLException e){
            throw e;
        }

    }

    @Override
    public Commentaire[] getComments(String drinkName) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM get_all_comment(?)");
        preparedStatement.setString(1,drinkName);

        ArrayList<Commentaire> arrayList = new ArrayList<>();


        try{
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                Commentaire c = new Commentaire(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3));
                arrayList.add(c);
            }
            return arrayList.toArray(new Commentaire[0]);
        }
        catch (SQLException e){
            throw e;
        }

    }
}


