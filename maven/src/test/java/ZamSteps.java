import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;

import java.io.IOException;


public class ZamSteps {

    public WebDriver driver;
    String address = "qwerty";

    @Given("I open and maximize the browser")
    public void iOpenAndMaximizeTheBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @And("I enter the web page")
    public void iEnterTheWebPage() {
        driver.get("https://mystore-testlab.coderslab.pl");
    }

    @And("I log in to the account {string} , {string}")
    public void iLogInToTheAccount(String login, String password) {
        WebElement element1 = driver.findElement(By.cssSelector("a[href=\"https://mystore-testlab.coderslab.pl/index.php?controller=my-account\"]"));
        element1.click();
        WebElement element2 = driver.findElement(By.cssSelector("input[id=field-email]"));
        element2.sendKeys(login);
        WebElement element3 = driver.findElement(By.cssSelector("input[id=field-password]"));
        element3.sendKeys(password);
        WebElement element4 = driver.findElement(By.cssSelector("button[id=submit-login]"));
        element4.click();
    }

    @And("I select the desired product")
    public void iSelectTheDesiredProduct() {
        WebElement element5 = driver.findElement(By.cssSelector("a[href=\"https://mystore-testlab.coderslab.pl/index.php?id_category=3&controller=category\"]"));
        element5.click();
        WebElement element6 = driver.findElement(By.cssSelector("a[href=\"https://mystore-testlab.coderslab.pl/index.php?id_product=2&id_product_attribute=9&rewrite=brown-bear-printed-sweater&controller=product#/1-size-s\"]"));
        element6.click();
    }

    @And("I select the desired size that is {string}")
    public void iSelectTheDesiredSizeThatIs(String size) {
        Select element8 = new Select(driver.findElement(By.cssSelector("select[id=group_1]")));
        element8.selectByVisibleText(size);
    }

    @And("I select the desired number of products that is {string}")
    public void iSelectTheDesiredNumberOfProductsThatIs(String number) throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.urlContains("https://mystore-testlab.coderslab.pl/index.php?id_product=2&id_product_attribute=10&rewrite=brown-bear-printed-sweater&controller=product#/2-size-m"));

            WebElement element9 = driver.findElement(By.cssSelector("i[class=\"material-icons touchspin-up\"]"));
            int numberInt = Integer.parseInt(number);
            for (int i = 1; i < numberInt; i++) {
                element9.click();
                Thread.sleep(300);
            }

    }

    @And("I add the product to the cart")
    public void iAddTheProductToTheCart() {
        WebElement element10 = driver.findElement(By.cssSelector("button[class=\"btn btn-primary add-to-cart\"]"));
        element10.click();
    }

    @When("I go to the checkout")
    public void iGoToTheCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id=\"blockcart-modal\"]")));
        WebElement element11 = driver.findElement(By.cssSelector("div[id=\"blockcart-modal\"]")).findElement(By.cssSelector("a[href=\"//mystore-testlab.coderslab.pl/index.php?controller=cart&action=show\"]"));
        element11.click();
        WebElement element12 = driver.findElement(By.cssSelector("a[href=\"//mystore-testlab.coderslab.pl/index.php?controller=cart&action=show\"]"));
        System.out.println(element12.getText());
        element12.click();
        WebElement element13 = driver.findElement(By.cssSelector("a[href=\"https://mystore-testlab.coderslab.pl/index.php?controller=order\"]"));
        element13.click();
    }

    @And("I confirm address: {string}, {string}, {string}, {string} and {string}")
    public void iConfirmAddressAnd(String alias, String city, String zip, String country, String phone) {
        try{
            WebElement element7z1 = driver.findElement(By.cssSelector("input[id=field-alias]"));
            element7z1.sendKeys(alias);
            WebElement elementXz1 = driver.findElement(By.cssSelector("input[id=field-address1]"));
            elementXz1.sendKeys(address);
            WebElement element8z1 = driver.findElement(By.cssSelector("input[id=field-city]"));
            element8z1.sendKeys(city);
            WebElement element9z1 = driver.findElement(By.cssSelector("input[id=field-postcode]"));
            element9z1.sendKeys(zip);
            WebElement element11z1 = driver.findElement(By.cssSelector("input[id=field-phone]"));
            element11z1.sendKeys(phone);
            WebElement element12z1 = driver.findElement(By.cssSelector("button[type=submit]"));
            element12z1.click();
            WebElement elementYz1 = driver.findElement(By.xpath("//*[contains(text(), \"Address successfully added!\")]"));
            Assert.assertTrue(elementYz1.isDisplayed());

        } catch (Exception e){
            System.out.println("Address istnieje");
        }
        WebElement element15 = driver.findElement(By.cssSelector("button[name=confirm-addresses]"));
        element15.click();
    }

    @And("I select a carrier as pick up in store")
    public void iSelectACarrierAsPickUpInStore() {
        WebElement element16 = driver.findElement(By.cssSelector("input[id=\"delivery_option_8\"]"));
        element16.submit();
        WebElement element17 = driver.findElement(By.cssSelector("button[name=\"confirmDeliveryOption\"]"));
        element17.click();
    }

    @And("I select payment as pay by check")
    public void iSelectPaymentAsPayByCheck() {
        WebElement element18 = driver.findElement(By.cssSelector("input[id=\"payment-option-1\"]"));
        element18.click();
    }

    @And("I select order with an obligation to pay")
    public void iSelectOrderWithAnObligationToPay() {
        WebElement element19 = driver.findElement(By.cssSelector("input[id=\"conditions_to_approve[terms-and-conditions]\"]"));
        element19.click();
        WebElement element20 = driver.findElement(By.cssSelector("button[type=submit]")).findElement(By.xpath("//*[contains(text(), \"Place order\")]"));
        element20.click();
    }

    public static void PartiPorCaptureScreenshot(WebDriver webdriver, String Parscrpath, WebElement PartiSection) throws IOException, InterruptedException {
        Thread.sleep(2000);
        File ParSrcShot = PartiSection.getScreenshotAs(OutputType.FILE);

        File ParSrcShotDesti = new File(Parscrpath);
        FileHandler.copy(ParSrcShot, ParSrcShotDesti);
    }

    @Then("I make a screenshot")
    public void iMakeAScreenshot() {
        WebElement elementScreen = driver.findElement(By.cssSelector("section[id=main]"));

        try {
            PartiPorCaptureScreenshot(driver,"C:\\Users\\User\\Documents\\screen.png", elementScreen);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
