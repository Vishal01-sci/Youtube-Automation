package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import java.time.Duration;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases extends ExcelDataProvider { // Lets us read the data
        public static ChromeDriver driver;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        @Test
        public void testCase01() throws InterruptedException {
                System.out.println("Start of Test case - 1");
                Wrappers navigateToYouTube = new Wrappers(driver);
                navigateToYouTube.navigateToYoutube();
                Thread.sleep(3000);

                String currentUrl = driver.getCurrentUrl();
                System.out.println(currentUrl);
                Boolean status = currentUrl.contains("youtube");
                Assert.assertTrue(status, "URL doesn't contains youtube!");

                Wrappers aboutYoutube = new Wrappers(driver);
                aboutYoutube.printAbout();
                System.out.println("End of Test case - 1");
        }

        @Test
        public void testCase02() throws InterruptedException {
                System.out.println("Start of Test case - 2");
                Wrappers navigateToYouTube = new Wrappers(driver);
                navigateToYouTube.navigateToYoutube();
                Thread.sleep(3000);

                Wrappers exploreFilms = new Wrappers(driver);
                exploreFilms.selectFilms();

                Wrappers topMovieTragetAud = new Wrappers(driver);
                String movieMarkedAs = topMovieTragetAud.aboutMovieCertification();
                SoftAssert sfa = new SoftAssert();

                sfa.assertTrue(movieMarkedAs.equals("U/A") || movieMarkedAs.equals("U") || movieMarkedAs.equals("A"), "Movie is not marked as A");
                Wrappers topMovieCatogory = new Wrappers(driver);
                String movieCtgy = topMovieCatogory.aboutMovieCatogory();

                sfa.assertTrue(
                                movieCtgy.contains("Drama") || movieCtgy.contains("Action and adventure")
                                                || movieCtgy.contains("Comedy") || movieCtgy.contains("Animation"),
                                "Movie catogory is not present!");
                sfa.assertAll();
                System.out.println("End of Test case - 2");

        }

        @Test
        public void testCase03() throws InterruptedException {
                System.out.println("Start of Test case - 3");
                Wrappers navigateToYouTube = new Wrappers(driver);
                navigateToYouTube.navigateToYoutube();
                Thread.sleep(3000);

                Wrappers exploreMusic = new Wrappers(driver);
                exploreMusic.selectMusic();

                Wrappers playlist = new Wrappers(driver);
                int tracksCount = playlist.musicPlaylist();
                System.out.println("Number of tracks in found playlist are - " + tracksCount);
                
                SoftAssert sfa = new SoftAssert();
                sfa.assertTrue(tracksCount <= 50,"Tracks are greater than 50!");
                sfa.assertAll();
                System.out.println("End of Test case - 3");
        }

        @Test
        public void testCase04() throws InterruptedException {
                System.out.println("Start of Test case - 4");
                Wrappers navigateToYouTube = new Wrappers(driver);
                navigateToYouTube.navigateToYoutube();
                Thread.sleep(3000);

                Wrappers selectLatestNews = new Wrappers(driver);
                selectLatestNews.selectNews();
                System.out.println("End of Test case - 4");

        }

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
        }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}