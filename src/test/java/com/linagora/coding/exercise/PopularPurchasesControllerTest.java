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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

class PopularPurchasesControllerTest {

    private PopularPurchasesController purchasesController;
    private RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = mock(RestClient.class);
        PopularPurchasesService popularPurchasesService = new PopularPurchasesService(restClient);
        purchasesController = new PopularPurchasesController(popularPurchasesService);
    }

    @Test
    void test() {
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
        Mockito.when(restClient.getUsersPurchasingProduct(Mockito.eq(productId1))).thenReturn(Flux.just(user1));
        Mockito.when(restClient.getUsersPurchasingProduct(Mockito.eq(productId2))).thenReturn(Flux.just(user1, user2));

        Product product1 = new Product(productId1, "15", 3);
        Product product2 = new Product(productId1, "16", 4);
        Mockito.when(restClient.getProductById(Mockito.eq(productId1))).thenReturn(Mono.just(product1));
        Mockito.when(restClient.getProductById(Mockito.eq(productId2))).thenReturn(Mono.just(product2));

        Flux<PopularProduct> result = purchasesController.usersWithSimilarPurchases(username);
        Mono<List<PopularProduct>> l = result.collect(Collectors.toList());
        l.doOnNext(list -> {
            Assertions.assertEquals(product2, list.get(0).getId());
            Assertions.assertEquals(product1, list.get(1).getId());
        });
    }
}
