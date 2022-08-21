[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7510998&assignment_repo_type=AssignmentRepo)
# WilmU-2022-Unit-11-Case-Problem-1

In this case problem, you will be creating a Quiz program with two different types of questions (Multiple Choice and Fill in the Blank).  If you would like to create additional question types you may, but make sure you have it fully working with the two required types first.

## Files for this case problem:
- Question.java
- MultipleChoiceQuestion.java
- FillInTheBlankQuestion.java
- Quiz.java
- QuizDemo.java

## Question.java Requirements
Create an abstract class called Question that has the following:
- Instance variables
  - prompt (This is the text of the question being asked)
  - numOfPoints (int)
- Methods
  - Constructor method
    - Include parameters for each of the instance variables
    - Set each of the values using the appropriate setter method
  - Getter/setter methods for the prompt
  - Getter/setter methods for the numOfPoits
    - Ensure the numOfPoints is not negative.
  - abstract method getCorrectAnswer that returns the correct answer as a String
  - abstract method getQuestionDisplay that returns a String 
  representing the question as it would be displayed in a quiz
  - abstract method isCorrectAnswer takes in a String answer choice 
  and returns true if it is the correct answer (false otherwise).

## MultipleChoiceQuestion.java Requirements
Create a class MultipleChoiceQuestion as a subclass of Question that does the following:
- Instance variable
  - answerChoices (String array)
  - correctAnswerIndex (int)
- Methods
  - Constructor method
    - Parameters for the prompt, numOfPoints, answerChoices, and correctAnswerIndex
    - Call the super constructor with the prompt and numOfPoints
    - Call the appropriate setter methods for answerChoices and correctAnswerIndex
  - Getter/setter methods for answerChoices
  - Getter/setter methods for correctAnswerIndex
    - When setting, if the index is invalid, set it equal to -1
  - getCorrectAnswer
    - This should return the correct answer as a String based on the correctAnswerIndex
    - If the index is -1 (meaning it was invalid), return "ERROR: Correct answer is unknown."
  - getQuestionDisplay
    -  Create a String initially set equal to the prompt.  
    -  Then for each of the answer choices, add on a new line character (\n), 
    a tab character (\t), the number for the answer choice in parentheses and 
    then the text of the answer choice.
    -  Return the completed String
    -  Example of a String that would be returned: 
    "Java is a programming language.\n\t(1) True\n\t(2) False"
  - isCorrectAnswer
    -  Return whether or not the value of the parameter 
    is equal to the correct answer

## FillInTheBlankQuestion.java Requirements
Create a class FillInTheBlankQuestion as a subclass of Question that does the following:
- Instance variable
  - correctAnswer (String)
  - isAnswerCaseSensitive (boolean)
- Methods
  - Constructor method
    - Parameters for the prompt, numOfPoints, correctAnswer, and isAnswerCaseSensitive
    - Call the super constructor with the prompt and numOfPoints
    - Call the appropriate setter methods for correctAnswer and isAnswerCaseSensitive
  - Getter/setter methods for correctAnswer
  - Getter/setter methods for isAnswerCaseSensitive
  - getQuestionDisplay
    -  Return the prompt
  - isCorrectAnswer
    -  If it is case sensitive, return whether or not the value of the parameter
     is equal to the correct answer exactly
    -  If it is not case sensitive, return whether or not the value of the parameter
     is equal to the correct answer when ignoring case


## Quiz.java Requirements
Create a class Quiz that does the following:
- Instance variable
  - questions (ArrayList of Question objects)
- Methods
  - Constructor method
    - No parameters
    - Call the method createSampleQuiz
  - Getter/setter methods for questions
  - createSampleQuiz
    - This will initialize questions to a new ArrayList of Question objects
    - You will then create and add at least 2 MultipleChoiceQuestions 
    and 2 FillInTheBlankQuestions
    - The questions can be on any topic that you want and you are
    welcome to add more questions if you would like

## QuizDemo.java Requirements
This class has been provided for you and assumes you have defined all the appropriate methods within the other classes. You may temporarily comment out code while testing, but do NOT remove or change code within this class. If there is an error with the code for this class, it is something you need to fix in the implementation of your other classes rather than this one.
