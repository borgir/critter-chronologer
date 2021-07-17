package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    CustomerRepository customerRepository;




    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }




    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }




    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }




    public Customer findCustomerById(long customerId) {
        Optional<Customer> c = customerRepository.findById(customerId);
        if (c.isPresent()) {
            return c.get();
        } else {
            return null;
        }
    }




    public Customer getCustomerByPet(Long petId) {
        Customer result = customerRepository.findCustomerByPetId(petId);
        return result;
    }

}
