package com.scaler.ecomm_productservice.services;

import com.scaler.ecomm_productservice.dtos.FakeStoreProductDTO;
import com.scaler.ecomm_productservice.exceptions.CategoryNotFoundException;
import com.scaler.ecomm_productservice.exceptions.ProductNotFoundException;
import com.scaler.ecomm_productservice.models.Category;
import com.scaler.ecomm_productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakePracticeService implements ProductService{

    private RestTemplate restTemplate;

    public FakePracticeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/"+productId, FakeStoreProductDTO.class);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();

        return convertDtoToProduct(fakeStoreProductDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDTO[]> fakeStoreProductListResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] fakeStoreProductDTOArray = fakeStoreProductListResponseEntity.getBody();

        List<Product> products = new ArrayList<>();
        if (fakeStoreProductDTOArray == null){
            return null;
        }

        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOArray){
            products.add(convertDtoToProduct(fakeStoreProductDTO));
        }

        return products;
    }

    @Override
    public Product createProduct(Product product) throws CategoryNotFoundException {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return List.of();
    }

    private Product convertDtoToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        if (fakeStoreProductDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImageUrl(fakeStoreProductDTO.getImage());

        Category category = new Category();
        category.setTitle(fakeStoreProductDTO.getCategory());
        product.setCategory(category);
        return product;
    }
}
