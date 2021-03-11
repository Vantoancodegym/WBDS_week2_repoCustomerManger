package vantoanProject.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vantoanProject.exception.DuplicatePhoneException;
import vantoanProject.exception.NotFoundException;
import vantoanProject.model.Customer;
import vantoanProject.model.Province;
import vantoanProject.repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service("cus")
public class CustomerService implements ICustomerService{
    public CustomerService() {
    }

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) throws NotFoundException {
        Page<Customer> customers;
        customers= customerRepository.findAllQuery(pageable);
        if (customers.getSize()==0) throw new NotFoundException();
        else return customers;
    }

    @Override
    public Customer save(Customer customer) throws DuplicatePhoneException {
        try {
            return customerRepository.save(customer);
        }catch (DataIntegrityViolationException e){
            throw new DuplicatePhoneException();
        }
    }

    @Override
    public Customer findById(Long id) throws NotFoundException {
        Customer customer= customerRepository.findOne(id);
        if (customer==null) throw new NotFoundException();
        else return customer;
    }

    @Override
    public void delete(Long id) {
        customerRepository.delete(id);

    }

    @Override
    public List<Customer> findByProvince(Long province_id) {
        String queryStr="SELECT c FROM Customer AS c WHERE c.province.id =:id";
        TypedQuery<Customer> query= entityManager.createQuery(queryStr,Customer.class);
        query.setParameter("id",province_id);
        return query.getResultList();

    }
}
