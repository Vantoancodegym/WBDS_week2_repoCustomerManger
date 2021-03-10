package vantoanProject.service.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vantoanProject.model.Customer;
import vantoanProject.model.Province;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Page<Customer> findAll(Pageable pageable);
    Customer save(Customer customer);
    Customer findById(Long id);
    void delete(Long id);
    List<Customer> findByProvince(Long province_id);
}
