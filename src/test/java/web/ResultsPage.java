package web;

import base.ExtentTestManager;
import base.SetupFactory;
import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultsPage extends SetupFactory {


    @FindBy(xpath = "//*[@id=\"updated-search_preloader\"]")
    public WebElement loaderButton;
    @FindBy(xpath = "//*[@id=\"results-returned\"]")
    public WebElement results;

    public void resultValidationCount(String value) {
        while (loaderButton.isDisplayed()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String condition = null;
        if (value.equalsIgnoreCase("images")) {
            condition = "1 -100 of 4650 for \"apollo\":";
        } else if (value.equalsIgnoreCase("videos")) {
            condition = "1 -100 of 114 for \"apollo\":";
        } else if (value.equalsIgnoreCase("audio")) {
            condition = "68 results returned for \"apollo\":";
        }
        String actualResultCount = results.getText();
        Assert.assertEquals(condition, actualResultCount);
        ExtentTestManager.log(actualResultCount + " has been validated", this.getClass());
    }

}
