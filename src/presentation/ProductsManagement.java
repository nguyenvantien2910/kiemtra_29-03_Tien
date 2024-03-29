package presentation;

import business.config.InputMethod;
import business.config.Message;
import business.implement.ProductIplm;

import java.util.Scanner;

public class ProductsManagement {
    public static void displayMenu() {
        ProductIplm productIplm = new ProductIplm();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("********************PRODUCTS MENU***********");
            System.out.println("1. Nhập thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp các sản phẩm theo giá");
            System.out.println("4. Cập nhật thông tin sản phẩm theo mã sản phẩm");
            System.out.println("5. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("6. Tìm kiếm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)");
            System.out.println("8. Quay lại");

            byte choice = InputMethod.getByte();

            switch (choice) {
                case 1:
                    productIplm.addNew(scanner);
                    break;
                case 2:
                    productIplm.displayAllData();
                    break;
                case 3:
                    productIplm.sortProductByPrice();
                    break;
                case 4:
                    productIplm.updateInfo(scanner);
                    break;
                case 5:
                    productIplm.delete(scanner);
                    break;
                case 6:
                    productIplm.findProductByName();
                    break;
                case 7:
                    productIplm.findProductByPriceRange();
                    break;
                case 8:
                    return;
                default:
                    System.err.println(Message.SELECT_INVALID);
            }
        }
    }
}