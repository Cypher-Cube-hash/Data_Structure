package com.example.views.components.profile;

import com.example.datastructures.order.OrderList;
import com.example.datastructures.order.OrderNode;
import com.example.datastructures.user.UserLinkedList;
import com.example.datastructures.user.UserNode;
import com.example.enums.TypeGender;
import com.example.models.Customer;
import com.example.models.Order;
import com.example.models.User;
import com.example.services.CustomerServices;
import com.example.services.OrderServices;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.example.datastructures.user.UserLinkedList;
import com.example.datastructures.user.UserNode;

import java.time.format.DateTimeFormatter;

public class CustomerProfileDrawerView extends Div {

    private final Customer customer;
    private final CustomerServices customerServices;
    public final OrderServices orderServices;
    
    private Tab profileTab;
    private Tab orderHistoryTab;
    private Tab manageOrdersTab;
    private Tabs tabContainer;

    private VerticalLayout profileContent;
    private VerticalLayout orderHistoryContent;
    private VerticalLayout manageOrdersContent;
    private Div tabContentDiv;

    public CustomerProfileDrawerView(Customer customer, CustomerServices customerServices, OrderServices orderServices) {
        this.customer = customer;
        this.customerServices = customerServices;
        this.orderServices = orderServices;

        getStyle()
            .set("position", "fixed")
            .set("top", "0")
            .set("right", "360px")
            .set("width", "360px")
            .set("height", "100svh")
            .set("background", "#0d1117")
            .set("border-left", "1px solid rgba(46,204,113,0.2)")
            .set("border-right", "1px solid rgba(46,204,113,0.2)")
            .set("z-index", "9998") // Below cart drawer
            .set("display", "flex")
            .set("flex-direction", "column")
            .set("box-shadow", "-8px 0 32px rgba(0,0,0,0.6)")
            .set("overflow", "hidden");

        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setAlignItems(HorizontalLayout.Alignment.CENTER);
        header.setJustifyContentMode(HorizontalLayout.JustifyContentMode.BETWEEN);
        header.getStyle()
            .set("padding", "1.2rem 1.5rem")
            .set("border-bottom", "1px solid rgba(255,255,255,0.08)")
            .set("flex-shrink", "0");

        Span title = new Span("My Profile");
        title.getStyle()
            .set("font-size", "16px")
            .set("font-weight", "600")
            .set("color", "#ffffff");

        Button closeBtn = new Button("✕");
        closeBtn.getStyle()
            .set("background", "transparent")
            .set("color", "rgba(255,255,255,0.5)")
            .set("border", "none")
            .set("font-size", "16px")
            .set("cursor", "pointer")
            .set("padding", "4px 8px");
        closeBtn.addClickListener(e -> setVisible(false));

        header.add(title, closeBtn);

        profileTab = new Tab("Profile");
        orderHistoryTab = new Tab("Order History");
        manageOrdersTab = new Tab("Manage Orders");

        tabContainer = new Tabs(profileTab, orderHistoryTab, manageOrdersTab);
        tabContainer.getStyle()
            .set("border-bottom", "1px solid rgba(255,255,255,0.08)")
            .set("flex-shrink", "0");

        tabContentDiv = new Div();
        tabContentDiv.getStyle()
            .set("flex", "1")
            .set("overflow-y", "auto")
            .set("padding", "1.5rem");

        profileContent = buildProfileTab();
        orderHistoryContent = buildOrderHistoryTab();
        manageOrdersContent = buildManageOrdersTab();

        tabContentDiv.removeAll();
        tabContentDiv.add(profileContent);

        tabContainer.addSelectedChangeListener(e -> {
            tabContentDiv.removeAll();
            if (e.getSelectedTab() == profileTab) {
                tabContentDiv.add(profileContent);
            } else if (e.getSelectedTab() == orderHistoryTab) {
                refreshOrderHistory();
                tabContentDiv.add(orderHistoryContent);
            } else if (e.getSelectedTab() == manageOrdersTab) {
                refreshManageOrders();
                tabContentDiv.add(manageOrdersContent);
            }
        });

        add(header, tabContainer, tabContentDiv);
        setVisible(false);
    }


