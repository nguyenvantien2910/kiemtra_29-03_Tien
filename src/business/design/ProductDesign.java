package business.design;

public interface ProductDesign extends CRUD{
    void sortProductByPrice();
    void findProductByName();
    void findProductByPriceRange();
}
