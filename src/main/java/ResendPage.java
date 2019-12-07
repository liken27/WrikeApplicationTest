import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ResendPage {
    WebDriver driver;

    public ResendPage(WebDriver driver) {
        this.driver = driver;
    }

    private String randomText;
    private int randomAnswer;
    private String interestXpath = "//div[@data-code='interest_in_solution']/label";
    private String teamXpath = "//div[@data-code='team_members']/label";
    private String businessXpath = "//div[@data-code='primary_business']/label";
    private By submitButton = By.xpath("//button[@class='submit wg-btn wg-btn--navy js-survey-submit']");
    private By footerSection = By.xpath("//ul[@class='wg-footer__social-list']");
    private By footerTwitter = By.xpath("//ul[@class='wg-footer__social-list']/li[1]/a");


    // Fill in the Q&A section at the left part of page (like random generated answers) + check with assertion that your answers are submitted
    public ResendPage fillQA() {

        // Interest radio div
        List<WebElement> interest = driver.findElements(By.xpath(interestXpath));
        randomAnswer = (new Random().nextInt(interest.size())) + 1;
        interestXpath = interestXpath.concat("[" + randomAnswer + "]");
        driver.findElement(By.xpath(interestXpath)).click();


        // Team members radio div
        List<WebElement> team = driver.findElements(By.xpath(teamXpath));
        randomAnswer = (new Random().nextInt(team.size())) + 1;
        teamXpath = teamXpath.concat("[" + randomAnswer + "]");
        driver.findElement(By.xpath(teamXpath)).click();


        // Business radio div
        List<WebElement> business = driver.findElements(By.xpath(businessXpath));
        randomAnswer = (new Random().nextInt(business.size())) + 1;
        businessXpath = businessXpath.concat("[" + randomAnswer + "]");
        driver.findElement(By.xpath(businessXpath)).click();
        if (randomAnswer == 3) {
            randomText = RandomStringUtils.randomAlphabetic(20);
            businessXpath = businessXpath.concat("/button//input");
            driver.findElement(By.xpath(businessXpath)).sendKeys(randomText);
        }

        // Submit results
        driver.findElement(submitButton).click();
        System.out.println("I've submitted smth");

        return this;
    }

    // Check that section "Follow us" at the site footer contains the "Twitter" button that leads to the correct url and has the correct icon
    public ResendPage checkFooterTwitter() {
        WebElement footerSocials = driver.findElement(footerSection);
        WebElement twitterFollowButton = driver.findElement(footerTwitter);

        // Check the footer "Follow us" section
        if (footerSocials.getAttribute("innerHTML").contains("href=\"https://twitter.com/wrike\" rel=\"dofollow\" target=\"_blank\"")) {
            // Check the link
            if (twitterFollowButton.getAttribute("href").equals("https://twitter.com/wrike")) {
                System.out.println("It's correct wrike twitter link");
            } else System.out.println("Wrong link");

            // Check the icon
            if (twitterFollowButton.getAttribute("innerHTML").contains("/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v2#twitter")) {
                System.out.println("It has twitter icon");
            } else System.out.println("Wrong icon");
        } else System.out.println("Footer doesnt contain twitter button");

        return this;
    }

}
