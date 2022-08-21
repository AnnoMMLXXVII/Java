[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7312061&assignment_repo_type=AssignmentRepo)
# WilmU-2022-Unit-8-Case-Problem-2

In this case problem, you will be developing a class to represent multiple choice type questions for 
a quiz and then creating a main class that allows a user to create a multiple choice quiz based on that representation.

## Files for this case problem:
- MultipleChoiceQuestion.java
- QuizGenerator.java

## MultipleChoiceQuestion.java Requirements
Create a class called MultipleChoiceQuestion that has the following:
- Class constant for the following:
  - The value to use for an unknown/invalid correct answer index.  Recommend using -1
- Instance variables for the following:
  - The question text
  - An array of answer choices
  - The index of the correct answer based on the array of answer choices
- Methods that need to be created:
  - Constructor method
    - Include parameters for each of the instance variables
    - Set each of the instance variables using the appropriate setter method
  - Getter/setter method for the question
  - Getter/setter method for the array of answer choices
  - setCorrectAnswerIndex will take in an int for the index of the correct answer as a parameter
    - If the value is too high or too low based on the size of the answer choices, set it to the constant for an unknown/invalid correct answer index
    - Otherwise, set it to given value
  - getCorrectAnswerIndex
    - Returns the index of the correct answer
  - getCorrectAnswerAsString
    - If the index of the correct answer is equal to the class constant for an unknown/invalid correct answer index, 
    then return a relevant string that would represent that.
      - Example: "Correct answer is unknown" or "Invalid answer choice set"
    - Otherwise, return the corresponding answer from the array

## QuizGenerator.java Requirements
Create a class QuizGenerator that does the following:
- Methods to create a MultipleChoiceQuestion based on user input in the console
  - One method for creating a MultipleChoiceQuestion based on console input
    - Take in a Scanner as a parameter
    - Call helper methods to get values for each instance variable needed to create a MultipleChoiceQuestion
    - Create and return a MultipleChoiceQuestion based on those values
  - Helper methods to get values of instance variables
    - One to get the question text from the user
      - Include the Scanner as a parameter and return what the user types in
    - One to get the answer choices from the user
      - Include the Scanner as a parameter
      - For this assignment, you can do one of the following:
        - Use a constant for the number of required answer choices
        - Ask the user how many answer choices there will be for the question (Be sure they enter a valid value)
      - Return an array with all the answer choices entered by the user
    - One to get the correct answer from the user
      - Include the Scanner and the array of answer choices as parameters
        - You may also want to include the question text if you want to include that as part of the prompts to the user
      - Ask the user for the correct answer
        - Be sure to show the answer choices with the options for what they need to type for the correct answer
        - You can label the answer choices with numbers (1, 2, 3,...) or letters (A, B, C,...)
      - Return the index of the correct answer
  - When asking for the correct answer, you should take in the question and answer choices
    - You should display the question with the answer choices (Use a helper method to be more efficient)
    - The user should then specify the correct answer (Using numbers for answer choices is probably simplest, but you could also use A, B, C, etc)
- Method to display quiz details
  - This will take in an array of MultipleChoiceQuestions, which represent the quiz
  - It will then print out each question, its answer choices, and the correct answer
    - Be sure to separate questions with a blank line and/or heading with the question number
    - Each answer choice should be on its own line and be labelled with numbers or letters that could be used for selecting an answer
      - It would look better to use letters, but as long as you show it with numbers that is fine
      - You may also want to indent the answer choices by using "\t" to improve the readability in the console
    - Finally, have a line that says what the correct answer is
      - At a minimum you need to show the text for the correct answer choice.
      - For an improved output, it would also be good to label it the same as the answer choice with the number or letter that goes with it
  - To be more efficient create a helper method that prints out the details for a single question AND/OR override the toString method of
   the MultipleChoiceQuestion class
    - To be more abstract, you could also make a method in the MultipleChoiceQuestion class that takes in a parameter of true or false for showing the answer.  
    - Then, it will return a string with the question and the answer choices each on their own line with the answer choices numbered or with letters.
    - Finally, based on the parameter it will determine whether or not to also include the answer as part of the string that gets returned
- Main method
  - Declare an array that will hold 5 MultipleChoiceQuestion objects (You may want to use a smaller number when testing)
  - Create a Scanner object
    - Remember to close it once user input is no longer needed
  - Use a for loop to fill the array with questions set by the user
    - Be sure to call the appropriate method to create each of the Multiple Choice Question objects
  - Call the method to display quiz details that takes in the array of MultipleChoiceQuestion objects you created
