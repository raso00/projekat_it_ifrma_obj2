package com.itfirma.client;

import com.itfirma.obj2.TimeZoneInfo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "timezone-api")
public interface TimeZoneClient {

    @GET
    @Path("/api/time/current/ip")
    TimeZoneInfo getTimeZoneByIp(@QueryParam("ipAddress") String ipAddress);
}