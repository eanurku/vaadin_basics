package com.app;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AFCommonPopupWindow extends Dialog {

    public AFCommonPopupWindow() {

        getElement().getStyle().set("border-radious", "var(--lumo-border-radius-l:0.5em)");

        VerticalLayout content = new VerticalLayout();

        content.setMargin(false);
        setWidth("500px");
        setHeight("500px");
        setModal(true);


        final VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setMargin(false);

        Label first=new Label("Home - (Popup for yards and downs +    name of Happening)");
        first.setWidth("300px");

        VerticalLayout header =
                new VerticalLayout(first);
        header.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        layout.add(header);
        layout.add(content);
        add(layout);
    }

}
