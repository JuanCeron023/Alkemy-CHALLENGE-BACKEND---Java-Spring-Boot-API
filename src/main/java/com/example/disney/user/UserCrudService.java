package com.example.disney.user;



import java.util.Optional;

/**
 * User security operations like login and logout, and CRUD operations on {@link User}.
 */
public interface UserCrudService {

    User save(User user);

    Optional<User> find(String id);

    Optional<User> findByUsername(String username);
}