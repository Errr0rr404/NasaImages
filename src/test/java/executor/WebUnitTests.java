package executor;

import base.SetupFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ResultsPage;
import pages.SearchPage;

public class WebUnitTests extends SetupFactory {
    SearchPage searchPage;
    ResultsPage resultsPage;

    /**
     * This method will initialize the pagefactory pattern
     */
    @BeforeMethod
    public void initialize() {
        searchPage = PageFactory.initElements(webDriver, SearchPage.class);
        resultsPage = PageFactory.initElements(webDriver, ResultsPage.class);
    }

    /**
     * This method will search for images & validate the count in the result page
     */
    @Test
    public void searchForImages() {
        searchPage.checkBox("images");
        searchPage.searchFor("apollo");
        resultsPage.resultValidationCount("images");
    }

    /**
     * This method will search for audio & validate the count in the result page
     */
    @Test
    public void searchForAudio() {
        searchPage.checkBox("audio");
        searchPage.searchFor("apollo");
        resultsPage.resultValidationCount("audio");
    }

    /**
     * This method will search for videos & validate the count in the result page
     */
    @Test
    public void searchForVideos() {
        searchPage.checkBox("videos");
        searchPage.searchFor("apollo");
        resultsPage.resultValidationCount("videos");
    }

    /**
     * This method will validate the title of the webPage
     */
    @Test
    public void titleValidation() {
        searchPage.validateTitle();
    }

    /**
     * This method will change the filter after searching for any specific
     */
    @Test
    public void filterChangeAfterSearch() {
        searchPage.checkBox("videos");
        searchPage.searchFor("apollo");
        resultsPage.changeFilterTo("images");
    }

    /**
     * This method will validate the logo redirection to the new page and validate the title & URL
     */
    @Test
    public void logoRedirectionValidation() {
        searchPage.logoValidation();
    }


    /**
     * This method will change the result displayed view to list or grid view
     */
    @Test
    public void displayedResultsListDetailFunctional() {
        searchPage.searchFor("apollo");
        resultsPage.listAndDetail();
    }


    /**
     * This method will validate the next button functionality and validate previous button visibility on next page
     */
    @Test
    public void nextButtonValidationInResult() {
        searchPage.searchFor("apollo");
        resultsPage.nextButton();
    }
}

