package business.implement;

import business.config.CONSTANT;
import business.config.InputMethod;
import business.config.Message;
import business.design.ProductDesign;
import business.entity.Products;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static business.implement.CategoryIplm.listCategories;

public class ProductIplm implements ProductDesign {
    private static List<Products> listProduct = new ArrayList<>();

    @Override
    public void addNew(Scanner scanner) {
        System.out.println("Nhập số lượng sản phẩm muốn thêm : ");
        byte addNum = InputMethod.getByte();
        for (int i = 0; i < addNum; i++) {
            Products newProduct = new Products();
            newProduct.inputData(scanner, listProduct, listCategories, true);
            listProduct.add(newProduct);
        }
        System.out.println(Message.ADD_NEW_SUCESS);
    }

    @Override
    public void updateInfo(Scanner sc) {
        System.out.println("Nhập vào mã sản phẩm muốn thay đổi thông tin : ");
        String inputId = InputMethod.getString();
        Products productToUpdate = getProductById(inputId);
        if (productToUpdate == null) {
            System.err.println(Message.ID_NOT_FOUND);
            return;
        }

        while (true) {
            System.out.println("Lựa chọn thông tin muốn thay đổi :");
            System.out.println("1. Thay đổi tên");
            System.out.println("2. Thay đổi giá");
            System.out.println("3. Thay mô tả");
            System.out.println("4. Thay đổi ngày nhập");
            System.out.println("5. Thay đổi mã danh mục");
            System.out.println("6. Thay đổi trạng thái");
            System.out.println("7. Thoát");

            System.out.print("Lựa chọn của bạn : ");
            int choice = InputMethod.getByte();

            switch (choice) {
                case 1:
                    System.out.println("Nhập tên mới cho sản phẩm:");
                    String newName = InputMethod.getString();
                    if (newName.trim().length() > 50 || newName.trim().length() < 10) {
                        System.err.println(Message.LENGTH_ERROR);
                        break;
                    }
                    productToUpdate.setProductName(newName);
                    System.out.println(Message.UPDATE_INFO_SUCESS);
                    return;
                case 2:
                    System.out.println("Nhập giá mới cho sản phẩm:");
                    float newPrice = InputMethod.getFloat();
                    if (newPrice < 0) {
                        System.err.println(Message.FLOAT_ERROR);
                        break;
                    }
                    productToUpdate.setPrice(newPrice);
                    System.out.println(Message.UPDATE_INFO_SUCESS);
                    return;

                case 3:
                    System.out.println("Nhập mô tả cho sản phẩm :");
                    productToUpdate.setDescription(InputMethod.getString());
                    System.out.println(Message.UPDATE_INFO_SUCESS);
                    return;
                case 4:
                    System.out.println("Nhập ngày nhập sản phẩm :");
                    productToUpdate.setCreated(InputMethod.getDate());
                    System.out.println(Message.UPDATE_INFO_SUCESS);
                    return;
                case 5:
                    System.out.println("Chọn danh mục của sản phẩm:");
                    for (int i = 0; i < listCategories.size(); i++) {
                        if (listCategories.get(i).isCatalogStatus()) {
                            System.out.printf("%d.%s\n", i + 1, listCategories.get(i).getCatalogName());
                        }
                    }
                    System.out.print("Lựa chọn của bạn: ");
                    choice = InputMethod.getByte();
                    productToUpdate.getCatalogId(listCategories.get(choice - 1).getCatalogID());
                    System.out.println(Message.UPDATE_INFO_SUCESS);
                    return;
                case 6:
                    System.out.println(Message.UPDATE_INFO_SUCESS);
                    return;
                case 7:
                    System.out.println("Mời bạn lựa chọn trạng thái cho sản phẩm :");

                    for (int i = 0; i < CONSTANT.PRODUCT_STATUS.values().length; i++) {
                        System.out.printf("%d. %s\n", i + 1, CONSTANT.PRODUCT_STATUS.values()[i]);
                    }

                    while (true) {
                        System.out.print("Lựa chọn của bạn: ");
                        choice = InputMethod.getByte();

                        if (choice >= 1 && choice <= CONSTANT.PRODUCT_STATUS.values().length) {
                            productToUpdate.setProductStatus(CONSTANT.PRODUCT_STATUS.values()[choice - 1]);
                            System.out.println(Message.UPDATE_INFO_SUCESS);
                            return;
                        } else {
                            System.err.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                        }
                    }

                default:
                    System.err.println(Message.SELECT_INVALID);
                    break;
            }
        }
    }

