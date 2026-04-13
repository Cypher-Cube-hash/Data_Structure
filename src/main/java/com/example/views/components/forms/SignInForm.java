package com.example.views.components.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.UI;
import com.example.repositories.AdminRepository;
import com.example.repositories.AuthenticationRepository;
import com.example.repositories.CustomerRepository;
import com.example.repositories.TemporaryPasswordRepository;
import com.example.services.CustomerServices;
import com.example.services.UserServices;
import com.example.models.TemporaryPassword;
import com.example.enums.TypeGender;
import com.example.models.Admin;
import com.example.repositories.CustomerRepository;
import com.example.services.CustomerServices;
import com.example.services.UserServices;
import com.example.models.Customer;
import com.example.models.User;
import com.example.enums.TypeGender;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.models.Authentication;
import com.example.models.Customer;
import com.example.utils.PassWordHasher;

@Component
public class SignInForm extends Div {

    private Input emailInput;
    private Input passwordInput;
    

    private final AdminRepository adminRepository;
    private final AuthenticationRepository authRepository;
    private final TemporaryPasswordRepository tempPass;
    private final CustomerRepository customerRepository;
    private final CustomerServices customerServices;
    private final UserServices userServices;

    public SignInForm(AdminRepository adminRepository, AuthenticationRepository authRepository,
                  TemporaryPasswordRepository tempPass, CustomerRepository customerRepository,
                  CustomerServices customerServices, UserServices userServices) {
        this.adminRepository = adminRepository;
        this.authRepository = authRepository;
        this.tempPass = tempPass;
        this.customerRepository = customerRepository;
        this.customerServices = customerServices;
        this.userServices = userServices;
            
        addClassName("form-container");
        
        Div welcomeDiv = new Div();
        welcomeDiv.addClassName("welcome-message");
        welcomeDiv.setText("Welcome Back!");

        Div emailGroup = buildInputGroup("Email", "Enter your email", "text", true);
         
        Div passwordGroup = buildInputGroup("Password", "Enter your password", "password", true);

        Div forgotPassword = new Div();
        forgotPassword.addClassName("forgot-password");
        forgotPassword.setText("Forgot password?");
        forgotPassword.addClickListener(e -> handleForgotPassword());

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

           
            if (temp_pass != null && !temp_pass.isExpired() && temp_pass.getPass().equals(password)) {
                getUI().ifPresent(ui -> ui.navigate("create-password"));
            } else {
                if (temp_entity == null) {
                    Notification.show("Invalid email or password.");
                    return;
                }
                String userId = temp_entity.getUser();

                if (userId != null) {
                    Optional<Admin> adminOpt = adminRepository.findByUser_UserId(userId);
                    if (adminOpt.isPresent()) {
                        getUI().ifPresent(ui -> ui.navigate("admin"));
                    } else {
                        // Create customer record if it doesn't exist yet
                        if (!customerRepository.findByUser_UserId(userId).isPresent()) {
                            User user = userServices.findById(userId);
                            if (user != null) {
                                customerRepository.save(new Customer(user, TypeGender.NULL));
                            }
                        }
                        getUI().ifPresent(ui -> ui.navigate("product-page/" + userId));
                    }
                }       
            }
        } catch (Exception e) {
            Notification.show("Error: " + e.getMessage());
        }


        
    }
    
    private void handleForgotPassword() {
      
    }
}