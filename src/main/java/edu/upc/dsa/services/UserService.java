package edu.upc.dsa.services;


import edu.upc.dsa.UserManager;
import edu.upc.dsa.UserManagerImpl;

import edu.upc.dsa.models.Consult;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.LogInParams;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/users", description = "Endpoint to user Service")
@Path("/users")
public class UserService {

    private UserManager um;

    public UserService() {
        this.um = UserManagerImpl.getInstance();
        um.addUser(new User("admin","admin@admin","admin"));
    }

/*
    @GET
    @ApiOperation(value = "get all Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Track.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks() {

        List<Track> tracks = this.tm.findAll();

        GenericEntity<List<Track>> entity = new GenericEntity<List<Track>>(tracks) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @DELETE
    @ApiOperation(value = "delete a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    public Response deleteTrack(@PathParam("id") String id) {
        Track t = this.tm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteTrack(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateTrack(Track track) {

        Track t = this.tm.updateTrack(track);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }

*/

    @POST
    @ApiOperation(value = "create a new user", notes = "xd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 404, message = "Username is already taken")

    })

    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {

        if (user.getUsername().equals("") || user.getEmail().equals("") || user.getPassword().equals(""))
        {
            return Response.status(404).entity(user).build();
        }

        User checking = this.um.addUser(user);
        if (checking != null)
        {
            return Response.status(201).entity(user).build();
        }
        else
        {
            return Response.status(404).entity(user).build();
        }
    }



    @GET
    @ApiOperation(value = "get all Objects in Catalogo", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/catalogo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjects() {

        List<Item> items = this.um.catalogoTienda();

        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @POST
    @ApiOperation(value = "log in user", notes = "xd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 404, message = "Incorrect username or password")

    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logIn(LogInParams loginpar) {

        //System.out.println("PARAMETROS "+loginpar.getUsername()+" ===> "+loginpar.getPassword());
            User u = this.um.login(loginpar.getUsername(), loginpar.getPassword());

            if (u!= null) {
                return Response.status(201).entity(u).build();
            }
            else {

                //System.out.println("Usuario o contraseña incorrectos");
                return Response.status(404).build();

            }
    }

    @POST
    @ApiOperation(value = "consulta", notes = "minim 2 Gilbert")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Consult.class, responseContainer="List"),


    })

    @Path("/question")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response question(Consult consult) {

        List<Consult> lquestions = this.um.questions(consult);
        GenericEntity<List<Consult>> entity = new GenericEntity<List<Consult>>(lquestions) {};
        return Response.status(201).entity(entity).build()  ;

    }



}