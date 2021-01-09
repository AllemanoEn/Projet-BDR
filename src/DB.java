import java.sql.*;

public class DB implements IDBAccess{

    Connection connection;
    Statement statement;

    @Override
    public void startDB() throws SQLException {

        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdr?currentSchema=projet", "bdr", "bdr");
        this.statement= connection.createStatement();

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
    public ResultSet createUser(String username, String password, String email, String orientation) throws SQLException {

        statement.executeUpdate("INSERT INTO utilisateur VALUES ('"+username+"', '"+password+"','"+email+"','"+orientation+"',FALSE); ");

        return null;
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

    @Override
    public ResultSet getDrink(String name) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM boisson_alcolise WHERE nom = '"+ name +"'");
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

        ResultSet resultSet = statement.executeQuery("SELECT * FROM classement_orientation ");
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

    /*public static void main(String ... args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://projetbdr2020.postgres.database.azure.com/", "bdr@projetbdr2020", "root-2020");

        System.out.println("Java JDBC PostgreSQL Example");
        // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within
        // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
//          Class.forName("org.postgresql.Driver");

        System.out.println("Connected to PostgreSQL database!");
        Statement statement = connection.createStatement();
        System.out.println("Reading car records...");


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

    }*/

}


