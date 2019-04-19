package web;

import base.ExtentTestManager;
import base.SetupFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SearchPage extends SetupFactory {

    @FindBy(id = "large-search_input")
    public WebElement searchBox;

    @FindBy(xpath = "//*[contains(text(),'Images')]")
    public WebElement imageOption;

    @FindBy(xpath = "//*[contains(text(), 'Audio')]")
    public WebElement audioOption;

    @FindBy(xpath = "//*[contains(text(), 'Videos')]")
    public WebElement videoOption;

    @FindBy(xpath = "//*[@class='video-asset']")
    public WebElement videoAllVel;

    @FindBy(xpath = "//*[@class='audio-asset']")
    public WebElement audioAllVel;

    @FindBy(xpath = "//*[@class='image-asset']")
    public WebElement imageAllVel;

    @FindBy(xpath = "//*[@class='button']")
    public WebElement buttonSearch;



    public void searchFor(String value) {
        searchBox.click();
        searchBox.sendKeys(value);
        buttonSearch.click();
        ExtentTestManager.log("Searched for " + value.toUpperCase(), this.getClass());
    }

    public void checkBox(String value) {
        if (value.equalsIgnoreCase("images")) {
            videoOption.click();
            audioOption.click();
        } else if (value.equalsIgnoreCase("videos")) {
            imageOption.click();
            audioOption.click();
        } else if (value.equalsIgnoreCase("audio")) {
            imageOption.click();
            videoOption.click();
        }
        ExtentTestManager.log(value.toUpperCase() + " has been selected", this.getClass());
    }

    public void validateTitle(){
        String expected = "NASA Image and Video Library";
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String actual = webDriver.getTitle();
        Assert.assertEquals(actual, expected);
        ExtentTestManager.log(actual + " : Title has been validated", this.getClass());
    }
}