package com.yekaa.modules.datamatakuliah.resource;

import com.yekaa.modules.datamatakuliah.dto.DataMataKuliahRequestDTO;
import com.yekaa.modules.datamatakuliah.dto.DataMataKuliahResponseDTO;
import com.yekaa.modules.datamatakuliah.service.DataMataKuliahServiceImpl;
import com.yekaa.modules.datamatakuliah.service.DataMataKuliahServiceImpl;
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
public class DataMataKuliahResource {

    @Inject
    DataMataKuliahServiceImpl service;

    @GET
    @Path("/getAllDataMataKuliah")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDataMataKuliah(){
        List<DataMataKuliahResponseDTO> response = service.getAllDataMataKuliah();
        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/getDataMataKuliahById/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataMataKuliahById(@PathParam("id") Long id){
        try {
            Optional<DataMataKuliahResponseDTO> response = service.getDataMataKuliahById(id);
            return Response.ok().entity(response).build();
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    @POST
    @Path("/addDataMataKuliah")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDataMataKuliah(@Valid DataMataKuliahRequestDTO request){
        service.createDataMataKuliah(request);
        return Response.created(URI.create("/addDataMataKuliah")).build();
    }

    @DELETE
    @Path("/deleteDataMataKuliahById/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDataMataKuliahById(@PathParam("id") Long id){
        try {
            service.deleteDataMataKuliahById(id);
            return Response.noContent().build();
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }
}
