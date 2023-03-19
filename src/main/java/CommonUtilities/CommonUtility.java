package CommonUtilities;


import lombok.SneakyThrows;

import java.io.*;
import java.util.Properties;


public class CommonUtility {




    @SneakyThrows
    public static Properties readPropertyFile(String propertyFilePath){

        FileReader fileReader = new FileReader(propertyFilePath);
        Properties properties = new Properties();
        try{
            properties.load(fileReader);
            return properties;
        }
        catch(Exception exception){
            throw new NullPointerException("Property file" +propertyFilePath+"is not present. Please check!");

        }
        finally{
            fileReader.close();
        }
    }


}
