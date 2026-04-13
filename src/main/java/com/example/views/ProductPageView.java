package com.example.views;

import com.example.datastructures.cart.CartSession;
import com.example.datastructures.product.ProductList;
import com.example.models.Customer;
import com.example.models.Product;
import com.example.repositories.CustomerRepository;
import com.example.services.CustomerServices;
import com.example.services.ProductServices;
import com.example.views.components.card.Card;
import com.example.views.components.cart.CartDrawerView;
import com.example.views.components.layouts.HeaderView;
import com.example.views.components.profile.CustomerProfileDrawerView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("product-page/:userId")
@PageTitle("Products – BrandEx")
@CssImport("./styles/styles.css")
@AnonymousAllowed
public class ProductPageView extends Section implements BeforeEnterObserver {

    private final ProductServices productServices;
    private final CustomerServices customerServices;
    private final CustomerRepository customerRepository;

    private CartSession cartSession;
    private HeaderView productHeader;
    private Div productDisplay;
    private CartDrawerView cartDrawer;
    private CustomerProfileDrawerView profileDrawer;
    private Span cartBadge;

    public ProductPageView(ProductServices productServices,
                           CustomerServices customerServices,
                           CustomerRepository customerRepository) {
        this.productServices = productServices;
        this.customerServices = customerServices;
        this.customerRepository = customerRepository;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // Get userId from query param
        String userId = event.getRouteParameters().get("userId").orElse(null);

        Customer customer = null;
        if (userId != null) {
            customer = customerRepository.findByUser_UserId(userId).orElse(null);
        }

        buildUI(customer);
    }

    private void buildUI(Customer customer) {
        removeAll();

        this.cartSession = new CartSession();

        addClassName("productPage");
        getStyle().set("position", "relative");

        productHeader = new HeaderView();
        add(productHeader);

        cartBadge = new Span("0");
        cartBadge.getStyle()
            .set("position", "absolute").set("top", "-6px").set("right", "-6px")
            .set("background", "#10b981").set("color", "white")
            .set("border-radius", "50%").set("width", "18px").set("height", "18px")
            .set("font-size", "10px").set("font-weight", "700")
            .set("display", "flex").set("align-items", "center").set("justify-content", "center");

        Div cartIconWrapper = productHeader.getOptionsView().getCartButton();
        cartIconWrapper.getStyle().set("position", "relative");
        cartIconWrapper.add(cartBadge);

        cartDrawer = new CartDrawerView(cartSession, this::refreshCartBadge);
        add(cartDrawer);

        profileDrawer = new CustomerProfileDrawerView(customer, customerServices);
        add(profileDrawer);

        cartIconWrapper.addClickListener(e -> {
            profileDrawer.setVisible(false);
            cartDrawer.setVisible(!cartDrawer.isVisible());
            if (cartDrawer.isVisible()) cartDrawer.refresh();
        });

        productHeader.getOptionsView().getUserButton().addClickListener(e -> {
            cartDrawer.setVisible(false);
            profileDrawer.setVisible(!profileDrawer.isVisible());
        });

        productHeader.getSearchBar().getSearchButton().addClickListener(e -> {
            String query = productHeader.getSearchBar().getSearchQuery().trim();
            renderProducts(query.isEmpty() ? productServices.getAllProducts() : productServices.searchByName(query));
        });

        productDisplay = new Div();
        productDisplay.addClassName("productDisplay");
        productDisplay.getStyle()
            .set("display", "flex").set("flex-wrap", "wrap").set("gap", "1rem")
            .set("overflow-y", "auto").set("align-content", "flex-start").set("padding", "0.5rem");

        add(productDisplay);
        renderProducts(productServices.getAllProducts());
    }

    private void renderProducts(ProductList products) {
        productDisplay.removeAll();
        if (products.size() == 0) {
            Span empty = new Span("No products found.");
            empty.getStyle().set("color", "rgba(255,255,255,0.4)").set("font-size", "14px").set("padding", "2rem");
            productDisplay.add(empty);
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            productDisplay.add(new Card(products.getIndex(i), cartSession, this::refreshCartBadge));
        }
    }

    private void refreshCartBadge() {
        int count = cartSession.cartSize();
        cartBadge.setText(count > 0 ? String.valueOf(count) : "0");
        cartBadge.setVisible(count > 0);
    }
}