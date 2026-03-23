package com.example.views.components.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SignInForm extends Div {

    public SignInForm() {
        addClassName("form-container");
        
        // Welcome message
        Div welcomeDiv = new Div();
        welcomeDiv.addClassName("welcome-message");
        welcomeDiv.setText("Welcome Back!");

        // Email field
        Div emailGroup = buildInputGroup("Email", "Enter your email", "text", true);

        // Password field
        Div passwordGroup = buildInputGroup("Password", "Enter your password", "password", true);

        // Forgot password
        Div forgotPassword = new Div();
        forgotPassword.addClassName("forgot-password");
        forgotPassword.setText("Forgot password?");

        // Sign In button
        Button signInButton = new Button("Sign In");
        signInButton.addClassName("action-button");
        signInButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        signInButton.addClickListener(e -> handleSignIn());

        add(welcomeDiv, emailGroup, passwordGroup, forgotPassword, signInButton);
    }

    private Div buildInputGroup(String label, String placeholder, String type, boolean required) {
        Div group = new Div();
        group.addClassName("input-group");

        Div labelDiv = new Div();
        labelDiv.addClassName("input-label");
        labelDiv.setText(label);

        Input input = new Input();
        input.setPlaceholder(placeholder);
        input.setType(type);
        input.addClassName("form-input");
        if (required) input.setRequiredIndicatorVisible(true);

        group.add(labelDiv, input);
        return group;
    }

    private void handleSignIn() {
        // TODO: wire to UserService for authentication
    }
}