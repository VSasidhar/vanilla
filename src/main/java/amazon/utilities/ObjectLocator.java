package amazon.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ObjectLocator {
	public 	WebElement element;
	public String locatorType="";
	public String locatorValue="";
	public String locatorDescription;
	public static String xpath="";
	public static String id="";
	public static String Name="";
     By Locator;
	


  public ObjectLocator(String LocatorType,String LocatorDescription)
  
	{
		this.locatorType=LocatorType;
		this.locatorDescription=LocatorDescription;
	}

    public By GetObjectLocator(String locatorType, String locatorValue)
    {
    	
    switch(locatorType.toLowerCase()){
	case("linktext"):
		Locator= By.linkText(locatorValue);	
		break;
	case("partiallinktext"):
		Locator= By.partialLinkText(locatorValue);	
		break;
	case("id"):
		Locator= By.id(locatorValue);
	break;
	case("css"):
		Locator = By.cssSelector(locatorValue);
	break;
	case("name"):
		Locator = By.name(locatorValue);
	break;
	case("xpath"):
		Locator = By.xpath(locatorValue);
	break;
	case("tagname"):
		Locator = By.tagName(locatorValue);
	break;
	} 
    return Locator;
    
}
    
	public ObjectLocator(String LocatorType, String LocatorValue,String LocatorDescription)
	  
	{
	     this.locatorType=LocatorType;
	     this.locatorValue=LocatorValue;
		this.Locator=GetObjectLocator(LocatorType,LocatorValue);
		this.locatorDescription=LocatorDescription;

	}
	
	
public  ObjectLocator ReplaceLocator(String ReplaceString)
	
	{
	  this.locatorValue =locatorValue.replace("$", ReplaceString);
	  this.locatorDescription = locatorDescription.concat(" :: "+ReplaceString);
	  
	  this.Locator=GetObjectLocator(locatorType,this.locatorValue);
	  
	  Reports.log("INFO", this.locatorValue);
	
	  return this;
		
	}

}
