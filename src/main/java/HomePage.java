import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = (new WebDriverWait(driver, 5));
    }

    private By getStartedButton = By.xpath("//div[@class='r']//button");
    private By trialInputField = By.xpath("//label[@class='modal-form-trial__label']/input");
    private By trialCreateButton = By.xpath("//label[@class='modal-form-trial__label']/button");


    // Click "Get started for free" button near "Login" button
    public HomePage clickGetStarted() {
        driver.findElement(getStartedButton).click();
        return this;
    }

    // Fill in the email field with random generated value of email with mask “<random_text>+wpt@wriketask.qaa”
    public HomePage inputRandomEmail() {
        String randomText = RandomStringUtils.randomAlphabetic(10);
        randomText = randomText.concat("+wpt@wriketask.qaa");
        driver.findElement(trialInputField).sendKeys(randomText);
        return this;
    }

    // Click on "Create my Wrike account" button + check with assertion that you are moved to the next page
    public ResendPage clickCreateButton() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);

        driver.findElement(trialCreateButton).click();
        //TODO Check with assertion
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[contains(text(), 'Help us provide you the best possible experience:')]")));

        if (currentUrl.equals(driver.getCurrentUrl())) {
            System.out.println("URL did not change after a button click, something went wrong");
        } else {
            System.out.println("ALL OKAY");
        }
        System.out.println(driver.getCurrentUrl());
        //TODO ---------------------

        return new ResendPage(driver);
    }




}
