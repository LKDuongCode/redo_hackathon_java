package ra.entity;

import ra.bussiness.BookBussines;
import ra.validate.Validator;

import java.util.Scanner;

public class Book implements IApp {
    private String bookId;
    private String bookTitle;
    private String author;
    private String publisher;
    private int publicationYear;
    private String category;
    private double price;
    private int quantity;

    public Book() {
    }

    public Book(String bookId, String bookTitle, String author, String publisher, int publicationYear, String category, double price, int quantity) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBooktitle() {
        return bookTitle;
    }

    public void setBooktitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.println("Nhập mã sách (Bxxxxx): ");
        String id = Validator.validateId(sc);

        String title = Validator.validateInputString(sc, 100, 1, "Nhập tiêu đề sách (không trống - max 100 kí tự):");

        String author = Validator.validateInputString(sc, 50, 1, "Nhập tác giả (không trống - max 50 kí tự):");

        String publisher = Validator.validateInputString(sc, 100, 1, "Nhập nhà xuất bản (không trống - tối đa 100 kí tự):");

        System.out.println("Nhập năm xuất bản (không trống)");
        int year = Validator.validateYear(sc);

        String category = Validator.validateInputString(sc, 100, 1, "Nhập thể loại (không trống - max 100 kí tự):");

        System.out.println("Nhập giá (không trống và > 0)");
        double price = Validator.validatePrice(sc);

        System.out.println("Nhập số lượng (không trống và > 0):");
        int quantity = Validator.validateQuantity(sc);

        this.setBookId(id);
        this.setBooktitle(title);
        this.setAuthor(author);
        this.setPublisher(publisher);
        this.setPublicationYear(year);
        this.setCategory(category);
        this.setPrice(price);
        this.setQuantity(quantity);
    }

    @Override
    public void displayData() {
        System.out.printf("%-7s | %-30s | %-30s | %-30s | %-5d | %-20s | %-10f | %-12d \n", this.bookId, this.bookTitle, this.author, this.publisher, this.publicationYear, this.category, this.price, this.quantity);
    }
}
