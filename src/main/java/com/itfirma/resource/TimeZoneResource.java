package com.itfirma.resource;

import com.itfirma.client.IpClient;
import com.itfirma.client.TimeZoneClient;
import com.itfirma.obj2.Osoba;
import com.itfirma.obj2.TimeZoneInfo;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/timezone")
public class TimeZoneResource {

    @Inject
    @RestClient
    IpClient ipClient;

    @Inject
    @RestClient
    TimeZoneClient timeZoneClient;

    @Inject
    EntityManager em;

    @GET
    @Path("/current")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentTimeZone() {
        String ip = ipClient.getPublicIp();
        System.out.println("ip adresa je: " + ip);

        TimeZoneInfo timeZoneInfo = timeZoneClient.getTimeZoneByIp(ip);

        System.out.println(timeZoneInfo);
        return Response.ok().entity(timeZoneInfo).build();
    }

    @GET
    @Path("/getTimezoneByIP")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getTimezoneByIP(@QueryParam("userId") Long userId) {
        Osoba osoba = em.find(Osoba.class, userId);

        if (osoba == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Osoba nije pronadjena")
                    .build();
        }

        String ip = ipClient.getPublicIp();
        System.out.println("ip: " + ip);

        TimeZoneInfo timeZoneInfo = timeZoneClient.getTimeZoneByIp(ip);
        osoba.setTimeZone(timeZoneInfo.getTimeZone());
        em.merge(osoba);

        timeZoneInfo.setOsoba(osoba);

        em.persist(timeZoneInfo);

        System.out.println(timeZoneInfo);

        return Response.ok().entity(timeZoneInfo).build();
    }
}