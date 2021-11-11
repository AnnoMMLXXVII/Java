package tester2;

import java.util.ArrayList;

public class OrderController {
    private static OrderController o = null;
    protected ArrayList<Order> orderList = new ArrayList<Order>();


    public static OrderController getInstance() {
        return (o == null) ? new OrderController() : o;
    }

    protected ArrayList<Order> getOrderList() {
        return (orderList == null) ? new ArrayList<Order>() : orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    protected void addOrder(Order o) {
        orderList.add(o);
        for (Order oc : orderList)
            System.out.println(oc.getProdCode());
//		setOrderList(orderList);
    }

    protected void removeOrderItem(int i) {
        orderList.remove(i);
        setOrderList(orderList);
    }

    protected void clearList() {
        orderList.removeAll(this.orderList);
        setOrderList(orderList);
    }

    protected int getNumberOfProducts() {
        return orderList.size();
    }

    protected void printList() {
        int i = 0;
        for (Order o : orderList) {
            System.out.printf("%d || %s: %s | %.2f | %d \n", i, o.getProdCode(), o.getQuantity(), o.getUnitPrice(), o.getTotal());
            i++;
        }
    }

}
