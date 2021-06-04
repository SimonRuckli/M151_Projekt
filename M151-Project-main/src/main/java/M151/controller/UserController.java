package M151.controller;

import M151.model.User;
import M151.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/users")
@PreAuthorize("hasAuthority('User') or hasAuthority('Admin') or hasAuthority('Meetingroom')")
public class UserController {
    private final UserRepo userService;

    @Autowired
    public UserController(final UserRepo userRepo) {
        this.userService = userRepo;
    }

    @GetMapping("/info")
    public Authentication getInfo() {
        final SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication();
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('Admin')")
    public List<User> getAll() {
        final Iterable<User> users = userService.findAll();
        return StreamSupport
                .stream(users.spliterator(), false)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('Admin')")
    public User add(@RequestBody final User user) {
        return userService.save(user);
    }
}
