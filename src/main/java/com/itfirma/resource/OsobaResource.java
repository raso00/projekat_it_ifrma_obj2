package com.itfirma.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.itfirma.obj2.Osoba;
import com.itfirma.obj2.Certifikat;
import com.itfirma.obj2.Ugovor;
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
    @RolesAllowed("admin")
    public Response getAllOsobe() {
        List<Osoba> osobe=null;
        try{
            osobe= osobaService.getAllOsobe();
        } catch (OsobaException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().entity(osobe).build();
    }

    @GET
    @Path("/getOsobaByIme")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOsobaByIme(@QueryParam("ime") String ime) {
        List<Osoba> osobe = osobaService.getOsobaByIme(ime);
        return Response.ok().entity(osobe).build();
    }

    @GET
    @Path("/getCertifikatiByOsobaId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCertifikatiByOsobaId(@QueryParam("id") Long id) {
        List<Certifikat> certifikati = osobaService.getCertifikatiByOsobaId(id);
        return Response.ok().entity(certifikati).build();
    }


}
