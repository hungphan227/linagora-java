package com.linagora.coding.exercise;

import com.linagora.coding.exercise.model.PopularProduct;
import reactor.core.publisher.Flux;

public interface PopularPurchasesService {

    Flux<PopularProduct> findPopularPurchases(String username);

}
