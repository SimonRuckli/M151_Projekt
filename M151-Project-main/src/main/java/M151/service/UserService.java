package M151.service;

import M151.model.User;
import M151.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(final UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    @Caching(evict = {@CacheEvict(key = "#id"), @CacheEvict(key = "0")})
    public User update(final long id, final User user) {
        final Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User foundUser = optionalUser.get();
            foundUser.setFirstname(user.getFirstname());
            foundUser.setLastname(user.getLastname());
            foundUser.setUsername(user.getUsername());
            foundUser.setUserRole(user.getUserRole());
            return userRepo.save(foundUser);
        }
        return null;
    }
}
