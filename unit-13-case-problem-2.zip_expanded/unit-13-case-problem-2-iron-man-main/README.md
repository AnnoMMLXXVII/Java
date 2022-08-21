[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7709275&assignment_repo_type=AssignmentRepo)
# WilmU-2022-Unit-13-Case-Problem-2

This lab will be a bit more open-ended than previous labs. 
 In this lab, you will need to find a dataset with information of interest to you from Kaggle 
 (https://www.kaggle.com/datasets) or CORGIS (https://corgis-edu.github.io/corgis/csv/). 
  If you would like to use a dataset from a different website, be sure to contact me for approval first by Thursday.  
  Once you find a file you want to use, be sure to bookmark it, download the dataset and add it to your files for this case problem.

You will be creating a class representation for the records within the dataset and 
designing a demo class that can read in the information from the file, create new instances of the class and 
display the information of those instances.

Be sure to follow the clean coding practices you have been practicing throughout this course and you have seen 
within the provided starter code.  If you need assistance, please let me know.

## Files for this case problem:
For this case problem, you will need the following files:
- File for your class to represent records from your selected dataset
  - You may incorporate the use of inheritance if it is relevant for your dataset, but this is not required
  - Example: If I used the following dataset on Comic Characters 
  (https://www.kaggle.com/fivethirtyeight/fivethirtyeight-comic-characters-dataset?select=marvel-wikia-data.csv),
   I would create at least a ComicCharacter.java file
- File for your demo class, which will have your main method
  - This is the only file that should contain input/output
  - Example: If I used the Comic Character dataset, I might call this file ComicCharacterDemo.java
- File that you are using for your processing

## File for Class to Represent Records
- Instance variables
  - Review your dataset and create an instance variable with the appropriate datatype for each column in your dataset
    - Looking at the Comic Character dataset, there is a table in the README file that details what each of the columns 
    represent to make easier for me to understand the data to pick an appropriate data type
    - You might consider using enums if there is a fixed set of possible values for something.  Example for the Comic Characters, 
    I could create an enum for ALIGN since it would only use the values "Good", "Bad", or "Neutral"
- Create a constructor method that has parameters for each of the instance variables
  - It needs to call the appropriate setter method for each instance variable
- Create setter/getter methods for each instance variable
- Override the toString method to provide a detailed String representation that includes all the information contained 
in the instance variables that would be easy for a user to understand

## File for Demo Class
- Include a link to the web page where you downloaded the dataset you are using as a part of the class comment

- askForFilePath method
  - This will ask the user for the file path to open for the dataset
  - It will return the file path provided by the user as a String
  - Note: As long as the file is saved in the same directory as these files, you only need to specify the name of the file
  and the file extension (Similar to the first case problem for this unit)

- openDataFile method *You may adjust this name slightly to reflect the type of data file if you would like, but it isn't required
  - Includes a parameter for the file
  - It will attempt to open the file and read in the information
  - It needs to create an object for each record within your dataset and add it to a list
  - If everything is successful, it will return the list of objects that were created
  - If the file doesn't exist or there was an error, it should provide an error message and return null

- displayData method
  - It will take in a list of the objects you created a class for
  - It will then print out each of the objects in the list

- main method
  - It will need to ask the user for a file path, using the appropriate method
  - It will then print out "Attempting to open file"
  - It will then need to attempt to open the data file, using the appropriate method
  - If the result from opening the data file isn't null, it will print out "Data file successfully opened" and then call the 
  appropriate method to display the data

## File Used for Processing
This is the file that you download with the data that you are reading in.  Include this file when you upload your code to GitHub.
