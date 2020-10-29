package com.vaadincrm.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadincrm.backend.Company;
import com.vaadincrm.backend.Contact;
import com.vaadincrm.backend.service.CompanyService;
import com.vaadincrm.backend.service.ContactService;

@Route
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {

    Grid<Contact> grid       = new Grid<>(Contact.class);
    TextField     filterText = new TextField();
    ContactForm   form;

    private ContactService contactService;
    private CompanyService companyService;

    public MainView(ContactService contactService, CompanyService companyService) {
        this.contactService = contactService;
        this.companyService = companyService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        getToolBar();

        form = new ContactForm(companyService.findAll());

        form.addListener(ContactForm.SaveEvent.class, this::saveContact);
        form.addListener(ContactForm.DeleteEvent.class, this::deleteContact);
        form.addListener(ContactForm.CloseEvent.class, event -> closeEditor());

        Div content = new Div(grid, form);
        content.setSizeFull();
        content.addClassName("content");

        add(getToolBar(), content);
        updateList();
        closeEditor();

    }

    private void deleteContact(ContactForm.DeleteEvent event) {
        contactService.delete(event.getContact());
        updateList();
        closeEditor();
    }

    private void saveContact(ContactForm.SaveEvent event) {

        contactService.save(event.getContact());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing");

    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("search...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(event -> updateList());

        Button addContactBtn = new Button("add contact",click->addContact());

        HorizontalLayout toolbarLayout = new HorizontalLayout(filterText, addContactBtn);
        toolbarLayout.setClassName("toolbar");
        return toolbarLayout;
    }

    private void addContact() {
        grid.asSingleSelect().clear();
        editContact(new Contact());

    }

    private void updateList() {

        grid.setItems(contactService.findAll(filterText.getValue()));

    }

    private void configureGrid() {

        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("company");
        grid.setColumns("firstName", "lastName", "email", "status");
        grid.addColumn(contact -> {
            Company company = contact.getCompany();
            return company == null ? "-" : company.getName();
        }).setHeader("Company");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> editContact(event.getValue()));

    }

    private void editContact(Contact contactSelected) {

        if (contactSelected == null) {
            closeEditor();
        } else {
            form.setContact(contactSelected);
            form.setVisible(true);
            addClassName("editing");
        }
    }

}
