package vantoanProject.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vantoanProject.model.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {

}
