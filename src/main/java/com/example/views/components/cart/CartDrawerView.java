package com.example.views.components.cart;

import com.example.datastructures.cart.CartItem;
import com.example.datastructures.cart.CartNode;
import com.example.datastructures.cart.CartSession;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CartDrawerView extends Div {

    private final CartSession cartSession;
    private final Runnable    onCartChanged;

    private final Div  itemsContainer;
    private final Span totalLabel;
    private final Button undoBtn;
    private final Button redoBtn;

    public CartDrawerView(CartSession cartSession, Runnable onCartChanged) {
        this.cartSession   = cartSession;
        this.onCartChanged = onCartChanged;

        getStyle()
            .set("position", "fixed")
            .set("top", "0")
            .set("right", "0")
            .set("width", "360px")
            .set("height", "100svh")
            .set("background", "#0d1117")
            .set("border-left", "1px solid rgba(46,204,113,0.2)")
            .set("z-index", "9999")
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

        Span title = new Span("Shopping Cart");
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

        itemsContainer = new Div();
        itemsContainer.getStyle()
            .set("flex", "1")
            .set("overflow-y", "auto")
            .set("padding", "1rem 1.5rem")
            .set("display", "flex")
            .set("flex-direction", "column")
            .set("gap", "0.75rem");

        Div footer = new Div();
        footer.getStyle()
            .set("padding", "1rem 1.5rem")
            .set("border-top", "1px solid rgba(255,255,255,0.08)")
            .set("display", "flex")
            .set("flex-direction", "column")
            .set("gap", "0.75rem")
            .set("flex-shrink", "0");

        HorizontalLayout totalRow = new HorizontalLayout();
        totalRow.setWidthFull();
        totalRow.setJustifyContentMode(HorizontalLayout.JustifyContentMode.BETWEEN);
        totalRow.setAlignItems(HorizontalLayout.Alignment.CENTER);

        Span totalLabelStatic = new Span("Total");
        totalLabelStatic.getStyle().set("color", "rgba(255,255,255,0.55)").set("font-size", "13px");

        totalLabel = new Span("$0.00");
        totalLabel.getStyle()
            .set("color", "#10b981")
            .set("font-size", "18px")
            .set("font-weight", "600");

        totalRow.add(totalLabelStatic, totalLabel);

        HorizontalLayout undoRedoRow = new HorizontalLayout();
        undoRedoRow.setWidthFull();
        undoRedoRow.setSpacing(true);

        undoBtn = new Button("↩ Undo");
        undoBtn.getStyle()
            .set("flex", "1")
            .set("background", "rgba(255,255,255,0.06)")
            .set("color", "#ffffff")
            .set("border", "1px solid rgba(255,255,255,0.12)")
            .set("border-radius", "8px")
            .set("cursor", "pointer");
        undoBtn.addClickListener(e -> {
            String msg = cartSession.undo();
            if (msg != null) {
                refresh();
                showToast(msg, false);
            }
        });

        redoBtn = new Button("↪ Redo");
        redoBtn.getStyle()
            .set("flex", "1")
            .set("background", "rgba(255,255,255,0.06)")
            .set("color", "#ffffff")
            .set("border", "1px solid rgba(255,255,255,0.12)")
            .set("border-radius", "8px")
            .set("cursor", "pointer");
        redoBtn.addClickListener(e -> {
            String msg = cartSession.redo();
            if (msg != null) {
                refresh();
                showToast(msg, false);
            }
        });

        undoRedoRow.add(undoBtn, redoBtn);

        // Checkout button
        Button checkoutBtn = new Button("Proceed to Checkout");
        checkoutBtn.setWidthFull();
        checkoutBtn.getStyle()
            .set("background", "#10b981")
            .set("color", "white")
            .set("border", "none")
            .set("border-radius", "10px")
            .set("padding", "0.9rem")
            .set("font-size", "14px")
            .set("font-weight", "600")
            .set("cursor", "pointer")
            .set("letter-spacing", "0.04em")
            .set("text-transform", "uppercase");

        // Checkout action: creates order and adds to queue
        checkoutBtn.addClickListener(e -> {
            if (cartSession.cartSize() == 0) {
                showToast("Your cart is empty.", true);
                return;
            }
            handleCheckout();
        });

        footer.add(totalRow, undoRedoRow, checkoutBtn);

        add(header, itemsContainer, footer);

        setVisible(false); 
        refresh();
    }
    private void handleCheckout() {
        double total = calculateCartTotal();

        showToast("Order placed! Total: $" + String.format("%.2f", total), false);
        
        cartSession.getCartList().clear();
        refresh();
        setVisible(false);
    }
    public void refresh() {
        itemsContainer.removeAll();

        if (cartSession.getCartList().isEmpty()) {
            Span empty = new Span("Your cart is empty.");
            empty.getStyle()
                .set("color", "rgba(255,255,255,0.3)")
                .set("font-size", "13px")
                .set("text-align", "center")
                .set("padding", "2rem 0");
            itemsContainer.add(empty);
            totalLabel.setText("$0.00");
            undoBtn.setEnabled(cartSession.canUndo());
            redoBtn.setEnabled(cartSession.canRedo());
            onCartChanged.run();
            return;
        }

        CartNode current = cartSession.getCartList().getHead();
        while (current != null) {
            CartItem item = current.getCartItem();
            itemsContainer.add(buildCartRow(item));
            current = current.getNext();
        }

        double runningTotal = calculateCartTotal();
        totalLabel.setText("$" + String.format("%.2f", runningTotal));

        undoBtn.setEnabled(cartSession.canUndo());
        redoBtn.setEnabled(cartSession.canRedo());
        onCartChanged.run();
    }

    private double calculateCartTotal() {
        double total = 0.0;
        CartNode current = cartSession.getCartList().getHead();

        while (current != null) {
            CartItem item = current.getCartItem();
            double itemTotal = item.getProduct().getProductPrice() * item.getQuantity();
            total += itemTotal;
            current = current.getNext();
        }

        return total;
    }

    private Div buildCartRow(CartItem item) {
        Div row = new Div();
        row.getStyle()
            .set("background", "rgba(255,255,255,0.04)")
            .set("border", "1px solid rgba(255,255,255,0.07)")
            .set("border-radius", "10px")
            .set("padding", "0.75rem 1rem")
            .set("display", "flex")
            .set("flex-direction", "column")
            .set("gap", "0.5rem");

        // Product name + remove button
        HorizontalLayout nameRow = new HorizontalLayout();
        nameRow.setWidthFull();
        nameRow.setAlignItems(HorizontalLayout.Alignment.CENTER);
        nameRow.setJustifyContentMode(HorizontalLayout.JustifyContentMode.BETWEEN);

        Span name = new Span(item.getProduct().getProductName());
        name.getStyle()
            .set("color", "#ffffff")
            .set("font-size", "14px")
            .set("font-weight", "500");

        Button removeBtn = new Button("×");
        removeBtn.getStyle()
            .set("background", "rgba(239,68,68,0.1)")
            .set("color", "#ef4444")
            .set("border", "1px solid rgba(239,68,68,0.25)")
            .set("border-radius", "6px")
            .set("width", "28px")
            .set("height", "28px")
            .set("cursor", "pointer")
            .set("font-size", "16px")
            .set("padding", "0")
            .set("min-width", "0");
        removeBtn.addClickListener(e -> {
            cartSession.removeFromCart(item.getProduct().getProductId());
            refresh();
            showToast("\"" + item.getProduct().getProductName() + "\" removed. Undo to restore.", false);
        });

        nameRow.add(name, removeBtn);

        Span type = new Span(item.getProduct().getProductType().name());
        type.getStyle()
            .set("font-size", "10px")
            .set("background", "rgba(16,185,129,0.1)")
            .set("color", "#10b981")
            .set("border-radius", "4px")
            .set("padding", "2px 7px")
            .set("font-weight", "600")
            .set("text-transform", "uppercase");

        HorizontalLayout priceRow = new HorizontalLayout();
        priceRow.setAlignItems(HorizontalLayout.Alignment.CENTER);
        priceRow.setSpacing(true);
        priceRow.getStyle().set("font-size", "12px");

        Span unitPrice = new Span(String.format("$%.2f", item.getProduct().getProductPrice()));
        unitPrice.getStyle().set("color", "#10b981");

        Span subtotal = new Span(
            String.format("= $%.2f", item.getProduct().getProductPrice() * item.getQuantity())
        );
        subtotal.getStyle()
            .set("color", "#94a3b8")
            .set("flex", "1")
            .set("text-align", "right");

        priceRow.add(unitPrice, subtotal);

        // Quantity controls row
        HorizontalLayout qtyRow = new HorizontalLayout();
        qtyRow.setAlignItems(HorizontalLayout.Alignment.CENTER);
        qtyRow.setSpacing(false);
        qtyRow.getStyle().set("gap", "0.5rem");

        Button minus = buildQtyBtn("−");
        Span   qtyDisplay = new Span(String.valueOf(item.getQuantity()));
        qtyDisplay.getStyle()
            .set("color", "#ffffff")
            .set("font-size", "14px")
            .set("min-width", "24px")
            .set("text-align", "center");
        Button plus = buildQtyBtn("+");

        minus.addClickListener(e -> {
            int newQty = item.getQuantity() - 1;
            cartSession.updateQuantity(item.getProduct().getProductId(), newQty);
            refresh();
        });

        plus.addClickListener(e -> {
            int newQty = item.getQuantity() + 1;
            if (newQty > item.getProduct().getProductQuantity()) {
                showToast("Not enough stock available.", true);
                return;
            }
            cartSession.updateQuantity(item.getProduct().getProductId(), newQty);
            refresh();
        });

        qtyRow.add(minus, qtyDisplay, plus);

        row.add(nameRow, type, priceRow, qtyRow);
        return row;
    }

    private Button buildQtyBtn(String label) {
        Button btn = new Button(label);
        btn.getStyle()
            .set("background", "rgba(255,255,255,0.06)")
            .set("color", "#ffffff")
            .set("border", "1px solid rgba(255,255,255,0.1)")
            .set("border-radius", "6px")
            .set("width", "28px")
            .set("height", "28px")
            .set("cursor", "pointer")
            .set("font-size", "16px")
            .set("padding", "0")
            .set("min-width", "0");
        return btn;
    }

    private void showToast(String message, boolean isError) {
        Notification n = Notification.show(message, 3000, Notification.Position.BOTTOM_END);
        n.addThemeVariants(isError
            ? NotificationVariant.LUMO_ERROR
            : NotificationVariant.LUMO_SUCCESS);
    }
}