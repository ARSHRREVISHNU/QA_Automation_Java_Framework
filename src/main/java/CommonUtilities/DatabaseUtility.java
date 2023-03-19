package CommonUtilities;

import org.openqa.selenium.InvalidArgumentException;

import java.sql.Connection;

public class DatabaseUtility {

        private static Connection connection;

        public Connection setUpConnection(String driverClass, Connection connectionUrl)
        {
            try{
                Class.forName(driverClass);
            }
            catch(ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            if(connectionUrl == null)
                throw new InvalidArgumentException("Value in connection class in null. Please Check");
            return connectionUrl;
        }
}
