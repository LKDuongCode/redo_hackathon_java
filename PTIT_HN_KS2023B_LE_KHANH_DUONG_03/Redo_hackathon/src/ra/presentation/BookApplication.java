package ra.presentation;

import ra.bussiness.BookBussines;
import ra.validate.Validator;

import java.util.Scanner;

public class BookApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            BookBussines.printMenu("main");
            int choice = Validator.validateInt(sc);

            switch (choice){
                case 1:
                    BookBussines.displayAllBook(BookBussines.books);
                    break;
                case 2:
                    BookBussines.addBook(sc);
                    break;
                case 3:
                    BookBussines.editBook(sc);
                    break;
                case 4:
                    BookBussines.deleteBook(sc);
                    break;
                case 5:
                    BookBussines.findBook(sc);
                    break;
                case 6:
                    BookBussines.sortBook(sc);
                    break;
                case 0:
                    BookBussines.exitProgram(sc);
                default:
                    System.out.println("lựa chọn không hợp lệ");
                    break;
            }
        }while(true);
    }
}