    private VerticalLayout buildProfileTab() {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(true);

        Div infoSection = new Div();
        infoSection.getStyle()
            .set("background", "rgba(255,255,255,0.04)")
            .set("border", "1px solid rgba(255,255,255,0.07)")
            .set("border-radius", "10px")
            .set("padding", "1rem")
            .set("margin-bottom", "1rem");

        Span sectionTitle = new Span("Basic Information");
        sectionTitle.getStyle()
            .set("font-size", "13px")
            .set("font-weight", "600")
            .set("color", "#ffffff")
            .set("text-transform", "uppercase")
            .set("letter-spacing", "0.04em");

        Div infoRows = new Div();
        infoRows.getStyle().set("display", "flex").set("flex-direction", "column").set("gap", "0.75rem");

        infoRows.add(createInfoRow("Customer ID", customer.getCustomerId()));
        infoRows.add(createInfoRow("Name", customer.getUserFullName() != null ? customer.getUserFullName() : "N/A"));
        infoRows.add(createInfoRow("Email", customer.getUserEmail() != null ? customer.getUserEmail() : "N/A"));
        infoRows.add(createInfoRow("Gender", customer.getGender() != null ? customer.getGender().name() : "N/A"));
        infoRows.add(createInfoRow("Member Since", customer.getCreatedAt() != null 
            ? customer.getCreatedAt().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")) 
            : "N/A"));

        infoSection.add(sectionTitle, infoRows);

        Button editBtn = new Button("Edit Profile");
        editBtn.setWidthFull();
        editBtn.getStyle()
            .set("background", "#10b981")
            .set("color", "white")
            .set("border", "none")
            .set("border-radius", "8px")
            .set("padding", "0.75rem")
            .set("font-weight", "600")
            .set("cursor", "pointer");
        editBtn.addClickListener(e -> openEditDialog());

        layout.add(infoSection, editBtn);
        return layout;
    }

    private Div createInfoRow(String label, String value) {
        Div row = new Div();
        row.getStyle()
            .set("display", "flex")
            .set("justify-content", "space-between")
            .set("align-items", "center");

        Span labelSpan = new Span(label);
        labelSpan.getStyle()
            .set("color", "#ffffff")
            .set("font-size", "13px")
            .set("font-weight", "500");

        Span valueSpan = new Span(value);
        valueSpan.getStyle()
            .set("color", "#ffffff")
            .set("font-size", "14px")
            .set("font-weight", "500")
            .set("text-align", "right");

        row.add(labelSpan, valueSpan);
        return row;
    }

    private void openEditDialog() {
        Dialog dialog = new Dialog();
        dialog.addClassName("edit-dialog");
        dialog.setHeaderTitle("Edit Profile");
        dialog.setWidth("420px");

        TextField firstNameField = new TextField("First Name");
        firstNameField.setValue(customer.getUserFirstName() != null ? customer.getUserFirstName() : "");
        firstNameField.setWidthFull();

        TextField lastNameField = new TextField("Last Name");
        lastNameField.setValue(customer.getUserLastName() != null ? customer.getUserLastName() : "");
        lastNameField.setWidthFull();

        TextField emailField = new TextField("Email");
        emailField.setValue(customer.getUserEmail() != null ? customer.getUserEmail() : "");
        emailField.setReadOnly(true);
        emailField.setWidthFull();

        ComboBox<TypeGender> genderField = new ComboBox<>("Gender");
        genderField.setItems(TypeGender.values());
        genderField.setValue(customer.getGender());
        genderField.setWidthFull();

        VerticalLayout form = new VerticalLayout(firstNameField, lastNameField, emailField, genderField);
        form.setPadding(false);
        form.setSpacing(true);
        dialog.add(form);

        Button save = new Button("Save Changes", e -> {
            if (firstNameField.isEmpty() || lastNameField.isEmpty()) {
                showNotification("First name and last name are required.", true);
                return;
            }

            UserLinkedList userList = new UserLinkedList();
            userList.add(customer.getUser());

            UserNode current = userList.getHead();

            while (current != null) {
                User u = current.getUser();

                u.setFirstName(firstNameField.getValue().trim());
                u.setLastName(lastNameField.getValue().trim());

                current = current.getUserNext();
            }

            customer.setGender(genderField.getValue());
            customer.setUpdatedAt(java.time.LocalDate.now());

            try {
                customerServices.updateCustomer(customer);
                showNotification("Profile updated successfully.", false);
                dialog.close();
                
                profileContent.removeAll();
                profileContent.add(buildProfileTab());
                
            } catch (Exception ex) {
                showNotification("Failed to update profile: " + ex.getMessage(), true);
            }
        });
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getStyle().set("background", "#10b981");

        Button cancel = new Button("Cancel", e -> dialog.close());

        dialog.getFooter().add(cancel, save);
        dialog.open();
    }

    // private VerticalLayout buildOrderHistoryTab() {
    //     VerticalLayout layout = new VerticalLayout();
    //     layout.setPadding(false);
    //     layout.setSpacing(true);

    //     Span emptyMessage = new Span("No orders yet. Start shopping!");
    //     emptyMessage.getStyle()
    //         .set("color", "rgba(255,255,255,0.3)")
    //         .set("font-size", "13px")
    //         .set("text-align", "center")
    //         .set("padding", "2rem 0");

    //     layout.add(emptyMessage);

    //     return layout;
    // }

