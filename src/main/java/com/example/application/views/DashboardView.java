package com.example.application.views;

import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | CRM")
public class DashboardView extends VerticalLayout {
    private CrmService service;

    public DashboardView(CrmService service) {
        this.service = service;
        addClassName("Dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getContactStats(), getCompaniesChart());
    }

    private Component getContactStats() {
        Span stats = new Span(service.CountContacts() + "contacte");
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

    private Component getCompaniesChart() {
        
        return null;
    }

}
