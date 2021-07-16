package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c.* FROM customer AS c INNER JOIN customer_pets AS cp ON cp.customer_id = c.id WHERE cp.pets_id = :id")
    Customer findAllCustomerByPetId(@Param("id") long petId);

}