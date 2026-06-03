package com.itfirma.client;

import com.itfirma.obj2.CurrencyResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "valuta-api")
public interface ValutaClient {

    @GET
    @Path("/api/rates")
    CurrencyResponse getRate(@QueryParam("from") String from, @QueryParam("to") String to);
}
