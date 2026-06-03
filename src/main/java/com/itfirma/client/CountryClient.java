package com.itfirma.client;

import com.itfirma.obj2.Country;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@RegisterRestClient(configKey = "country-api")
public interface CountryClient {

    @GET
    @Path("/api/countries")
    Country getCountryByCode(@QueryParam("code") String code);
}
