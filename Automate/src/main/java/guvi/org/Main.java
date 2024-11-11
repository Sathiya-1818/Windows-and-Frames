package guvi.org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Objects;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        String title = driver.getTitle();
        System.out.println("The Original Window is " +title);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/a")).click();

        Set<String> windowHandles = driver.getWindowHandles();
        String mainWindowHandle = driver.getWindowHandle();
        String newWindowHandle = null;

        for (String handle : windowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                newWindowHandle = handle;
                break;
            }
        }

        driver.switchTo().window(newWindowHandle);
        String title1 = driver.getTitle();
        System.out.println("The Active Window is " +title1);
        if(title1 == "New Window"){
             System.out.println("New Window is Active");
         }
        driver.close();

        driver.switchTo().window(mainWindowHandle);
        String Title = driver.getTitle();
        System.out.println("Now The Active Window is " + Title);
        if(Objects.equals(title, Title)){
           System.out.println("The Original Window is Active");
        }
        driver.close();


    }
}