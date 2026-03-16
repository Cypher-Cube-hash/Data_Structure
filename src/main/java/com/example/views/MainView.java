package com.example.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;

@Route("")
@PageTitle("Brand-Ex")
public class MainView extends VerticalLayout {
    
    private Div signInTab;
    private Div registerTab;
    private Div rowOneDiv;
    private Div rowTwoDiv;
    
    public MainView() {
        addClassName("homePage");
        
        // Box one setup (left side - lava effect)
        Div boxOne = new Div();
        boxOne.addClassName("boxOne");
        
        Div innerDiv = new Div();
        innerDiv.addClassName("innerDivMainPage");
        H1 heading = new H1("BrandEx – Where Shopping Comes Alive");
        heading.setId("logo");
        Paragraph paraOne = new Paragraph("Discover exclusive deals, trendsetting products, and a shopping experience tailored just for you.");
        paraOne.setId("logo_para");
        innerDiv.add(heading);
        innerDiv.add(paraOne);
        boxOne.add(innerDiv);

        // Box two setup (right side - authentication panel)
        Div boxTwo = new Div();
        boxTwo.addClassName("boxTwo");

        Div inner_div_box_two = new Div();
        inner_div_box_two.addClassName("inner_div_box_two");

        // Row One - Tabs
        rowOneDiv = new Div();
        rowOneDiv.addClassName("tabs-container");
        
        // Sign In Tab
        signInTab = new Div();
        signInTab.addClassName("tab");
        signInTab.addClassName("active-tab");
        signInTab.setText("SIGN IN");
        signInTab.addClickListener(e -> showSignInForm());
        
        // Register Tab
        registerTab = new Div();
        registerTab.addClassName("tab");
        registerTab.setText("REGISTER");
        registerTab.addClickListener(e -> showRegisterForm());
        
        rowOneDiv.add(signInTab, registerTab);
        
        // Row Two - Form Container
        rowTwoDiv = new Div();
        rowTwoDiv.addClassName("form-container");
        
        // Initialize with Sign In form
        showSignInForm();

        inner_div_box_two.add(rowOneDiv, rowTwoDiv);
        boxTwo.add(inner_div_box_two);

        add(boxOne);
        add(boxTwo);
    }
    
    private void showSignInForm() {
        // Update tab styles
        signInTab.addClassName("active-tab");
        registerTab.removeClassName("active-tab");
        
        // Clear and rebuild form
        rowTwoDiv.removeAll();
        
        // Welcome back message
        Div welcomeDiv = new Div();
        welcomeDiv.addClassName("welcome-message");
        welcomeDiv.setText("Welcome Back!");
        
        // Email field
        Div emailGroup = new Div();
        emailGroup.addClassName("input-group");
        
        Div emailLabel = new Div();
        emailLabel.addClassName("input-label");
        emailLabel.setText("Email");
        
        Input emailInput = new Input();
        emailInput.setPlaceholder("Enter your email");
        emailInput.addClassName("form-input");
        emailInput.setRequiredIndicatorVisible(true);
        
        emailGroup.add(emailLabel, emailInput);
        
        // Password field
        Div passwordGroup = new Div();
        passwordGroup.addClassName("input-group");
        
        Div passwordLabel = new Div();
        passwordLabel.addClassName("input-label");
        passwordLabel.setText("Password");
        
        Input passwordInput = new Input();
        passwordInput.setPlaceholder("Enter your password");
        passwordInput.setType("password");
        passwordInput.addClassName("form-input");
        passwordInput.setRequiredIndicatorVisible(true);
        
        passwordGroup.add(passwordLabel, passwordInput);
        
        // Forgot password link
        Div forgotPassword = new Div();
        forgotPassword.addClassName("forgot-password");
        forgotPassword.setText("Forgot password?");
        
        // Sign In button
        Button signInButton = new Button("Sign In");
        signInButton.addClassName("action-button");
        signInButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        rowTwoDiv.add(welcomeDiv, emailGroup, passwordGroup, forgotPassword, signInButton);
    }
    
