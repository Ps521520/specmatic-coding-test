package com.store.services

import com.store.models.Product
import com.store.models.ProductType
import java.util.concurrent.atomic.AtomicInteger
import org.springframework.stereotype.Service

@Service
class ProductService {
    private val products = mutableMapOf<Int, Product>()
    private val idGenerator = AtomicInteger(1)

    fun getProductsByType(type: ProductType): List<Product> {
        return products.values.filter { it.type == type.toString() }
    }

    fun getAllProducts(): List<Product> {
        return products.values.toList();
    }

    fun createProduct(details: Product): Int {
        val id = idGenerator.getAndIncrement()
        val product = Product(id, details.name, details.type, details.inventory, details.cost)
        products[id] = product
        return id
    }
}
