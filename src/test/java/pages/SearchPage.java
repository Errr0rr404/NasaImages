package pages;

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

    @FindBy(xpath = "//*[@class='button']")
    public WebElement buttonSearch;

    @FindBy(xpath = "/html//img[@id='logo']")
    public WebElement logo;

    /**
     * This method will click on search box & search for given parameter
     *
     * @param value : the desired value to search
     */
    public void searchFor(String value) {
        searchBox.click();
        searchBox.sendKeys(value);
        buttonSearch.click();
        ExtentTestManager.log("Searched for " + value.toUpperCase(), this.getClass());
    }

    /**
     * This method will select the checkbox options for given parameter
     *
     * @param value : the desired value to click checkbox
     */
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

    /**
     * This method will validate the title of the page
     */
    public void validateTitle() {
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

    /**
     * This method validate the URL & page Title
     */
    public void logoValidation() {
        logo.click();
        String expectedURL = "https://www.nasa.gov/";
        String actualURL = webDriver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
        ExtentTestManager.log(actualURL + " : has been validated", this.getClass());
        String expectedTitle = "NASA";
        String actualTitle = webDriver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        ExtentTestManager.log(actualTitle + " : has been validated", this.getClass());
    }
}