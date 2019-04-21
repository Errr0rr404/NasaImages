package pages;

import base.ExtentTestManager;
import base.SetupFactory;
import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultsPage extends SetupFactory {

    @FindBy(xpath = "//*[@id='updated-search_preloader']")
    public WebElement loaderButton;
    @FindBy(xpath = "//*[@id='results-returned']")
    public WebElement results;

    @FindBy(xpath = "//div[@id='filterBy-Images']/a")
    public WebElement addImagesFilter;

    @FindBy(xpath = "//div[@id='filterBy-Videos']/a")
    public WebElement addVideosFilter;

    @FindBy(xpath = "//div[@id='filterBy-Audio']/a")
    public WebElement addAudioFilter;

    @FindBy(xpath = "//div[@id='updateFilters']/button[.='Update results']")
    public WebElement updateResult;

    @FindBy(id = "grid-view")
    public WebElement gridView;

    @FindBy(id = "list-view")
    public WebElement listView;

    @FindBy(linkText = "Next")
    public WebElement next;
    @FindBy(linkText = "Previous")
    public WebElement previous;

    /**
     * This method will validate the result count
     *
     * @param value : the desired value to match with expected count
     */
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

    /**
     * This method will change filter to desired parameter
     *
     * @param value : the desired value to change filter to
     */
    public void changeFilterTo(String value) {
        if (value.equalsIgnoreCase("images")) {
            addImagesFilter.click();
        } else if (value.equalsIgnoreCase("videos")) {
            addVideosFilter.click();
        } else if (value.equalsIgnoreCase("audio")) {
            addAudioFilter.click();
        }
        updateResult.click();
        ExtentTestManager.log(value.toUpperCase() + " has been selected", this.getClass());
    }

    /**
     * This method will do finctional validation of both list and grid view
     */
    public void listAndDetail() {
        gridView.click();
        listView.click();
        gridView.click();
        ExtentTestManager.log(listView.toString() + " & " + gridView.toString() + " has been selected", this.getClass());
    }

    /**
     * This method will click on next button and validate is previous is present in the next page
     */
    public void nextButton() {
        next.click();
        if (previous.isDisplayed()) {
            ExtentTestManager.log("NEXT Button has been clicked", this.getClass());

        } else {
            Assert.fail();
        }
    }

}
