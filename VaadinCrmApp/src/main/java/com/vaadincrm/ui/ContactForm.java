package com.vaadincrm.ui;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import com.vaadincrm.backend.Company;
import com.vaadincrm.backend.Contact;

import java.util.List;

public class ContactForm extends FormLayout {

    TextField                firstName = new TextField("First name");
    TextField                lastName  = new TextField("Last name");
    EmailField               email     = new EmailField("Email");
    ComboBox<Contact.Status> status    = new ComboBox<>("Status");
    ComboBox<Company>        company   = new ComboBox<>("Company");

    Button          save   = new Button("Save");
    Button          delete = new Button("Delete");
    Button          close  = new Button("Cancel");
    // Other fields omitted
    Binder<Contact> binder = new BeanValidationBinder<>(Contact.class);

    Contact contact;

    public ContactForm(List<Company> companies) {
        addClassName("contact-form");

        binder.bindInstanceFields(this);
        status.setItems(Contact.Status.values());

        company.setItems(companies);
        company.setItemLabelGenerator(Company::getName);

        add(firstName,
                lastName,
                email,
                company,
                status,
                createButtonsLayout());
    }
    public void setContact(Contact contact) {
        this.contact = contact;
        binder.readBean(contact);
    }


    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);


        save.addClickListener(event->validateAndSave());
        delete.addClickListener(event->fireEvent(new DeleteEvent(this,binder.getBean())));
        close.addClickListener(event->fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(event->save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(contact);
            fireEvent(new SaveEvent(this,binder.getBean()));
        } catch (ValidationException e) {
            e.printStackTrace();
        }


    }

    // Events
    public static abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
        private Contact contact;

        protected ContactFormEvent(ContactForm source, Contact contact) {
            super(source, false);
            this.contact = contact;
        }

        public Contact getContact() {
            return contact;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(ContactForm source, Contact contact) {
            super(source, contact);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(ContactForm source, Contact contact) {
            super(source, contact);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(ContactForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
            ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}