        private VerticalLayout buildOrderHistoryTab() {

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(true);

        String currentUserId = customer.getUserId(); // ✅ get logged-in user

        OrderList userOrders = orderServices.getProcessedOrdersByUser(currentUserId);

        // ✅ If no orders → show empty message
        if (userOrders.size() == 0) {
            Span emptyMessage = new Span("No orders yet. Start shopping!");
            emptyMessage.getStyle()
                .set("color", "rgb(255, 255, 255)")
                .set("font-size", "13px")
                .set("text-align", "center")
                .set("padding", "2rem 0");

            layout.add(emptyMessage);
            return layout;
        }

        // ✅ Otherwise display orders
        for (int i = 0; i < userOrders.size(); i++) {
            Order order = userOrders.getIndex(i);

            Div card = new Div();
            card.getStyle()
                .set("border", "1px solid #ccc")
                .set("color", "rgb(255, 255, 255)")
                .set("padding", "10px")
                .set("border-radius", "8px")
                .set("margin-bottom", "10px");

            // Order info
            Span id = new Span("Order ID: " + order.getOrderId());
            Span total = new Span("Total: $" + order.getTotal());
            Span date = new Span("Date: " + order.getOrderDate());

            VerticalLayout info = new VerticalLayout(id, total, date);
            info.setSpacing(false);
            info.setPadding(false);

            // Items
            VerticalLayout itemsLayout = new VerticalLayout();
            itemsLayout.setSpacing(false);
            itemsLayout.setPadding(false);

            if (order.getItems() != null) {
                OrderNode current = order.getItems().getHead();

                while (current != null) {
                    itemsLayout.add(new Span(
                        current.getCartItem().getProduct().getProductName()
                        + " x" + current.getCartItem().getQuantity()
                    ));
                    current = current.getNext();
                }
            }

            card.add(info, itemsLayout);
            layout.add(card);
        }

        return layout;
    }

    private void refreshOrderHistory() {
        orderHistoryContent.removeAll();
        orderHistoryContent.add(buildOrderHistoryTab());
    }

    private VerticalLayout buildManageOrdersTab() {

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(true);

        String currentUserId = customer.getUserId(); // ✅ get logged-in user

        OrderList userOrders = orderServices.getOrdersByUser(currentUserId);

        // ✅ If no orders → show empty message
        if (userOrders.size() == 0) {
            Span emptyMessage = new Span("No orders yet. Start shopping!");
            emptyMessage.getStyle()
                .set("color", "rgb(255, 255, 255)")
                .set("font-size", "13px")
                .set("text-align", "center")
                .set("padding", "2rem 0");

            layout.add(emptyMessage);
            return layout;
        }

        // ✅ Otherwise display orders
        for (int i = 0; i < userOrders.size(); i++) {
            Order order = userOrders.getIndex(i);

            Div card = new Div();
            card.getStyle()
                .set("border", "1px solid #ccc")
                .set("color", "rgb(255, 255, 255)")
                .set("padding", "10px")
                .set("border-radius", "8px")
                .set("margin-bottom", "10px");

            // Order info
            Span id = new Span("Order ID: " + order.getOrderId());
            Span total = new Span("Total: $" + order.getTotal());
            Span date = new Span("Date: " + order.getOrderDate());

            VerticalLayout info = new VerticalLayout(id, total, date);
            info.setSpacing(false);
            info.setPadding(false);

            // Items
            VerticalLayout itemsLayout = new VerticalLayout();
            itemsLayout.setSpacing(false);
            itemsLayout.setPadding(false);

            if (order.getItems() != null) {
                OrderNode current = order.getItems().getHead();

                while (current != null) {
                    itemsLayout.add(new Span(
                        current.getCartItem().getProduct().getProductName()
                        + " x" + current.getCartItem().getQuantity()
                    ));
                    current = current.getNext();
                }
            }

            card.add(info, itemsLayout);
            layout.add(card);
        }

        return layout;
    }

    // private VerticalLayout buildManageOrdersTab() {
    //     VerticalLayout layout = new VerticalLayout();
    //     layout.setPadding(false);
    //     layout.setSpacing(true);

    //     Span emptyMessage = new Span("No active orders to manage.");
    //     emptyMessage.getStyle()
    //         .set("color", "rgba(255,255,255,0.3)")
    //         .set("font-size", "13px")
    //         .set("text-align", "center")
    //         .set("padding", "2rem 0");

    //     layout.add(emptyMessage);

    //     return layout;
    // }

    private void refreshManageOrders() {
        manageOrdersContent.removeAll();
        manageOrdersContent.add(buildManageOrdersTab());
    }

    private void showNotification(String message, boolean isError) {
        Notification n = Notification.show(message, 3000, Notification.Position.BOTTOM_END);
        n.addThemeVariants(isError
            ? NotificationVariant.LUMO_ERROR
            : NotificationVariant.LUMO_SUCCESS);
    }
}