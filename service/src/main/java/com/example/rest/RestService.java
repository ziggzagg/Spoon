package com.example.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
/**
 * @author ziggzagg
 */

// The Java class will be hosted at the URI path "hello-world"
@Path("/users")
public class RestService {

    @GET
    public Response getUser() {
        return Response.status(200).entity("getUser is called").build();
    }

    @GET
    @Path("{name}")
    public Response getUserByName(@PathParam("name") String name) {
        return Response.status(200).entity("getUserByName is called, name: "
                + name).build();
    }


    @GET
    @Path("/vip")
    public Response getUserVIP() {
        return Response.status(200).entity("getUserVIP is called").build();
    }

    @GET
    @Path("{id: \\d+}") // only support digit
    public Response getUserById(@PathParam("id") String id) {
        return Response.status(200).entity("getUserById is called, id: "
                + id).build();
    }

    @GET
    @Path("/username/{name: [a-zA-Z_0-9]*}")
    public Response getUserByUserName(@PathParam("name") String username) {
        return Response.status(200).entity("getUserByUserName is called, username:"
                + username).build();
    }


    @GET
    @Path("{year}/{month}/{day}")
    public Response getUserHistory(@PathParam("year") int year,
                                   @PathParam("month") int month,
                                   @PathParam("day") int day) {
        String date = year + "/" + month + "/" + day;

        return Response.status(200).entity("getUserHistory is called, date: "
                + date).build();
    }


    @GET
    @Path("/query")
    public Response getUsers(@QueryParam("from") int from,
                            @QueryParam("to") int to,
                            @QueryParam("orderBy") List<String> orderBy) {
        return Response.status(200).entity("getUsers is called, from: "
                + from + ", to: " + to + ", orderBy " + orderBy.toString())
                .build();
    }


    @GET
    @Path("/query")
    @Produces(MediaType.TEXT_XML)
    public Response getUsers(@Context UriInfo info) {
        String from = info.getQueryParameters().getFirst("from");
        String to = info.getQueryParameters().getFirst("to");
        List<String> orderBy = info.getQueryParameters().get("orderBy");

        return Response.status(200).entity(
                "<comments>getUsers is called</comment>"
                        + "<from>" + from + "</from>"
                        + "<to>" + to + "</to>"
                        + "<orderBy>" + orderBy.toString() + "</orderBy>"
        ).build();
    }


    @GET
    @Path("/search")
    public Response getUsersWithDefaultValue(@DefaultValue("100") @QueryParam("from") int from,
                             @DefaultValue("200") @QueryParam("to") int to,
                             @DefaultValue("name") @QueryParam("orderBy") List<String> orderBy) {
        return Response.status(200).entity(
                "getUsersWithDefaultValue is called, from: " + from
                        + ", to: " + to
                        + ", orderBy: " + orderBy.toString()
        ).build();
    }


    @GET
    @Path("/book/{year}")
    public Response getBooks(@PathParam("year") String year,
                             @MatrixParam("author") String author,
                             @MatrixParam("country") String country) {
        return Response.status(200).entity(
                "getBooks is called, year:" + year
                        + ", author: " + author
                        + ", country: " + country
        ).build();
    }
}
