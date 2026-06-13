package com.itfirma.client;

import com.itfirma.obj2.Holiday;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@RegisterRestClient(configKey = "api-holiday")
public interface HolidayClient {

    @GET
    @Path("/PublicHolidays")
    @Produces(MediaType.APPLICATION_JSON)
    List<Holiday> getPublicHolidays(@QueryParam("countryIsoCode") String countryIsoCode,
                                    @QueryParam("validFrom") String validFrom,
                                    @QueryParam("validTo") String validTo);
}