    @Override
    public void delete(Scanner sc) {
        System.out.println("Nhập mã sản phẩm muốn xóa : ");
        String inputId = InputMethod.getString();

        Iterator<Products> iterator = listProduct.iterator();
        while (iterator.hasNext()) {
            Products product = iterator.next();
            if (product.getProductID().equals(inputId)) {
                iterator.remove();
                System.out.println(Message.DELETE_SUCESS);
                return;
            }
        }
        System.err.println(Message.ID_NOT_FOUND);
    }

    private Products getProductById(String productId) {
        for (Products product : listProduct) {
            if (product.getProductID().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void displayAllData() {
        if (listProduct.isEmpty()) {
            System.err.println(Message.EMTY_LIST);
        } else {
            Scanner sc = new Scanner(System.in);
            int firstIndexOfPage = 0;
            int lastIndexOfPage = 2;
            int elementPerPage = 3;
            int page = 1;
            int numberOfPage;
            if (listProduct.size() % elementPerPage == 0) {
                numberOfPage = listProduct.size() / elementPerPage;
            } else {
                numberOfPage = listProduct.size() / elementPerPage + 1;
            }
            do {
                for (int i = 0; i < listProduct.size(); i++) {
                    if (i >= firstIndexOfPage && i <= lastIndexOfPage) {
                        listProduct.get(i).displayData();
                    }
                }

                System.out.println("Trang : " + page + "/" + numberOfPage);
                if (page == 1) {
                    System.out.println("2.Trang sau");
                    System.out.println("3.Thoát");
                } else if (page == numberOfPage) {
                    System.out.println("1.Trang Trước");
                    System.out.println("3.Thoát");
                } else {
                    System.out.println("1.Trang trước  ||  2.Trang sau");
                    System.out.println("3.Thoát");
                }

                System.out.println("Mời nhập lựa chọn: ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        if (page <= numberOfPage && page >= 0) {
                            firstIndexOfPage -= elementPerPage;
                            lastIndexOfPage -= elementPerPage;
                            page -= 1;
                            break;
                        }
                    case 2:
                        if (page <= numberOfPage && page >= 0) {
                            firstIndexOfPage += elementPerPage;
                            lastIndexOfPage += elementPerPage;
                            page += 1;
                            break;
                        }
                    case 3:
                        return;
                    default:
                        System.err.print(Message.SELECT_INVALID);
                        break;
                }
            } while (true);
        }

    }

    @Override
    public void sortProductByPrice() {
        if (listProduct.isEmpty()) {
            System.err.println(Message.EMTY_LIST);
        } else {
            listProduct.sort(Comparator.comparing(Products::getPrice));
            System.out.print("Danh sách sản phẩm sau khi sắp xếp theo giá :\n");
            displayAllData();
        }

    }

    @Override
    public void findProductByName() {
        if (listProduct.isEmpty()) {
            System.err.println(Message.EMTY_LIST);
        }else {
            System.out.println("Mời bạn nhập vào tên sản phẩm muốn tìm kiếm :");
            String inputName = InputMethod.getString();

            boolean isExits = false;
            for (Products product : listProduct) {
                if(product.getProductName().equals(inputName)) {
                    product.displayData();
                    isExits = true;
                }
            }

            if (!isExits) {
                System.err.println(Message.NAME_NOT_FOUND);
            }
        }
    }

    @Override
    public void findProductByPriceRange() {
        if (listProduct.isEmpty()) {
            System.err.println(Message.EMTY_LIST);
        } else {
            System.out.println("Nhập khoảng giá bạn muốn tìm kiếm (a – b)");
            System.out.print("Nhập giá bắt đầu (a): ");
            float formPrice = InputMethod.getFloat();
            System.out.print("Nhập giá kết thúc (b): ");
            float toPrice = InputMethod.getFloat();
            boolean foundProduct = false;
            for (Products product : listProduct) {
                if (product != null && product.getPrice() >= formPrice && product.getPrice() <= toPrice) {
                    product.displayData();
                    foundProduct = true;
                }
            }
            if (!foundProduct) {
                System.err.printf("Không tìm thấy sản phẩm nào trong khoảng giá từ %.2f đến %.2f \n", formPrice,toPrice);
            }
        }

    }
}
