/**
 * Created by a.makarov on 10.05.2018.
 */
package com.bass;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.*;

public class Test {
    static final String SCV_FILE = "d:\\1\\playlist.csv";
    static final String DOWNLOAD_FILEPATH = "D:\\1";
    static final String CHROME_DRIVER_PATH = "D:\\selenium\\chromedriver_win32\\chromedriver.exe";
    static PageGetter pg;
    static WebDriver driver;

    public static void main(String... args) throws InterruptedException, IOException{

        CSVListMP3 csv = new CSVListMP3(SCV_FILE);
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = makeWebDrv();

        pg = new PageGetter(driver);
        getMP3(csv.getLines());

    }
    static WebDriver makeWebDrv(){
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", DOWNLOAD_FILEPATH);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        return new ChromeDriver(options);
    }
    static void getMP3(HashMap<String, String> hm) throws InterruptedException{
        while(!pg.getPage("http://ru23348.mp3cc.org", "ru23348.mp3cc.org"));


        for(String s : hm.keySet()){
            if(!pg.searchQuery(hm.get(s) + " " + s, hm.get(s))) {
                System.out.println("Error search: " + hm.get(s) + " - " + s);
                while(!pg.getPage("http://ru23348.mp3cc.org", "ru23348.mp3cc.org"));
                continue;
            }
            if(!pg.downloadClick()) {
                System.out.println("Error click: " + hm.get(s) + " " + s);
                while(!pg.getPage("http://ru23348.mp3cc.org", "ru23348.mp3cc.org"));
                continue;
            }
                    }

    }
}


