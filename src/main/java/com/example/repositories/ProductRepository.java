package com.example.repositories;

import com.example.models.Product;
/* We learnt that this jackra extension allows us to connect the models with the 
general database and if not implemented then we would have to write out
raw vanila sql/postgrsql/mysql commands */
import org.springframework.data.jpa.repository.JpaRepository;;

public interface ProductRepository  extends JpaRepository <Product, Integer>{
    //It gives us a total of 10 prebuilt sql methods.
    /* USED GPT TO TELL ME WHAT THEY ARE ITS LATE, TIRED */

    /* 1. save(S entity)
    2. saveAll(Iterable<S> entities)
    3. findById(ID id)
    4. existsById(ID id)
    5. findAll()
    6. findAllById(Iterable<ID> ids)
    7. count()
    8. deleteById(ID id)
    9. delete(T entity)
    10. deleteAll() */







    // Find by name
    //List<Product> findByProductName(String productName);

    // Find by type
    //List<Product> findByProductType(Product.TypeProduct type);

    // Find by SKU
    //Optional<Product> findBySkuNumber(String skuNumber);

    // Find all products with quantity below a threshold
    //List<Product> findByProductQuantityLessThan(int quantity);

    // Find by name AND type
    //List<Product> findByProductNameAndProductType(String name, Product.TypeProduct type);
}
