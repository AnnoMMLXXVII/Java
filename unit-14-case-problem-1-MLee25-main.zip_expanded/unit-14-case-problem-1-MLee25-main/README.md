[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7749510&assignment_repo_type=AssignmentRepo)
# WilmU-2022-Unit-14-Case-Problem-1

In this lab, you will be creating a GUI for ordering a meal.  This program assumes that only one person is ordering, 
so you only need to handle one meal order. If you would like to extend the program further once you have it working for one order, 
you could enhance it so a person can add multiple orders and find out the total price for all of the orders.

## Files for this case problem:
For this case problem, you will need the following files:
- OrderDemo.java
- JOrderMenu.java

## OrderDemo.java Requirements
This file with contain your main method that does the following:
- Creates an instance of the JOrderMenu

## JOrderDemo.java Requirements
This will be a subclass of JFrame and will need to include the following:
- Default constructor
  - Call super constructor with title
  - Set the initial size and default close operation
  - Call method to initialize components
  - Makes it visible
- A section for an entree to order
  - Use JCheckbox (only allow one to be selected) or a JComboBox (include an option for none)
  - Be sure to show the price for each item
  - Minimum of 4 options available
- A section for side(s) to order
  - Use JCheckBox
  - Be sure to show the price for each item
  - Minimum of 5 options available
- A section for drink(s) to order
  - Use JCheckBox
  - Be sure to show the price for each item
  - Minimum of 3 options available
- Option to upgrade size
  - This is a single JCheckBox
  - Add 10% to the total price for an upgraded size
- Order Summary
  - The order summary should show
    - all the items that have been selected to order
    - if the order is standard size meal or upgraded size
    - the current total price
  - It needs to be updated when selections are changed
- Be sure to implement the appropriate listeners to handle the relevant events
- Be sure to organize your code well into appropriate methods
  - Refer to the video tutorials to see how you can organize your code into helper methods
  - Also, the video tutorials will demonstrate some additional things you can do to improve the layout some
