package ru.Product.service;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Product.dto.ProductDto;
import ru.Product.model.Category;
import ru.Product.model.Product;
import ru.Product.repository.ProductRepository;
import ru.Product.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;

    @Test
    public void TestPurchaseItem() {
        UUID productId = UUID.fromString("22c132ca-20c7-4f1e-b164-9e27143256f8");
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Integer amountProduct = -1;
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            amountProduct = existingProduct.getQuantity();
        }
        if (amountProduct == -1) {
//            log.info("Данного продукта не существует");
            System.out.println("Данного продукта не существует");
        } else {
            productService.purchaseItem(productId, amountProduct - 1);
            productService.purchaseItem(productId, amountProduct + 1);
            productService.purchaseItem(productId, 0);
            productService.purchaseItem(productId, -1);
            Product existingProduct = optionalProduct.get();
            existingProduct.setQuantity(amountProduct);
        }
    }

}
