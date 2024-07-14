Feature: Zadanie Zaliczeniowe 2 Zamawianie Produktu i Screenshot

  Scenario Outline: Zamawianie Produktu i Screenshot
    Given I open and maximize the browser
    And I enter the web page
    And I log in to the account "<login>" , "<password>"
    And I select the desired product
    And I select the desired size that is "<size>"
    And I select the desired number of products that is "<number>"
    And I add the product to the cart
    When I go to the checkout
    And I confirm address: "<alias>", "<city>", "<zip>", "<country>" and "<phone>"
    And I select a carrier as pick up in store
    And I select payment as pay by check
    And I select order with an obligation to pay
    Then I make a screenshot

    Examples:
      | login           | password   | size     | number | alias  | city   | zip    | country | phone     |
      | adrian777@wp.pl | Qwerty123@ | M        | 5      | Adi    | Warsaw | 04-761 | Poland  | 787100393 |