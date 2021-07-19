package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    PetService petService;


    @Autowired
    EmployeeService employeeService;


    @Autowired
    CustomerService customerService;




    /**
     *
     * @param customerDTO
     * @return
     */
    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {

        Customer customer = convertDTOToCustomer(customerDTO);
        customer = customerService.saveCustomer(customer);
        return convertCustomerToCustomerDTO((customer));

    }




    /**
     *
     * @param customerDTO
     * @return
     */
    private Customer convertDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        List<Long> _pets = customerDTO.getPetIds();

        List<Pet> pets = new ArrayList<>();
        if(_pets != null && _pets.size() > 0){
            for(Long id : _pets){
                pets.add(petService.getPet(id));
            }
        }
        customer.setPets(pets);
        return customer;
    }




    /**
     *
     * @param customer
     * @return
     */
    private CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        List<Pet> _pets = customer.getPets();

        List<Long> pets = new ArrayList<>();
        if(_pets.size() > 0){
            for(Pet p : _pets){
                Long id = p.getId();
                pets.add(id);
            }
        }
        customerDTO.setPetIds(pets);
        return customerDTO;
    }




    /**
     *
     * @return
     */
    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        if(customerList.size() > 0){
            for(Customer customer : customerList){
                CustomerDTO dto = convertCustomerToCustomerDTO(customer);
                customerDTOList.add(dto);
            }
        }
        return customerDTOList;
    }




    /**
     *
     * @param petId
     * @return
     */
    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {

        Pet pet = petService.getPet(petId);
        Customer customer = pet.getCustomer();

        return convertCustomerToCustomerDTO(customer);

     
    }




    /**
     *
     * @param employeeDTO
     * @return
     */
    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
        employee = employeeService.saveEmployee(employee);
        return convertEmployeeToEmployeeDTO(employee);
    }




    /**
     *
     * @param employee
     * @return employeeDTO
     */
    private EmployeeDTO convertEmployeeToEmployeeDTO (Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }




    /**
     *
     * @param employeeDTO
     * @return employee
     */
    private Employee convertEmployeeDTOToEmployee (EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }




    /**
     *
     * @param employeeId
     * @return
     */
    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        return convertEmployeeToEmployeeDTO(employee);
    }




    /**
     *
     * @param daysAvailable
     * @param employeeId
     */
    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setEmployeeAvailability(daysAvailable, employeeId);
    }




    /**
     *
     * @param employeeDTO
     * @return
     */
    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        List<Employee> employeeList = employeeService.getAvailableEmployees(skills, dayOfWeek);

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        if(employeeList.size() > 0) {
            for(Employee e : employeeList){
                EmployeeDTO _employeeDTO = convertEmployeeToEmployeeDTO(e);
                employeeDTOList.add(_employeeDTO);
            }
        }
        return employeeDTOList;
    }

}
