package com.datastory.leetcode;

import com.dataw.leetcode.TwoSum;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Kinyi_Chan on 04/01/2017.
 */
public class TwoSumTest {

    @Test
    public void twoSum() throws Exception {

        TwoSum object = new TwoSum();
        int[] arr = {3,2,4};
        int[] rst = object.twoSum(arr, 6);
        System.out.println(Arrays.toString(rst));

        int[] rst2 = object.twoSumOptimize(arr, 6);
        System.out.println(Arrays.toString(rst2));
    }

    @Test
    public void testDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        System.out.println(sdf.format(new Date()));
    }

    @Test
    public void testSelenium() {
        System.getProperties().setProperty("webdriver.chrome.driver", "/Users/Kinyi_Chan/Downloads/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.instagram.com/");
        WebElement element = webDriver.findElement(By.cssSelector("._3bqd5"));
//        element.findElement(By.)
        System.out.println(element.getText());
        webDriver.close();
    }
}