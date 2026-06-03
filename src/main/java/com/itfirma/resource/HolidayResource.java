package com.itfirma.resource;

import com.itfirma.client.CountryClient;
import com.itfirma.client.HolidayClient;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path( "/holiday")
public class HolidayResource {

    @Inject
    @RestClient
    CountryClient countryClient;

    @Inject
    @RestClient
    HolidayClient holidayClient;

    @Inject
    EntityManager em;



}
