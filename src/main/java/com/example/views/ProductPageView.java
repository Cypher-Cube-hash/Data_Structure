package com.example.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.views.components.layouts.*;
import com.example.views.components.card.*;
import com.example.views.components.layouts.HeaderView;



@Route("product-page")
@PageTitle("Products")
@CssImport("./styles/styles.css")
public class ProductPageView extends Section
{

    private final HeaderView productHeader;
    private final Card cardComponent;

    public ProductPageView(){
        addClassName("productPage");
        productHeader = new HeaderView();
        cardComponent = new Card();

        Div productDisplay = new Div();
        productDisplay.addClassName("productDisplay");
        productDisplay.add(cardComponent);

        add(productHeader);
        add(productDisplay);

        

        
    }
    
    
}
