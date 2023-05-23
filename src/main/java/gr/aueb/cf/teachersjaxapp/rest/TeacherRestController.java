package gr.aueb.cf.teachersjaxapp.rest;

import gr.aueb.cf.teachersjaxapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.teachersjaxapp.dto.TeacherDTO;
import gr.aueb.cf.teachersjaxapp.model.Teacher;
import gr.aueb.cf.teachersjaxapp.service.ITeacherService;
import gr.aueb.cf.teachersjaxapp.service.exceptions.TeacherNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

@Path("/teachers")
public class TeacherRestController {
    @Inject
    private ITeacherService teacherService;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeacherByLastname(@QueryParam("lastname") String lastname){
        try {
            List<Teacher> teachers = new ArrayList<>();
            teachers = teacherService.getTeachersByLastname(lastname);

            if (teachers.size() == 0){
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity("Bad Request")
                        .build();
            }

            return Response
                    .status(Response.Status.OK)
                    .entity(teachers)
                    .build();
        }catch (TeacherDAOException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error")
                    .build();
        }
    }

    @Path("/{teacherId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeacher(@PathParam("teacherId")int teacherId){
        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);

            if (teacher == null){
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity("Bad Request")
                        .build();
            }

            return Response
                    .status(Response.Status.OK)
                    .entity(teacher)
                    .build();
        }catch (TeacherDAOException e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error")
                    .build();
        }
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTeacher(TeacherDTO dto,@Context UriInfo uriInfo){
        try {
            Teacher teacher = teacherService.insertTeacher(dto);

            if (teacher == null){
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity("Bad Request")
                        .build();
            }
            TeacherDTO teacherDTO = new TeacherDTO(
                    teacher.getId(),
                    teacher.getFirstname(),
                    teacher.getLastname()
            );

            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();

            return Response
                    .created(uriBuilder.path(String.valueOf(teacher.getId())).build())
                    .entity(teacherDTO)
                    .build();

        }catch (TeacherDAOException e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error")
                    .build();
        }
    }

    @Path("/{teacherId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeacher(TeacherDTO teacherDTO,@PathParam("teacherId") int teacherId){
        try {
            teacherDTO.setId(teacherId);
            Teacher teacher = teacherService.updateTeacher(teacherDTO);

            TeacherDTO updatedTeacher = new TeacherDTO(
                    teacher.getId(),
                    teacher.getFirstname(),
                    teacher.getLastname()
            );

            return Response.status(Response.Status.OK)
                    .entity(updatedTeacher)
                    .build();
        }catch (TeacherDAOException | TeacherNotFoundException e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Bad Request")
                    .build();
        }
    }

    @Path("/{teacherId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTeacher(@PathParam("teacherId")int teacherId){
        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);
            teacherService.deleteTeacher(teacherId);

            TeacherDTO deletedTeacherDTO = new TeacherDTO(
                    teacher.getId(),teacher.getFirstname(),teacher.getLastname()
            );

            return Response.status(Response.Status.OK)
                    .entity(deletedTeacherDTO)
                    .build();
        }catch (TeacherDAOException e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Internal Server Error")
                    .build();
        }catch (TeacherNotFoundException e1){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Teacher Not Found")
                    .build();
        }
    }
}
