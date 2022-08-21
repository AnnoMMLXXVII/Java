[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7511015&assignment_repo_type=AssignmentRepo)
# WilmU-2022-Unit-11-Case-Problem-2

In this case problem, you will be creating Employees for a company.

## Files for this case problem:
- Employee.java
- SalaryEmployee.java
- HourlyEmployee.java
- Department.java
- DepartmentDemo.java

## Employee.java Requirements
Create an abstract class called Employee that has the following:
- Instance variables
  - id (String to allow for leading 0's to be preserved and possibly letters)
  - firstName (String)
  - lastName (String)
- Methods
  - Constructor method
    - Include parameters for each of the instance variables
    - Set each of the values using the appropriate setter method
  - Getter/setter methods for the id
  - Getter/setter methods for the firstName
  - Getter/setter methods for the lastName
  - abstract method getPayInfo that returns a String that provides information about how much the Employee is paid

## SalaryEmployee.java Requirements
Create a class SalaryEmployee as a subclass of Employee that does the following:
- Class constant
  - MINIMUM_SALARY and set this to a value you feel is appropriate
- Instance variable
  - salary (double)
- Methods
  - Constructor method
    - Parameters for the id, first name, last name, and salary
    - Call the super constructor with the id, first name, and last name
    - Call the appropriate setter method for salary
  - Getter/setter methods for salary
    - If the value is less than MINIMUM_SALARY then set the salary to the MINIMUM_SALARY
  - getPayInfo
    - This will return a String with the salary information like the following example
    - Example: "$75000 per year"

## HourlyEmployee.java Requirements
Create a class HourlyEmployee as a subclass of Employee that does the following:
= Class constant
  = MINIMUM_HOURLY_PAY and set this to a value you feel is appropriate
- Instance variable
  - hourlyPay (double)
- Methods
  - Constructor method
    - Parameters for the id, first name, last name, and hourlyPay
    - Call the super constructor with the id, first name, and last name
    - Call the appropriate setter method for hourlyPay
  - Getter/setter methods for hourlyPay
    - If the value is less than MINIMUM_HOURLY_PAY, then set the hourlyPay to the MINIMUM_HOURLY_PAY
  - getPayInfo
    - This will return a String with the hourly pay information like the following example
    - Example: "$25 per hour"

## Department.java Requirements
Create a class Department that does the following:
- Instance variable
  - name (String) *Name of the department like Accounting or IT
  - employees (ArrayList of Employee objects)
- Methods
  - Constructor method
    - Parameters for the name and list of employees
    - Call the appropriate setter methods for each parameter
  - Getter/setter methods for name
  - Getter/setter method for employees

## DepartmentDemo.java Requirements
You will use what you have learned to design an appropriate DepartmentDemo class.  
This class will allow the user to provide the information about the employees of a department.
  This class needs to do the following at a minimum:
- Ask the user for a department name
- Either ask the user how many employees are in the department OR keep asking the user if they would like to add another employee to the department
- For each employee, you will ask the user for the employee's id, first name, last name, and what type of employee they are (Salary or Hourly)
- Based on the type of employee, you will ask for their relevant pay and then create that type of Employee and add it to a list of employees
- After the user has entered all the information for the employees in the department, you will then create a Department object.
- Finally, you will display the details of the Department object that was created so that it shows the department name, 
and information about each employee (be sure to include their pay information)

Be sure that your class is well designed and uses helper methods for sub-tasks rather than coding everything directly in the main method.  
You may find it helpful to go back and override the toString methods in classes, but that is up to you to determine how you want to handle displaying the information.