    private void showRegisterForm() {
        // Update tab styles
        registerTab.addClassName("active-tab");
        signInTab.removeClassName("active-tab");
        
        // Clear and rebuild form
        rowTwoDiv.removeAll();
        
        // Join us message
        Div joinDiv = new Div();
        joinDiv.addClassName("welcome-message");
        joinDiv.setText("Create Your Account");
        
        // Name fields container (side by side)
        Div nameContainer = new Div();
        nameContainer.addClassName("name-container");
        
        // First Name field
        Div firstNameGroup = new Div();
        firstNameGroup.addClassName("input-group");
        firstNameGroup.addClassName("half-width");
        
        Div firstNameLabel = new Div();
        firstNameLabel.addClassName("input-label");
        firstNameLabel.setText("First Name *");
        
        Input firstNameInput = new Input();
        firstNameInput.setPlaceholder("John");
        firstNameInput.addClassName("form-input");
        firstNameInput.setRequiredIndicatorVisible(true);
        
        firstNameGroup.add(firstNameLabel, firstNameInput);
        
        // Last Name field
        Div lastNameGroup = new Div();
        lastNameGroup.addClassName("input-group");
        lastNameGroup.addClassName("half-width");
        
        Div lastNameLabel = new Div();
        lastNameLabel.addClassName("input-label");
        lastNameLabel.setText("Last Name *");
        
        Input lastNameInput = new Input();
        lastNameInput.setPlaceholder("Doe");
        lastNameInput.addClassName("form-input");
        lastNameInput.setRequiredIndicatorVisible(true);
        
        lastNameGroup.add(lastNameLabel, lastNameInput);
        
        nameContainer.add(firstNameGroup, lastNameGroup);
        
        // Email field
        Div emailGroup = new Div();
        emailGroup.addClassName("input-group");
        
        Div emailLabel = new Div();
        emailLabel.addClassName("input-label");
        emailLabel.setText("Email *");
        
        Input emailInput = new Input();
        emailInput.setPlaceholder("john.doe@example.com");
        emailInput.addClassName("form-input");
        emailInput.setRequiredIndicatorVisible(true);
        
        emailGroup.add(emailLabel, emailInput);
        
        // Address Line 1 field
        Div addressLine1Group = new Div();
        addressLine1Group.addClassName("input-group");
        
        Div addressLine1Label = new Div();
        addressLine1Label.addClassName("input-label");
        addressLine1Label.setText("Address Line 1 *");
        
        Input addressLine1Input = new Input();
        addressLine1Input.setPlaceholder("Street address, P.O. box");
        addressLine1Input.addClassName("form-input");
        addressLine1Input.setRequiredIndicatorVisible(true);
        
        addressLine1Group.add(addressLine1Label, addressLine1Input);
        
        // Address Line 2 field
        Div addressLine2Group = new Div();
        addressLine2Group.addClassName("input-group");
        
        Div addressLine2Label = new Div();
        addressLine2Label.addClassName("input-label");
        addressLine2Label.setText("Address Line 2 (optional)");
        
        Input addressLine2Input = new Input();
        addressLine2Input.setPlaceholder("Apartment, suite, unit, building, floor, etc.");
        addressLine2Input.addClassName("form-input");
        // No required indicator for optional field
        
        addressLine2Group.add(addressLine2Label, addressLine2Input);
        
        // City and State container (side by side)
        Div cityStateContainer = new Div();
        cityStateContainer.addClassName("city-state-container");
        
        // City field
        Div cityGroup = new Div();
        cityGroup.addClassName("input-group");
        cityGroup.addClassName("city-width");
        
        Div cityLabel = new Div();
        cityLabel.addClassName("input-label");
        cityLabel.setText("City *");
        
        Input cityInput = new Input();
        cityInput.setPlaceholder("New York");
        cityInput.addClassName("form-input");
        cityInput.setRequiredIndicatorVisible(true);
        
        cityGroup.add(cityLabel, cityInput);
        
        // State field
        Div stateGroup = new Div();
        stateGroup.addClassName("input-group");
        stateGroup.addClassName("state-width");
        
        Div stateLabel = new Div();
        stateLabel.addClassName("input-label");
        stateLabel.setText("State *");
        
        Input stateInput = new Input();
        stateInput.setPlaceholder("NY");
        stateInput.addClassName("form-input");
        stateInput.setRequiredIndicatorVisible(true);
        
        stateGroup.add(stateLabel, stateInput);
        
        cityStateContainer.add(cityGroup, stateGroup);
        
        // Country field
        Div countryGroup = new Div();
        countryGroup.addClassName("input-group");
        
        Div countryLabel = new Div();
        countryLabel.addClassName("input-label");
        countryLabel.setText("Country *");
        
        // Using ComboBox for country selection
        ComboBox<String> countryCombo = new ComboBox<>();
        countryCombo.setPlaceholder("Select your country");
        countryCombo.setItems("United States", "Canada", "United Kingdom", "Australia", "Germany", "France", "Japan", "Brazil", "India", "Other");
        countryCombo.addClassName("form-input");
        countryCombo.addClassName("country-combo");
        countryCombo.setRequiredIndicatorVisible(true);
        
        countryGroup.add(countryLabel, countryCombo);
        
        // Terms and conditions
        Div termsDiv = new Div();
        termsDiv.addClassName("terms-text");
        termsDiv.setText("By registering, you agree to our Terms and Privacy Policy");
        
        // Register button
        Button registerButton = new Button("Create Account");
        registerButton.addClassName("action-button");
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        rowTwoDiv.add(
            joinDiv, 
            nameContainer, 
            emailGroup, 
            addressLine1Group, 
            addressLine2Group, 
            cityStateContainer, 
            countryGroup, 
            termsDiv, 
            registerButton
        );
    }
}