Feature: This Feature File is to validate the End-to-End functionality of Luma Ecommerce Website

  @E2E-Test
  Scenario: To Verify Add Cart Flow of Luma Ecommerce Website
    Given Navigate to the "LUMA" Ecommerce Website
    And Perform the mouse "Hovering" action on the "Men"
    And Click on the link "Tops"
    And Sort By "Product Name" in ""
    Then Wait for "5" seconds
    Then Add the list of product items & verify the success message upon adding the product items into cart:
      | Product Name           | Color | Size | Text Message                                            |
      | Abominable Hoodie      | Red   | L    | You added Abominable Hoodie to your shopping cart.      |
      | Aero Daily Fitness Tee | Black | XL   | You added Aero Daily Fitness Tee to your shopping cart. |
    And Verify the presence & get the product details:
      | Product Name           | Details                      |
      | Abominable Hoodie      | Price_Abominable             |
      | Abominable Hoodie      | Size:XS;S;M;L;XL             |
      | Aero Daily Fitness Tee | Price_Aero Daily Fitness Tee |
    And Save the following details in feature properties:
      | Key                         | Value                        |
      | Abominable_Hoodie_Cost      | Price_Abominable             |
      | Aero_Daily_Fitness_Tee_Cost | Price_Aero Daily Fitness Tee |
    Then Click cart icon of "My Cart" link
    And Verify the sub total and product price of items on cart:
      | Key                         | Element                |
      | Abominable_Hoodie_Cost      | Abominable Hoodie      |
      | Aero_Daily_Fitness_Tee_Cost | Aero Daily Fitness Tee |
      | Sub_Total                   | Sub Total              |
    Then Update the product items on the shopping cart list:
      | Product Name           | QTY | Key                                 |
      | Aero Daily Fitness Tee | 2   | Updated_Aero_Daily_Fitness_Tee_Cost |
      | Abominable Hoodie      | 2   | Updated_Abominable_Hoodie_Cost      |
    And Verify the sub total and product price of items on cart:
      | Key                         | Element                |
      | Abominable_Hoodie_Cost      | Abominable Hoodie      |
      | Aero_Daily_Fitness_Tee_Cost | Aero Daily Fitness Tee |
      | Sub_Total                   | Sub Total              |
    And Click on the hyperlink of "View and Edit Cart"
    And Wait for "5" seconds
    And Verify the page navigation url ends with "checkout/cart/"
    And Verify the Page Header is "Shopping Cart"





