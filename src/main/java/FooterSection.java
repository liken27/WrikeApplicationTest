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

    // Check "Follow us" at the site footer
    public boolean containsTwitter() {
        if (footerSocials.getAttribute("innerHTML").contains("href=\"https://twitter.com/wrike\" rel=\"dofollow\" target=\"_blank\"")) {
            return true;
        } else return false;
    }

    // Check Twitter link
    public boolean linkTwitterIsValid() {
        if (footerTwitterButton.getAttribute("href").equals("https://twitter.com/wrike")) {
            return true;
        } else return false;
    }

    // Check Twitter icon
    public boolean iconTwitterIsValid() {
        if (footerTwitterButton.getAttribute("innerHTML").contains("/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v2#twitter")) {
            return true;
        } else return false;
    }
}
