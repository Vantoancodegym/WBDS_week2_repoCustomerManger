package vantoanProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vantoanProject.model.Customer;
import vantoanProject.model.Province;
import vantoanProject.service.customer.ICustomerService;
import vantoanProject.service.province.IProvinceService;

import java.util.List;

@Controller
@RequestMapping("customers")
public class CustomerController {
    @Qualifier("cus")
    @Autowired
    private ICustomerService iCustomerService;
    @Qualifier("prov")
    @Autowired
    private IProvinceService iProvinceService;
    @GetMapping("")
    public ModelAndView showAll(@PageableDefault(size = 3)Pageable pageable){
        Page<Customer> list= iCustomerService.findAll(pageable);
        ModelAndView modelAndView= new ModelAndView("home","list",list);
        return modelAndView;
    }
    @ModelAttribute("provinces")
    public List<Province> getListProvices(){
        return iProvinceService.findAll();
    }
    @GetMapping("edit")
    public ModelAndView showFormEdit(@RequestParam Long id){
        Customer customer=iCustomerService.findById(id);
        ModelAndView modelAndView=new ModelAndView("edit","customer",customer);
        return modelAndView;
    }
    @PostMapping("edit")
    public ModelAndView edit(@RequestParam Long id, @ModelAttribute Customer customer ){
        customer.setId(id);
        iCustomerService.save(customer);
        return new ModelAndView("redirect:/customers");
    }
    @GetMapping("create")
    public ModelAndView showFormCreate(@ModelAttribute Customer customer ){
        ModelAndView modelAndView=new ModelAndView("create","customer",customer);
        return modelAndView;
    }
    @PostMapping("create")
    public ModelAndView create(@ModelAttribute Customer customer){
        iCustomerService.save(customer);
        return new ModelAndView("redirect:/customers");
    }
    @GetMapping("delete")
    public ModelAndView delete(@RequestParam Long id){
        iCustomerService.delete(id);
        return new ModelAndView("redirect:/customers");
    }
    @PostMapping("search")
    public ModelAndView search(@RequestParam Long province_id){
        List<Customer> customerList= iCustomerService.findByProvince(province_id);
        return new ModelAndView("search","customerList",customerList);
    }



}
