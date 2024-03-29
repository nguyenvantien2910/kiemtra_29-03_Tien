package business.entity;

import business.config.InputMethod;
import business.config.Message;

import java.util.List;
import java.util.Scanner;

import static business.implement.CategoryIplm.listCategories;

public class Catagories {
    private int catalogID;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;

    public Catagories() {
    }

    public Catagories(String catalogName, String descriptions, boolean catalogStatus) {
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogID() {
        return catalogID;
    }

    public void setCatalogID(int catalogID) {
        this.catalogID = catalogID;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void displayData() {
        System.out.printf("|ID: %-5d | Name: %-15s | Description : %-50s | Status : %-10s\n",
                this.catalogID, this.catalogName, this.descriptions, this.isCatalogStatus() ? "Hoạt động" : "Không hoạt động");
    }

    public void inputData(Scanner sc, List<Catagories> listCategory, boolean isAdd) {
        if (isAdd)
        {
            this.catalogID = findMaxId();
        }
        this.catalogName = inputNameCategory(sc);
        this.descriptions = inputDescription(sc);
        this.catalogStatus = inputCategoryStatus(sc);
    }

    private boolean inputCategoryStatus(Scanner sc) {
        System.out.println("Mời bạn nhập trạng thái cho danh mục (true/false) :");
        return InputMethod.getBoolean();
    }

    private String inputDescription(Scanner sc) {
        System.out.println("Mời bạn nhập mô tả cho danh mục :");
        return InputMethod.getString();
    }

    private String inputNameCategory(Scanner sc) {
        System.out.println("Mời bạn nhập tên danh mục (< 50 kí tự): ");
        String inputName = InputMethod.getString();

        if (inputName.trim().length() > 50) {
            System.err.println(Message.LENGTH_ERROR);
        } else {
            for (Catagories catagories : listCategories) {
                if (catagories.getCatalogName().equals(inputName)) {
                    System.err.println(Message.IS_EXITS_ERROR);
                }
            }
        }

        return inputName;
    }

    //Tìm và trả về ID lớn nhất
    public int findMaxId() {
        int IdMax = 0;
        for (Catagories catagories : listCategories) {
            if (IdMax < catagories.getCatalogID()) {
                IdMax = catagories.getCatalogID();
            }
        }
        return IdMax + 1;
    }
}
