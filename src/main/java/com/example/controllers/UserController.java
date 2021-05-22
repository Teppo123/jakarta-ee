package com.example.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.services.UserService;

@Path(UserController.ROOT_PATH)
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

	protected static final String ROOT_PATH = "user-controller"; // NOSONAR

	@Inject
	private UserService userService;

	// http://localhost:8080/jakartaee8-starter/api/user-controller/users
	@GET
	@Path("users")
	public Response getUsers() {
		return Response.ok(this.userService.getUsers()).build();
	}

	// http://localhost:8080/jakartaee8-starter/api/user-controller/user/11
	@GET
	@Path("user/{id}")
	public Response getUser(@PathParam("id") long id) {
		return Response.ok(this.userService.getUserById(id)).build();
	}

//	@GetMapping("/user-by-name")
//	@GET
//	@Path("user-by-name")
//	@javax.ws.rs.
//	public ResponseEntity<UserTO> getUserByName(@RequestParam(name = PARAM_FIRST_NAME) String firstName,
//			@RequestParam(name = PARAM_LAST_NAME) String lastName) {
//		return ResponseEntity.ok(this.userService.getUserByName(firstName, lastName));
//	}
//
//	@GetMapping("/user-born-before")
//	public ResponseEntity<List<UserTO>> getUsersBornBefore(@RequestParam(name = PARAM_DATE) LocalDate date) {
//		return ResponseEntity.ok(this.userService.getUsersBornBefore(date));
//	}
//
//	@PostMapping("/save-user")
//	public ResponseEntity<UserTO> saveUser(@Validated @RequestBody UserTO to) {
//		return new ResponseEntity<>(this.userService.saveUser(to), HttpStatus.CREATED);
//	}

}
