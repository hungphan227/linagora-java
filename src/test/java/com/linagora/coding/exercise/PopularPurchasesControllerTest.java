package com.linagora.coding.exercise;

import static org.mockito.Mockito.mock;

import com.linagora.coding.exercise.model.PopularProduct;
import com.linagora.coding.exercise.model.Product;
import com.linagora.coding.exercise.model.Purchase;
import com.linagora.coding.exercise.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

class PopularPurchasesControllerTest {

    private PopularPurchasesController purchasesController;
    private RestClientImpl restClient;

    @BeforeEach
    void setUp() {
        restClient = mock(RestClientImpl.class);
        PopularPurchasesServiceImpl popularPurchasesService = new PopularPurchasesServiceImpl(restClient);
        purchasesController = new PopularPurchasesController(popularPurchasesService);
    }

    @Test
    void testUsersWithSimilarPurchasesCaseNormal() {
        String username = "aaa";
        UUID productId1 = UUID.fromString("cc8c5dd3-d46c-4945-894d-6160f830d815");
        UUID productId2 = UUID.fromString("cc8c5dd3-d46c-4945-894d-6160f830d816");

        Purchase purchase1 = new Purchase(UUID.fromString("ac8c5dd3-d46c-4945-894d-6160f830d815"), UUID.fromString("bc8c5dd3-d46c-4945-894d-6160f830d815"),
                productId1, ZonedDateTime.now());
        Purchase purchase2 = new Purchase(UUID.fromString("ac8c5dd3-d46c-4945-894d-6160f830d816"), UUID.fromString("bc8c5dd3-d46c-4945-894d-6160f830d815"),
                productId2, ZonedDateTime.now());
        Mockito.when(restClient.getRecentPurchases(Mockito.eq(username))).thenReturn(Flux.just(purchase1, purchase2));

        User user1 = new User(UUID.fromString("bc8c5dd3-d46c-4945-894d-6160f830d815"), "aaa", "aaa@gmail.com");
        User user2 = new User(UUID.fromString("bc8c5dd3-d46c-4945-894d-6160f830d816"), "bbb", "bbb@gmail.com");
        //UUID.fromString("bc8c5dd3-d46c-4945-894d-6160f830d818")
        Mockito.when(restClient.getUsersPurchasingProduct(Mockito.eq(productId1))).thenReturn(Flux.just(user1));
        Mockito.when(restClient.getUsersPurchasingProduct(Mockito.eq(productId2))).thenReturn(Flux.just(user1, user2));

        Product product1 = new Product(productId1, "15", 3);
        Product product2 = new Product(productId2, "16", 4);
        Mockito.when(restClient.getProductById(Mockito.eq(productId1))).thenReturn(Mono.just(product1));
        Mockito.when(restClient.getProductById(Mockito.eq(productId2))).thenReturn(Mono.just(product2));

        Flux<PopularProduct> result = purchasesController.usersWithSimilarPurchases(username);
        Mono<List<PopularProduct>> mono = result.collect(Collectors.toList());
        List<PopularProduct> expected = new ArrayList<>();
        expected.add(new PopularProduct(productId2, "16", 4).setRecent(Arrays.asList("aaa", "bbb")));
        expected.add(new PopularProduct(productId1, "15", 3).setRecent(Arrays.asList("aaa")));
        mono.doOnNext(list -> {
            Assertions.assertEquals(expected, list);
        }).block();
    }

    @Test
    void testUsersWithSimilarPurchasesCaseExceptionUserNotFound() {
        String username = "aaa";
        Mockito.when(restClient.getRecentPurchases(Mockito.eq(username))).thenReturn(Flux.error(WebClientResponseException.create(HttpStatus.BAD_REQUEST.value(),null,null,null, null)));
        Flux<PopularProduct> result = purchasesController.usersWithSimilarPurchases(username);
        Mono<List<PopularProduct>> mono = result.collect(Collectors.toList());
        mono.doOnNext(list -> {
            Assertions.fail();
        }).onErrorResume(ex -> {
            Exception expected = new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with username of '" + username + "' was not found");
            if (ex instanceof ResponseStatusException) {
                Assertions.assertEquals(expected.getMessage(), ex.getMessage());
                return Mono.just(new ArrayList<>());
            }
            return Mono.error(new RuntimeException());
        }).block();
    }
}
