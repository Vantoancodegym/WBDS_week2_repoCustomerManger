package vantoanProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vantoanProject.model.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {
@Query(value = "select c from Customer as c",nativeQuery = false)
    Page<Customer> findAllQuery(Pageable pageable);
}
