package com.app;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {

        Button button = new Button("click me.");
        DatePicker datePicker = new DatePicker("pick a date");

        HorizontalLayout horizonalLayout = new HorizontalLayout(button, datePicker);
        horizonalLayout.setDefaultVerticalComponentAlignment(Alignment.END);
        add(horizonalLayout);

        button.addClickListener(clickEvent -> add(new Paragraph("clicked :"+datePicker.getValue())));
    }
}
