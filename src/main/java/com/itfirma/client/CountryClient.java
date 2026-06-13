package com.itfirma.client;

import com.itfirma.obj2.Country;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@RegisterRestClient(configKey = "api-country")
public interface CountryClient {

    @GET
    @Path("/Countries")
    @Produces(MediaType.APPLICATION_JSON)
    List<Country> getCountries();
}
