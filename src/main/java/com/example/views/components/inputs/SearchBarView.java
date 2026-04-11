package com.example.views.components.inputs;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.select.Select;

@Component
public class SearchBarView extends HorizontalLayout {

    private final Select<String> categorySelection;
    private final Input searchInput;
    private final Button searchButton;

    public SearchBarView() {

        
        addClassName("header_search_container");

        HorizontalLayout inputSearch = new HorizontalLayout();
        inputSearch.addClassName("input_search");
        inputSearch.setAlignItems(Alignment.CENTER);
        inputSearch.setSpacing(true);

        Div searchBox = new Div();
        searchBox.addClassName("search_box");

        Image searchIcon = new Image("icon/search.svg", "search");
        searchIcon.setWidth("18px");
        searchIcon.setHeight("18px");

        // INPUT
        searchInput = new Input();
        searchInput.setId("searchInputBox");
        searchInput.setPlaceholder("Search products...");
        searchInput.getElement().setAttribute("type", "text");


        // BUTTON
        searchButton = new Button("Search");
        searchButton.setId("submit_search");

        // ADD TO SEARCH BOX
        searchBox.add(searchIcon, searchInput, searchButton);

        // CATEGORY SELECT
        categorySelection = new Select<>();
        categorySelection.setItems("All", "Electronics", "Clothing", "Accessories");
        categorySelection.setValue("All");

        // ADD EVERYTHING
        inputSearch.add(searchBox, categorySelection);
        add(inputSearch);
    }

    // OPTIONAL: expose values
    public String getSearchQuery() {
        return searchInput.getValue();
    }

    public String getSelectedCategory() {
        return categorySelection.getValue();
    }

    public Button getSearchButton() {
        return searchButton;
    }
}