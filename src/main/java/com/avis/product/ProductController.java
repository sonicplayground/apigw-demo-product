package com.avis.product;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  // sample test data
  List<Product> products = new ArrayList<>();

  @PostConstruct
  public void init() {
    products.add(new Product(1, "Product 1", 1000, 1));
    products.add(new Product(2, "Product 2", 2000, 1));
    products.add(new Product(3, "Product 3", 3000, 1));
  }

  @RequestMapping
  public List<Product> getProducts() {

    return products;
  }

  @RequestMapping("/{id}")
  public ResponseEntity<Map<String, Object>> getProduct(@PathVariable int id) {
    Product product = findProductById(id);
    if (Objects.isNull(product)) {
      return ResponseEntity.status(404)
          .body(Map.of(
              "status", "error",
              "reason", "Product not found"));
    } else {
      return ResponseEntity.ok(Map.of(
          "status", "success",
          "data", product
      ));
    }
  }

  private Product findProductById(int id) {
    return products.stream().filter(p -> (p.id == id)).findFirst().orElse(null);
  }

  /**
   * @param body { "name": "Product 4", "price": "4000" }
   * @return Created product with id
   */
  @PostMapping
  public ResponseEntity<Map<String, Object>> createProduct(
      @RequestHeader(name = "Passport-Id") int creatorId,
      @RequestBody Map<String, String> body) {
    int id = products.size() + 1;
    String name = body.get("name");
    int price = Integer.parseInt(body.get("price"));

    Product created = new Product(id, name, price, creatorId);
    products.add(created);

    return ResponseEntity.status(201)
        .body(Map.of(
            "status", "success"
            , "data", created
        ));
  }

  @Getter
  public static final class Product {

    int id;
    String name;
    int price;
    int creatorId;

    Product(int id, String name, int price, int creatorId) {
      this.id = id;
      this.name = name;
      this.price = price;
      this.creatorId = creatorId;
    }
  }
}
