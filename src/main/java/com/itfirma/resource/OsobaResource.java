package com.itfirma.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.itfirma.obj2.Osoba;
import com.itfirma.service.OsobaService;
import java.util.List;

@Path("/osoba")
public class OsobaResource {

    @Inject
    private OsobaService osobaService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addOsoba")
    public String addOsoba(Osoba osoba) {
        osobaService.createOsoba(osoba);
        return "Osoba uspjesno sacuvana";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllOsobe")
    public Response getAllOsobe() {
        List<Osoba> osobe = osobaService.getAllOsobe();
        return Response.ok().entity(osobe).build();
    }
}
