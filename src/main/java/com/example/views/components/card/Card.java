package com.example.views.components.card;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Card extends VerticalLayout {

    public Card() {
        // Main Container Styling
        addClassName("product-card");
        setPadding(false);
        setSpacing(false);
        setWidth("350px");

        // Top Image Section
        Div imageContainer = new Div();
        imageContainer.addClassName("image-container");
        Image appleImage = new Image("images/apple.png", "Green Apple");
        imageContainer.add(appleImage);

        // Content Section
        VerticalLayout detailsContent = new VerticalLayout();
        detailsContent.addClassName("details-content");

        // Header: Name and Cart Icon
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        
        Span title = new Span("Apple");
        title.addClassName("product-title");
        
        Icon cartIcon = VaadinIcon.CART_O.create();
        cartIcon.addClassName("cart-icon");
        
        header.add(title, cartIcon);

        // Stock Info
        Span stock = new Span("In Stock: 25");
        stock.addClassName("stock-info");

        // Footer: Price and Buy Button
        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidthFull();
        footer.setJustifyContentMode(JustifyContentMode.BETWEEN);
        footer.setAlignItems(Alignment.CENTER);

        Span price = new Span("$5.00");
        price.addClassName("price-tag");

        Button buyButton = new Button("BUY");
        buyButton.addClassName("buy-button");

        footer.add(price, buyButton);

        detailsContent.add(header, stock, footer);
        add(imageContainer, detailsContent);
    }
}