package com.RSA.TestFiles;//

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.RSA.AppiumFrameworkDesign.CartPage;
import com.RSA.AppiumFrameworkDesign.FormPage;
import com.RSA.AppiumFrameworkDesign.ProductCatalogue;
import com.google.common.collect.ImmutableMap;

import TestUtils.AndroidBaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_tc_4_Hybrid extends AndroidBaseTest{
	@Test(dataProvider="getData", groups= {"Smoke"})
	public void FillForm(HashMap<String, String> input) throws InterruptedException
	{
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submittForm(); 
	    productCatalogue.addItemToCartByIndex(0);
	    productCatalogue.addItemToCartByIndex(0);
	   
	    CartPage cartPage =  productCatalogue.goToCartPage();
	    double totalSum = cartPage.getProductsSum();
	    double displayFormattedSum = cartPage.getTotalAmountDisplayed();
	    Assert.assertEquals(totalSum, displayFormattedSum);
		/*
		 * cartPage.popUpText(); String expectedText = "Terms of Conditions";
		 * Assert.assertEquals(expectedText, popUpText);
		 */
	    cartPage.acceptTermsConditions();
		cartPage.submitOrder();
		
		
	}
		
	@BeforeMethod(alwaysRun=true)
    public void beforeTest() {
		formPage.setActivity();
	}
	
	@DataProvider
   public Object[][] getData() throws IOException, InterruptedException {
		Thread.sleep(5000);
	List<HashMap<String, String>>data =getJsonData(System.getProperty("user.dir")+"//src//test//java//RSA//TestData//eCommerce.Json");
	   return new Object[][] {{data.get(0)}, {data.get(1)}};
   }
	
	
	}
	


