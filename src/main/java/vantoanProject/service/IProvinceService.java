package vantoanProject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vantoanProject.model.Customer;
import vantoanProject.model.Province;

import java.util.List;

@Service
public interface IProvinceService {
    List<Province> findAll();
    Province save(Province province);
    Province findById(Long id);
    void delete(Long id);
}
