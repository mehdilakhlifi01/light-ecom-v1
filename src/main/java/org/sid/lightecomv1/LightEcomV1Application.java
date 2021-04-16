package org.sid.lightecomv1;

import net.bytebuddy.utility.RandomString;
import org.sid.lightecomv1.entities.Category;
import org.sid.lightecomv1.entities.Product;
import org.sid.lightecomv1.repository.CategoryRepository;
import org.sid.lightecomv1.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class LightEcomV1Application implements CommandLineRunner {


    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public LightEcomV1Application(ProductRepository productRepository, CategoryRepository categoryRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.repositoryRestConfiguration = repositoryRestConfiguration;
    }


    public static void main(String[] args) {
        SpringApplication.run(LightEcomV1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repositoryRestConfiguration.exposeIdsFor(Product.class,Category.class);

      /* categoryRepository.save(new Category(null," ELECTROPORTATIF",null,null,null));
        categoryRepository.save(new Category(null,"GAMME JARDIN",null,null,null));
        categoryRepository.save(new Category(null,"OUTILS À MAIN",null,null,null));

        Random rnd =new Random();

        categoryRepository.findAll().forEach(c->{
            for (int i=0 ;i<20;i++){

                Product p=new Product();
                p.setName(RandomString.make(18));
                p.setCurrentPrice(100+rnd.nextInt(1000));
                p.setAvailable(rnd.nextBoolean());
                p.setPromotion(rnd.nextBoolean());
                p.setPhotoName("unknown.png");
                p.setSelected(rnd.nextBoolean());
                p.setCategory(c);

                productRepository.save(p);

            }

        });*/

    }
}
