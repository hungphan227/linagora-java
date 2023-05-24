package com.linagora.coding.exercise;

import java.util.UUID;

import com.linagora.coding.exercise.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsersRepository {
    Mono<User> retrieveUser(UUID id);

    Flux<User> listUsers();
}
