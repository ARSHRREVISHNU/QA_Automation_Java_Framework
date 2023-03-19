package AmazonPages.LandingPage;

import AmazonPages.TestUtils;
import Constants.FilePaths;
import io.cucumber.java.ja.但し;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LandingPageTest extends TestUtils {


    @Test
    public void landingPageLoadCheck(Method m){
        setMethodName(m);
        test.info("Amazon PageLoad Check");
        Assert.assertEquals(amazonLoginPage.amazonPageLoadCheck(), getValueFromJson(FilePaths.AMAZON_DATA, "data[0].presentOrNot"));
    }


    @Test
    public void placeHolderCheck(Method m){
        setMethodName(m);
        test.info("The placeholder value should be 'Search Amazon.in' ");
        softAssert.assertEquals(amazonLoginPage.searchBarPlaceHolder(getValueFromJson(FilePaths.AMAZON_DATA, "data[0].placeHolderAttribute").toString()), getValueFromJson(FilePaths.AMAZON_DATA, "data[0].expectedPlaceHolderText"));

    }


//    @Test
//    public void dropdownPreSelectValuecheck(Method m){
//        setMethodName(m);
//        test.info("All Categories option should be preselected");
//        softAssert.assertEquals(amazonLoginPage.preSelectedOptionCheck(), getValueFromJson(FilePaths.AMAZON_DATA, "data[0].preSelectedOption"));
//    }

//    @Test
//    public void primeVideoPageNavigationCheck(Method m){
//     setMethodName(m);
//     test.info("Selected Prime Video in the category and entered the KGF and checked the navigation");
//        amazonLoginPage.searchPageNavigationCheck(getValueFromJson(FilePaths.AMAZON_DATA, "data[0].primeVideoSelection").toString(),getValueFromJson(FilePaths.AMAZON_DATA, "data[0].textToBewSearched").toString() );
//    }


}
