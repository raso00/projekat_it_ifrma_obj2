package com.itfirma.resource;

import com.itfirma.client.ValutaClient;
import com.itfirma.obj2.CurrencyResponse;
import com.itfirma.obj2.Osoba;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/valuta")
public class ValutaResource {

    @Inject
    @RestClient
    ValutaClient valutaClient;

    @Inject
    EntityManager em;

    @GET
    @Path("/currencyConversion")
    @Produces(MediaType.APPLICATION_JSON)
   // @RolesAllowed("admin")
    @Transactional
    public Response currencyConversion(@QueryParam("from") String from, @QueryParam("to") String to, @QueryParam("value") double value, @QueryParam("userId") Long userId) {
        Osoba osoba = em.find(Osoba.class, userId);

        if (osoba == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Osoba nije pronadjena")
                    .build();
        }

        CurrencyResponse currencyResponse = valutaClient.getRate(from, to);
        System.out.println("rate: " + currencyResponse.getRate());

        currencyResponse.setValue(value);
        currencyResponse.setConvertedValue(value * currencyResponse.getRate());
        currencyResponse.setOsoba(osoba);

        em.persist(currencyResponse);

        System.out.println(currencyResponse);

        return Response.ok().entity(currencyResponse).build();
    }
}
