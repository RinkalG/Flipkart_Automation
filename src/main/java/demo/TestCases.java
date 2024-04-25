package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.google.com");
        driver.get("https://www.flipkart.com/");
        WebElement searchBar  = driver.findElement(By.xpath("//input[contains(@title,'Search')]"));
        searchBar.sendKeys("Washing Machine");
        searchBar.click();
        Thread.sleep(2000);
        WebElement sortByPopularity = driver.findElement(By.xpath("//div[text()='Popularity']"));
        sortByPopularity.click();
        Thread.sleep(2000);
        System.out.println("end Test case: testCase01");
    }


}
