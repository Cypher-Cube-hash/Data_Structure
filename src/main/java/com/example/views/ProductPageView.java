package com.example.views;

import com.example.datastructures.cart.CartSession;
import com.example.datastructures.product.ProductList;
import com.example.models.Product;
import com.example.services.ProductServices;
import com.example.views.components.card.Card;
import com.example.views.components.cart.CartDrawerView;
import com.example.views.components.layouts.HeaderView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.server.VaadinSession;

@Route("product-page")
@PageTitle("Products – BrandEx")
@CssImport("./styles/styles.css")
@AnonymousAllowed
public class ProductPageView extends Section {

    private final ProductServices productServices;
    private final CartSession cartSession;
    private final HeaderView productHeader;
    private final Div productDisplay;
    private final CartDrawerView cartDrawer;

    private final Span cartBadge;

    public ProductPageView(ProductServices productServices) {
    this.productServices = productServices;
    this.cartSession = getOrCreateCartSession();

        addClassName("productPage");
        getStyle().set("position", "relative");

        productHeader = new HeaderView();
        add(productHeader);

        cartBadge = new Span("0");
        cartBadge.getStyle()
            .set("position", "absolute")
            .set("top", "-6px")
            .set("right", "-6px")
            .set("background", "#10b981")
            .set("color", "white")
            .set("border-radius", "50%")
            .set("width", "18px")
            .set("height", "18px")
            .set("font-size", "10px")
            .set("font-weight", "700")
            .set("display", "flex")
            .set("align-items", "center")
            .set("justify-content", "center");

        Div cartIconWrapper = productHeader.getOptionsView().getCartButton();
        cartIconWrapper.getStyle().set("position", "relative");
        cartIconWrapper.add(cartBadge);

        cartDrawer = new CartDrawerView(cartSession, this::refreshCartBadge);
        add(cartDrawer);

        cartIconWrapper.addClickListener(e -> {
            cartDrawer.setVisible(!cartDrawer.isVisible());
            if (cartDrawer.isVisible()) cartDrawer.refresh();
        });

        productHeader.getSearchBar().getSearchButton().addClickListener(e -> {
            String query = productHeader.getSearchBar().getSearchQuery().trim();
            if (query.isEmpty()) {
                renderProducts(productServices.getAllProducts());
            } else {
                renderProducts(productServices.searchByName(query));
            }
        });

        productDisplay = new Div();
        productDisplay.addClassName("productDisplay");
        productDisplay.getStyle()
            .set("display", "flex")
            .set("flex-wrap", "wrap")
            .set("gap", "1rem")
            .set("overflow-y", "auto")
            .set("align-content", "flex-start")
            .set("padding", "0.5rem");

        add(productDisplay);

        renderProducts(productServices.getAllProducts());
    }
    private void renderProducts(ProductList products) {
        productDisplay.removeAll();

        if (products.size() == 0) {
            Span empty = new Span("No products found.");
            empty.getStyle()
                .set("color", "rgba(255,255,255,0.4)")
                .set("font-size", "14px")
                .set("padding", "2rem");
            productDisplay.add(empty);
            return;
        }

        for (int i = 0; i < products.size(); i++) {
            Product p = products.getIndex(i);
            productDisplay.add(new Card(p, cartSession, this::refreshCartBadge));
        }
    }

    private void refreshCartBadge() {
        int count = cartSession.cartSize();
        cartBadge.setText(count > 0 ? String.valueOf(count) : "0");
        cartBadge.setVisible(count > 0);
    }

    private CartSession getOrCreateCartSession() {
    CartSession session = VaadinSession.getCurrent().getAttribute(CartSession.class);
    if (session == null) {
        session = new CartSession();
        VaadinSession.getCurrent().setAttribute(CartSession.class, session);
    }
    return session;
}
}