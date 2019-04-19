package web;

import base.ExtentTestManager;
import base.SetupFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends SetupFactory {

    @FindBy(id = "large-search_input")
    public WebElement searchBox;

    @FindBy(xpath = "//*[contains(text(),'Images')]")
    public WebElement imageVel;

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

    @FindBy(xpath = "//div[@id='searchFor-Videos']/a[@class='checked']")
    public WebElement checkVideos;
    @FindBy(xpath = "//div[@id='searchFor-Audio']/a[@class='checked']")
    public WebElement checkAudio;
    @FindBy(xpath = "//div[@id='searchFor-Images']/a[@class='checked']")
    public WebElement checkImages;

    public void searchFor(String value) {
        searchBox.click();
        searchBox.sendKeys(value);
        buttonSearch.click();
        ExtentTestManager.log("Searched for " + value.toUpperCase(), this.getClass());
    }

    public void checkBox(String value) {
        if (value.equalsIgnoreCase("images")) {
            checkVideos.click();
            checkAudio.click();
        } else if (value.equalsIgnoreCase("videos")) {
            checkImages.click();
            checkAudio.click();
        } else if (value.equalsIgnoreCase("audio")) {
            checkImages.click();
            checkAudio.click();
        }
        ExtentTestManager.log(value.toUpperCase() + " has been selected", this.getClass());
    }
}