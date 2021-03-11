package vantoanProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import vantoanProject.exception.DuplicatePhoneException;
import vantoanProject.exception.NotFoundException;
import vantoanProject.model.Customer;
import vantoanProject.model.Province;
import vantoanProject.service.customer.ICustomerService;
import vantoanProject.service.province.IProvinceService;


import java.util.List;

@Controller
@RequestMapping("customers")
public class CustomerController  {
    @Qualifier("cus")
    @Autowired
    private ICustomerService iCustomerService;
    @Qualifier("prov")
    @Autowired
    private IProvinceService iProvinceService;

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView showError(){
        return new ModelAndView("error");
    }
    @ExceptionHandler(DuplicatePhoneException.class)
    public ModelAndView inputNotAcception(){
        return new ModelAndView("inputNotAcception");
    }


    @GetMapping("")
    public ModelAndView showAll(@PageableDefault(size = 3)Pageable pageable) throws NotFoundException {
        Page<Customer> list= iCustomerService.findAll(pageable);
        ModelAndView modelAndView= new ModelAndView("home","list",list);
        return modelAndView;
    }
    @ModelAttribute("provinces")
    public List<Province> getListProvices(){
        return iProvinceService.findAll();
    }

    @GetMapping("edit")
    public ModelAndView showFormEdit(@RequestParam Long id) throws NotFoundException{
        Customer customer=iCustomerService.findById(id);
        ModelAndView modelAndView=new ModelAndView("edit","customer",customer);
        return modelAndView;
    }
    @PostMapping("edit")
    public ModelAndView edit(@RequestParam Long id, @ModelAttribute Customer customer ) throws DuplicatePhoneException {
        customer.setId(id);
        iCustomerService.save(customer);
        return new ModelAndView("redirect:/customers");
    }
    @GetMapping("create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView=new ModelAndView("create","customer",new Customer());
        return modelAndView;
    }
    @PostMapping("create")
    public ModelAndView create(@Validated @ModelAttribute Customer customer, BindingResult bindingResult) throws DuplicatePhoneException{
        if (bindingResult.hasFieldErrors()){
            return new ModelAndView("create","customer",customer);}
        else {
            iCustomerService.save(customer);
            return new ModelAndView("redirect:/customers");
        }
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
