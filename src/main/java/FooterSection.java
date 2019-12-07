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
    // Code below provides info via SOUT, for assertion test run FooterSectionTest.java
    public FooterSection checkFooterTwitter() {
        // Check the footer "Follow us" section
        if (footerSocials.getAttribute("innerHTML").contains("href=\"https://twitter.com/wrike\" rel=\"dofollow\" target=\"_blank\"")) {
            // Check the link
            if (footerTwitterButton.getAttribute("href").equals("https://twitter.com/wrike")) {
                System.out.println("It's correct wrike twitter link");
            } else System.out.println("Wrong link");

            // Check the icon
            if (footerTwitterButton.getAttribute("innerHTML").contains("/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v2#twitter")) {
                System.out.println("It has twitter icon");
            } else System.out.println("Wrong icon");
        } else System.out.println("Footer doesnt contain twitter button");

        return this;
    }
}
