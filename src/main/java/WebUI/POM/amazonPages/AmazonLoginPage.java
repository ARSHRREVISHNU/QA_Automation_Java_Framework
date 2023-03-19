package WebUI.POM.amazonPages;

import CommonUtilities.CommonUtility;
import Constants.FilePaths;
import WebUI.Base.SeleniumUtilities;

import java.util.Properties;

public class AmazonLoginPage extends SeleniumUtilities {


    static AmazonLoginPage amazonLoginPage;
    Properties amazonPageProperties;

    private AmazonLoginPage(){

        amazonPageProperties = CommonUtility.readPropertyFile(FilePaths.AMAZON_LOGIN_PAGE);
    }

    public static AmazonLoginPage getInstance(){

        if(amazonLoginPage == null){

            synchronized(AmazonLoginPage.class) {
                amazonLoginPage = new AmazonLoginPage();
            }

        }

        return amazonLoginPage;
    }


    public void webDriverLoad(){
        initialization("https://www.amazon.in/", "chrome");
    }


    public boolean amazonPageLoadCheck(){
        return getElementDisplayedOrNot(amazonPageProperties.getProperty("inputSearchBox"));
    }

    public String preSelectedOptionCheck(){
        return preSelectedDropdownValue(amazonPageProperties.getProperty("selectDropdownButtonCheck"));
    }

    public String searchBarPlaceHolder(String attributeValue){
        return getAttributeValue(amazonPageProperties.getProperty("inputSearchBox"), attributeValue);
    }

//    public void searchPageNavigationCheck(String optionToBeSelected, String text){
//        selectValueInDropdown(amazonPageProperties.getProperty("selectDropdownButtonCheck"), optionToBeSelected);
//        enterText(amazonPageProperties.getProperty("inputSearchBox"), text);
//        getElement(amazonPageProperties.getProperty("searchButton")).click();
//    }
}
