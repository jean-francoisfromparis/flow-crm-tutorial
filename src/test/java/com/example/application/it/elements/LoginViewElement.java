package com.example.application.it.elements;

import com.vaadin.flow.component.login.testbench.LoginFormElement;
import com.vaadin.flow.component.orderedlayout.testbench.VerticalLayoutElement;
import com.vaadin.testbench.annotations.Attribute;

@Attribute(name = "class", contains = "login-view")
public class LoginViewElement extends VerticalLayoutElement{

    public boolean login(String username, String password) {

                 // Recherche le formulaire de login sur la page
                 LoginFormElement form = $(LoginFormElement.class).first();

                 form.getUsernameField().setValue(username);
                 form.getPasswordField().setValue(password);
                 form.getSubmitButton().click();
                // Revoie true si le test arrive a sur une autre page
                 return !$(LoginViewElement.class).onPage().exists();
    }
}
