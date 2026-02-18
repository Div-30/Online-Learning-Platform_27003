package auca.ac.rw.restfullApiAssignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.rw.restfullApiAssignment.modal.Product;
import auca.ac.rw.restfullApiAssignment.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public String saveProduct(Product product) {

        Optional<Product> checkProduct = productRepo.findById(product.getId());

        if (checkProduct.isPresent()) {
            return "Product with id " + product.getId() + " already exists.";
        } else {
            productRepo.save(product);
            return "Product saved successfully.";
        }
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public String updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepo.findById(id);

        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setCategory(updatedProduct.getCategory());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            productRepo.save(product);
            return "Product updated successfully.";
        } else {
            return "Product with id " + id + " not found.";
        }
    }

    public String deleteProduct(Long id) {
        Optional<Product> existingProduct = productRepo.findById(id);

        if (existingProduct.isPresent()) {
            productRepo.deleteById(id);
            return "Product deleted successfully.";
        } else {
            return "Product with id " + id + " not found.";
        }
    }

}
