package com.itfirma.resource;

import com.itfirma.client.CountryClient;
import com.itfirma.client.HolidayClient;
import com.itfirma.obj2.Country;
import com.itfirma.obj2.Holiday;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Random;

@Path("/holiday")
public class HolidayResource {

    @Inject
    @RestClient
    CountryClient countryClient;

    @Inject
    @RestClient
    HolidayClient holidayClient;

    @Inject
    EntityManager em;

    @GET
    @Path("/getRandomHolidays")
    @Produces(MediaType.APPLICATION_JSON)
   // @RolesAllowed("admin")
    @Transactional
    public Response getRandomHolidays(@QueryParam("from") String from,
                                      @QueryParam("to") String to) {
        List<Country> countries = countryClient.getCountries();
        System.out.println("Broj drzava: " + countries.size());

        Random random = new Random();
        Country randomCountry = countries.get(random.nextInt(countries.size()));
        String isoCode = randomCountry.getIsoCode();
        System.out.println("Izabrana drzava: " + isoCode);

        List<Holiday> holidays = holidayClient.getPublicHolidays(isoCode, from, to);
        System.out.println("Broj praznika: " + holidays.size());

        Country country = new Country();
        country.setIsoCode(isoCode);
        em.persist(country);

        for (Holiday holiday : holidays) {
            holiday.setCountry(country);
            em.persist(holiday);
        }

        country.setHolidays(holidays);

        return Response.ok().entity(country).build();
    }
}
