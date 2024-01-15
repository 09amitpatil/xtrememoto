package com.itv.xtrememoto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itv.xtrememoto.dtos.RegisterProductDto;
import com.itv.xtrememoto.entities.Product;
import com.itv.xtrememoto.services.ProductServices;

import jakarta.validation.Valid;

@CrossOrigin(origins = { "*" }, maxAge = 4800, allowCredentials = "false")

@RestController
public class productcontroller {

    @Autowired
    private ProductServices productServices;

    // adding product
    @PostMapping("/products")
    public ResponseEntity<?> registerMob(@RequestBody @Valid RegisterProductDto registerProductDto) {
        return new ResponseEntity<>(this.productServices.registerproduct(registerProductDto), HttpStatus.CREATED);
    }

    // retriving product
    @GetMapping("/products")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.productServices.getAll());
    }

    // searching product
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Product productExits = this.productServices.getById(id);
        if (productExits != null) {
            return new ResponseEntity<>(productExits, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    // Delete product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteproduct(@PathVariable Integer id) {
        Product productExits = this.productServices.getById(id);
        if (productExits != null) {
            this.productServices.deleteproduct(id);
            return new ResponseEntity<>("product deleted sucessfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("product not found", HttpStatus.NOT_FOUND);
        }
    }

    // update product
    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateproduct(@PathVariable Integer id) {
        Product productExits = this.productServices.getById(id);
        if (productExits != null) {
            this.productServices.deleteproduct(id);
            return new ResponseEntity<>("product update successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("product not found", HttpStatus.NOT_FOUND);
        }
    }

    // find product
    @GetMapping("/products/searchbyname")
    public ResponseEntity<?> findByname(@RequestParam("name") String name) {
        List<Product> products = this.productServices.findByName(name);
        if (products.isEmpty()) {
            return new ResponseEntity<>("no product exist with this  name", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(this.productServices.findByName(name), HttpStatus.NOT_FOUND);
        }
    }

    // upload file
    @PutMapping("/products/{id}/upload")
    public ResponseEntity<?> upload(@PathVariable Integer id, @RequestParam MultipartFile file) {
        System.out.println("File Uploaded");
        return ResponseEntity.ok(this.productServices.uploadFile(id, file));
    }
    //download file
    @GetMapping("/products/download/{filename}")
    public ResponseEntity<?> download(@PathVariable String filename) {
        UrlResource resource = this.productServices.downloadFile(filename);
        return ResponseEntity.ok()
                .header("attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

}