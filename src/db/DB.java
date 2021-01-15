package db;

import db.IDBAccess;

import java.sql.*;

public class DB implements IDBAccess {
    PreparedStatement preparedStatement;
    Connection connection;


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
    public boolean login(String username, String password) throws SQLException {

        preparedStatement = connection.prepareStatement("SELECT count(pseudo) FROM utilisateur WHERE pseudo = ? AND password = ?;");
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
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
    public ResultSet getLeaderboard() throws SQLException{
        preparedStatement = connection.prepareStatement("SELECT * FROM classement_biere");
        try{
           return preparedStatement.executeQuery();
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


     // non
    @Override
    public ResultSet getDrink(String name) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM boisson_alcolise WHERE nom =?");
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
    public ResultSet addComment(String drinkName, String comment, int note) {
        return null;
    }

    @Override
    public ResultSet getOrientationLeaderboard() throws SQLException {

        preparedStatement = connection.prepareStatement("SELECT * FROM classement_orientation ");
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

}


