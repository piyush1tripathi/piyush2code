package Learnings.User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UserLogin {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ASUS\\Desktop\\chromedriver-win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
		driver.findElement(By.linkText("Table Data")).click();
		driver.findElement(By.id("jsondata")).click();
		try {
		 WebElement inputTextBox = driver.findElement(By.id("inputTextBoxId"));
         inputTextBox.sendKeys("[{\"name\":\"Bob\",\"age\":20,\"gender\":\"male\"},{\"name\":\"George\",\"age\":42,\"gender\":\"male\"},{\"name\":\"Sara\",\"age\":42,\"gender\":\"female\"},{\"name\":\"Conor\",\"age\":40,\"gender\":\"male\"},{\"name\":\"Jennifer\",\"age\":42,\"gender\":\"female\"}]");

         // Find and click the button to populate the table
         WebElement populateButton = driver.findElement(By.id("populateButtonId"));
         populateButton.click();
         Thread.sleep(2000);

         // Get the data from the UI table
         WebElement table = driver.findElement(By.id("tableId"));
         String tableData = table.getText();

         // Parse the JSON data
         JsonArray expectedData = JsonParser.parseString("[{\"name\":\"Bob\",\"age\":20,\"gender\":\"male\"},{\"name\":\"George\",\"age\":42,\"gender\":\"male\"},{\"name\":\"Sara\",\"age\":42,\"gender\":\"female\"},{\"name\":\"Conor\",\"age\":40,\"gender\":\"male\"},{\"name\":\"Jennifer\",\"age\":42,\"gender\":\"female\"}]").getAsJsonArray();

         // Parse the table data
         JsonArray actualData = JsonParser.parseString(tableData).getAsJsonArray();

         // Assert that the data in the table matches the expected data
         if (expectedData.equals(actualData)) {
             System.out.println("Test Passed: Data in the table matches the expected data.");
         } else {
             System.out.println("Test Failed: Data in the table does not match the expected data.");
         }

     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         // Close the browser
         driver.quit();
     }
 }
}