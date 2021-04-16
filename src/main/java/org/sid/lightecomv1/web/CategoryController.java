package org.sid.lightecomv1.web;


import org.sid.lightecomv1.entities.Category;
import org.sid.lightecomv1.entities.Product;
import org.sid.lightecomv1.repository.CategoryRepository;
import org.sid.lightecomv1.repository.ProductRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CategoryController {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public CategoryController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping(path = "/photoProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{

        Product p=productRepository.findById(id).get();
       return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()));

    }

    @PostMapping(path = "/uploadPhoto/{id}")
    public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception{
        Product p=productRepository.findById(id).get();
        p.setPhotoName(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()),file.getBytes());
        productRepository.save(p);
    }


    @PostMapping(path = "/addProduct/{id}")
    public Category addProdcut(@RequestBody Product p,@PathVariable Long id){

        Category category=new Category();

        category=categoryRepository.findById(id).get();

        category.getProducts().add(p);

        p.setCategory(category);

        productRepository.save(p);


             return category;




    }

    @PatchMapping("update/{id}")
    public Product update(@PathVariable long id, @RequestBody Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable long id){

      Optional<Product> p =  productRepository.findById(id);

      productRepository.deleteById(p);
    }

}
