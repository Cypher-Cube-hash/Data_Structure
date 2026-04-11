package com.example.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("admin")
@PageTitle("Admin Dashboard")
public class AdminDashboard extends VerticalLayout{
    public AdminDashboard(){
        addClassName("adminDashboard");

        
    }
}
