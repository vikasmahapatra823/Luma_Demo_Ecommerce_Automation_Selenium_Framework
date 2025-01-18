Feature: Swags Lab Login functionality

  @Test
  Scenario: To Verify Login of Ecommerce Website
    Given Navigate to the "LUMA" Ecommerce Website
    And Perform the mouse "HOVERING" action on the "Men"
    And Click on the link "Tops"
    And Sort By "Product Name" in ""
    Then Wait for "5" seconds
    Then Add the List of Product items:
      | Product Name           | Color | Size | Text Message                                            |
      | Abominable Hoodie      | Red   | L    | You added Abominable Hoodie to your shopping cart.      |
      | Aero Daily Fitness Tee | Black | XL   | You added Aero Daily Fitness Tee to your shopping cart. |
    And Verify the presence & get the product details:
      | Product Name      | Details          |
      | Abominable Hoodie | Price_Abominable |
      | Abominable Hoodie | Size:XS;S;M;L;XL |
