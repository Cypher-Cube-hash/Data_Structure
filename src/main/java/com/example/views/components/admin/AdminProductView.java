package com.example.views.components.admin;

import com.example.datastructures.product.ProductList;
import com.example.enums.TypeProduct;
import com.example.models.Product;
import com.example.services.ProductServices;
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
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AdminProductView extends VerticalLayout {

    private final ProductServices productServices;
    private Grid<Product> productGrid;

    public AdminProductView(ProductServices productServices) {
        this.productServices = productServices;

        addClassName("admin-section");
        setPadding(false);
        setSpacing(false);

        add(buildHeader());
        add(buildMetrics());
        add(buildGrid());

        refreshGrid();
    }

    // ── Section header + Add button ────────────────────────────────────────
    private HorizontalLayout buildHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.setAlignItems(Alignment.CENTER);
        header.addClassName("admin-section-header");

        Div titles = new Div();
        Div title = new Div();
        title.addClassName("admin-section-title");
        title.setText("Product Management");

        Div subtitle = new Div();
        subtitle.addClassName("admin-section-subtitle");
        subtitle.setText("Add, edit, or remove products from the catalog");

        titles.add(title, subtitle);

        Button addBtn = new Button("+ Add Product");
        addBtn.addClassName("admin-add-btn");
        addBtn.getStyle().set("background", "#10b981").set("color", "white");
        addBtn.addClickListener(e -> openProductDialog(null));

        header.add(titles, addBtn);
        return header;
    }

    // ── Metric cards row ───────────────────────────────────────────────────
    private HorizontalLayout buildMetrics() {
        HorizontalLayout row = new HorizontalLayout();
        row.addClassName("admin-metrics-row");
        row.setWidthFull();

        // Pull counts directly from the in-memory linked list via the service
        ProductList all = productServices.getAllProducts();
        int total = all.size();
        int inStock = 0;
        for (int i = 0; i < total; i++) {
            if (all.getIndex(i).getProductQuantity() > 0) inStock++;
        }
        int outOfStock = total - inStock;

        row.add(
            metricCard("Total Products",  String.valueOf(total)),
            metricCard("In Stock",        String.valueOf(inStock)),
            metricCard("Out of Stock",    String.valueOf(outOfStock))
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

    // ── Data grid ──────────────────────────────────────────────────────────
    private Grid<Product> buildGrid() {
        productGrid = new Grid<>(Product.class, false);
        productGrid.addClassName("admin-grid");
        productGrid.setWidthFull();

        productGrid.addColumn(Product::getProductName)
            .setHeader("Name").setFlexGrow(2);
        productGrid.addColumn(Product::getSkuNumber)
            .setHeader("SKU").setFlexGrow(2);
        productGrid.addColumn(p -> p.getProductType().name())
            .setHeader("Type").setFlexGrow(1);
        productGrid.addColumn(p -> String.format("$%.2f", p.getProductPrice()))
            .setHeader("Price").setFlexGrow(1);
        productGrid.addColumn(Product::getProductQuantity)
            .setHeader("Qty").setFlexGrow(1);
        productGrid.addComponentColumn(p -> {
            Span badge = new Span(p.getProductQuantity() > 0 ? "In Stock" : "Out of Stock");
            badge.addClassName(p.getProductQuantity() > 0 ? "badge-success" : "badge-warning");
            return badge;
        }).setHeader("Status").setFlexGrow(1);

        productGrid.addComponentColumn(p -> {
            HorizontalLayout actions = new HorizontalLayout();

            Button edit = new Button("Edit");
            edit.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            edit.addClickListener(e -> openProductDialog(p));

            Button delete = new Button("Delete");
            delete.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ERROR,
                                    ButtonVariant.LUMO_TERTIARY);
            delete.addClickListener(e -> {
                productServices.deleteProduct(p.getProductId());
                refreshGrid();
                showNotification("Product \"" + p.getProductName() + "\" deleted.", false);
            });

            actions.add(edit, delete);
            return actions;
        }).setHeader("Actions").setFlexGrow(1);

        return productGrid;
    }

    // ── Add / Edit dialog ──────────────────────────────────────────────────
    private void openProductDialog(Product existing) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle(existing == null ? "Add Product" : "Edit Product");
        dialog.setWidth("460px");

        // Product Name
        TextField nameField = new TextField("Product Name *");
        nameField.setWidthFull();
        nameField.setPlaceholder("e.g. Wireless Headphones");

        // Product Type
        ComboBox<TypeProduct> typeField = new ComboBox<>("Product Type *");
        typeField.setItems(TypeProduct.values());
        typeField.setWidthFull();

        // Price (NEW FIELD)
        NumberField priceField = new NumberField("Product Price * ($)");
        priceField.setMin(0);
        priceField.setStep(0.01);
        priceField.setWidthFull();
        priceField.setPlaceholder("e.g. 29.99");

        // Quantity
        IntegerField qtyField = new IntegerField("Quantity *");
        qtyField.setMin(0);
        qtyField.setValue(0);
        qtyField.setWidthFull();

        // Description (max 25 chars to match @Column(length=25))
        TextField descField = new TextField("Description (max 25 chars)");
        descField.setWidthFull();
        descField.setMaxLength(25);
        descField.setPlaceholder("Short description...");

        // Image upload — stored as byte[] in Product
        Div imageSection = new Div();
        imageSection.getStyle().set("width", "100%");

        Span imageLabel = new Span("Product Image (optional)");
        imageLabel.getStyle()
            .set("font-size", "13px")
            .set("font-weight", "600")
            .set("color", "#64748b")
            .set("display", "block")
            .set("margin-bottom", "6px");

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("image/jpeg", "image/png", "image/gif", "image/webp");
        upload.setMaxFiles(1);
        upload.setWidthFull();

        // Holds the uploaded bytes so we can use them on save
        byte[][] uploadedImage = { null };

        upload.addSucceededListener(event -> {
            try (InputStream is = buffer.getInputStream()) {
                uploadedImage[0] = is.readAllBytes();
            } catch (IOException ex) {
                showNotification("Failed to read image.", true);
            }
        });

        imageSection.add(imageLabel, upload);

        // Pre-fill when editing
        if (existing != null) {
            nameField.setValue(existing.getProductName());
            typeField.setValue(existing.getProductType());
            qtyField.setValue(existing.getProductQuantity());
            priceField.setValue((double) existing.getProductPrice());
            if (existing.getProductDesc() != null)
                descField.setValue(existing.getProductDesc());
        }

        VerticalLayout form = new VerticalLayout(
            nameField, typeField, priceField, qtyField, descField, imageSection
        );
        form.setPadding(false);
        form.setSpacing(true);
        dialog.add(form);

        // Save
        Button save = new Button(existing == null ? "Add Product" : "Save Changes", e -> {

            // Validation
            if (nameField.isEmpty() || typeField.isEmpty()) {
                showNotification("Name and Type are required.", true);
                return;
            }
            if (priceField.isEmpty() || priceField.getValue() == null) {
                showNotification("Price is required.", true);
                return;
            }
            if (priceField.getValue() < 0) {
                showNotification("Price must be 0 or greater.", true);
                return;
            }
            if (qtyField.getValue() == null || qtyField.getValue() < 0) {
                showNotification("Quantity must be 0 or more.", true);
                return;
            }

            String name  = nameField.getValue().trim();
            TypeProduct type = typeField.getValue();
            float price = priceField.getValue().floatValue();
            int qty = qtyField.getValue();
            String desc  = descField.getValue().trim();

            if (existing == null) {
                // ── ADD via ProductServices → persists to DB + inserts into LinkedList + BST
                byte[] imageBytes = uploadedImage[0];
                Product newProduct = new Product(name, type, qty, desc.isEmpty() ? null : desc, imageBytes, price);
                productServices.addProduct(newProduct);
                showNotification("Product \"" + name + "\" added.", false);

            } else {
                // ── EDIT: update fields, re-save via repository
                existing.setProductName(name);
                existing.setProductType(type);
                existing.setProductPrice(price);
                existing.setProductQuantity(qty);
                existing.setProductDesc(desc.isEmpty() ? null : desc);
                if (uploadedImage[0] != null) {
                    existing.setProductImage(uploadedImage[0]);
                }
                // Use addProduct which calls productRepository.save() and keeps BST in sync
                productServices.addProduct(existing);
                showNotification("Product \"" + name + "\" updated.", false);
            }

            refreshGrid();
            dialog.close();
        });
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getStyle().set("background", "#10b981");

        Button cancel = new Button("Cancel", e -> dialog.close());

        dialog.getFooter().add(cancel, save);
        dialog.open();
    }

    // ── Grid refresh — pulls from the in-memory LinkedList via ProductServices ──
    private void refreshGrid() {
        ProductList productList = productServices.getAllProducts();
        List<Product> items = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            items.add(productList.getIndex(i));
        }
        productGrid.setItems(items);
    }

    private void showNotification(String message, boolean isError) {
        Notification n = Notification.show(message, 3000, Notification.Position.BOTTOM_END);
        n.addThemeVariants(isError
            ? NotificationVariant.LUMO_ERROR
            : NotificationVariant.LUMO_SUCCESS);
    }
}