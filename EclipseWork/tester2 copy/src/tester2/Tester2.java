/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Hacked
 */
public class Tester2 {
    private final String col1 = "Product Code\t", col2 = "Product Name\t", col3 = "Unit Price\t", col4 = "Stock Available\t";
    private final String ocol1 = "Product Code\t", ocol2 = "Quantity\t", ocol3 = "Unit Price\t", ocol4 = "Total\t";
    private Scanner z = new Scanner(System.in);

    private InventoryController ic = InventoryController.getInstance();
    private OrderController oc = OrderController.getInstance();
    protected ArrayList<Products> prodList;
    protected ArrayList<Order> orderList;


    public static void main(String[] args) {
        new Tester2();
    }

    public Tester2() {
        initRun();
    }


    private void initRun() {
        prompts();
        initAll();
        String userResponse = z.next();
        controlRun(userResponse);
    }

    public void prompts() {
        System.out.println("Show List(s) (1)\nAdd To Inventory (2)\nAdd To Checkout (3)\nEnter \"CHECK OUT\" to Exit");
    }

    private void initAll() {
        prodList = ic.getProdList();
        orderList = oc.getOrderList();
    }

    public void controlRun(String input) {
        input.toLowerCase();
        if (input.equals("1")) {
            System.out.println("INVENTORY List");
            showInventoryList();
            System.out.println("-----------------------------------------------------");
            System.out.println("Order Checkout List");
            showOrderList();
            System.out.println("\n\n");
            initRun();
        } else if (input.equals("2")) {
            userAddToInventory();
            System.out.println("\n");
            initRun();
        } else if (input.equals("3")) {
            userAddToCheckout();
            System.out.println("\n");
            initRun();
        } else if (input.contains("check") || input.contains("out")) {
            System.out.println("Terminating Program . . . ");
            System.exit(1);
        } else {
            initRun();
        }
    }
    // Uncomment lines 78 - 87 below to auto generate product items 
    // comment line 56 or userAddToInventory() call method
    // uncomment line 57

    private void autoAddToInventory() {
        Random r = new Random();
        int rand = r.nextInt(10) + 1;
        for (int i = 0; i < 10; i++) {
            ic.addNewProduct(new Products("Product" + i, "ProdName" + i, (double) (rand * 10), rand));
            rand = r.nextInt(10) + 1;
        }
        prodList = ic.getProdList();
        showInventoryList();
    }

    private void userAddToInventory() {
        System.out.println("ENTER REQUIRED FIELDS BELOW\n");
        System.out.print("Product Code: ");
        String prodCode = z.next();
        System.out.print("\nProduct Name: ");
        String prodName = z.next();
        z.nextLine();
        System.out.print("\nUnit Price: ");
        double unitPrice = z.nextDouble();
        System.out.print("\nStock Availability: ");
        int stock = z.nextInt();
        int rowsAffected = ic.addNewProduct(new Products(prodCode, prodName, unitPrice, stock));
        if (rowsAffected >= 0) {
            System.out.println("--> Successfully Added to Inventory");
        }
//    	initRun();
    }

    private void userAddToCheckout() {
        prodList = ic.getProdList();
        if (prodList.size() == 0) {
            System.out.println("Inventory List is Empty!\n");
            initRun();
        }
        System.out.println("ENTER REQUIRED FIELDS BELOW\n");
        System.out.print("Product Code: ");
        String prodCode = z.next();
        pullFromInventory(ic.pullProduct(prodCode));

    }

    private void pullFromInventory(int idx) {
        System.out.printf("Adding To Order . . .");
        if (idx >= 0) {
            oc.addOrder(
                    new Order(prodList.get(idx).getProdCode(), prodList.get(idx).getstockAvailable(), prodList.get(idx).getUnitPrice(), (prodList.get(idx).getUnitPrice() * (double) prodList.get(idx).getstockAvailable())));
            ic.removeProdItem(idx);
            System.out.println("Successfully Added!\n\n");
            initRun();
        } else {
            System.out.println("No Matching Product Code!");
        }
    }

    private void showInventoryList() {
        prodList = ic.getProdList();
        if (prodList.size() == 0) {
            System.out.println("Empty Inventory");
        } else {
            System.out.printf("%s %s %s %s\n", col1, col2, col3, col4);
            for (Products p : prodList) {
                System.out.printf("%s\t %s\t %.2f\t\t %d\t\n", p.getProdCode(), p.getprodName(), p.getUnitPrice(), p.getstockAvailable());
            }
        }
    }

    private void showOrderList() {
        orderList = oc.getOrderList();
        if (orderList.size() == 0) {
            System.out.println("Empty Checkout");
        } else {
            System.out.printf("%s %s %s %s\n", ocol1, ocol2, ocol3, ocol4);
            for (Order o : orderList) {
                System.out.printf("%s\t\t%d\t\t%.2f\t\t%.2f\n", o.getProdCode(), o.getQuantity(), o.getUnitPrice(), o.getTotal());
            }
        }
    }
}
                        
                     