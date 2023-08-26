package com.yekaa.modules.datamahasiswa.resources;

import com.yekaa.common.exception.DataNotFoundException;
import com.yekaa.modules.datamahasiswa.dto.DataMahasiswaRequestDTO;
import com.yekaa.modules.datamahasiswa.dto.DataMahasiswaResponseDTO;
import com.yekaa.modules.datamahasiswa.service.DataMahasiswaServiceImpl;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Optional;

// spring boot mvc
//@RestController()
//@RequestMapping("/api/v1")
@Path("/api/v1")
public class DataMahasiswaResource {

    @Inject
    DataMahasiswaServiceImpl service;

    @GET
    @Path("/healthcheck")
    public String hello() {
        return "Hello yeKaa brrtatatata";
    }

    @GET
    @Path("/getAllDataMahasiswa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDataMahasiswa() {
        List<DataMahasiswaResponseDTO> response = service.getAllDataMahasiswa();
        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/getDataMahasiswaById/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataMahasiswaById(@PathParam("id") Long id){
        Optional<DataMahasiswaResponseDTO> response = service.getDataMahasiswaById(id);
        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/getDataMahasiswaByNrp/{nrp}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataMahasiswaByNrp(@PathParam("nrp") String nrp){
        Optional<DataMahasiswaResponseDTO> response = service.getDataMahasiswaByNrp(nrp);
        return Response.ok().entity(response).build();
    }

    @POST
    @Path("/addDataMahasiswa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDataMahasiswa(@Valid DataMahasiswaRequestDTO request){
        service.createDataMahasiswa(request);
        return Response.created(URI.create("/addDataMahasiswa")).build();
    }

    @DELETE
    @Path("/deleteDataMahasiswaById/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDataMahasiswaById(@PathParam("id") Long id){
        service.deleteDataMahasiswaById(id);
        return Response.noContent().build();
    }
}
