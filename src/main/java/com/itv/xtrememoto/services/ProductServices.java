package com.itv.xtrememoto.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.itv.xtrememoto.FileStorageProperties;
import com.itv.xtrememoto.dtos.RegisterProductDto;
import com.itv.xtrememoto.entities.Product;
import com.itv.xtrememoto.repositories.ProductRepository;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository repository;

    public Product registerproduct(RegisterProductDto registerproductDto) {
        Product product = new Product();
        product.setName(registerproductDto.getName());
        product.setManufature(registerproductDto.getManufature());
        product.setPrice(registerproductDto.getPrice());
        product.setDescription(registerproductDto.getDescription());
        repository.save(product);
        return product;

    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteproduct(Integer id) {
        repository.deleteById(id);
    }

    public Product updateproduct(Integer id, Product product) {
        product.setId(id);
        return repository.save(product);
    }

    public List<Product> findByName(String name) {
        return this.repository.findByName(name);
    }

    private final java.nio.file.Path rootLocation;

    public ProductServices(FileStorageProperties properties) {
        this.rootLocation = Paths.get(properties.getUploadDir());
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Upload Directory Not Created");
        }
    }

    public String uploadFile(Integer id, MultipartFile file) {

        java.nio.file.Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()));

        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "File Cannot be uploaded");
        }

        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/products/download/")
                .path(file.getOriginalFilename())
                .toUriString();

        Product product = this.repository.findById(id).get();
        product.setImageUrl(imageUrl);
        this.repository.save(product);

        return "File Uploaded Successfully";
    }

    public UrlResource downloadFile(String filename) {

        java.nio.file.Path file = rootLocation.resolve(filename);
        try {

            UrlResource resource = new UrlResource(file.toUri());

            if (((org.springframework.core.io.Resource) resource).exists()
                    || ((org.springframework.core.io.Resource) resource).isReadable()) {
                return resource;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "File Cannot be Downloaded");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
