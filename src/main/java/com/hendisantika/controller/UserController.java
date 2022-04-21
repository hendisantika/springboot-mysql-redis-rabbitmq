package com.hendisantika.controller;

import com.hendisantika.entity.User;
import com.hendisantika.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-mysql-redis-rabbitmq
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/04/22
 * Time: 07.00
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "API to manage User Controller")
public class UserController extends AbstractRestHandler {

    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get a single user.",
            description = "Get a single user. You have to provide a valid user ID.",
            tags = {"Users"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            User.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not Authorized", responseCode = "401",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public User findOneUser(@Parameter(name = "The ID of the user.", required = true) @PathVariable("id") Long id) {
        log.info("User UserController {}", userService.findUserById(id));
        return userService.findUserById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get a paginated list of all users.",
            description = "The list is paginated. You can provide a page number (default 0) and a page size (default " +
                    "100)",
            tags = {"Users"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            User.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not Authorized", responseCode = "401",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public @ResponseBody
    Page<User> getAllUsers(@Parameter(name = "The page number (zero-based)", required = true)
                           @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUM) Integer page,
                           @Parameter(name = "Tha page size", required = true)
                           @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping("/me")
    @Operation(
            summary = "Get User Profile",
            description = "Get User Profile",
            tags = {"Users"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            User.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not Authorized", responseCode = "401",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .collect(toList())
        );
        return ok(model);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a user resource.",
            description = "Returns the URL of the new resource in the Location header.",
            tags = {"Users"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            User.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not Authorized", responseCode = "401",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public void createUser(@RequestBody User user,
                           HttpServletRequest request, HttpServletResponse response) {
        final User createUser = userService.saveUser(user);
        response.setHeader("Location", request.getRequestURL().append("/").append(createUser.getId()).toString());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Update a user resource.",
            description = "You have to provide a valid user ID in the URL and in the payload. The ID attribute can " +
                    "not be updated.",
            tags = {"Users"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            User.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not Authorized", responseCode = "401",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public void modifyUser(@Parameter(name = "The ID of the existing user resource.", required = true) @PathVariable(
            "id") Long id, @RequestBody User user) {
        checkResourceFound(this.userService.findUserById(id));
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a user resource.",
            description = "You have to provide a valid user ID in the URL. Once deleted the resource can not be " +
                    "recovered.",
            tags = {"Users"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            User.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not Authorized", responseCode = "401",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public void deleteUser(@Parameter(name = "The ID of the existing user resource.", required = true) @PathVariable(
            "id") Long id) {
        checkResourceFound(this.userService.findUserById(id));
        userService.deleteUser(id);
    }
}
