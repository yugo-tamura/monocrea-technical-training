package jp.co.monocrea.presentation.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import jp.co.monocrea.application.service.UserService;
import jp.co.monocrea.presentation.model.request.UserRequest;
import jp.co.monocrea.presentation.model.response.UserResponse;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

@Path("/users")
public class UserResource {

  @Inject UserService userService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<UserResponse> list(
      @RestQuery String condition,
      @RestQuery String keyword,
      @RestQuery String sort,
      @RestQuery String order) {

    return userService.getUserList(condition, keyword, sort, order);
  }

  @GET
  @Path("/{userId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response detail(@RestPath int userId) {

    return userService
        .getUserById(userId)
        .map(user -> Response.ok(user).build())
        .orElse(Response.status(Response.Status.NOT_FOUND).build());
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response create(UserRequest request) {

    UserResponse response = userService.createUser(request.toDomainModel());

    return Response.status(Response.Status.CREATED).entity(response).build();
  }

  @PUT
  @Path("/{userId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response update(UserRequest request) {

    UserResponse response = userService.updateUser(request.toDomainModel());

    return Response.ok(response).build();
  }

  @DELETE
  @Path("/{userId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response delete(@RestPath int userId) {

    UserResponse response = userService.deleteUser(userId);

    return Response.ok(response).build();
  }
}
