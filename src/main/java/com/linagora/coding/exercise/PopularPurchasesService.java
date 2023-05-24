package com.linagora.coding.exercise;

import com.linagora.coding.exercise.model.PopularProduct;
import com.linagora.coding.exercise.model.Product;
import com.linagora.coding.exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PopularPurchasesService {

    private RestClient restClient;

    @Autowired
    public PopularPurchasesService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Flux<PopularProduct> findPopularPurchases(String username) {
        return restClient.getRecentPurchases(username).map(purchase -> {
            return purchase.getProductId();
        }).flatMap(productId -> {
            Flux<User> users = restClient.getUsersPurchasingProduct(productId);
            Mono<Product> product = restClient.getProductById(productId);
            Mono<List<String>> monoUsernames = users.map(user -> user.getName()).collect(Collectors.toList());
            return product.flatMap(p -> {
                return monoUsernames.map(usernames -> {
                    PopularProduct popularProduct = new PopularProduct(p.getId(), p.getName(), p.getPrice());
                    popularProduct.setRecent(usernames);
                    return popularProduct;
                });
            });
        }).sort((first, second) -> second.getRecent().size() - first.getRecent().size());
    }

}
