import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//button[@class='submit wg-btn wg-btn--navy js-survey-submit']")
    private WebElement submitButton;


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
        submitButton.click();
        System.out.println("I've submitted smth");

        return this;
    }

}
