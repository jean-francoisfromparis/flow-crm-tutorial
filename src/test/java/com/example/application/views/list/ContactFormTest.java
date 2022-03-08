package com.example.application.views.list;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ContactFormTest {
    private List<Company> companies;
    private List<Status> statuses;
    private Contact bernardMartin;
    private Company company1;
    private Company company2;
    private Status status1;
    private Status status2;

    @Before
    public void setupData() {
        // object declared
        companies = new ArrayList<>();
        company1 = new Company();
        company1.setName("Martin SAS");
        company2 = new Company();
        company2.setName("Bernard IT");
        companies.add(company1);
        companies.add(company2);

        statuses = new ArrayList<>();
        status1 = new Status();
        status1.setName("Status 1");
        status2 = new Status();
        status2.setName("Status 2");
        statuses.add(status1);
        statuses.add(status2);
        //new contact object initiate
        bernardMartin = new Contact();
        bernardMartin.setFirstName("bernard");
        bernardMartin.setLastName("Martin");
        bernardMartin.setEmail("bernard@martin.com");
        bernardMartin.setStatus(status1);
        bernardMartin.setCompany(company2);
    }

    //Entity population test

    @Test
    public void formFiledsPopulated() {
        ContactForm form = new ContactForm(companies, statuses);
        form.setContact(bernardMartin);

        Assert.assertEquals("bernard", form.firstName.getValue());
        Assert.assertEquals("Martin", form.lastName.getValue());
        Assert.assertEquals("bernard@martin.com", form.email.getValue());
        Assert.assertEquals(company2, form.company.getValue());
        Assert.assertEquals(status1, form.status.getValue());
    }

    //Save in db test
    @Test
    public void saveEventHasCorrectValues() {
        ContactForm form = new ContactForm(companies, statuses);
        Contact contact = new Contact();
        form.setContact(contact);

        form.firstName.setValue("Marie");
        form.lastName.setValue("Petit");
        form.email.setValue("marie@petit.fr");
        form.company.setValue(company2);
        form.status.setValue(status2);

        AtomicReference<Contact> savedContact = new AtomicReference<>(null);
        form.addListener(ContactForm.SaveEvent.class, e -> savedContact.set(e.getContact()));

        form.save.click();

        Contact saved = savedContact.get();

        Assert.assertEquals("Marie", saved.getFirstName());
        Assert.assertEquals("Petit", saved.getLastName());
        Assert.assertEquals("marie@petit.fr", saved.getEmail());
        Assert.assertEquals(company2, saved.getCompany());
        Assert.assertEquals(status2, saved.getStatus());
    }
}