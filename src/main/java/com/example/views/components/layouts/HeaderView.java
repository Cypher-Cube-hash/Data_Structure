package com.example.views.components.layouts;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.H4;

import com.example.views.components.header.HeaderOptionsView;
import com.example.views.components.inputs.SearchBarView;

@Component
public class HeaderView extends Div {

    private final Div logoSection;
    private final SearchBarView searchBar;
    private final HeaderOptionsView optionsView;

    public HeaderView() {

        addClassNames("header", "flexing");

        logoSection = new Div();
        logoSection.addClassName("header_icon_container");

        Image logo = new Image("icon/logo.svg", "logo");
        logo.setWidth("58px");
        logo.setHeight("60px");


        logoSection.add(logo);

        
        searchBar = new SearchBarView();

        
        optionsView = new HeaderOptionsView();

        
        add(logoSection, searchBar, optionsView);
    }


    
    
    
    public SearchBarView getSearchBar() {
        return searchBar;
    }

    public HeaderOptionsView getOptionsView() {
        return optionsView;
    }
}