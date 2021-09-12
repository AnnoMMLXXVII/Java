package cs423.DijkstraAlgo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Tests {

	/// INSTANCE VARIABLES --> SHOULD HAVE ALREADY DECLARED THESE IN YOUR CODE
	private Connection conn;
	String cell = "userInput";
	PreparedStatement pStmt;
	String sql = "";
	int rowsAffected = 0;

	/*
	 * You're not going to copy this exactly. You might need to change this slightly
	 * but for the most part should be a good outline or guidance... i'll comment as
	 * I go. you can along the way. If you have any comments --> Just ask in the
	 * chat
	 */

	public Tests() {

		try {
//			Class.forName(driver);
<<<<<<< HEAD
			Connection conn = null;
//			DriverManager.getConnection(url, user, pass);
			// FOREIGN KEY DELETE HERE

=======
//			Connection conn = DriverManager.getConnection(url, user, pass);
	// FOREIGN KEY DELETE HERE
		
>>>>>>> 0fd58fcb2afd83c31017800ac74c13f1dbcbd81a
			rowsAffected = removeAllForeignKeys(); // calls method at line 48

			// after all Foreign Keys are removed -->

			// PRODUCT_NAME from Product_Item is to be removed
			sql = "DELETE FROM Product_Items WHERE Product_Name = " + cell + ";";
			pStmt = conn.prepareStatement(sql);
//			rowsAffected = rowsAffected + pStmt.executeUpdate();
			pStmt.executeUpdate(); // WITHOUT ROWS AFFECTED
			pStmt.close();

			// printing out the total number of rows affect
			System.out.printf("Total Rows Affected: %d", rowsAffected);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Product's is already Removed!");
		}
	}

	private int removeAllForeignKeys() {
		// this method here will search for all foreign keys (Product_Name) and search
		// for matching input
		// then it will delete all those that match one by one
//				--> Product_Sales
//				--> Product_Stock
//				--> Sales_History
//				--> Stocks_History
		try {
//		SPECIFIC SQL STATEMENT FOR JUST DELETING PRODUCT_SALES THAT'S LOOKING FOR A MATCH IN THE PRODUCT_NAME COLUMN
			sql = "DELETE FROM Product_Sales WHERE Product_Name = " + cell + ";";
			// this statment will prepare the the string above to remove of all extra spaces
			// or what not. help prevent sql injection
			pStmt = conn.prepareStatement(sql);
			rowsAffected = rowsAffected + pStmt.executeUpdate(); // SHOULD DELETE JUST THE PRODUCT_SALES -- FK
			pStmt.close(); // PREVENT PREVIOUS STATEMENT FROM EXECUTING

//		SPECIFIC SQL STATEMENT FOR JUST DELETING PRODUCT_STOCK THAT'S LOOKING FOR A MATCH IN THE PRODUCT_NAME COLUMN
			sql = "DELETE FROM Product_Stock WHERE Product_Name = " + cell + ";";
			// this statment will prepare the the string above to remove of all extra spaces
			// or what not. help prevent sql injection
			pStmt = conn.prepareStatement(sql);
			rowsAffected = rowsAffected + pStmt.executeUpdate(); // SHOULD DELETE JUST THE PRODUCT_STOCK -- FK
			pStmt.close(); // PREVENT PREVIOUS STATEMENT FROM EXECUTING

//		SPECIFIC SQL STATEMENT FOR JUST DELETING STOCKS_HISTORY THAT'S LOOKING FOR A MATCH IN THE PRODUCT_NAME COLUMN
			sql = "DELETE FROM Sales_History WHERE Product_Name = " + cell + ";";
			// this statment will prepare the the string above to remove of all extra spaces
			// or what not. help prevent sql injection
			pStmt = conn.prepareStatement(sql);
			rowsAffected = rowsAffected + pStmt.executeUpdate();
			pStmt.close();

//		SPECIFIC SQL STATEMENT FOR JUST DELETING SALES_HISTORY THAT'S LOOKING FOR A MATCH IN THE PRODUCT_NAME COLUMN
			sql = "DELETE FROM Stocks_History WHERE Product_Name = " + cell + ";";
			// this statment will prepare the the string above to remove of all extra spaces
			// or what not. help prevent sql injection
			pStmt = conn.prepareStatement(sql);
			rowsAffected = rowsAffected + pStmt.executeUpdate();
			pStmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Product's is already Removed!");
		}
		return rowsAffected;
	}
}
