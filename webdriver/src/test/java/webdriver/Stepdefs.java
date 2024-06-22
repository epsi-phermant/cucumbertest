package webdriver;

import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Stepdefs {

    // we will use chrome web driver
    private final ChromeOptions options = new ChromeOptions();
    private final WebDriver driver = new ChromeDriver(options);

    // start with feature (adapted from google search)
    @Given("I am on the Google search page")
    public void I_visit_google() {
        // airtable form copy/paste link
        String url = "https://airtable.com/appix2swBL4eKIZOc/paghEzG7G0uoP9JRw/form";
        // web driver will open the url
        driver.get(url);
    }

    @When("I search for {string}")
    public void search_for(String query) {
        // I recommend to start using selenium chrome extension to see the scenario then export code in Junit format
        // or retrieve full xpath with inspect / web developer tools
        String xpath1 = "/html/body/div[1]/div/div[1]/div/div/div/div/div[1]/main/div/div/div/div/div/div[1]/div[2]/div[3]/div/div/div/div/div/div[1]/div/div/div/div/div[2]/div[2]/div/div/textarea";

        // due to the cookies nag screen, wait 5 seconds for all elements to appear
        // TODO: remove cookies with options or code user accepting the cookies
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // find the element based on full xpath
        WebElement element = driver.findElement(By.xpath(xpath1));

        // Enter something in the element
        element.sendKeys(query);

        // Now submit the form. WebDriver will find the form for us from the element
        // element.submit(); does not work with Selenium 4
        // so we will simulate submit by user clicking on the submit button instead
        // full xpath of submit button
        String xpathbutton = "/html/body/div[1]/div/div[1]/div/div/div/div/div[1]/main/div/div/div/div/div/div[2]/div/div[1]/div[3]/button";
        WebElement checkInput=driver.findElement(By.xpath(xpathbutton));

        // click on this button
        checkInput.click();
    }

    @Then("the returned page should contain {string}")
    public void checkTitle(String titleStartsWith) {
        // TODO: change parameters name (titlestartswith) and conditions as we test exact phrase return
        // Google's search is rendered dynamically with JavaScript
        // Wait for the page to load timeout after ten seconds
        Duration duration = Duration.ofSeconds(10);

        new WebDriverWait(driver,duration).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {

                // element will be retrieved by class and not full xpath
                String text = d.findElement(By.className("css-bk9fzy")).getText();
                return text.equals(titleStartsWith);

            }
        });
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}