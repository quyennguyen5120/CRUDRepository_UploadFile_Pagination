package com.example.springdata_01.Controller;

import com.example.springdata_01.Domain.Product;
import com.example.springdata_01.Repository.ProductRepository;
import com.example.springdata_01.Repository.ProductUserRepository;
import com.example.springdata_01.Repository.UserRepository;
import com.example.springdata_01.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductUserRepository productUserRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/{pageIndex}/{pageSize}")
    public String getProductView(Model model, @PathVariable Integer pageIndex, @PathVariable Integer pageSize){
        Integer abc = null;
        Long length = productRepository.count();
        int numberPage = Math.round (length /  pageSize);
        pageIndex -=1;
        if(length % pageSize >= 1){
            numberPage+=1;
        }
        Pageable paging = PageRequest.of(pageIndex, pageSize, Sort.by("id").descending());
        Page<Product> page = productService.findAll(paging);
        List<Product> lstProduct = page.toList();

        model.addAttribute("numberPage",numberPage);
        model.addAttribute("pageIndex",pageIndex + 1);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("lstProduct",lstProduct);
        return "AdminView";
    }

    @GetMapping("/redirectUpdate/{id}")
    public String redirectUpdateProduct(@PathVariable("id") Long id,Model model){
        Product p = productRepository.findById(id).orElse(null);
        model.addAttribute("product",p);
        return "ProductDetail";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@PathVariable("file") MultipartFile file, Product p){
        productService.updateProduct(file,p);
        return "redirect:/admin/1/3";
    }

    @GetMapping("/redirectCreate")
    public String redirectCreate(Model model){
        model.addAttribute("product",new Product());
        return "ProductCreate";
    }

    @PostMapping("/createProduct")
    public String createProduct(@PathVariable("file") MultipartFile file, Product p){
        productService.insertProduct(file,p);
        return "redirect:/admin/1/3";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productRepository.deleteById(id);
        return "redirect:/admin/1/3";
    }
}
