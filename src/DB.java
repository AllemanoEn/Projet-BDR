import java.sql.*;

public class DB implements IDBAccess{

    @Override
    public void startDB() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdr?currentSchema=projet", "bdr", "bdr");
        Statement statement = connection.createStatement();
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
    }

    @Override
    public ResultSet createUser(String username, String password, String email, String orientation) {
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

    /*public static void main(String ... args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://projetbdr2020.postgres.database.azure.com/", "bdr@projetbdr2020", "root-2020");
>>>>>>> main

        System.out.println("Java JDBC PostgreSQL Example");
        // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within
        // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
        //Class.forName("org.postgresql.Driver");

        System.out.println("Connected to PostgreSQL database!");
        Statement statement = connection.createStatement();
        System.out.println("Reading car records...");


        ResultSet resultSet = statement.executeQuery("SELECT * FROM utilisateur");
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


