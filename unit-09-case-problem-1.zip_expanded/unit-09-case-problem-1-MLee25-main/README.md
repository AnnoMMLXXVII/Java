[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7419704&assignment_repo_type=AssignmentRepo)
# WilmU-2022-Unit-9-Case-Problem-1

In this case problem, you will be creating a representation for the products that a store sells and provide functions that allow for the products to be sorted.

## Files for this case problem:
- Product.java
- ProductList.java
- StoreDemo.java

## Product.java Requirements
This class is provided for you.  Do not change or remove any code in this file.

## ProductList.java Requirements
Create a class ProductList that does the following:
- Instance variable
  - products (An ArrayList)
- Methods
  - Constructor method
    - No parameters for this class
    - Call the appropriate method to set products with the initial inventory
  - setInitialInventory method
    - No parameters for this method
    - Initialize products to an ArrayList of Product objects
    - Create at least 5 products and add them to the ArrayList
      - *Since you are manually creating these you won't need to use a loop
  - addProduct method
    - Takes in a Product object
    - Adds it to products
  - removeProduct method
    - Takes in a Product object
    - Removes it from the products
  - sortProductsById method
    - Sorts the products by the ID of the Product objects
  - sortProductsByName method
    - Sorts the products by the name of the Product objects
  - sortProductsByCost method
    - Sorts the products by the cost of the Product objects
  - sortProductsByPrice method
    - Sorts the products by the price of the Product objects
  - getProductsDisplay method
    - Creates a String representation of the products
    - Use a loop to create this String
    - All details of a product should be added by calling the appropriate Product method
    - Add a new line character between Product objects ("\n")

## StoreDemo.java Requirements
This class has been provided for you and assumes you have defined all the appropriate methods within the ProductList class. 
You may temporarily comment out code while testing, but do NOT remove or change code within this class. 
If there is an error with the code for this class, 
it is something you need to fix in the implementation of your ProductList class rather than this one.
