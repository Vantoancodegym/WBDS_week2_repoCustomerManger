package vantoanProject.fomatter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import vantoanProject.model.Province;
import vantoanProject.service.province.IProvinceService;
import vantoanProject.service.province.ProvinceService;

import java.text.ParseException;
import java.util.Locale;

@Component
public class ProvinceFomatter implements Formatter<Province> {
//    @Qualifier("prov")
//    @Autowired
//    private IProvinceService iProvinceService;
    private IProvinceService iProvinceService;
    @Autowired
    public ProvinceFomatter(@Qualifier("prov") IProvinceService iProvinceService){
        this.iProvinceService=iProvinceService;
    }
    @Override
    public Province parse(String text, Locale locale) throws ParseException {
        return iProvinceService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Province object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
