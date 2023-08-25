package com.yekaa.modules.dataprs.resource;

import com.yekaa.modules.dataprs.dto.DataPrsRequestDTO;
import com.yekaa.modules.dataprs.dto.DataPrsResponseDTO;
import com.yekaa.modules.dataprs.service.DataPrsServiceImpl;
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
public class DataPrsResource {

    @Inject
    DataPrsServiceImpl service;

    @GET
    @Path("/getAllDataPrs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDataPrs(){
        List<DataPrsResponseDTO> response = service.getAllDataPrs();
        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/getDataPrsById/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataPrsById(@PathParam("id") Long id){
        try {
            Optional<DataPrsResponseDTO> response = service.getDataPrsById(id);
            return Response.ok().entity(response).build();
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(ex);
        }
    }

    @POST
    @Path("/addDataPrs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDataPrs(@Valid DataPrsRequestDTO request){
        service.createDataPrs(request);
        return Response.created(URI.create("/addDataPrs")).build();
    }

    @DELETE
    @Path("/deleteDataPrsById/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDataPrsById(@PathParam("id") Long id){
        try {
            service.deleteDataPrsById(id);
            return Response.noContent().build();
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(ex);
        }
    }
}
