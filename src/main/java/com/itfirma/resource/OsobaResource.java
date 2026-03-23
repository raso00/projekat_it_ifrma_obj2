package com.itfirma.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.itfirma.obj2.Osoba;
import com.itfirma.service.OsobaService;
import java.util.List;
import com.itfirma.exception.OsobaException;

@Path("/osoba")
public class OsobaResource {

    @Inject
    private OsobaService osobaService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addOsoba")

    public Response addOsoba(Osoba osoba) {
        try{
            osobaService.createOsoba(osoba);
        }catch (OsobaException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllOsobe")
    public Response getAllOsobe() {
        List<Osoba> osobe=null;
        try{
            osobe= osobaService.getAllOsobe();
        } catch (OsobaException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().entity(osobe).build();
    }
}
