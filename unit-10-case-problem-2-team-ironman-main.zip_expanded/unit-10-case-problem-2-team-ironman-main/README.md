[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7506235&assignment_repo_type=AssignmentRepo)
# WilmU-2022-Unit-10-Case-Problem-2

In this case problem, you will be creating classes to serve as a Menu for a user to order from.  Below are the minimum requirements, but if you have ideas for making the program better, send me a message for what you would like to do to improve it and I'll let you know if it is approved as long as you still meet the learning requirements for this week.

## Files for this case problem:
- MenuItem.java
- Appetizer.java
- Entree.java
- Side.java
- Dessert.java
- MenuDemo.java

## MenuItem.java Requirements
Create a class called MenuItem that has the following:
- enum
  - Create an enum for the different types of menu items.  
  - At a minimum it should have the values APPETIZER, ENTREE, SIDE, and DESSERT
- Instance variables
  - name
  - price
  - type (Uses the enum)
- Methods
  - Constructor method
    - Include parameters for each of the instance variables
    - Set each of the values using the appropriate setter method
  - Getter/setter methods for the name
  - Getter/setter methods for the price
    - If the given value is less than 0, set it to 0.
  - Getter/setter methods for the type
  - toString method (Override)
    - Returns a String representation of the MenuItem
    - It will return a String matching the following format:
      - _name_ (_type_) sells for $_price_
      - Example: Tomato soup (Appetizer) sells for $3.25.

## Appetizer.java, Entree.java, Side.java, Dessert.java Requirements
Create classes Appetizer, Entree, Side, and Dessert that do the following:
- They are a child class of the MenuItem class (modify the class header to implement this requirement)
- Instance variable
  - None required, but this could be something you request to add if you want to add more detail
- Methods
  - Constructor method
    - Parameters for the name and price
    - Call the super constructor with the given name, price, and relevant enum value for the type

## MenuDemo.java Requirements
Starter code has been provided for you for this class.  The general outline has been completed to allow a user to complete one order.  You will need to finish defining the following methods in the class:
- createAppetizers
  - Create and add 3 appetizers of your choice to the array
- createEntrees
  - Create and add 4 entrees of your choice to the array
- createSides
  - Create and add 4 sides of your choice to the array
- createDesserts
  - Create and add 3 desserts of your choice to the array
- askForMenuItemSelection
  - This needs to display the menu choices based on the given array
  - They should be clearly labelled for easy user input and validation (numbering the choices is the simplest)
  - Be sure to repeat the prompt until the user enters a valid selection
  - Return the selected menu item
