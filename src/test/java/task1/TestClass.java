package task1;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;


/**
 * @author Vadym Nastoiashchyi
 */

public class TestClass extends WebDriverProperties {

    @Test
    public void test() {
        //create link storage
        List<String> links = new LinkedList<>();
        String searchWord = "automation";

        //get connection to url
        driver.get("https://www.google.com/");

        //use google search form
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(searchWord);
        element.submit();

        //get result from google
        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']/div/div"));

        //save result to link storage
        getLinks(findElements, links);

        //go to first link
        driver.navigate().to(links.get(0));

        //get page title
        String actualTitle = driver.getTitle();

        //Verify page title contains searched word
        Assert.assertTrue(actualTitle.toLowerCase().contains(searchWord));

    }

    public void getLinks(List<WebElement> findElements, List<String> links) {
        for (WebElement findElement : findElements) {
            WebElement getLinks = findElement.findElement(By.tagName("a"));
            links.add(getLinks.getAttribute("href"));
        }
    }

}




