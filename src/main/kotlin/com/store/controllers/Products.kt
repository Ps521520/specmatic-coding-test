package com.store.controllers

import com.store.models.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import com.store.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

@RestController @RequestMapping("/products") class Products(private val productService: ProductService) {
    private val errorResponse = ErrorResponseBody(
        timestamp = LocalDateTime.now(),
        status = HttpStatus.BAD_REQUEST.value(),
        error = "Bad Request",
        path = "/products"
    )
    @GetMapping
    fun getProducts(@RequestParam(name = "type", required = false) type: String?): ResponseEntity<Any> {
        if(type != null){
            val productType = ProductType.entries.map { it.name }
            if(productType.contains(type)){
                return ResponseEntity.ok(productService.getProductsByType(ProductType.valueOf(type)))
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
        }
        return ResponseEntity.ok(productService.getAllProducts())
    }

    @PostMapping
    fun createProduct(@RequestBody details: Product): ResponseEntity<Any> {
        if(details.name.matches(Regex("^[a-zA-Z ]+$")) && details.name != "true" && details.name != "false" && details.inventory >=1 && details.inventory <= 9999){
            val productType = ProductType.entries.map { it.name }
            if(productType.contains(details.type)){
                return ResponseEntity.status(HttpStatus.CREATED).body(ProductId(productService.createProduct(details)))
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}
