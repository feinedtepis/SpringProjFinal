package com.example.demo.Service;
import com.example.demo.Repo.ProductRepo;
import com.example.demo.dto.ProductResponse;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<ProductResponse> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(new Product());
    }

    public void addProduct(String name, String description, BigDecimal price,
                            MultipartFile imageFile) throws Exception {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);


        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImage(imageFile.getBytes());
        }


        productRepo.save(product);
    }

    public boolean deleteProductById(int id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

