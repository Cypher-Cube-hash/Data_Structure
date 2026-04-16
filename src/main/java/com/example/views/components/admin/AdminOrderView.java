package com.example.views.components.admin;

import com.example.services.OrderServices;

import java.util.ArrayList;
import java.util.List;

import com.example.datastructures.order.OrderList;
import com.example.datastructures.product.ProductList;
import com.example.models.Order;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;

/*
AI was used to help with the layout and structure of this page.
*/

public class AdminOrderView extends VerticalLayout {
    public final OrderServices orderServices;
    public Grid<Order> orderGrid;

    public AdminOrderView(OrderServices orderServices) {
        this.orderServices = orderServices;
        addClassName("admin-section");
        setPadding(false);
        setSpacing(false);

        add(buildHeader());
        add(buildMetrics());
        add(buildGrid());
        add(buildQueueNote());
    }

    private HorizontalLayout buildHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.setAlignItems(Alignment.CENTER);
        header.addClassName("admin-section-header");

        Div titles = new Div();
        Div title = new Div();
        title.addClassName("admin-section-title");
        title.setText("Order queue");
        Div subtitle = new Div();
        subtitle.addClassName("admin-section-subtitle");
        subtitle.setText("Process orders in the order received — FIFO");
        titles.add(title, subtitle);

        Button processBtn = new Button("Process next order");
        processBtn.addClassName("admin-add-btn");
        processBtn.addClickListener(e -> processNextOrder());

        header.add(titles, processBtn);
        return header;
    }

    private HorizontalLayout buildMetrics() {
        HorizontalLayout row = new HorizontalLayout();
        row.addClassName("admin-metrics-row");
        row.setWidthFull();

        OrderList allOrders = orderServices.getAllOrders();
        int total = allOrders.size();

        row.add(
            metricCard("Pending orders", String.valueOf(total)),
            metricCard("Processed today", "0"),
            metricCard("Next in queue", "—")
        );
        return row;
    }

    private Div metricCard(String label, String value) {
        Div card = new Div();
        card.addClassName("admin-metric-card");
        Div l = new Div(); l.addClassName("metric-label"); l.setText(label);
        Div v = new Div(); v.addClassName("metric-value"); v.setText(value);
        card.add(l, v);
        return card;
    }

    private Grid<Order> buildGrid() {
        orderGrid = new Grid<>(Order.class, false);
        orderGrid.addClassName("admin-grid");
        orderGrid.setWidthFull();

        orderGrid.setItems(convertToList(orderServices.getAllOrders()));

        // OrderList orderList = orderServices.getAllOrders();
        // List<Order> items = new ArrayList<>();

        // for (int i = 0; i < orderList.size(); i++) {
        //     items.add(orderList.getIndex(i));
        // }
        // orderGrid.setItems(items);

        orderGrid.addColumn(Order::getOrderId)
            .setHeader("Order ID").setFlexGrow(2);

        orderGrid.addColumn(Order::getUserId)
            .setHeader("User ID").setFlexGrow(2);

        orderGrid.addColumn(Order::getTotal)
            .setHeader("Total").setFlexGrow(2);

        orderGrid.addColumn(Order::getOrderDate)
            .setHeader("Order Date").setFlexGrow(1);

        orderGrid.addColumn(order -> {
            System.out.println("Items: " + order.getItems());
            if (order.getItems() == null) {
                return "No Orders";
            }
            return order.getItems().size();
        }).setHeader("Items").setFlexGrow(1);

        // orderGrid.addComponentColumn(o -> {
        //     HorizontalLayout actions = new HorizontalLayout();

        //     Button edit = new Button("Edit");
        //     edit.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
        //     // edit.addClickListener(e -> openProductDialog(o));

        //     Button delete = new Button("Delete");
        //     delete.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ERROR,
        //                             ButtonVariant.LUMO_TERTIARY);
        //     delete.addClickListener(e -> {
        //         // orderServices.deleteOrder(o.getOrderId());
        //         refreshGrid();
        //         // showNotification("Order \"" + o.getOrderId() + "\" deleted.", false);
        //     });

        //     actions.add(edit, delete);
        //     return actions;
        // }).setHeader("Actions").setFlexGrow(1);

        return orderGrid;
    }

    private Div buildQueueNote() {
        Div note = new Div();
        note.addClassName("admin-queue-note");
        note.setText(
            "Order queue is ready to connect. " +
            "Once your OrderQueue data structure is built, " +
            "inject it here and call queue.enqueue() at checkout " +
            "and queue.dequeue() on 'Process next order'."
        );
        return note;
    }

    private void processNextOrder() {
        Order processed = orderServices.processNextOrder();

        if (processed == null) {
            Notification.show("No orders in queue");
            return;
        }

        Notification.show("Processed order: " + processed.getOrderId());

        refreshGrid();
    }

    // private void refreshGrid() {
    //     OrderList orderList = orderServices.getAllOrders();
    //     List<Order> items = new ArrayList<>();
    //     for (int i = 0; i < orderList.size(); i++) {
    //         items.add(orderList.getIndex(i));
    //     }
    //     orderGrid.setItems(items);
    // }

    /*
    AI was used to improve the function above
    */

        // ================= REFRESH =================
    private void refreshGrid() {
        orderGrid.setItems(convertToList(orderServices.getAllOrders()));
    }

    // ================= HELPER =================
    private List<Order> convertToList(OrderList orderList) {
        List<Order> list = new ArrayList<>();

        for (int i = 0; i < orderList.size(); i++) {
            list.add(orderList.getIndex(i));
        }

        return list;
    }
}