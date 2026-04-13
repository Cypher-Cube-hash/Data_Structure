package com.example.views.components.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AdminOrderView extends VerticalLayout {

    public AdminOrderView() {
        addClassName("admin-section");
        setPadding(false);
        setSpacing(false);

        add(buildHeader());
        add(buildMetrics());
        add(buildQueueNote());
    }

    private HorizontalLayout buildHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.setAlignItems(Alignment.CENTER);
        header.addClassName("admin-section-header");

        Div titles = new Div();
        Div title = new Div();
        title.addClassName("admin-section-title");
        title.setText("Order queue");
        Div subtitle = new Div();
        subtitle.addClassName("admin-section-subtitle");
        subtitle.setText("Process orders in the order received — FIFO");
        titles.add(title, subtitle);

        Button processBtn = new Button("Process next order");
        processBtn.addClassName("admin-add-btn");
        processBtn.addClickListener(e -> processNextOrder());

        header.add(titles, processBtn);
        return header;
    }

    private HorizontalLayout buildMetrics() {
        HorizontalLayout row = new HorizontalLayout();
        row.addClassName("admin-metrics-row");
        row.setWidthFull();
        row.add(
            metricCard("Pending orders", "0"),
            metricCard("Processed today", "0"),
            metricCard("Next in queue", "—")
        );
        return row;
    }

    private Div metricCard(String label, String value) {
        Div card = new Div();
        card.addClassName("admin-metric-card");
        Div l = new Div(); l.addClassName("metric-label"); l.setText(label);
        Div v = new Div(); v.addClassName("metric-value"); v.setText(value);
        card.add(l, v);
        return card;
    }

    private Div buildQueueNote() {
        Div note = new Div();
        note.addClassName("admin-queue-note");
        note.setText(
            "Order queue is ready to connect. " +
            "Once your OrderQueue data structure is built, " +
            "inject it here and call queue.enqueue() at checkout " +
            "and queue.dequeue() on 'Process next order'."
        );
        return note;
    }

    private void processNextOrder() {
        
    }
}