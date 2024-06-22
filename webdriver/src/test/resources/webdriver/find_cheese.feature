Feature: Find on Google
   Let's find out on website

Scenario: Finding some cheese
   Given I am on the Google search page
   When I search for "Cheese!"
   Then the returned page should contain "Merci d’avoir envoyé le formulaire !"
