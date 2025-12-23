package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    ChromeDriver driver;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public Wrappers(ChromeDriver driver){
        this.driver = driver;
    }

    String youTubeURL = "https://www.youtube.com/";

    // Navigating to Youtube
    public void navigateToYoutube(){
        driver.get(this.youTubeURL);
    }

    // Printing the message in About page of Youtube - Test case - 1
    public void printAbout() throws InterruptedException {
        WebElement about = driver.findElement(By.xpath("//a[text() = 'About']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",about);
        about.click();
        Thread.sleep(3000);
        WebElement printMessage = driver.findElement(By.xpath("//section[@class='ytabout__content']"));
        System.out.println(printMessage.getText());
    }

    // Clicking on Films from the categories in Youtube - Test case - 2
    public void selectFilms() throws InterruptedException {
        WebElement films = driver.findElement(By.xpath("//yt-formatted-string[contains(text(),'Films') or contains(text(),'Movies')]"));
        films.click();
        Thread.sleep(3000);

    }

    // Printing and asserting(SoftAssert) the movie's catogory and certification - Test case - 2
    public String aboutMovieCertification() throws InterruptedException {
        WebElement scrollRight = driver.findElement(By.xpath("(//button[@aria-label='Next'])[1]"));
        while(scrollRight.isDisplayed()) {
           scrollRight.click();
        }
        Thread.sleep(3000);
        //  Whether movie is marked as A or not
        WebElement movieCerification = driver.findElement(By.xpath("(((//*[@id='scroll-container'])[1])//ytd-grid-movie-renderer//child::div[@class='yt-badge-shape__text'])[last()]"));
        String movieCert = movieCerification.getText();
        return movieCert;
    }
        // Catogory of the movie
        public String aboutMovieCatogory() throws InterruptedException{
        WebElement movieCatogory = driver.findElement(By.xpath("(((//*[@id='scroll-container'])[1])//ytd-grid-movie-renderer//child::a/span)[last()]"));
        if(!movieCatogory.isDisplayed()){
            System.out.println("Movie catogory is not displayed");
            return "Catogory is not displayed!";
        }
        String movieCtg = movieCatogory.getText().replaceAll("[^a-zA-Z]","").trim();
        return movieCtg;

    }

    //  Clicking on Music from the categories in Youtube - Test case - 3
    public void selectMusic() throws InterruptedException{
        WebElement music = driver.findElement(By.xpath("//yt-formatted-string[(text()='Music') and contains(@class,'title')]"));
        wait.until(ExpectedConditions.elementToBeClickable(music));
        music.click();
        Thread.sleep(3000);
        
    }

    // Printing and asserting(SoftAssert) of music playlist - Test case - 3 
    public int musicPlaylist() throws InterruptedException {
        int tracks=0;
        List<WebElement> listOfPlaylists = driver.findElements(By.xpath("(//ytd-rich-item-renderer//child::div[@class='yt-badge-shape__text'])"));
        for(int i=0; i<listOfPlaylists.size(); i++){
            if(!(listOfPlaylists.get(i).isDisplayed())){
                WebElement noOfTracks = driver.findElement(By.xpath("(//ytd-rich-item-renderer//child::div[@class='yt-badge-shape__text'])["+i+"]"));
                tracks = Integer.parseInt(noOfTracks.getText().replaceAll("[^0-9]","").trim());
                WebElement nameOfTrack = driver.findElement(By.xpath("(((//ytd-rich-item-renderer)//h3)//span)["+i+"]"));
                System.out.println("Title of the playlist is - " + nameOfTrack.getText());
                break;
            }
        }
        return tracks;
    }

    // Clicking on News from the categories in Youtube - Test case - 4

    public void selectNews() throws InterruptedException {
        // Clicking on show more
        WebElement showMore = driver.findElement(By.xpath("//yt-formatted-string[text()='Show more']"));
        wait.until(ExpectedConditions.elementToBeClickable(showMore));
        showMore.click();
        // Clicking on News catogory in Youtube
        WebElement selectNews = driver.findElement(By.xpath("//yt-formatted-string[text()='News']"));
        wait.until(ExpectedConditions.elementToBeClickable(selectNews));
        selectNews.click();
        Thread.sleep(5000);
        // Printing the Title , body and sum of likes combined of top 3 latest news
        long sumOfLikes = 0;
        double like=0;
        for(int i=1; i<=3; i++){
            WebElement titleOfNews = driver.findElement(By.xpath("(((//div[@id='contents'])[3])//ytd-rich-item-renderer//child::a[@id='author-text']/span)[" + i +"]"));
            System.out.println("Title of news - " + titleOfNews.getText());
            WebElement bodyOfNews = driver.findElement(By.xpath("(((//div[@id='contents'])[3])//yt-formatted-string[@id='home-content-text'])["+i+"]"));
            System.out.println("Body of news - " + bodyOfNews.getText());
            WebElement noOfLikes = driver.findElement(By.xpath("(((//div[@id='contents'])[3])//div[@id='toolbar']//span[@id='vote-count-middle'])["+i+"]"));
            if(noOfLikes.isDisplayed()){
                String likes = noOfLikes.getText().trim().toLowerCase();
                System.out.println(likes);
                if(likes.endsWith("k")){
                    like = Double.parseDouble(likes.replaceAll("[^0-9]","")) * 1000;
                    sumOfLikes = sumOfLikes + Math.round(like);
                }
                else if(likes.endsWith("m")){
                    like = Double.parseDouble(likes.replaceAll("[^0-9]","")) * 1000000;
                    sumOfLikes = sumOfLikes + Math.round(like);
                }
                else if(likes.endsWith("b")){
                    like = Double.parseDouble(likes.replaceAll("[^0-9]","")) * 1000000000;
                    sumOfLikes = sumOfLikes + Math.round(like);
                }
                else{
                    sumOfLikes = sumOfLikes + Long.parseLong(likes.replaceAll("[^0-9]", ""));
                }
            }
            else{
                sumOfLikes = sumOfLikes + 0;
                System.out.println("The "+ titleOfNews.getText() + " has no likes!");
            }
            
        }
        System.out.println("Total sum of likes for first 3 latest news is - " + sumOfLikes);

    }
}
