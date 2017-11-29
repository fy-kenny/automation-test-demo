package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

/**
 * wifi auto verification.
 *
 * @author Kenny Fang
 * @version 1.0
 * @date 2017/11/28 10:15
 */
public class VerifyWifiLogin {

    public void wifiAutoLogin() throws IOException {
        chromeDriverExcutable();

        WebDriver webDriver = new ChromeDriver(options());

        try {
            while (true) {
                boolean accessible = isBuiduAccessible(webDriver);

                if (!accessible) {
                    webDriver.navigate().to("http://192.168.200.2/webAuth/");

                    // retrieve elements
                    WebElement webElementUserName = webDriver.findElement(By.id("une"));
                    WebElement webElementPasswd = webDriver.findElement(By.id("passwd"));
                    WebElement webElementSubmitBtn = webDriver.findElement(By.id("submit_btn"));

                    // type login data
                    webElementUserName.sendKeys("方垚");
                    webElementPasswd.sendKeys("123456");
                    webElementSubmitBtn.click();
                }


                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                // it doesn't work expected
                webDriver.close();
                // windows management instrumentation service must be started
                webDriver.quit();
            } finally {
                Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
            }
        }
    }

    private void chromeDriverExcutable() {
        System.setProperty("webdriver.chrome.driver", "chromedriver_win32/chromedriver.exe");
    }

    private ChromeOptions options() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--test-type", "--ignore-certificate-errors");
//        options.addArguments("--window-size=-1,-1");
//        options.addArguments("--headless");
//        options.addArguments("--no-startup-window");
//        options.addArguments("--disable-cache");
        options.addArguments("--window-position=-32000,-32000");
        return options;
    }

    private boolean isBuiduAccessible(WebDriver webDriver) {
        webDriver.get("http://www.baidu.com");
        boolean accessible = true;
        try {
            webDriver.findElement(By.id("reload-button"));
            accessible = false;
        } catch (NoSuchElementException e) {
            // can accessible
        }

        return accessible;
    }
}
