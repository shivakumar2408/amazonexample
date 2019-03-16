package seleniumtestingNew;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;




public class amazonTestNew {
	static ConfigFileReader configFileReader;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test");
		configFileReader= new ConfigFileReader();
		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
	    WebDriver driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.navigate().to(configFileReader.getApplicationUrl());
	    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
	   driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys("samsung galaxy s9");
	   driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
	   driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input")).click();
	   driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
	  List<WebElement> links = driver.findElements(By.xpath("//*[contains(text(), 'Galaxy S9 Unlocked')]"));
	 // System.out.println(links.get(0).getAttribute("id"));
	 String xpath1 = generateXPATH(links.get(0), "");
	 //System.out.println(xpath1);
	 //String value = driver.findElement(By.xpath(xpath1)).getText();
	// System.out.println(value);
	 String[] parts = xpath1.split("li"); 
	 System.out.println(parts[0] + "\n" + parts[1]);
	 String price_1= parts[0]+"li[2]/div[1]/div[1]/div[4]/div[1]/div[1]/a[1]/span[1]/span[2]/span[2]";
	 String price_2 = parts[0]+"li[2]/div[1]/div[1]/div[4]/div[1]/div[1]/a[1]/span[1]/span[2]/span[3]";
	 System.out.println(price_1);
	 System.out.println(price_2);
	 String pricewhole1=driver.findElement(By.xpath(price_1)).getText();
	 String pricewhole2=driver.findElement(By.xpath(price_2)).getText();
	 String pricewhole3 = "$" +pricewhole1+ "." + pricewhole2;
	 System.out.println(pricewhole3);
	 driver.findElement(By.xpath(xpath1)).click();
	 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
	 String price_out = driver.findElement(By.xpath("//*[@id=\"priceblock_ourprice\"]")).getText();
	 System.out.println(price_out);
	 Assert.assertEquals(pricewhole3, price_out, "Please check the prices as they are not on par");
	 driver.findElement(By.xpath("//*[@id=\"nav-cart\"]")).click();
	 System.out.println("Added to Cart");
	 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
	 driver.findElement(By.xpath("//*[@id=\"nav-logo\"]/a/span[1]")).click();
	 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
	 driver.findElement(By.xpath("//*[@id=\"nav-your-amazon\"]")).click();
	 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
	 driver.findElement(By.xpath("//*[@id=\"createAccountSubmit\"]")).click();
	 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
	 driver.findElement(By.xpath("//*[@id=\"ap_customer_name\"]")).sendKeys("Shivakumar");
	 driver.findElement(By.xpath("//*[@id=\"ap_email\"]")).sendKeys("abc.xyz@gmail.com");
	 driver.findElement(By.xpath("//*[@id=\"ap_password\"]")).sendKeys("111111");
	 driver.findElement(By.xpath("//*[@id=\"ap_password_check\"]")).sendKeys("111111");
	 System.out.println(driver.findElement(By.xpath("//*[@id=\"a-autoid-0-announce\"]")).getText());
	 
	 //System.out.println(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[3]/div[1]/div[3]/div[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ol[1]/li[2]/div[1]/div[1]/div[4]/div[1]/div[1]/a[1]/span[1]/span[2]/span[2]")).getText());
	}
	
	private static String generateXPATH(WebElement childElement, String current) {
	    String childTag = childElement.getTagName();
	    if(childTag.equals("html")) {
	        return "/html[1]"+current;
	    }
	    WebElement parentElement = childElement.findElement(By.xpath("..")); 
	    List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
	    int count = 0;
	    for(int i=0;i<childrenElements.size(); i++) {
	        WebElement childrenElement = childrenElements.get(i);
	        String childrenElementTag = childrenElement.getTagName();
	        if(childTag.equals(childrenElementTag)) {
	            count++;
	        }
	        if(childElement.equals(childrenElement)) {
	            return generateXPATH(parentElement, "/" + childTag + "[" + count + "]"+current);
	        }
	    }
	    return null;
	}
	
}
