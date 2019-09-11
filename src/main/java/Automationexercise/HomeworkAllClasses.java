package Automationexercise;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class HomeworkAllClasses
{
    protected static WebDriver driver;

    @BeforeMethod
    public void commonstep()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\dev\\Automation\\src\\BrowserDriver\\chromedriver.exe");
        //open the browser
        driver = new ChromeDriver();
        //maximize the browser screen
        driver.manage().window().fullscreen();
        //set implicity wait for driver object
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
    }
    @AfterMethod
    public void closebrowser(){driver.quit();}


    public String generateEmail(String startValue)
    {
        String email = startValue.concat(new Date().toString());
        return email;
    }
    public static String randomDate()
    {
        DateFormat format = new SimpleDateFormat("ddMMyyHHmmss");
        return format.format(new Date());
    }


    @Test
    //program for user should be able to login successfully
    public  void usershouldbeabletologinsuccessfully()
    {
        //click on register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        //enter first name
        driver.findElement(By.id("FirstName")).sendKeys("Venus");
        //enter last name
        driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("Patel");
        //calling method for generating email
        //removing special character from the string
        String s =generateEmail("").replaceAll("[^a-zA-Z0-9]","");
        //enter email
        driver.findElement(By.name("Email")).sendKeys(s +"@gmail.com");
        //enter password
        driver.findElement(By.xpath("//input[@data-val-regex-pattern='^.{6,}$']")).sendKeys("mynameissunil");
        //enter confirm password field
        driver.findElement(By.xpath("//input[@data-val-equalto-other='*.Password']")).sendKeys("mynameissunil");
        //click on register
        driver.findElement(By.xpath("//input[@class='button-1 register-next-step-button']")).click();
        String actual = driver.findElement(By.xpath("//div[@class='result']")).getText();
        String expected = "Your registration completed";
        Assert.assertEquals(actual,expected);
    }

    @Test
    //program for user should be able to refer product to a friend
    public void usershouldbeabletoreferproducttoafriend()
    {
        //click on register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        //enter first name
        driver.findElement(By.id("FirstName")).sendKeys("Venus");
        //enter last name
        driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("Patel");
        //calling method for generating email
        //removing special character from the string
        String s =generateEmail("").replaceAll("[^a-zA-Z0-9]","");
        //enter email
        driver.findElement(By.name("Email")).sendKeys(s +"@gmail.com");
        //enter password
        driver.findElement(By.xpath("//input[@data-val-regex-pattern='^.{6,}$']")).sendKeys("mynameissunil");
        //enter confirm password field
        driver.findElement(By.xpath("//input[@data-val-equalto-other='*.Password']")).sendKeys("mynameissunil");
        //click on register
        driver.findElement(By.xpath("//input[@class='button-1 register-next-step-button']")).click();
        //click on logo
        driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).click();
        //click on apple macbook pro 13 inch
        driver.findElement(By.xpath("//img[@alt='Picture of Apple MacBook Pro 13-inch']")).click();
        //click on email friend
        driver.findElement(By.xpath("//input[@value='Email a friend']")).click();
        //fill up friend's email address details
        driver.findElement(By.xpath("//input[@class='friend-email']")).sendKeys("sunilkathalana@yahoo.co.in");
        //enter details in message
        driver.findElement(By.xpath("//textarea[@class='your-email']")).sendKeys("laptop information");
        //click on send button
        driver.findElement(By.xpath("//input[@class='button-1 send-email-a-friend-button']")).click();
        String actual = driver.findElement(By.xpath("//div[@class='result']")).getText();
        String expected = "Your message has been sent.";
        Assert.assertEquals(actual,expected);
    }

    @Test
    //program for user should be able to verify that he is navigated to camera and photo page
    public void usershouldbeabletonavigatecameraandphotopag()
        {
            //click on logo
            driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).click();
            //click on Electronics
            driver.findElement(By.linkText("Electronics")).click();
            //click on camera and photos
            driver.findElement(By.xpath("//a[@title='Show products in category Camera & photo']")).click();
            String actual = driver.findElement(By.xpath("//h1[contains(text(),'Camera & photo')]")).getText();
            String expected = "Camera & photo";
            Assert.assertEquals(actual,expected);
        }
    @Test
    //program that user should be able to verify jewelery by filter between $700.00 and $3,000.00 and he is on that filtered page.
    public void usershouldbeabletoselectjewelrybyfilter(){
            //click on logo
            driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).click();
            //click on jewelry
            driver.findElement(By.linkText("Jewelry")).click();
            driver.findElement(By.xpath("//a[contains(@href,'700-3000')]")).click();
            //comparing that "$700,00 - $3,000.00 with actual filter value on page
            String s2 = driver.findElement(By.xpath("//span[contains(@class,'item')]")).getText();
            String expected = "$700.00 - $3,000.00";
            Assert.assertEquals(s2,expected);
            //extracting 700.0 numberic value from $700.00 string value
            String[] s3 = s2.split("-");
            String p = s3[0].substring(1);
            double q = Double.parseDouble(p);
            //extracting 3000.0 numeric value from $3,000.00
            s3[1] = s3[1].replaceAll(" ", "");
            s3[1] = s3[1].replaceAll(",", "");
            String r = s3[1].substring(1);
            double s = Double.parseDouble(r);
            //extracting 2100.0 numeric value from $2,100.00
            String s1 = driver.findElement(By.xpath("//span[text()='$2,100.00']")).getText();
            s1 = s1.substring(1);
            s1 = s1.replaceAll(",", "");
            double t = Double.parseDouble(s1);
            //checking whether 2100 is falling between 700 and 3000 or not
            Assert.assertTrue(q<s && s>t);
    }

    @Test
    public void usershouldbeabletoaddtwobooksincheckoutbasket()
    {
        //click on logo
        driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).click();
        //click on books
        driver.findElement(By.linkText("Books")).click();
        //click on book1
        driver.findElement(By.xpath("//img[@alt=\"Picture of Fahrenheit 451 by Ray Bradbury\"]")).click();
        //click on ADD to cart
        driver.findElement(By.id("add-to-cart-button-37")).click();
        //delay for 3 seconds for adding items into basket.
        try
        {
          Thread.sleep(3000);
        } catch (InterruptedException e) {e.printStackTrace();}
        //click on books
        driver.findElement(By.linkText("Books")).click();
        //click on book2
        driver.findElement(By.xpath("//img[@alt=\"Picture of Pride and Prejudice\"]")).click();
        //click on ADD to cart
        driver.findElement(By.id("add-to-cart-button-39")).click();
        //click on shopping basket
        driver.findElement(By.xpath("//span[@class='cart-label']")).click();

        //get text for 1st book
        String r= "FR_451_RB";
        String p = driver.findElement(By.xpath("//span[text()=\"FR_451_RB\"]")).getText();
        //checking 1st book added into basket
        Assert.assertEquals(p,r);

        //get text for 3rd book
        String s="PRIDE_PRJ";
        String q = driver.findElement(By.xpath("//span[text()=\"PRIDE_PRJ\"]")).getText();
        //checking 3rd book is added into basket.
        Assert.assertEquals(q,s);
    }
}


