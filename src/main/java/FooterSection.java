import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FooterSection {
    WebDriver driver;

    public FooterSection(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//ul[@class='wg-footer__social-list']")
    private WebElement footerSocials;
    @FindBy(xpath = "//ul[@class='wg-footer__social-list']/li[1]/a")
    private WebElement footerTwitterButton;

    // Check that section "Follow us" at the site footer contains the "Twitter" button that leads to the correct url and has the correct icon
    // For assertion test run FooterSectionTest.java

}
