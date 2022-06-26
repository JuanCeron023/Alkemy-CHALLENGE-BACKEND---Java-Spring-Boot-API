package com.example.disney.user;


import com.example.disney.auth.UserAuthenticationService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
final class UUIDAuthenticationService implements UserAuthenticationService {
    @NonNull
    UserCrudService users;

    @Override
    public Optional<String> login(final String username, final String password) {

   /*     final User user = User
                .builder()
                .id(uuid)
                .username(username)
                .password(password)
                .build();
*/
        Optional<User> userFind = users.findByUsername(username);
        AtomicReference<Boolean> abc= new AtomicReference<>(false);
        AtomicReference<String> uuid = new AtomicReference<>("");
        userFind.ifPresent(usertem ->
        {if(password.equals(usertem.getPassword()))
            {
               abc.set(true);
               uuid.set(usertem.getId());
            }
        });

        //users.save(user);
        if(abc.get()){
            return Optional.of(uuid.get());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByToken(final String token) {
        return users.find(token);
    }

    @Override
    public void logout(final User user) {

    }
}
