package com.app;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {

//        Button button = new Button("open dialog");
//        add(button);
//        button.addClickListener(event -> {
//            AFCommonPopupWindow af = new AFCommonPopupWindow();
//            af.open();
//        });

        RadioButtonGroup<String> radioGroup=new RadioButtonGroup<>();
        radioGroup.setLabel("Possession");
        radioGroup.setItems("Home","Away");
        radioGroup.setValue(null);
        radioGroup.setReadOnly(true);
        add(radioGroup);



//
//                Button button = new Button("click me.");
//                DatePicker datePicker = new DatePicker("pick a date");
//
//                HorizontalLayout horizonalLayout = new HorizontalLayout(button, datePicker);
//                horizonalLayout.setDefaultVerticalComponentAlignment(Alignment.END);
//                add(horizonalLayout);
//
//                button.addClickListener(clickEvent -> add(new Paragraph("clicked :"+datePicker.getValue())));


//
//        Button newbutton=new Button("click me");
//        DatePicker date=new DatePicker();
//        date.setLabel("pick a date");
//        HorizontalLayout layout = new HorizontalLayout(newbutton, date);
//        layout.setDefaultVerticalComponentAlignment(Alignment.END);
//        add(layout);
//
//        newbutton.addClickListener(event->add(new Paragraph("i got clicked "+date.getValue())));
    }
}
