import java.sql.*;

public class DB implements IDBAccess{
    PreparedStatement preparedStatement;
    Connection connection;


    @Override
    public void startDB() throws SQLException {

        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdr?currentSchema=projet", "bdr", "bdr");

       /*
        ResultSet resultSet = statement.executeQuery("SELECT * FROM pagila.actor");
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(", \n ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
        }

        */
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
    public ResultSet login(String username, String password) {
        return null;
    }

    @Override
    public ResultSet getLeaderboard() {
        return null;
    }

    @Override
    public ResultSet addDrink(String name, String type) {
        return null;
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
                if (i > 1) System.out.print(",\n ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
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
                if (i > 1) System.out.print(",\n ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
        }
        return null;
    }
}


