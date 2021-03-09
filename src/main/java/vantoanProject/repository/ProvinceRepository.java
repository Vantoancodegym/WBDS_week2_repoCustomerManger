package vantoanProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vantoanProject.model.Province;

@Repository
public interface ProvinceRepository extends CrudRepository<Province,Long> {

}
