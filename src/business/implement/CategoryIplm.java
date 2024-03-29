package business.implement;

import business.config.InputMethod;
import business.config.Message;
import business.design.CatagoryDesign;
import business.entity.Catagories;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryIplm implements CatagoryDesign {

    public static List<Catagories> listCategories = new ArrayList<>();

    @Override
    public void addNew(Scanner sc) {
        System.out.println("Nhập số lượng danh mục muốn thêm : ");
        byte addNum = InputMethod.getByte();
        for (int i = 0; i < addNum; i++) {
            Catagories newCatagory = new Catagories();
            newCatagory.inputData(sc, listCategories,true);
            listCategories.add(newCatagory);
        }
        System.out.println(Message.ADD_NEW_SUCESS);
    }

    @Override
    public void updateInfo(Scanner sc) {
        System.out.println("Nhập vào mã danh mục muốn thay đổi thông tin : ");
        int inputId = InputMethod.getInteger();
        listCategories.forEach(catagorie -> {
            if (catagorie.getCatalogID() == inputId) {
                catagorie.inputData(sc, listCategories, false);
                System.out.println(Message.UPDATE_INFO_SUCESS);
            }
        });
    }

    @Override
    public void delete(Scanner sc) {
        System.out.println("Nhập mã danh mục muốn xóa : ");
        int inputId = InputMethod.getInteger();

        boolean found = false;
        for (Catagories category : listCategories) {
            if (category.getCatalogID() == inputId) {
                found = true;
                listCategories.remove(category);
                System.out.println(Message.DELETE_SUCESS);
                break;
            }
        }

        if (!found) {
            System.err.println(Message.ID_NOT_FOUND);
        }
    }

    @Override
    public void displayAllData() {
        if (listCategories.isEmpty()) {
            System.err.println(Message.EMTY_LIST);
        } else {
            System.out.println("Danh sách các danh mục hiện có :");
            for (Catagories category : listCategories) {
                category.displayData();
            }
        }
    }

    @Override
    public void updateCategoryStatus(Scanner sc) {
        System.out.println("Nhập vào mã danh mục muốn đổi trạng thái :");
        int inputId = InputMethod.getInteger();
        boolean isExit = false;

        for (Catagories category : listCategories) {
            if (category.getCatalogID() == inputId) {
                category.setCatalogStatus(!category.isCatalogStatus());
                isExit = true;
                System.out.println(Message.UPDATE_STATUS_SUCESS);
                break;
            }
        }
        if (!isExit) {
            System.err.println(Message.ID_NOT_FOUND);
        }
    }
}
