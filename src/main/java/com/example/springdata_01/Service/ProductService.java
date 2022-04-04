package com.example.springdata_01.Service;

import com.example.springdata_01.Domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    public Page<Product> findAll(Pageable paging);
    public void updateProduct(MultipartFile file, Product p);
    public void insertProduct(MultipartFile file, Product p);
}
