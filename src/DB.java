import java.sql.*;

public class DB {

    public static void main(String ... args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdr?currentSchema=projet", "bdr", "bdr");

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

    }

}


