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
    public Biere getDrink(String name) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM boisson_alcolise WHERE nom = ?");
        preparedStatement.setString(1,name);

        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i) +",\n");
            }
        }
        return null;
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
        String[] s = new String[0];
        int i = 0;


        try{
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                s[i] = resultSet.getString(1) + " " + resultSet.getString(2);
                i++;
            }

            return s;
        }
        catch (SQLException e){
            throw e;
        }

    }
}


