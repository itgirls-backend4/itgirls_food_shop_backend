package ru.Product.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.Product.service.ProductService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@Component
@SessionScope
@Table(name = "carts")
public class Cart implements ProductService {
    private final Map<String, Integer> cartItems = new HashMap<>(); // Используем айди продукта как ключ и количество как значение
    private Set<Product> product;

    // Добавить продукт в корзину
    public void addProductToCart(String productUUID) {
        cartItems.put(productUUID, cartItems.getOrDefault(productUUID, 0) + 1);
    }

    // Убрать продукт из корзины
    public void removeProductFromCart(String productUUID) {
        int itemCount = cartItems.getOrDefault(productUUID, 0);
        if (itemCount > 0) {
            cartItems.put(productUUID, itemCount - 1);
        }
    }

    // Очистить корзину
    public void removeAllProductsFromCart() {
        cartItems.clear();
    }

    // Получить список всех продуктов в корзине с общей ценой
    public Map<String, Integer> getCart() {
        return cartItems;
    }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @ManyToMany
        @JoinTable(
                name = "cart_products",
                joinColumns = @JoinColumn(name = "cart_id"),
                inverseJoinColumns = @JoinColumn(name = "product_id")
        )
        private Set<Product> products = new HashSet<>();

        @OneToOne(mappedBy = "cart")
        private User user;

    public Map<Product, Integer> getCartItems() {
        return getCartItems();
    }

    public void add(Product product) {
    }
}
