import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class='r']//button")
    private WebElement getStartedButton;
    @FindBy(xpath = "//label[@class='modal-form-trial__label']/input")
    private WebElement trialInputField;
    @FindBy(xpath = "//label[@class='modal-form-trial__label']/button")
    private WebElement trialCreateButton;

    // Click "Get started for free" button near "Login" button
    public HomePage clickGetStarted() {
        getStartedButton.click();
        return this;
    }

    // Fill in the email field with random generated value of email with mask “<random_text>+wpt@wriketask.qaa”
    public HomePage inputRandomEmail() {
        String randomText = RandomStringUtils.randomAlphabetic(10);
        randomText = randomText.concat("+wpt@wriketask.qaa");
        trialInputField.sendKeys(randomText);
        return this;
    }

    // Click on "Create my Wrike account" button + check with assertion that you are moved to the next page
    public ResendPage clickCreateButton() {
        trialCreateButton.click();
        return new ResendPage(driver);
    }
}
