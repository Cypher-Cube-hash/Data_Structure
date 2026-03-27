package com.example.views.components.forms;

import com.example.services.AuthenticationServices;
import com.example.services.UserServices;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.example.services.AuthenticationServices;
import com.example.utils.PassWordHasher;

public class CreatePasswordForm extends Div {

    private final AuthenticationServices authenticationServices;
    private final UserServices userServices;
    
    private EmailField emailField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private Button createPasswordButton;
    private Button backButton;

    public CreatePasswordForm(AuthenticationServices authenticationServices, 
                              UserServices userServices) {
        this.authenticationServices = authenticationServices;
        this.userServices = userServices;
        addClassName("form-container");

        // Welcome message
        Div welcomeMessage = new Div();
        welcomeMessage.addClassName("welcome-message");
        welcomeMessage.setText("Create New Password");

        // Instruction text
        Div instructionText = new Div();
        instructionText.addClassName("instruction-text");
        instructionText.setText("Please create a secure password for your account");
        instructionText.getStyle()
            .set("color", "#64748b")
            .set("text-align", "center")
            .set("margin-bottom", "2rem")
            .set("font-size", "14px");

        // Email field group
        Div emailGroup = buildEmailGroup();
        
        // Password field group
        Div passwordGroup = buildPasswordGroup();
        
        // Confirm password field group
        Div confirmPasswordGroup = buildConfirmPasswordGroup();
        
        // Password requirements section
        Div requirementsDiv = buildPasswordRequirements();
        
        // Buttons
        Div buttonContainer = buildButtons();

        add(
            welcomeMessage,
            instructionText,
            emailGroup,
            passwordGroup,
            confirmPasswordGroup,
            requirementsDiv,
            buttonContainer
        );
    }

    // ─── Email Group ────────────────────────────────────────
    private Div buildEmailGroup() {
        Div group = new Div();
        group.addClassName("input-group");

        Div label = new Div();
        label.addClassName("input-label");
        label.setText("EMAIL ADDRESS *");

        emailField = new EmailField();
        emailField.setPlaceholder("Enter your email address");
        emailField.addClassName("form-input");
        emailField.setWidthFull();
        emailField.setRequiredIndicatorVisible(true);

        group.add(label, emailField);
        return group;
    }

    // ─── Password Group ────────────────────────────────────
    private Div buildPasswordGroup() {
        Div group = new Div();
        group.addClassName("input-group");

        Div label = new Div();
        label.addClassName("input-label");
        label.setText("PASSWORD *");

        passwordField = new PasswordField();
        passwordField.setPlaceholder("Enter your password");
        passwordField.addClassName("form-input");
        passwordField.setWidthFull();
        passwordField.setRequiredIndicatorVisible(true);

        group.add(label, passwordField);
        return group;
    }

    // ─── Confirm Password Group ────────────────────────────
    private Div buildConfirmPasswordGroup() {
        Div group = new Div();
        group.addClassName("input-group");

        Div label = new Div();
        label.addClassName("input-label");
        label.setText("CONFIRM PASSWORD *");

        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPlaceholder("Confirm your password");
        confirmPasswordField.addClassName("form-input");
        confirmPasswordField.setWidthFull();
        confirmPasswordField.setRequiredIndicatorVisible(true);

        group.add(label, confirmPasswordField);
        return group;
    }

    // ─── Password Requirements Section ─────────────────────
    private Div buildPasswordRequirements() {
        Div container = new Div();
        container.addClassName("password-requirements");
        container.getStyle()
            .set("background", "#f8fafc")
            .set("padding", "1rem")
            .set("border-radius", "12px")
            .set("margin", "1rem 0")
            .set("border", "1px solid #e2e8f0");

        Div reqTitle = new Div();
        reqTitle.setText("Password must contain:");
        reqTitle.getStyle()
            .set("font-weight", "600")
            .set("margin-bottom", "0.5rem")
            .set("color", "#1e293b");

        Div reqList = new Div();
        reqList.getStyle().set("font-size", "13px");

        Div req1 = new Div();
        req1.setText("✓ At least 8 characters");
        req1.getStyle().set("color", "#64748b").set("margin", "0.25rem 0");

        Div req2 = new Div();
        req2.setText("✓ At least one uppercase letter");
        req2.getStyle().set("color", "#64748b").set("margin", "0.25rem 0");

        Div req3 = new Div();
        req3.setText("✓ At least one lowercase letter");
        req3.getStyle().set("color", "#64748b").set("margin", "0.25rem 0");

        Div req4 = new Div();
        req4.setText("✓ At least one number");
        req4.getStyle().set("color", "#64748b").set("margin", "0.25rem 0");

        Div req5 = new Div();
        req5.setText("✓ At least one special character (!@#$%^&*)");
        req5.getStyle().set("color", "#64748b").set("margin", "0.25rem 0");

        reqList.add(req1, req2, req3, req4, req5);
        container.add(reqTitle, reqList);
        
        return container;
    }

    // ─── Buttons Container ─────────────────────────────────
    private Div buildButtons() {
        Div container = new Div();
        container.getStyle().set("margin-top", "1rem");

        // Create Password Button
        createPasswordButton = new Button("CREATE PASSWORD");
        createPasswordButton.addClassName("action-button");
        createPasswordButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        createPasswordButton.setWidthFull();
        createPasswordButton.addClickListener(e -> handleCreatePassword());

        // Back Button
        backButton = new Button("← BACK TO SIGN IN");
        backButton.addClassName("back-button");
        backButton.getStyle()
            .set("background", "transparent")
            .set("color", "#10b981")
            .set("border", "2px solid #10b981")
            .set("border-radius", "12px")
            .set("padding", "0.75rem")
            .set("font-weight", "600")
            .set("cursor", "pointer")
            .set("width", "100%")
            .set("margin-top", "1rem")
            .set("transition", "all 0.3s ease");
        
        backButton.addClickListener(e -> {
            UI.getCurrent().navigate("");
        });

        container.add(createPasswordButton, backButton);
        return container;
    }




    // ─── Handle Password Creation ──────────────────────────
    private void handleCreatePassword() {
        if(emailField.isEmpty() || passwordField.isEmpty() || confirmPasswordField.isEmpty()){
            Notification.show("Form submission blocked: missing required fields");
            return;
        }
        
        try{

            if(
                isValidPassword(passwordField.getValue()) && 
                isValidPassword(confirmPasswordField.getValue()) && 
                passwordField.getValue().equals(confirmPasswordField.getValue())
            ){
                String password = PassWordHasher.hashPassword(passwordField.getValue());
                authenticationServices.addAuthentication(emailField.getValue(), password, true);
                Notification.show("Successful ");
                getUI().ifPresent(ui -> ui.navigate(""));
                return;
            }
            
            Notification.show("Unsuccessful ");
        }catch(Exception e){
            Notification.show("Error: " + e);
        }





        
    }

    // ─── Password Validation Helper ───────────────────────
    private boolean isValidPassword(String password) {
        if (password.length() < 8) return false;
        if (!password.matches(".*[A-Z].*")) return false;
        if (!password.matches(".*[a-z].*")) return false;
        if (!password.matches(".*\\d.*")) return false;
        return true;
    }

    /* // ─── Notification Helper ───────────────────────────────
    private void showNotification(String message, NotificationVariant variant) {
        Notification notification = new Notification(message, 3000);
        notification.addThemeVariants(variant);
        notification.setPosition(Notification.Position.TOP_CENTER);
        notification.open();
    } */
}