package WebUI.Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.apache.logging.log4j.LogManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.ITestResult;


public class SeleniumUtilities {


    public static WebDriver driver;
    public static WebDriverWait driverWait;
    JavascriptExecutor executor;
    ChromeOptions options;

    Select select;
    public void initialization(String url, String browserName){

        Capabilities cap = null;
        options = new ChromeOptions();

        if(browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("google chrome") || browserName.isEmpty()){

            driver = WebDriverManager.chromedriver().create();
        }


        else if(browserName.equalsIgnoreCase("headless chrome")){
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            driver = WebDriverManager.chromedriver().create();
        }
        options.setExperimentalOption("detach", true);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    private String getLocatorType(String locator ){
        String locatorType = splitString(locator, "]: ", 0);
        return locatorType.substring(1).toUpperCase();
    }

    private String getLocatorValue(String locator){
        String locatorValue = splitString(locator, "]: ", 1);
        return locatorValue;
    }

    public WebElement getElement(String locator){

        WebElement element = null;
        String locatorType = getLocatorType(locator);
        String locatorValue = getLocatorValue(locator);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        switch (locatorType) {
            case "XPATH" :
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
                break;
            case "CSS" :
            case "CSSSELECTOR" :
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
                break;
            case "PARTIALLINKTEXT" :
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
                break;
            case "ID" :
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
            case "NAME" :
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
            case "TAGNAME" :
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
            default :
                throw new InvalidArgumentException("The locator type"+locatorType+"was not the valid argument");
        }
        jse.executeScript("arguments[0].style.border='cyan solid 3px'", element);
        return element;
    }

    public List<WebElement> getElements(String locator){

         List<WebElement> elements = null;
        String locatorType = getLocatorType(locator);
        String locatorValue = getLocatorValue(locator);
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        switch (locatorType){
            case "XPATH" :
                elements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locatorValue)));
                break;
            case "CSS" :
            case "CSSSELECTOR" :
                elements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locatorValue)));
                break;
            case "ID" :
                elements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
                break;
            case "PARTIALLINKTEXT" :
                elements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(locatorValue)));
                break;
            case "TAGNAME" :
                elements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(locatorValue)));
                break;
            case "LINKTEXT" :
                elements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(locatorValue)));
                break;
            case "NAME" :
                elements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(locatorValue)));
                break;
            case "CLASSNAME" :
                elements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(locatorValue)));
                break;
            default :
                throw new InvalidArgumentException("The locator type: "+locatorType+" is not a valid type");
        }

        jse.executeScript("arguments[0].style.border='cyan solid 3px'", elements);
        return elements;
    }

    public boolean getElementDisplayedOrNot(String locator){

            boolean displayedOrNot = getElement(locator).isDisplayed();
        try{
            if(displayedOrNot)
            return true;
            else
            return false;
        }
        catch(Exception exception)
        {
            return false;
        }
    }

    private String splitString(String locator, String regex, int index){
        String splitted = locator.split(regex)[index];
        return splitted;
    }

    public boolean verifyElementIsClikable(String locator)
    {
        WebElement element = getElement(locator);

        try{
            ExpectedConditions.elementToBeClickable(element);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    public String getTextValue(String locator){
        return getElement(locator).getText();
    }


    public String getAttributeValue(String locator, String attribute){
        return getElement(locator).getAttribute(attribute);
    }

    public void enterText(String locator, String text){

        getElement(locator).sendKeys(text);
    }

    public void clearField(String locator)
    {
        getElement(locator).clear();
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public boolean isElementDisplayed(String locator){
        try
        {
            if(getElement(locator).isDisplayed())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            return false;
        }

    }

    public boolean isElementEnabled(String locator)
    {

        try
        {
         if(getElement(locator).isEnabled()){
             return true;
         }
         else {
             return false;
         }
        }
        catch(Exception e)
        {
            return false;
        }

    }


    public boolean isElementSelected(String locator){
        try{
            if(getElement(locator).isSelected()){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }



    public void scrollToElement(String locator)
    {
        executor = (JavascriptExecutor)driver;

        try
        {
                executor.executeScript("arguments[0].scrollIntoView(true);",getElement(locator));
        }
        catch(Exception e){

        }
    }

    public String getAlertText(){
        return    driver.switchTo().alert().getText();
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    public void dismissAlert(){
        driver.switchTo().alert().dismiss();
    }

    public void sendValueToAlert(String input){
        driver.switchTo().alert().sendKeys(input);
    }


    public void selectDropdownByVisibleText(String locator, String text)
    {
        try {
             select = new Select(getElement(locator));
            select.selectByVisibleText(text);
        }
        catch(Exception e)
        {

        }
    }

    public void selectDropdownByValue(String locator, String value)
    {
        try {
             select = new Select(getElement(locator));
            select.selectByValue(value);
        }
        catch(Exception e)
        {

        }

    }



    public void selectDropdownValueByIndex(String locator, int index){


        try{
             select = new Select(getElement(locator));
            select.selectByIndex(index);
        }
        catch(Exception e)
        {

        }
    }

    public String preSelectedDropdownValue(String locator){

        try {
            Select select = new Select(getElement(locator));
            WebElement element = select.getFirstSelectedOption();
            String selectedValueText = element.getText();
            return selectedValueText;
        }
        catch(Exception e)
        {
            return " ";
        }
    }

   public void selectValueInDropdown(String locator, String value)
   {
       try{
           Select select = new Select(getElement(locator));
           List<WebElement> optionsList= select.getOptions();
           for(WebElement option:optionsList)
           {
               if(option.getText().equals(value)){

                   option.click();
                   break;
               }
           }

       }
       catch(Exception e)
       {

       }
   }

   public void selectValueInBootstrapDropdown(String locator, String options, String value)
   {
       getElement(locator).click();
       List<WebElement> optionsValues = getElements(options);

       for(WebElement option: optionsValues){
           if(option.getText().equals(value)){
               option.click();
           }
       }
   }

}
