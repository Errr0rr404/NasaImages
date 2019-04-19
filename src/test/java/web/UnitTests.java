package web;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UnitTests extends SearchPage {
    SearchPage searchPage;

    @BeforeMethod
    public void init() {
        searchPage = PageFactory.initElements(webDriver, SearchPage.class);
    }

    @Test
    public void searchForImages() {
        searchPage.checkBox("images");
        searchPage.searchFor("apollo");
    }

    @Test
    public void searchForAudio() {
        searchPage.checkBox("audio");
        searchPage.searchFor("apollo");
    }

    @Test
    public void searchForVideos() {
        searchPage.checkBox("videos");
        searchPage.searchFor("apollo");
    }
}