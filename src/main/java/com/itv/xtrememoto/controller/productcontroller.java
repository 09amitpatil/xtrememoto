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

@CrossOrigin(origins = {"*"},maxAge = 4800,allowCredentials = "false")

@RestController
public class productcontroller {

    @Autowired
    private ProductServices productServices;

    // adding data
    @PostMapping("/products")
    public ResponseEntity<?> registerMob(@RequestBody @Valid RegisterProductDto registerProductDto) {
        return new ResponseEntity<>(this.productServices.registerproduct(registerProductDto), HttpStatus.CREATED);
    }

    // retriving data
    @GetMapping("/products")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.productServices.getAll());
    }

    // searching data
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Product mobExits = this.productServices.getById(id);
        if (mobExits != null) {
            return new ResponseEntity<>(mobExits, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("mob not found", HttpStatus.NOT_FOUND);
        }
    }

    
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deletepet(@PathVariable Integer id) {
        Product mobExits = this.productServices.getById(id);
        if (mobExits != null) {
            this.productServices.deleteproduct(id);
            return new ResponseEntity<>("mob deleted sucessfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("mob not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updatemob(@PathVariable Integer id) {
        Product mobExits = this.productServices.getById(id);
        if (mobExits != null) {
            this.productServices.deleteproduct(id);
            return new ResponseEntity<>("mob update sucessfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("mob not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/searchbyname")
    public ResponseEntity<?> findByname(@RequestParam("name") String name) {
        List<Product> products = this.productServices.findByName(name);
        if (products.isEmpty()) {
            return new ResponseEntity<>("no mob exist with this  name", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(this.productServices.findByName(name), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/products/{id}/upload")
    public ResponseEntity<?> upload(@PathVariable Integer id, @RequestParam MultipartFile file) {
        System.out.println("File Uploaded");
        return ResponseEntity.ok(this.productServices.uploadFile(id, file));
    }

    @GetMapping("/products/download/{filename}")
    public ResponseEntity<?> download(@PathVariable String filename) {
        UrlResource resource = this.productServices.downloadFile(filename);
        return ResponseEntity.ok()
                .header("attachment; filename=\"" + filename + "\"")
                .body(resource);
    }
   

}