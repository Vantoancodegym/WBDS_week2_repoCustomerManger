package vantoanProject.service.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vantoanProject.exception.DuplicatePhoneException;
import vantoanProject.exception.NotFoundException;
import vantoanProject.model.Customer;
import vantoanProject.model.Province;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Page<Customer> findAll(Pageable pageable) throws NotFoundException;
    Customer save(Customer customer) throws DuplicatePhoneException;
    Customer findById(Long id) throws NotFoundException;
    void delete(Long id);
    List<Customer> findByProvince(Long province_id);
}
