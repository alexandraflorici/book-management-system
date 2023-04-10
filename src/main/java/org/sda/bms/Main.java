package org.sda.bms;

import org.sda.bms.controller.AuthorController;
import org.sda.bms.controller.BookController;
import org.sda.bms.controller.UserOption;
import org.sda.bms.repository.AuthorRepositoryImpl;
import org.sda.bms.repository.BookRepositoryImpl;
import org.sda.bms.service.AuthorServiceImpl;
import org.sda.bms.service.BookServiceImpl;
import org.sda.bms.utils.SessionManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startHibernate();

        //Dependencies:
        Scanner scanner = new Scanner(System.in);
        AuthorController authorController = new AuthorController(
                new AuthorServiceImpl(new AuthorRepositoryImpl()),
                scanner
        );
        BookController bookController = new BookController(
                new BookServiceImpl(
                        new BookRepositoryImpl(),
                        new AuthorRepositoryImpl()
                ),
                scanner
        );

        UserOption userOption = UserOption.UNKNOWN;
        while (userOption != UserOption.EXIT) {
            UserOption.printAllOptions();
            System.out.println(" ");
            System.out.println("Please select an option: ");
            try {
                int selectedOption = Integer.parseInt(scanner.nextLine().trim());
                userOption = UserOption.findUserOption(selectedOption).orElse(UserOption.UNKNOWN);
            } catch (NumberFormatException e) {
                userOption = UserOption.UNKNOWN;
            }
            switch (userOption) {
                case CREATE_AUTHOR:
                    authorController.create();
                    System.out.println("Not implemented");
                    break;
                case VIEW_ALL_AUTHORS:
                    authorController.displayAll();
                    break;
                case UPDATE_AUTHOR:
                    authorController.update();
                    break;
                case DELETE_AUTHOR:
                    authorController.deleteById();
                    break;
                case CREATE_BOOK:
                    bookController.create();
                    break;
                case VIEW_ALL_BOOKS:
                    bookController.displayAll();
                    break;
                case VIEW_BOOK_BY_ID:
                    bookController.displayById();
                    break;
                case UNKNOWN:
                    System.out.println("please insert a valid option!!!! ");
                    break;
                case EXIT:
                    stopHibernate();
                    System.out.println("Good bye!");
                    break;
                default:
                    System.out.println("Not implemented. Please contact your Administrator");
                    break;
            }
        }
    }

    private static void startHibernate() {
        SessionManager.getSessionFactory();

        for (int i = 0; i <= 50; i++) {
            System.out.println("  ");
        }
    }

    private static void stopHibernate() {
        SessionManager.shutDown();
        for (int i = 0; i <= 50; i++) {
            System.out.println(" ");
        }
    }
}