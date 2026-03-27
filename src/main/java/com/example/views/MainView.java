package com.example.views;

import com.example.views.components.forms.RegisterForm;
import com.example.views.components.forms.SignInForm;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.services.RegistrationServices;
import com.example.repositories.AuthenticationRepository;
import com.example.repositories.TemporaryPasswordRepository;

@Route("")
@PageTitle("Brand-Ex")
public class MainView extends VerticalLayout {

    private Div signInTab;
    private Div registerTab;
    private Div rowTwoDiv;
    private final RegistrationServices registrationServices;
    private final AuthenticationRepository authRepository;
    private final TemporaryPasswordRepository tempPassRepository;
    

    public MainView(RegistrationServices registrationServices, AuthenticationRepository authRepository,
                    TemporaryPasswordRepository tempPassRepository) {
        this.registrationServices = registrationServices; 
        this.authRepository = authRepository;
        this.tempPassRepository = tempPassRepository;
        addClassName("homePage");


        // ─── Box One (left) ──────────────────────────────
        Div boxOne = new Div();
        boxOne.addClassName("boxOne");

        Div innerDiv = new Div();
        innerDiv.addClassName("innerDivMainPage");

        H1 heading = new H1("BrandEx – Where Shopping Comes Alive");
        heading.setId("logo");

        Paragraph paraOne = new Paragraph(
            "Discover exclusive deals, trendsetting products, and a shopping experience tailored just for you."
        );
        paraOne.setId("logo_para");

        innerDiv.add(heading, paraOne);
        boxOne.add(innerDiv);

        // ─── Box Two (right) ─────────────────────────────
        Div boxTwo = new Div();
        boxTwo.addClassName("boxTwo");

        Div innerBoxTwo = new Div();
        innerBoxTwo.addClassName("inner_div_box_two");

        // Tabs
        Div tabs = buildTabs();

        // Form container — starts with Sign In
        rowTwoDiv = new Div();
        rowTwoDiv.addClassName("form-container");
        showSignIn();

        innerBoxTwo.add(tabs, rowTwoDiv);
        boxTwo.add(innerBoxTwo);

        add(boxOne, boxTwo);
    }

    // ─── Tab Builder ─────────────────────────────────────
    private Div buildTabs() {
        Div tabsContainer = new Div();
        tabsContainer.addClassName("tabs-container");

        signInTab = new Div();
        signInTab.addClassNames("tab", "active-tab");
        signInTab.setText("SIGN IN");
        signInTab.addClickListener(e -> showSignIn());

        registerTab = new Div();
        registerTab.addClassName("tab");
        registerTab.setText("REGISTER");
        registerTab.addClickListener(e -> showRegister());

        tabsContainer.add(signInTab, registerTab);
        return tabsContainer;
    }

    // ─── Tab Switchers ────────────────────────────────────
    private void showSignIn() {
        signInTab.addClassName("active-tab");
        registerTab.removeClassName("active-tab");
        rowTwoDiv.removeAll();
        rowTwoDiv.add(new SignInForm(authRepository, tempPassRepository));  
    }

    private void showRegister() {
        registerTab.addClassName("active-tab");
        signInTab.removeClassName("active-tab");
        rowTwoDiv.removeAll();
        rowTwoDiv.add(new RegisterForm(registrationServices));
    }
}