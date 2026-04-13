package com.example.views;

import com.example.services.AuthenticationServices;
import com.example.services.UserServices;
import com.example.views.components.forms.CreatePasswordForm;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.example.repositories.CustomerRepository;

@Route("create-password")
@PageTitle("Create Password - BrandEx")
@AnonymousAllowed
public class CreatePasswordView extends VerticalLayout {

    private final AuthenticationServices authenticationServices;
    private final UserServices userServices;
    private final CustomerRepository customerRepository;

    public CreatePasswordView(AuthenticationServices authenticationServices, 
                              UserServices userServices, CustomerRepository customerRepository) {
        this.authenticationServices = authenticationServices;
        this.userServices = userServices;
        this.customerRepository = customerRepository;
        
        addClassName("create-password-view");
        setSizeFull();
        setSpacing(false);
        setPadding(false);
        
        Div mainContainer = new Div();
        mainContainer.addClassName("homePage");
        
        Div boxOne = createBrandingBox();
        
        Div boxTwo = createPasswordFormBox();
        
        mainContainer.add(boxOne, boxTwo);
        add(mainContainer);
    }
    
    private Div createBrandingBox() {
        Div boxOne = new Div();
        boxOne.addClassName("boxOne");
        
        Div innerDiv = new Div();
        innerDiv.addClassName("innerDivMainPage");
        
        H1 heading = new H1("BrandEx – Secure Your Account");
        heading.setId("logo");
        
        Paragraph paraOne = new Paragraph(
            "Create a strong password to protect your account and enjoy a secure shopping experience."
        );
        paraOne.setId("logo_para");
        
        innerDiv.add(heading, paraOne);
        boxOne.add(innerDiv);
        
        return boxOne;
    }
    
    private Div createPasswordFormBox() {
        Div boxTwo = new Div();
        boxTwo.addClassName("boxTwo");
        
        Div innerBoxTwo = new Div();
        innerBoxTwo.addClassName("inner_div_box_two");
        
        CreatePasswordForm passwordForm = new CreatePasswordForm(authenticationServices, userServices, customerRepository);
        
        innerBoxTwo.add(passwordForm);
        boxTwo.add(innerBoxTwo);
        
        return boxTwo;
    }
}