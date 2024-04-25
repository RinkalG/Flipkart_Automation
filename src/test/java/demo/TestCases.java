package demo;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import com.github.dockerjava.core.command.WaitContainerResultCallback;
import org.openqa.selenium.Keys;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    @BeforeClass
    public void firstTest() {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    @Test(enabled = true,priority = 1)
    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        int count = 0;
        driver.get("https://www.flipkart.com/");
        WebElement searchBar = driver.findElement(By.xpath("//input[contains(@title,'Search')]"));
        Wrapper.advanceSendkeys(searchBar,"Washing Machine");
        searchBar.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement sortByPopularity = driver.findElement(By.xpath("//div[text()='Popularity']"));
        Wrapper.advanceClick(sortByPopularity);
        Thread.sleep(2000);
        List<WebElement> allProducts = driver
                .findElements(By.xpath("//div[@class='yKfJKb row']//div[@class='XQDdHH']"));
        for (WebElement ele : allProducts) {
            if (Float.parseFloat(ele.getText()) >= 4) {
                count++;
            }
        }
        System.out.println("Count of products with rating 4 or less than 4 -->" + count);
        System.out.println("end Test case: testCase01");
    }

    @Test(enabled = true,priority = 2)
    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        WebElement searchBar = driver.findElement(By.xpath("//input[contains(@title,'Search')]"));
        Wrapper.advanceSendkeys(searchBar,"iPhone");
        searchBar.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        List<WebElement> allRatings = driver.findElements(By.xpath("//div[@class='yKfJKb row']/div[2]//span"));
        List<WebElement> allTitles = driver.findElements(By.xpath("//div[@class='yKfJKb row']/div[1]/div[1]"));
        for (int i = 0; i < allRatings.size(); i++) {
            String[] discountInString = allRatings.get(i).getText().split("%", 2);
            int discount = Integer.parseInt(discountInString[0]);
            System.out.println("Product title with more that 17% discount:");
            if (discount > 17) {
                System.out.println("Product Title  > " + allTitles.get(i).getText()+"with discount "+discount+"%");
                System.out.println("Discount on Product > " + discount + "%");
            }
        }
        System.out.println("End Test case: testCase02");
    }

    @Test(enabled = true,priority = 3)
    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        WebElement searchBar = driver.findElement(By.xpath("//input[contains(@title,'Search')]"));
        Wrapper.advanceSendkeys(searchBar,"Coffee Mug");
        searchBar.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(1000);
        WebElement fourStarAbove = driver.findElement(By.xpath(
                "//*[@id='container']/div/div[3]/div[1]/div[1]/div/div/div/section[5]/div[2]/div/div[1]/div/label"));
        //fourStarAbove.click();
        Wrapper.advanceClick(fourStarAbove);
        List<WebElement> reviews = driver
                .findElements(By.xpath(
                        "//div[@class='slAVV4']/div[@class='_5OesEi afFzxY']//span[2]"));
        Integer[] reviewInInteger = new Integer[reviews.size()];
        int k = 0;
        int j=0;
        for (WebElement ele : reviews) {
            String element = ele.getText();
            element = element.substring(1, element.length() - 1);
            element = element.replaceAll(",","");
            reviewInInteger[k] = Integer.parseInt(element);
            k++;  
        }
        Arrays.sort(reviewInInteger,Collections.reverseOrder());
        String[] reviewInString = new String[reviewInInteger.length];
        for (Integer i : reviewInInteger) {
            reviewInString[j] = String.valueOf(i);
            j++;
        }
        for (int i=0;i<5;i++) {
           String str =reviewInString[i];
           str = str.substring(0, str.length() - 3) + "," + str.substring(str.length() - 3, str.length());
           WebElement title = driver.findElement(By.xpath("//div[@class='slAVV4']/div[@class='_5OesEi afFzxY']//span[contains(text(),'"+str+"')]/parent::div/preceding-sibling::a[1]"));
           WebElement ImageUrl = driver.findElement(By.xpath("//div[@class='slAVV4']/div[@class='_5OesEi afFzxY']//span[contains(text(),'"+str+"')]/parent::div/preceding-sibling::a[2]"));
           System.out.println("Title and ImageUrl with "+str+" review>>>>");
           System.out.println(title.getText());
           System.out.println(ImageUrl.getAttribute("href"));

        }
        
        System.out.println("End test Case: testCase03");
    }

}
