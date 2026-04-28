package com.itfirma.schedulers;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.itfirma.service.OsobaService;

@ApplicationScoped
public class OsobaScheduler {

    @Inject
    OsobaService osobaService;

    @Scheduled(every = "10s")
    public void brojOsoba() {
        try {
            int broj = osobaService.getAllOsobe().size();
            System.out.println("Trenutni broj osoba u bazi: " + broj);
        } catch (Exception e) {
            System.out.println("Nema osoba u bazi");
        }
    }
}