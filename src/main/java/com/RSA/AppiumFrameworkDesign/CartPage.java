package com.RSA.AppiumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.RSA.AppiumFrameworkDesign.utils.AndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{

AndroidDriver driver;
AndroidActions androidActions = new AndroidActions(driver);

	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver =driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy (id ="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;

	@AndroidFindBy (id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/termsButton")
	private WebElement terms;
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/alertTitle")
	private WebElement popuptext;
	
	@AndroidFindBy (id="android:id/button1")
	private WebElement acceptButton;
	
	@AndroidFindBy (className="android.widget.CheckBox")
	private WebElement checkbox;
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;
	

		
	public List<WebElement> getProductList() 
	{
		return productList;
	}
	
	public double getProductsSum() {
	int count = productList.size();
	double totalSum =0;
	
	for(int i=0; i<count; i++)
	   {
	String amountString = productList.get(i).getText();
	Double price=getFormattedAmount(amountString);
	totalSum = totalSum + price;//160.97+120.0=280.97
	 }
	return totalSum;
	}
	
	public Double getTotalAmountDisplayed() {
		return getFormattedAmount(totalAmount.getText());
	}
	
	public void acceptTermsConditions() {
		  longPressAction(terms);
			acceptButton.click();
		}
	
	/*
	 * public String popUpText() { longPressAction(terms); popuptext.getText();
	 * return popUpText(); } public void acceptTermsConditions() {
	 * longPressAction(terms); acceptButton.click(); }
	 */
	public Double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}

	public void submitOrder() throws InterruptedException {
		checkbox.click();
		proceed.click();
		Thread.sleep(5000);
	}
	
}
