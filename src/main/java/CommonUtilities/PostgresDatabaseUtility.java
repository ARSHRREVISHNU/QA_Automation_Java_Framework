package CommonUtilities;

import Constants.FilePaths;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDatabaseUtility extends DatabaseUtility{

    private static PostgresDatabaseUtility postgresDatabaseUtilityInstance;
    private static String driverClass, pgUserName, pgPassword;
    private static String databaseUrl;

    private PostgresDatabaseUtility(){

        databaseUrl = CommonUtility.readPropertyFile(FilePaths.DATABASE_PROPERTIES).getProperty("dataBaseUrl");
        driverClass = CommonUtility.readPropertyFile(FilePaths.DATABASE_PROPERTIES).getProperty("postgresDriverClass");
        pgUserName = CommonUtility.readPropertyFile(FilePaths.DATABASE_PROPERTIES).getProperty("pgUserName");
        pgPassword = CommonUtility.readPropertyFile(FilePaths.DATABASE_PROPERTIES).getProperty("pgPassword");
    }

    public static PostgresDatabaseUtility getInstance(){
        if(postgresDatabaseUtilityInstance==null)
            postgresDatabaseUtilityInstance = new PostgresDatabaseUtility();

        return postgresDatabaseUtilityInstance;
    }

    public Connection postgresDBConnection(){
        Connection connection;

        try
        {
            connection = DriverManager.getConnection(databaseUrl, pgUserName, pgPassword);
            setUpConnection(driverClass, connection);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            connection = null;
        }
        return connection;
    }

}
