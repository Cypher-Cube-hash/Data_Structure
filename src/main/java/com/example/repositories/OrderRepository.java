package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.models.Order;

@Repository
public interface OrderRepository extends JpaRepository <Order, String> {


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


    
}
