package business.design;

import java.util.Scanner;

public interface CRUD {
    void addNew(Scanner sc);
    void updateInfo(Scanner sc);
    void delete(Scanner sc);

    void displayAllData();

}
