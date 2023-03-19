package Constants;

import java.io.File;

public class FilePaths {

    public static final String RESOURCE_FOLDER_PATH = "src"+File.separator+"main"+File.separator+"resources"+File.separator;

    //********************Pages******************

    public static final String AMAZON_LOGIN_PAGE = RESOURCE_FOLDER_PATH+File.separator+"WebUILocatorsRepo"+File.separator+"AmazonPages"+File.separator+"AmazonLoginPage.properties";

    public static final String AMAZON_DATA = RESOURCE_FOLDER_PATH+File.separator+"TestData"+File.separator+"AmazonData.json";

    public static final String REQRES_DATA = RESOURCE_FOLDER_PATH+File.separator+"TestData"+File.separator+"ReqresData.json";

    public static final String EXTENTHTML_REPORT_PATH = System.getProperty("user.dir")+File.separator+"extentReportsOutput"+File.separator+"htmlReports"+File.separator;

    public static final String EXTENT_REPORT_PROPERTIES = RESOURCE_FOLDER_PATH+File.separator+"CommonProperties"+File.separator+"ExtentProperties.properties";


    public static final String DATABASE_PROPERTIES = RESOURCE_FOLDER_PATH+File.separator+"Database"+File.separator+"db.properties";

    public static final String API_CONFIG_PROPERTIES = RESOURCE_FOLDER_PATH+File.separator+"CommonProperties"+File.separator+"apiConfig.properties";

    public static final String Email_CONFIG_PROPERTIES = RESOURCE_FOLDER_PATH+File.separator+"CommonProperties"+File.separator+"EmailConfig.properties";

}
