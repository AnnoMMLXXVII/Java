[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7749574&assignment_repo_type=AssignmentRepo)
# WilmU-2022-Unit-14-Case-Problem-2

In this lab, you will be creating a GUI that could be tied in with your program from Unit 13 Case Problem 2.

## Files for this case problem:
For this case problem, you will need the following files:
- Demo file (Name it to be relevant to the topic of your data)
- JFrame subclass file (Name it to be relevant to the topic of your data)

## Demo File Requirements
This file with contain your main method that does the following:
- Creates an instance of the JFrame sublcass you create

## JFrame Subclass File Requirements
This will be a subclass of JFrame and will need to include the following:
- Default constructor
  - Calls super constructor with title
  - Sets initial size and default close operation
  - Calls method to initialize components
  - Makes it visible
- Include a link to the data set you are basing this program on in your class comment.
  - *If you wish to change the data set you are basing this program on, you may. 
   Just make sure you include a link to the data set in your class comment*
- JLabel and relevant input for each field/instance variable
  - *If you have a data set that includes a large number of fields/instance, you may limit it to at least the 5 most imporant fields/instance variables
- JList to show the current list of items
  - Items that are added will be shown here
  - Be sure to add new items to the end of the list
  - It should show a summary of the information from the inputs in a meaningful way
- Button to add an item to the list
  - Need to validate each input to ensure there is valid data
  - If a field/instance variable needs to be a data type other than String, you need to ensure it is that type of data.  
    - Example:  If it needs to be an int, then you need to verify that the value in the input is an int
  - Provide an alert if there is blank or invalid data and do not add the item to the list
  - If everything is valid, add the data to the list
- Button to remove an item from the list
  - When nothing is selected in JList, this button should be disabled
  - When something is selected in JList, this button should be enabled
  - If the button is clicked, it will remove that item from the list
- Be sure your code is well organized into appropriate methods
  - I recommend watching the tutorial videos for an example of appropriate organization
  - The tutorial videos also cover some recommendations that aren't covered in the reading for improving layout

Additonal Tutorial on JList: https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
