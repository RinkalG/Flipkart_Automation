package demo;

import org.openqa.selenium.WebElement;

public class Wrapper {
    public static void advanceClick(WebElement element) throws InterruptedException{
        if(element.isEnabled()){
       element.click(); 
       Thread.sleep(2000);
        }else{
            System.out.println("Element not Clickable");
        }
    }
    public static void advanceSendkeys(WebElement element, String message){
        element.clear();
        element.sendKeys(message);
    }
}
