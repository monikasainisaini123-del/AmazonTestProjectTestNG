package com.amazon.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    // ThreadLocal ensures parallel threads get their own isolated browser instance
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Getter method for tests and listeners
    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "url"})
    public void setUp(
            @Optional("chrome") String browser,
            @Optional("https://www.amazon.com") String url
    ) {
        WebDriver localDriver = null;

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36");
            localDriver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            localDriver = new FirefoxDriver();
        }

        if (localDriver != null) {
            localDriver.manage().window().maximize();
            localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            localDriver.get(url);
            driver.set(localDriver); // Store driver in current thread
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver localDriver = getDriver();
        if (localDriver != null) {
            try {
                // Clear interrupted state if a test timed out so driver.quit() works cleanly
                if (Thread.interrupted()) {
                    System.out.println("Thread was interrupted by timeout. Clearing flag to close browser...");
                }
                localDriver.quit(); // Closes the browser window!
            } catch (Exception e) {
                System.err.println("Error quitting driver: " + e.getMessage());
            } finally {
                driver.remove(); // Prevent memory leaks
            }
        }
    }
}