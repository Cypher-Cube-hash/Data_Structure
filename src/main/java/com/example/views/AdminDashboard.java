package com.example.views;

import com.example.services.ProductServices;
import com.example.views.components.admin.AdminOrderView;
import com.example.views.components.admin.AdminProductView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("admin")
@PageTitle("Admin Dashboard – BrandEx")
@CssImport("./styles/styles.css")
@AnonymousAllowed
public class AdminDashboard extends HorizontalLayout {

    private Div navProducts;
    private Div navOrders;
    private Div contentArea;

    private final ProductServices productServices;

    public AdminDashboard(ProductServices productServices) {
        this.productServices = productServices;

        setSizeFull();
        setSpacing(false);
        setPadding(false);
        addClassName("admin-dashboard");

        Div sidebar = buildSidebar();

        contentArea = new Div();
        contentArea.addClassName("admin-content");
        contentArea.setSizeFull();

        add(sidebar, contentArea);
        showProducts(); // default view
    }

    private Div buildSidebar() {
        Div sidebar = new Div();
        sidebar.addClassName("admin-sidebar");

        Div title = new Div();
        title.addClassName("admin-sidebar-title");
        title.setText("BrandEx Admin");

        navProducts = buildNavItem("Products");
        navProducts.addClickListener(e -> showProducts());

        navOrders = buildNavItem("Orders");
        navOrders.addClickListener(e -> showOrders());

        Div logoutBtn = buildNavItem("Logout");
        logoutBtn.addClassName("admin-nav-logout");
        logoutBtn.addClickListener(e ->
            getUI().ifPresent(ui -> ui.navigate(""))
        );

        sidebar.add(title, navProducts, navOrders, logoutBtn);
        return sidebar;
    }

    private Div buildNavItem(String label) {
        Div item = new Div();
        item.addClassName("admin-nav-item");
        item.setText(label);
        return item;
    }

    private void showProducts() {
        setActive(navProducts);
        contentArea.removeAll();
        // Pass ProductServices — AdminProductView uses the LinkedList + BST
        contentArea.add(new AdminProductView(productServices));
    }

    private void showOrders() {
        setActive(navOrders);
        contentArea.removeAll();
        contentArea.add(new AdminOrderView());
    }

    private void setActive(Div selected) {
        navProducts.removeClassName("active");
        navOrders.removeClassName("active");
        selected.addClassName("active");
    }
}