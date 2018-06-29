package com.bass;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;

/**
 * Created by a.makarov on 24.05.2018.
 */
public class PageGetter {
    WebDriver wd;
    WebElement we;
    public long timeOut = 5;
    public PageGetter(WebDriver wd){
        this.wd = wd;
    }
    public boolean getPage(String link, final String result){
        wd.get(link);
        try {
            new WebDriverWait(wd, timeOut).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getTitle().toLowerCase().startsWith(result);
                }
            });
            return true;
        }
        catch (TimeoutException te){
            return false;
        }
    }
    public boolean searchQuery(String line, final String result){
        try {
            we = wd.findElement(By.name("q"));
            we.clear();
            we.sendKeys(new String(line));
            we.submit();

            new WebDriverWait(wd, 10).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getTitle().toLowerCase().startsWith(result.toLowerCase());
                }
            });

            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
        catch (TimeoutException e){
            return false;
        }
        catch (StaleElementReferenceException e){
            return false;
        }
    }
    public boolean downloadClick(){
        int n = 0;
        int sum = wd.findElements(By.linkText("(скачать)")).size();
        while(n < sum){
            if(downloadClick(n))
                return true;
        }
        return false;
    }
    private boolean downloadClick(int num){
        try{
            we = wd.findElements(By.linkText("(скачать)")).get(num);
            we.click();
            return true;
        }
        catch (NoSuchElementException nsee){
            wd.navigate().back();
            System.out.println("Skipped");
            return false;
        }
    }

}
