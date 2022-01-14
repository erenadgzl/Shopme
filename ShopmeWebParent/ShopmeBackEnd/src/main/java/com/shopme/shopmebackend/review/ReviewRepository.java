package com.shopme.shopmebackend.review;

import com.shopme.shopmebackend.paging.SearchRepository;
import com.shopme.shopmecommon.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends SearchRepository<Review, Integer> {

    @Query("SELECT r FROM Review r WHERE r.headline LIKE %?1% OR "
            + "r.comment LIKE %?1% OR r.product.name LIKE %?1% OR "
            + "CONCAT(r.customer.firstName, ' ', r.customer.lastName) LIKE %?1%")
    Page<Review> findAll(String keyword, Pageable pageable);

    List<Review> findAll();
}