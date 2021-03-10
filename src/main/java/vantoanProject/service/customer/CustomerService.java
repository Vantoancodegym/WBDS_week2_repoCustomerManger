package vantoanProject.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vantoanProject.model.Customer;
import vantoanProject.model.Province;
import vantoanProject.repository.CustomerRepository;

import java.util.List;

@Service("cus")
public class CustomerService implements ICustomerService{
    public CustomerService() {
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        customerRepository.delete(id);

    }

    @Override
    public List<Customer> findByProvince(Province province) {
        return province.getCustomers();
    }
}
