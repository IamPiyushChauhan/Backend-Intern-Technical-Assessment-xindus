package com.example.wishlists.controller;

import com.example.wishlists.model.User;
import com.example.wishlists.model.WishlistItem;
import com.example.wishlists.security.WishlistItemRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;

import java.util.List;


// WishlistController class
@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    @Autowired
    private WishlistItemRepository wishlistItemRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<WishlistItem>> getUserWishlist(Authentication authentication) {
        User user = getUserFromAuthentication(authentication);
        List<WishlistItem> wishlist = wishlistItemRepository.findByUser(user);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping
    public ResponseEntity<WishlistItem> createWishlistItem(@RequestBody WishlistItem wishlistItem, Authentication authentication) {
        User user = getUserFromAuthentication(authentication);
        wishlistItem.setUser(user);
        WishlistItem savedItem = wishlistItemRepository.save(wishlistItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlistItem(@PathVariable Long id, Authentication authentication) {
        User user = getUserFromAuthentication(authentication);
        wishlistItemRepository.findByIdAndUser(id, user).ifPresent(item -> wishlistItemRepository.delete(item));
        return ResponseEntity.noContent().build();
    }

    private User getUserFromAuthentication(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}

