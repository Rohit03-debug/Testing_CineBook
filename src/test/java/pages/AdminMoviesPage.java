package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class AdminMoviesPage extends BasePage {
    private final By title = id("manage-movies-title");
    private final By form = id("mm-form");
    private final By titleInput = id("mm-input-title");
    private final By genreInput = id("mm-input-genre");
    private final By durationInput = id("mm-input-duration");
    private final By posterInput = id("mm-input-poster");
    private final By trailerInput = id("mm-input-trailer");
    private final By priceInput = id("mm-input-price");
    private final By submitButton = id("mm-submit");
    private final By table = id("mm-table");
    private final By emptyTable = id("mm-table-empty");

    public AdminMoviesPage(WebDriver driver) {
        super(driver);
    }

    public AdminMoviesPage open() {
        driver.get(ConfigReader.baseUrl() + "/manage-movies");
        visible(title);
        return this;
    }

    public boolean isDisplayed() {
        return isVisible(title) && isVisible(form);
    }

    public boolean hasMovieFormFields() {
        return isVisible(titleInput) && isVisible(genreInput) && isVisible(durationInput)
                && isVisible(posterInput) && isVisible(trailerInput) && isVisible(priceInput);
    }

    public void submitEmptyForm() {
        click(submitButton);
    }

    public boolean formStillDisplayed() {
        return isVisible(form);
    }

    public boolean hasTableOrEmptyState() {
        return isVisible(table) || isVisible(emptyTable);
    }
}
