package com.example.wishlists.security;

import com.example.wishlists.model.User;
import com.example.wishlists.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByUser(User user);

    Optional<Object> findByIdAndUser(Long id, User user);
}
