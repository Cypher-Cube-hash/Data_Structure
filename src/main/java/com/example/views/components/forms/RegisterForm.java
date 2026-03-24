package com.example.views.components.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.Span;
import com.example.models.Address;
import com.example.services.RegistrationServices;
import com.example.models.Telephone;




public class RegisterForm extends Div {

    private final RegistrationServices registrationServices;
    private Input firstNameInput;
    private Input lastNameInput;
    private Input emailInput;
    private Input addressLine1Input;
    private Input addressLine2Input;
    private Input cityInput;
    private Input stateInput;
    private ComboBox<String> countryCombo;
    private Input countryCodeInput;
    private Input areaCodeInput;
    private Input exchangeCodeInput;
    private Input subscriberLineInput;

    public RegisterForm(RegistrationServices registrationServices) {
        this.registrationServices = registrationServices;
        addClassName("form-container");

        Div joinDiv = new Div();
        joinDiv.addClassName("welcome-message");
        joinDiv.setText("Create Your Account");

        Div nameContainer = buildNameContainer();

        Div emailGroup = buildInputGroup("Email *", "john.doe@example.com", "email", true);
        emailInput = extractInput(emailGroup);



        Div telephoneSection = buildTelephoneContainer();

        Div addressLine1Group = buildInputGroup("Address Line 1 *", "Street address, P.O. box", "text", true);
        addressLine1Input = extractInput(addressLine1Group);

        Div addressLine2Group = buildInputGroup("Address Line 2 (optional)", "Apartment, suite, unit...", "text", false);
        addressLine2Input = extractInput(addressLine2Group);

        Div cityStateContainer = buildCityStateContainer();

        Div countryGroup = buildCountryGroup();

        Div termsDiv = new Div();
        termsDiv.addClassName("terms-text");
        termsDiv.setText("By registering, you agree to our Terms and Privacy Policy");

        Button registerButton = new Button("Create Account");
        registerButton.addClassName("action-button");
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerButton.addClickListener(e -> handleRegister());

        add(
            joinDiv,
            nameContainer,
            emailGroup,
            telephoneSection,
            addressLine1Group,
            addressLine2Group,
            cityStateContainer,
            countryGroup,
            termsDiv,
            registerButton
        );
    }

    // ─── Name Row ────────────────────────────────────────
    private Div buildNameContainer() {
        Div container = new Div();
        container.addClassName("name-container");

        Div firstNameGroup = new Div();
        firstNameGroup.addClassNames("input-group", "half-width");

        Div firstLabel = new Div();
        firstLabel.addClassName("input-label");
        firstLabel.setText("First Name *");

        firstNameInput = new Input();
        firstNameInput.setPlaceholder("John");
        firstNameInput.addClassName("form-input");
        firstNameInput.setRequiredIndicatorVisible(true);

        firstNameGroup.add(firstLabel, firstNameInput);

        Div lastNameGroup = new Div();
        lastNameGroup.addClassNames("input-group", "half-width");

        Div lastLabel = new Div();
        lastLabel.addClassName("input-label");
        lastLabel.setText("Last Name *");

        lastNameInput = new Input();
        lastNameInput.setPlaceholder("Doe");
        lastNameInput.addClassName("form-input");
        lastNameInput.setRequiredIndicatorVisible(true);

        lastNameGroup.add(lastLabel, lastNameInput);

        container.add(firstNameGroup, lastNameGroup);
        return container;
    }

    // ─── City & State Row ────────────────────────────────
    private Div buildCityStateContainer() {
        Div container = new Div();
        container.addClassName("city-state-container");

        Div cityGroup = new Div();
        cityGroup.addClassNames("input-group", "city-width");

        Div cityLabel = new Div();
        cityLabel.addClassName("input-label");
        cityLabel.setText("City *");

        cityInput = new Input();
        cityInput.setPlaceholder("New York");
        cityInput.addClassName("form-input");
        cityInput.setRequiredIndicatorVisible(true);

        cityGroup.add(cityLabel, cityInput);

        Div stateGroup = new Div();
        stateGroup.addClassNames("input-group", "state-width");

        Div stateLabel = new Div();
        stateLabel.addClassName("input-label");
        stateLabel.setText("State *");

        stateInput = new Input();
        stateInput.setPlaceholder("NY");
        stateInput.addClassName("form-input");
        stateInput.setRequiredIndicatorVisible(true);

        stateGroup.add(stateLabel, stateInput);

        container.add(cityGroup, stateGroup);
        return container;
    }

    // ─── Country Dropdown ────────────────────────────────
    private Div buildCountryGroup() {
        Div group = new Div();
        group.addClassName("input-group");

        Div label = new Div();
        label.addClassName("input-label");
        label.setText("Country *");

        countryCombo = new ComboBox<>();
        countryCombo.setPlaceholder("Select your country");
        countryCombo.setItems(
            "United States", "Canada", "United Kingdom",
            "Australia", "Germany", "France", "Japan",
            "Brazil", "India", "Jamaica", "Other"
        );
        countryCombo.addClassNames("form-input", "country-combo");
        countryCombo.setRequiredIndicatorVisible(true);

        group.add(label, countryCombo);
        return group;
    }

    // ─── Reusable input group ────────────────────────────
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

        if (required) {
            input.setRequiredIndicatorVisible(true);
        }

        group.add(labelDiv, input);
        return group;
    }



    private Input extractInput(Div group) {
        return (Input) group.getChildren()
                .filter(c -> c instanceof Input)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Input not found"));
    }

    // ─── Telephone Row ────────────────────────────────────
    private Div buildTelephoneContainer() {
        Div container = new Div();
        container.addClassName("telephone-container");

        Div label = new Div();
        label.addClassName("input-label");
        label.setText("Phone Number *");

        Div fieldsWrapper = new Div();
        fieldsWrapper.addClassName("telephone-fields-wrapper");

        countryCodeInput = createPhoneInput("1", "40px");
        areaCodeInput = createPhoneInput("876", "50px");
        exchangeCodeInput = createPhoneInput("555", "50px");
        subscriberLineInput = createPhoneInput("0123", "70px");

        fieldsWrapper.add(
            new Span("+"),
            countryCodeInput,
            new Span("-"),
            areaCodeInput,
            new Span("-"),
            exchangeCodeInput,
            new Span("-"),
            subscriberLineInput
        );

        container.add(label, fieldsWrapper);
        return container;
    }

    private Input createPhoneInput(String placeholder, String width) {
        Input input = new Input();
        input.setPlaceholder(placeholder);
        input.addClassName("form-input");
        input.getStyle().set("width", width);

        // ✅ FIX: required indicator
        input.setRequiredIndicatorVisible(true);

        return input;
    }

    // ─── Submit Handler ──────────────────────────────────
    private void handleRegister() {

        if (firstNameInput.isEmpty() ||
            lastNameInput.isEmpty() ||
            emailInput.isEmpty() ||
            addressLine1Input.isEmpty() ||
            cityInput.isEmpty() ||
            stateInput.isEmpty() ||
            countryCombo.isEmpty()) {
            return;
        }

        String country = countryCombo.getValue() != null ? countryCombo.getValue() : "";



        registrationServices.addRegisteredUser(
            firstNameInput.getValue(),
            lastNameInput.getValue(),
            emailInput.getValue(),
            new Address(
                addressLine1Input.getValue(),
                addressLine2Input.getValue(),
                cityInput.getValue(),
                stateInput.getValue(),
                country
            ),
            new Telephone(
                countryCodeInput.getValue(),
                areaCodeInput.getValue(),
                exchangeCodeInput.getValue(),
                subscriberLineInput.getValue()
            )
        );
    }
}