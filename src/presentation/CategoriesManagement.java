package presentation;

import business.config.InputMethod;
import business.config.Message;
import business.implement.CategoryIplm;

import java.util.Scanner;

public class CategoriesManagement {
    public static void displayMenu() {
        CategoryIplm categoryIplm = new CategoryIplm();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("********************CATEGORIES MENU***********");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Quay lại");

            byte choice = InputMethod.getByte();

            switch (choice) {
                case 1:
                    categoryIplm.addNew(scanner);
                    break;
                case 2:
                    categoryIplm.displayAllData();
                    break;
                case 3:
                    categoryIplm.updateInfo(scanner);
                    break;
                case 4:
                    categoryIplm.delete(scanner);
                    break;
                case 5:
                    categoryIplm.updateCategoryStatus(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.err.println(Message.SELECT_INVALID);
            }
        }while (true);
    }
}
