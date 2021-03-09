package vantoanProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vantoanProject.model.Province;
import vantoanProject.repository.ProvinceRepository;

import java.util.List;

@Service
public class ProvinceService implements IProvinceService{
    @Autowired
    public ProvinceRepository provinceRepository;
    @Override
    public List<Province> findAll() {
        return (List<Province>) provinceRepository.findAll();
    }

    @Override
    public Province save(Province province) {
        return provinceRepository.save(province);
    }

    @Override
    public Province findById(Long id) {
        return provinceRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {

    }
}
