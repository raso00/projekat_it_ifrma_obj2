package com.itfirma.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.itfirma.obj2.Osoba;
import com.itfirma.obj2.Certifikat;
import com.itfirma.obj2.Ugovor;
import com.itfirma.obj2.MultipartBody;
import com.itfirma.obj2.UploadedFile;
import com.itfirma.service.OsobaService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import com.itfirma.exception.OsobaException;

@Path("/osoba")
public class OsobaResource {

    @Inject
    private OsobaService osobaService;

    @Inject
    EntityManager em;

    @org.eclipse.microprofile.config.inject.ConfigProperty(name = "upload.dir")
    String uploadDir;

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

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/addImageToOsoba")
    public Response addImageToOsoba(
            @RestForm("file") FileUpload file,
            @RestForm("fileName") String fileName) {
        try {
            Files.copy(
                file.uploadedFile(),
                java.nio.file.Path.of(uploadDir + fileName),
                StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/addFileToOsoba")
    @Transactional
    public Response addFileToOsoba(
            @RestForm("file") FileUpload file,
            @RestForm("fileName") String fileName,
            @QueryParam("osobaId") Long osobaId) {

        Osoba osoba = em.find(Osoba.class, osobaId);
        if (osoba == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Osoba nije pronadjena")
                    .build();
        }

        String putanja = uploadDir + fileName;
        java.nio.file.Path destinacija = java.nio.file.Path.of(putanja);

        UploadedFile uploadedFile = new UploadedFile();

        if (java.nio.file.Files.exists(destinacija)) {
            uploadedFile.setFilename(putanja);
            em.persist(uploadedFile);
            osoba.getUploadedFiles().add(uploadedFile);
            em.merge(osoba);
            return Response.ok().entity(uploadedFile).build();
        }

        try {
            java.nio.file.Files.copy(
                file.uploadedFile(),
                destinacija,
                StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            return Response.serverError().build();
        }

        uploadedFile.setFilename(putanja);
        em.persist(uploadedFile);
        osoba.getUploadedFiles().add(uploadedFile);
        em.merge(osoba);

        return Response.ok().entity(uploadedFile).build();
    }

    @GET
    @Path("/getOsobaWithFiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOsobaWithFiles(@QueryParam("osobaId") Long osobaId) {
        Osoba osoba = em.find(Osoba.class, osobaId);

        if (osoba == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Osoba nije pronadjena")
                    .build();
        }

        for (UploadedFile uploadedFile : osoba.getUploadedFiles()) {
            uploadedFile.setFile(new java.io.File(uploadedFile.getFilename()));
        }

        return Response.ok().entity(osoba).build();
    }

}
