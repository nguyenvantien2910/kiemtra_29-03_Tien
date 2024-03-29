package business.entity;

import business.config.CONSTANT;
import business.config.InputMethod;
import business.config.Message;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Products {
    private String productID;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int catalogId;

    private CONSTANT.PRODUCT_STATUS productStatus;

    public Products() {
    }

    public Products(String productID, String productName, float price, String description, Date created, int catalogId) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public CONSTANT.PRODUCT_STATUS getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(CONSTANT.PRODUCT_STATUS productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId(int catalogID) {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public void inputData(Scanner sc, List<Products> listProduct, List<Catagories> listCategories, boolean isAdd) {
        if (isAdd){
            this.productID = inputProductId(sc,listProduct);
        }
        this.productName = inputProductName(sc, listProduct);
        this.price = inputPrice(sc);
        this.description = inputDescription(sc);
        this.created = inputDate(sc);
        this.catalogId = inputCatalogId(sc,listCategories);
        this.productStatus = inputProductStatus(sc);
    }

    private CONSTANT.PRODUCT_STATUS inputProductStatus(Scanner sc) {
        System.out.println("Mời bạn lựa chọn trạng thái cho sản phẩm :");

        for (int i = 0; i < CONSTANT.PRODUCT_STATUS.values().length; i++) {
            System.out.printf("%d. %s\n", i + 1, CONSTANT.PRODUCT_STATUS.values()[i]);
        }

        while (true) {
            System.out.print("Lựa chọn của bạn: ");
            int choice = InputMethod.getByte();

            if (choice >= 1 && choice <= CONSTANT.PRODUCT_STATUS.values().length) {
                return CONSTANT.PRODUCT_STATUS.values()[choice - 1];
            } else {
                System.err.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }


    private int inputCatalogId(Scanner sc, List<Catagories> listCategories) {
        System.out.println("Chọn danh mục của sản phẩm:");
        for (int i = 0; i < listCategories.size(); i++) {
            if (listCategories.get(i).isCatalogStatus()) {
                System.out.printf("%d.%s\n", i + 1, listCategories.get(i).getCatalogName());
            }
        }
        System.out.print("Lựa chọn của bạn: ");
        int choice = InputMethod.getByte();
        return listCategories.get(choice - 1).getCatalogID();
    }

    private Date inputDate(Scanner sc) {
        System.out.println("Nhập ngày nhập sản phẩm :");
        return InputMethod.getDate();
    }

    private String inputDescription(Scanner sc) {
        System.out.println("Nhập mô tả cho sản phẩm :");
        return InputMethod.getString();
    }

    private float inputPrice(Scanner sc) {
        while (true) {
            System.out.println("Nhập giá cho sản phẩm :");
            float inputPrice = InputMethod.getFloat();

            if (inputPrice < 0) {
                System.err.println(Message.FLOAT_ERROR);
            } else {
                return inputPrice;
            }
        }
    }


    private String inputProductId(Scanner sc, List<Products> listProduct) {
        while (true) {
            System.out.println("Mời bạn nhập mã sản phẩm :");
            String inputID = InputMethod.getString();

            if (!inputID.matches(CONSTANT.REGEX_NAME)) {
                System.err.println(Message.ID_REGEX_ERROR);
                continue;
            }

            boolean isExists = false;
            for (Products products : listProduct) {
                if (products.getProductID().equals(inputID)) {
                    isExists = true;
                    break;
                }
            }

            if (isExists) {
                System.err.println(Message.IS_EXITS_ERROR);
            } else {
                return inputID;
            }
        }
    }

    private String inputProductName(Scanner sc, List<Products> listProduct) {
        while (true) {
            System.out.println("Nhập tên cho sản phẩm : ");
            String inputName = InputMethod.getString().trim();

            if (inputName.length() > 50 || inputName.length() < 10) {
                System.err.println(Message.LENGTH_ERROR);
                continue;
            }

            boolean isExists = false;
            for (Products products : listProduct) {
                if (products.getProductName().equals(inputName)) {
                    isExists = true;
                    break;
                }
            }

            if (isExists) {
                System.err.println(Message.IS_EXITS_ERROR);
            } else {
                return inputName;
            }
        }
    }


    public void displayData() {
        System.out.printf("|ID: %-5s | Name: %-10s | Price: %-12.2f | Description : %-30s | Created : %-10s | CatalogId : %-5d | Status : %-10s",
                this.productID, this.productName,this.price,this.description,this.created.toString(),this.catalogId,
                switch (this.productStatus) {
                    case BLOCK -> "Hết hàng";
                    case ACTIVE -> "Đang bán";
                    case INACTIVE -> "Không bán";
                });
    }
}
