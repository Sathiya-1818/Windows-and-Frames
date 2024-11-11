package guvi.org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/nested_frames");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("frame[name='frame-top']")));
        System.out.println("Switched to the Top Frame");
        int noOfFrames = driver.findElements(By.cssSelector("frame")).size();
        System.out.println("No of Frames in Top Frame : "+noOfFrames);

        //left frame
        driver.switchTo().frame(driver.findElement(By.cssSelector("Frame[name='frame-left")));
        System.out.println("Switched to Left Frame");
        WebElement leftFrame = driver.findElement(By.cssSelector("body"));
        String leftFrameText = leftFrame.getText();
        System.out.println("Text inside left frame : " + leftFrameText);

        if (leftFrameText.contains("LEFT")) {
            System.out.println("Text verification successful");
        } else {
            System.out.println("Text verification failed");
        }
        driver.switchTo().parentFrame();
        System.out.println("Switched back to the top frame.");
        System.out.println();

        //right frame
        driver.switchTo().frame(driver.findElement(By.cssSelector("Frame[name='frame-right")));
        System.out.println("Switched to Right Frame");
        WebElement rightFrame = driver.findElement(By.cssSelector("body"));
        String Txt = rightFrame.getText();
        System.out.println("Text inside Right frame : " + Txt);

        if (Txt.contains("RIGHT")) {
            System.out.println("Text verification successful");
        } else {
            System.out.println("Text verification failed");
        }
        driver.switchTo().parentFrame();
        System.out.println("Switched back to the top frame.");
        System.out.println();

        //middle frame
        driver.switchTo().frame(driver.findElement(By.cssSelector("Frame[name='frame-middle")));
        System.out.println("Switched to Middle Frame");
        WebElement middleFrame = driver.findElement(By.cssSelector("body"));
        String middleFrameText = middleFrame.getText();
        System.out.println("Text inside middle frame : " + middleFrameText);

        if (middleFrameText.contains("MIDDLE")) {
            System.out.println("Text verification successful");
        } else {
            System.out.println("Text verification failed");
        }
        driver.switchTo().parentFrame();
        System.out.println("Switched back to the Top frame.");
        System.out.println();

        //bottom frame
        driver.switchTo().frame(driver.findElement(By.xpath("//frameset/frame[2]//div[@id='body']")));
        System.out.println("Switched to the bottom frame.");
        WebElement bottomFrameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body")));
        String bottomFrameText = bottomFrameElement.getText();
        System.out.println("Text inside bottom frame: " + bottomFrameText);
        if (bottomFrameText.contains("BOTTOM")) {
            System.out.println("Text verification successful");
        } else {
            System.out.println("Text verification failed");
        }

        driver.switchTo().parentFrame();
        System.out.println("Switched back to the Top frame.");
        System.out.println();
        driver.quit();

    }
}