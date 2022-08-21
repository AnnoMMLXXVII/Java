[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7532508&assignment_repo_type=AssignmentRepo)
# WilmU-2022-Unit-12-Case-Problem-2

In this lab, you will be modifying the Menu program that you made in Unit 10 to use exception handling to protect your program from crashing and prevent invalid data.

## Files for this case problem:
For this case problem, you will use your code from the Unit 10 Case Problem 2 as your starter code.  You will need to copy the code from your Unit 10 Case Problem 2 assignment to this assignment to update:
- MenuItem.java
- Entree.java
- Side.java
- Appetizer.java
- Dessert.java
- MenuDemo.java
- NegativePriceException.java

## NegativePriceException.java
- Make this a child class of Exception
- Define a default constructor method
  - It will call the super constructor with the following message: "Price cannot be negative."

## MenuItem.java Requirements
You should already have this complete from the original assignment.  You are now going to make some adjustments for this class:
- Make your setter method for price throw a NegativePriceException when the value of the parameter is less than 0
- Since the setter method is throwing an Exception, you will need to update the constructor method to also throw the Exception since it calls the setter method

These updates will now make it so a MenuItem cannot be created with invalid data.

## Appetizer.java, Entree.java, Side.java, Dessert.java Requirements
You should already have this complete from the original assignment.  You are now going to make some adjustments for these classes:
- Since these are subclasses of MenuItem, their constructors need to be updated to handle the possible Exception. 
 Therefore, these need to be updated to also throw the NegativePriceException

These updates will make it so the subclasses also cannot be created with invalid data.

## MenuDemo.java Requirements
You should already have this complete from the original assignment.  You are now going to make some adjustments for this class:
- In the methods where you are creating the different foods, you will need to use a try-catch
  - Technically, you should use a try-catch for each time you call the constructor so the successful ones can still be created with ease.  
  - For the purpose of this lab, you may use one try-catch within each of the methods that create items
  - If an Exception is caught, print out a message with the error
    - You need to update any constructor method calls that result in an Exception being thrown
- Update the askForMenuItemSelection method to use try-catch for an invalid input type
  - This will need to catch Exceptions related to the user entering in values that are not numeric so that the program no longer crashes
  - If an Exception is caught, it will need to alert the user and repeat trying to get a valid input from the user.  
    - Be sure to clear the keyboard buffer before it repeats.

With these updates, you should now be able to enter in any values you want and your program will no longer crash.
