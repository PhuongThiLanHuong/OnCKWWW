package com.example.onck2.fontend.controller;

import com.example.onck2.backend.models.Category;
import com.example.onck2.backend.models.Product;
import com.example.onck2.backend.repositories.ProductRepository;
import com.example.onck2.backend.services.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    @GetMapping("/product-listing")
    public String showProductPaging(HttpSession session, Model model, @RequestParam("page")Optional<Integer> page,@RequestParam("size") Optional<Integer> size)
    {
        int currentPage=page.orElse(1);
        int pageSize=size.orElse(10);
        Page<Product> productPage=productService.findPaginated(currentPage-1,pageSize,"name","asc");
        model.addAttribute("productPage",productPage);
        int totalPages=productPage.getTotalPages();
        if(totalPages>0){
            List<Integer> pageNumbers= IntStream.rangeClosed(1,totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        return "/product/listProduct";
    }
    @GetMapping("/show-add-form")
    public String showAddForm(Model model){
        Product product=new Product();
        model.addAttribute("productToAdd",product);
        return "/product/addProduct";
    }
    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute("product") Product product, BindingResult bindingResult,Model model)
    {
        productRepository.save(product);
        return "redirect:/product/product-listing";
    }
    @GetMapping("/products/show-edit-form/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            model.addAttribute("productToUpdate", optionalProduct.get());
            return "product/editProduct";
        }
        return "product/listProduct";
    }

    @PostMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable long id, @RequestParam String name, @RequestParam double price, @RequestParam Category category) throws Exception
   {
       Optional<Product> optionalProduct=productRepository.findById(id);
       if(optionalProduct.isPresent())
       {
           Product product=optionalProduct.get();
           product.setName(name);
           product.setPrice(price);
           product.setCategory(category);
           productRepository.save(product);
       }else{
           throw new Exception("Khong tim thay Product");
       }
       return "redirect:/product/product-listing";
   }
   @GetMapping("/products/delete/{id}")
   @Secured("ADMIN")
   public String deleteProduct(@PathVariable long id){
        Optional<Product> optionalProduct=productRepository.findById(id);
        optionalProduct.ifPresent(productRepository::delete);
        return "redirect:/product/product-listing";
   }
    @GetMapping("/category/{id}")
    public String showProductPage(Model model, @PathVariable("id") long id) {
        List<Product> products = productService.getAllByCategoryId(id);
        model.addAttribute("products", products);
        return "product/listProductByCategory";
    }

}
