package tester2;

import java.util.ArrayList;

public class InventoryController {
    private static InventoryController pc = null;
    protected ArrayList<Products> prodList = new ArrayList<Products>();

    public static InventoryController getInstance() {
        return (pc == null) ? new InventoryController() : pc;
    }

    private void setProdList(ArrayList<Products> prodList) {
        this.prodList = prodList;
    }

    protected ArrayList<Products> getProdList() {
        return (prodList == null) ? new ArrayList<Products>() : prodList;
    }

    protected int addNewProduct(Products newProduct) {
        int rowsAffected = 0;
        if (prodList.size() == 0 && !isMatchedWithProdCode(newProduct.getProdCode())) {
            prodList.add(newProduct);
            rowsAffected++;
        }
        return rowsAffected;
    }

    private boolean isMatchedWithProdCode(String newProdCode) {
        for (Products p : prodList) {
            if (p.getProdCode().equalsIgnoreCase(newProdCode)) {
                return true;
            }
        }
        return false;
    }

    protected void clearList() {
        prodList.removeAll(this.prodList);
        setProdList(prodList);
    }

    protected void removeProdItem(int i) {
        prodList.remove(i);
        setProdList(prodList);
    }

    protected int getNumberOfProducts() {
        return prodList.size();
    }

    protected int pullProduct(String prodCode) {
        int flag = -1;
        prodCode.trim();
        for (Products p : prodList) {
            if (prodCode.equals(p.getProdCode())) {
                if (p.getstockAvailable() <= 0) {
                    System.out.println("No Stock Available! ");
                    return flag;
                } // enade IF
                else {
                    flag = prodList.indexOf(p);
                    return flag;
                } // endae ELSE
            } // endae IF
        } // end FOR
        return flag;
    } // endae pull Product method

    protected void printList() {
        int i = 0;
        for (Products p : prodList) {
            System.out.printf("%d || %s: %s | %.2f | %d \n", i, p.getProdCode(), p.getprodName(), p.getUnitPrice(), p.getstockAvailable());
            i++;
        }
    }


}
