package web;

import base.SetupFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UnitTests extends SetupFactory {
    SearchPage searchPage;
    ResultsPage resultsPage;

    @BeforeMethod
    public void initialize() {
        searchPage = PageFactory.initElements(webDriver, SearchPage.class);
        resultsPage = PageFactory.initElements(webDriver, ResultsPage.class);
    }

    @Test
    public void searchForImages() {
        searchPage.checkBox("images");
        searchPage.searchFor("apollo");
        resultsPage.resultValidationCount("images");
    }

    @Test
    public void searchForAudio() {
        searchPage.checkBox("audio");
        searchPage.searchFor("apollo");
        resultsPage.resultValidationCount("audio");
    }

    @Test
    public void searchForVideos() {
        searchPage.checkBox("videos");
        searchPage.searchFor("apollo");
        resultsPage.resultValidationCount("videos");
    }

    @Test
    public void titleValidation() {
        searchPage.validateTitle();
    }
}