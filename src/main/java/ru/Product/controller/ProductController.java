package ru.Product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.Product.dto.CategoryDto;
import ru.Product.dto.ProductCreateDto;
import ru.Product.dto.ProductDto;
import ru.Product.dto.ProductUpdateDto;
import ru.Product.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@SecurityRequirement(name = "Продукты")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getAllProducts")
    @Operation(summary = "Получить список всех продуктов")
    public List<ProductDto> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/getOneProduct")
    @Operation(summary = "Получить продукт по id")
    public ProductDto getOneProduct(
            @Parameter(description = "id продукта", required = true) @RequestParam String productId) {
        return productService.getOne(UUID.fromString(productId));
    }

    @GetMapping("/getAllProductsFromOneCategory")
    @Operation(summary = "Получить все продукты из одной категории")
    public List<ProductDto> getAllProductsFromOneCategory(
            @Parameter(description = "id категории", required = true) @RequestParam String id) {
        return productService.getAllFromOneCategory(UUID.fromString(id));
    }

    @GetMapping("/getProductsInStock")
    @Operation(summary = "Получить количество продуктов в наличии")
    public Map<UUID, Integer> getProductsInStock(
            @Parameter(description = "Список id продукта", required = true) @RequestParam(value = "id продукта") List<UUID> productIds) {
        return productService.getProductsInStock(productIds);
    }

    @PostMapping("/create")
    @Operation(summary = "Создание нового продукта")
    public ResponseEntity createProduct(@RequestBody @Valid ProductCreateDto productCreateDto) {
        try {
            ProductDto createdProduct = productService.createProduct(productCreateDto);
            return ResponseEntity.ok(createdProduct);
        } catch (EntityExistsException e) {
            return ResponseEntity.badRequest().body("Такой продукт уже существует");
        }
        catch (NotFoundException e) {
            return ResponseEntity.badRequest().body("Такой категории не существует");
        }
    }

    @PutMapping("/update")
    @Operation(summary = "Обновление продукта")
    public ResponseEntity updateProduct(@RequestBody @Valid ProductUpdateDto productUpdateDto) {
        UUID productId = productUpdateDto.getId();
        try {
            ProductDto updatedProduct = productService.updateProduct(productId, productUpdateDto);
            return ResponseEntity.ok(updatedProduct);
        }
        catch (EntityExistsException e) {
            return ResponseEntity.badRequest().body("Такой продукт уже существует");
        }
        catch (NotFoundException e) {
            return ResponseEntity.badRequest().body("Такой категории не существует");
        }
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Удаление продукта")
    public void deleteProduct(@RequestBody ProductDto productDto) {
        UUID productId = productDto.getId();
        productService.deleteProduct(productId);
    }
}
