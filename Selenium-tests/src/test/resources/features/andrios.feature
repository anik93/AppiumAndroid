@scenarioTest 
Feature: Product Comparison. 
	Choose product to compare from Product List ,Add to basket, change quantity, remove from basket

Scenario Outline: 
	Should Add product from Comparison Page to basket 
	Given I open app using "<browser>"

	Examples: 
		| browser |
		| ANDROID |
