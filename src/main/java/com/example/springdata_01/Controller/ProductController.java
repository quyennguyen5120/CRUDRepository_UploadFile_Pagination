package com.example.springdata_01.Controller;

import com.example.springdata_01.Domain.Product;
import com.example.springdata_01.Domain.ProductUser;
import com.example.springdata_01.Domain.User;
import com.example.springdata_01.Dto.CartDto;
import com.example.springdata_01.Dto.HistoryUserDto;
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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
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
        return "ProductView";
    }

    @GetMapping("/cart")
    public String redirectCart(HttpSession session,Model model){
        List<CartDto> lstCartDto = (List<CartDto>) session.getAttribute("cart");
        if(lstCartDto == null){
            return "redirect:/product/0/3";
        }
        else{
            model.addAttribute("listCart", lstCartDto);
            return "cart";
        }
    }

    @GetMapping("/add_cart/{id}")
    public String addCart(@PathVariable Integer id, HttpSession session){
        List<CartDto> lstCart = (List<CartDto>) session.getAttribute("cart");
        if(lstCart == null){
            lstCart = new ArrayList<>();
            Product p = productRepository.findById(id.longValue()).orElse(null);
            lstCart.add(new CartDto(p));
            session.setAttribute("cart",lstCart);
        }
        else{
            Boolean isExsit = false;
            for(CartDto x : lstCart){
                if(x.getId() == id.longValue()){
                    x.setAmount(x.getAmount() + 1);
                    x.setTotalPrice(x.getAmount() * x.getPrice());
                    isExsit = true;
                }
            }
            if(!isExsit){
                Product p = productRepository.findById(id.longValue()).orElse(null);
                CartDto dto = new CartDto(p);
                lstCart.add(dto);
            }
            session.setAttribute("cart",lstCart);
        }
        return "redirect:/product/0/3";
    }
    @GetMapping("/paycart")
    public String redirectPayCart(HttpSession session, Model model){
        List<CartDto> lstCart = (List<CartDto>) session.getAttribute("cart");
        Double totalPrice = 0D;
        for(CartDto dto : lstCart){
            totalPrice +=dto.getTotalPrice();
        }
        model.addAttribute("listCart", lstCart);
        model.addAttribute("totalPrice", totalPrice);
        return "paycart";
    }

    @GetMapping("/paysuccess")
    public String paySuccess(HttpSession session){
        List<CartDto> lstCart = (List<CartDto>) session.getAttribute("cart");
        List<ProductUser> lstPU = new ArrayList<>();
        for(CartDto dto : lstCart){
            ProductUser pu = new ProductUser();
            pu.setUserId(1);
            pu.setProductId(Integer.parseInt(dto.getId().toString()));
            pu.setAmount(dto.getAmount());
            lstPU.add(pu);
        }
        session.setAttribute("cart", null);
        productUserRepository.saveAll(lstPU);
        return "redirect:/product/0/3";
    }

    @GetMapping("/viewhistory")
    public String viewhistory(Model model){
        List<Product> lstProduct = (List<Product>) productRepository.findAll();
        User u= (User) userRepository.findById(1L).orElse(null);
        List<ProductUser> lstProductUser= (List<ProductUser>) productUserRepository.findByUserId(1L);
        List<HistoryUserDto> ht = new ArrayList<>();
        HistoryUserDto dto = null;

        for(ProductUser pu : lstProductUser){
            dto = new HistoryUserDto();
            if(u.getId() == pu.getUserId().longValue()){
                dto.setUsername(u.getUsername());
                Product p =lstProduct.stream().filter(x->x.getId() == pu.getProductId().longValue()).findAny().orElse(null);
                dto.setName(p.getName());
                dto.setPrice(p.getPrice());
                dto.setAmount(pu.getAmount());
                dto.setTotalPrice(dto.getPrice() * dto.getAmount());
            }
            ht.add(dto);
        }
        model.addAttribute("ht",ht);
        return "viewhistory";
    }

}
