package com.linagora.coding.exercise;

import com.linagora.coding.exercise.model.PopularPurchase;
import com.linagora.coding.exercise.model.Product;
import com.linagora.coding.exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class PopularPurchasesService {

    private RestClient restClient;

    @Autowired
    public PopularPurchasesService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Flux<Product> findPopularPurchases(String username) {
        return restClient.getRecentPurchases(username).map(purchase -> {
            return purchase.getProductId();
//            return new PopularPurchase(null);
        }).flatMap(productId -> {
            Flux<User> users = restClient.getUsersPurchasingProduct(productId);
            Flux<Product> product = Flux.from(restClient.getProductById(productId));
            return Flux.zip(users, product).;
        }).map(pa -> {
             List<User> users = pa.getT1();
        });
//        return Flux.just(new PopularPurchase(null));
    }

}
