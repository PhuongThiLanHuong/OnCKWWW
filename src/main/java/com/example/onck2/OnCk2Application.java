package com.example.onck2;

import com.example.onck2.backend.models.Category;
import com.example.onck2.backend.models.Product;
import com.example.onck2.backend.repositories.CategoryRepository;
import com.example.onck2.backend.repositories.ProductRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class OnCk2Application {

    public static void main(String[] args) {
        SpringApplication.run(OnCk2Application.class, args);
    }
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
//    @Bean
//    CommandLineRunner getSampleCategory()
//    {
//        return args -> {
//            Faker faker=new Faker();
//            Random random=new Random();
//            for(int i=1;i<=3;i++) {
//                Category category = new Category(
//                        "Loai #" + i
//                );
//                categoryRepository.save(category);
//                System.out.println("Added :"+category);
//            }
//        };
//
//    }
//    @Bean
//    CommandLineRunner getSampleProduct()
//    {
//        return args -> {
//            Faker faker=new Faker();
//            Random random=new Random();
//            for(int i=1;i<=200;i++)
//            {
//                Product product=new Product(
//                        faker.commerce().productName(),
//                       Double.parseDouble(faker.commerce().price()) ,
//                        new Category(random.nextLong(1l,4l))
//                );
//                productRepository.save(product);
//                System.out.println("Added: "+product);
//
//            }
//        };
//    }
}
