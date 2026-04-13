package com.example.views.components.header;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;

@Component
public class HeaderOptionsView extends HorizontalLayout {

    private final Div cartButton;
    private final Div userButton;
    private final Button logoutButton;

    public HeaderOptionsView() {

        addClassName("header_option_section");
        setAlignItems(Alignment.CENTER);
        setSpacing(true);

        cartButton = createIconButton(
            "icon/cart.svg",
            "cart"
        );

        // USER ICON BUTTON
        userButton = createIconButton(
            "icon/profile.svg",
            "user"
        );

        logoutButton = new Button("Logout");
        Div logoutContainer = new Div(logoutButton);
        logoutContainer.addClassName("log_out_container");

        add(cartButton, userButton, logoutContainer);
    }

    private Div createIconButton(String src, String alt) {
        Div wrapper = new Div();
        wrapper.addClassName("icon_btn");

        Image icon = new Image(src, alt);
        icon.setWidth("18px");
        icon.setHeight("18px");

        wrapper.add(icon);
        return wrapper;
    }

    public Div getCartButton() {
        return cartButton;
    }

    public Div getUserButton() {
        return userButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }
}