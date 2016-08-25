package services;

import entities.Product;

/**
 * Created by csexton on 7/18/16.
 */
public class ProductService extends GenericService<Product, Integer> {

    public ProductService() {
        super(Product.class);
    }


}
