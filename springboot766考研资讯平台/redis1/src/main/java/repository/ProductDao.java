package repository;

import entity.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    public static final String HASH_KEY = "product";
    private RedisTemplate template;

    public Product save(Product product){
        template.opsForHash().put(HASH_KEY,product.getId(),product);
        return product;
    }
    public List<Product> findALL(){
        return template.opsForHash().values(HASH_KEY);
    }
    public Product findProductByID(int id){
        return (Product) template.opsForHash().get(HASH_KEY,id);
    }

    public String deleteProduct(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "product removed";
    }
}
