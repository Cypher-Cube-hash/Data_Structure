package com.example.views.components.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.UI;
import com.example.repositories.AuthenticationRepository;
import com.example.repositories.TemporaryPasswordRepository;
import com.example.models.TemporaryPassword;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.models.Authentication;
import com.example.utils.PassWordHasher;

@Component
public class SignInForm extends Div {

    
    
    // Store references to the input fields
    private Input emailInput;
    private Input passwordInput;
    
    // Repositories (you'll need to inject these via constructor)
    private final AuthenticationRepository authRepository;
    private final TemporaryPasswordRepository tempPass;

    public SignInForm(AuthenticationRepository authRepository, TemporaryPasswordRepository tempPass) {
        this.authRepository = authRepository;
        this.tempPass = tempPass;
        
        addClassName("form-container");
        
        // Welcome message
        Div welcomeDiv = new Div();
        welcomeDiv.addClassName("welcome-message");
        welcomeDiv.setText("Welcome Back!");

        // Email field - store reference
        Div emailGroup = buildInputGroup("Email", "Enter your email", "text", true);
        
        // Password field - store reference  
        Div passwordGroup = buildInputGroup("Password", "Enter your password", "password", true);

        // Forgot password
        Div forgotPassword = new Div();
        forgotPassword.addClassName("forgot-password");
        forgotPassword.setText("Forgot password?");
        forgotPassword.addClickListener(e -> handleForgotPassword());

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
        
        // Store reference based on label
        if (label.equals("Email")) {
            this.emailInput = input;
        } else if (label.equals("Password")) {
            this.passwordInput = input;
        }

        group.add(labelDiv, input);
        return group;
    }

    private void handleSignIn() {
       
        String email = emailInput.getValue();
        String password = passwordInput.getValue();
        
        
        if (email.isEmpty() || password.isEmpty()) {
            Notification.show("Please enter both email and password");
            return;
        }
        
        
        try {
            Optional<TemporaryPassword> tempEntity = tempPass.findByPass(password);
            TemporaryPassword temp_pass = null;
            if(tempEntity.isPresent()){temp_pass = tempEntity.get();}

            

            Optional<Authentication> tempAuth = authRepository.findByEmail(email);
            Authentication temp_entity = null;
            if(tempAuth.isPresent()){temp_entity = tempAuth.get();}

            //The first section of the if statement checks wether ot not the entered pass and the temp matches
            //if so it pushes the cus to create a new one

            //The else section is the second option that would check another table password and matches the values and if o
            //pushes them to another page. 
            if (temp_pass != null && !temp_pass.isExpired() && temp_pass.getPass().equals(password)) {
                getUI().ifPresent(ui -> ui.navigate("create-password"));
            } else {
                if (temp_entity == null) {
                    Notification.show("Invalid email or password.");
                    return;
                }

                // ✅ Pass raw password directly — do NOT pre-hash it
                if (PassWordHasher.verifyPassword(password, temp_entity.getPasswordHash())) {
                    getUI().ifPresent(ui -> ui.navigate("product-page"));
                } else {
                    Notification.show("Invalid email or password.");
                }
            }
            


        } catch (Exception e) {
            Notification.show("Error: " + e.getMessage());
        }


        
    }
    
    private void handleForgotPassword() {
      
    }
}