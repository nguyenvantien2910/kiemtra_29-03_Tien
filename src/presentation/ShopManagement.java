package presentation;

import business.config.InputMethod;
import business.config.Message;

import java.util.Scanner;

public class ShopManagement {
    public static void main(String[] args) {
        CategoriesManagement categoriesManagement = new CategoriesManagement();
        ProductsManagement productsManagement = new ProductsManagement();
        do {
            System.out.println("******************SHOP MENU*******************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Nhập lựa chọn của bạn : ");

            byte choice = InputMethod.getByte();

            switch (choice) {
                case 1:
                    categoriesManagement.displayMenu();
                    break;
                case 2:
                    productsManagement.displayMenu();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println(Message.SELECT_INVALID);
            }
        } while (true);
    }
}
