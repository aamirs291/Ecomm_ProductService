package com.scaler.ecomm_productservice.services;

import com.scaler.ecomm_productservice.dtos.FakeStoreProductDTO;
import com.scaler.ecomm_productservice.exceptions.ProductNotFoundException;
import com.scaler.ecomm_productservice.models.Category;
import com.scaler.ecomm_productservice.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {

//        throw new RuntimeException("Not implemented yet");
        throw new ProductNotFoundException(productId);
//        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
//                restTemplate.getForEntity("https://fakestoreapi.com/products/"+productId, FakeStoreProductDTO.class);
//
//        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
//
//        return convertFakeStoreProductDtoToProduct(fakeStoreProductDTO);

    }


    @Override
    public List<Product> getAllProducts() {

//        ResponseEntity<List<FakeStoreProductDTO>> responseList= restTemplate.exchange("https://fakestoreapi.com/products/", HttpMethod.GET, null, new ParameterizedTypeReference<List<FakeStoreProductDTO>>() {});

        ResponseEntity<FakeStoreProductDTO[]> responseList = restTemplate.getForEntity("https://fakestoreapi.com/products/", FakeStoreProductDTO[].class);

        FakeStoreProductDTO[] fakeStoreProductDTOList = responseList.getBody();

        List<Product> productsList = new ArrayList<>();

        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOList){
            productsList.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDTO));
        }

        return productsList;
        //return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {
//        return null;
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return List.of();
    }

    @Override
    public Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize) {
        return null;
    }

    private static Product convertFakeStoreProductDtoToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
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
