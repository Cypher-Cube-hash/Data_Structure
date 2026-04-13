package com.example.views.components.card;

import com.example.datastructures.cart.CartSession;
import com.example.models.Product;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;

public class Card extends VerticalLayout {

    public Card(Product product, CartSession cartSession, Runnable onAddToCart) {
        addClassName("product-card");
        setPadding(false);
        setSpacing(false);
        setWidth("220px");

        Div imageContainer = new Div();
        imageContainer.addClassName("image-container");

        if (product.getProductImage() != null && product.getProductImage().length > 0) {
            StreamResource resource = new StreamResource(
                product.getSkuNumber() + ".jpg",
                () -> new ByteArrayInputStream(product.getProductImage())
            );
            Image img = new Image(resource, product.getProductName());
            img.setWidth("100%");
            img.getStyle().set("object-fit", "cover").set("height", "140px");
            imageContainer.add(img);
        } else {
            Div placeholder = new Div();
            placeholder.getStyle()
                .set("width", "100%")
                .set("height", "140px")
                .set("background", "linear-gradient(135deg,#1a2e1f,#0f1f14)")
                .set("display", "flex")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("color", "rgba(255,255,255,0.2)")
                .set("font-size", "12px");
            placeholder.setText("No image");
            imageContainer.add(placeholder);
        }

        VerticalLayout details = new VerticalLayout();
        details.addClassName("details-content");
        details.setPadding(false);
        details.setSpacing(false);
        details.getStyle().set("gap", "6px");

        HorizontalLayout nameRow = new HorizontalLayout();
        nameRow.setWidthFull();
        nameRow.setJustifyContentMode(JustifyContentMode.BETWEEN);
        nameRow.setAlignItems(Alignment.CENTER);

        Span title = new Span(product.getProductName());
        title.addClassName("product-title");
        title.getStyle().set("font-size", "0.95rem");

        Icon cartIcon = VaadinIcon.CART_O.create();
        cartIcon.addClassName("cart-icon");
        cartIcon.getStyle().set("cursor", "pointer");
        cartIcon.addClickListener(e -> {
            cartSession.addToCart(product);
            onAddToCart.run();     
            showToast("\"" + product.getProductName() + "\" added. Undo available.", false);
        });

        nameRow.add(title, cartIcon);

        Span typeBadge = new Span(product.getProductType().name());
        typeBadge.getStyle()
            .set("font-size", "10px")
            .set("background", "rgba(16,185,129,0.12)")
            .set("color", "#10b981")
            .set("border-radius", "4px")
            .set("padding", "2px 7px")
            .set("font-weight", "600")
            .set("text-transform", "uppercase")
            .set("letter-spacing", "0.04em");

        boolean inStock = product.getProductQuantity() > 0;
        Span stock = new Span(inStock
            ? "In Stock: " + product.getProductQuantity()
            : "Out of Stock");
        stock.addClassName("stock-info");
        stock.getStyle()
            .set("font-size", "0.82rem")
            .set("color", inStock ? "#10b981" : "#ef4444");

        Span priceDisplay = new Span(String.format("$%.2f", product.getProductPrice()));
        priceDisplay.getStyle()
            .set("font-size", "1.1rem")
            .set("font-weight", "600")
            .set("color", "#10b981")
            .set("margin-top", "4px");

        details.add(nameRow, typeBadge, stock, priceDisplay);

        if (product.getProductDesc() != null && !product.getProductDesc().isBlank()) {
            Span desc = new Span(product.getProductDesc());
            desc.getStyle()
                .set("font-size", "11px")
                .set("color", "#94a3b8")
                .set("white-space", "nowrap")
                .set("overflow", "hidden")
                .set("text-overflow", "ellipsis")
                .set("display", "block");
            details.add(desc);
        }

        Button buyButton = new Button("ADD TO CART");
        buyButton.addClassName("buy-button");
        buyButton.setWidthFull();
        buyButton.setEnabled(inStock);
        buyButton.addClickListener(e -> {
            cartSession.addToCart(product);
            onAddToCart.run();
            showToast("\"" + product.getProductName() + "\" added to cart.", false);
        });

        details.add(buyButton);
        add(imageContainer, details);
    }

    private void showToast(String message, boolean isError) {
        Notification n = Notification.show(message, 2500, Notification.Position.BOTTOM_CENTER);
        n.addThemeVariants(isError
            ? NotificationVariant.LUMO_ERROR
            : NotificationVariant.LUMO_SUCCESS);
    }
}