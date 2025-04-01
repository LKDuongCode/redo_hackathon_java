package ra.bussiness;

import ra.entity.Book;
import ra.validate.Validator;

import java.util.*;

public class BookBussines {
    public static List<Book> books = new ArrayList<>();

    public static void printMenu (String mes){
        if(mes.equals("main")){
            System.out.println("Book Menu ============");
            System.out.println("1. Hiển thị danh sách sách.");
            System.out.println("2. Thêm mới 1 sách.");
            System.out.println("3. Chỉnh sửa thông tin sách.");
            System.out.println("4. Xóa sách");
            System.out.println("5. Tìm kiếm sách.");
            System.out.println("6. Sắp xếp sách.");
            System.out.println("0. Thoát chương trình.");
            System.out.println("Lựa chọn của bạn: ");
            return;
        }

        if(mes.equals("edit")){
            System.out.println("Các phần có thể sửa: ");
            System.out.println("1. title");
            System.out.println("2. author");
            System.out.println("3. publisher");
            System.out.println("4. year");
            System.out.println("5. category");
            System.out.println("6. price");
            System.out.println("7. quantity");
            System.out.println("8. Quay lại.");
            return;
        }


        if(mes.equals("find")){
            System.out.println("1. Tìm kiếm theo tiêu đề.");
            System.out.println("2. Tìm kiếm theo thể loại.");
            System.out.println("3. Tìm kiếm theo khoảng giá (min - max)");
            System.out.println("4. Quay lại");
            return;
        }

        if(mes.equals("sort")){
            System.out.println("1. Sắp xếp theo tiêu đề tăng dần.");
            System.out.println("2. Sắp xếp theo tiêu đề giảm dần.");
            System.out.println("3. Sắp xếp theo giá tăng dần.");
            System.out.println("4. Sắp xếp theo giá giảm dần.");
            System.out.println("5. Quay lại.");
            return;
        }
    }

    public static void exitProgram (Scanner sc){
        System.out.println("thoát chương trình....");
        sc.close();
        System.exit(0);
    }

    public static void addBook (Scanner sc){
        System.out.println("nhập thông tin sách mới.");
        Book newBook = new Book();
        newBook.inputData(sc);

        books.add(newBook);
        System.out.println("thêm thành công sách có id : " + newBook.getBookId());
    }

    public static void displayAllBook (List<Book> books){
        if(books.isEmpty()){
            Validator.logger.warning("danh sách rỗng.");
            return;
        }

        System.out.printf("%-7s | %-30s | %-30s | %-30s | %-5s | %-20s | %-10s | %-12s \n", "ID" ,"tiêu đề", "tác giả", "nhà xuất bản", "năm", "loại","giá","số lượng");

        books.forEach(b -> b.displayData());
    }

    public static void editBook (Scanner sc){
        if(books.isEmpty()){
            Validator.logger.warning("danh sách rỗng.");
            return;
        }

        System.out.println("nhập id cần chỉnh sửa: ");
        Optional<Book> found = getBookBy(sc,"id");

        if(found.isPresent()){
            boolean exit = false;
            while (!exit){
                printMenu("edit");

                int choice = Validator.validateInt(sc);
                switch (choice){
                    case 1:
                        String title = Validator.validateInputString(sc,100,1,"Nhập tiêu đề sách (không trống - max 100 kí tự):");
                        found.get().setBooktitle(title);
                        break;
                    case 2:
                        String author = Validator.validateInputString(sc,50,1,"Nhập tác giả (không trống - max 50 kí tự):");
                        found.get().setAuthor(author);
                        break;
                    case 3:
                        String publisher = Validator.validateInputString(sc,100,1,"Nhập nhà xuất bản (không trống - tối đa 100 kí tự):");
                        found.get().setPublisher(publisher);
                        break;
                    case 4:
                        System.out.println("Nhập năm xuất bản (không trống)");
                        int year = Validator.validateYear(sc);
                        found.get().setPublicationYear(year);
                        break;
                    case 5:
                        String category = Validator.validateInputString(sc,100,1,"Nhập thể loại (không trống - max 100 kí tự):");
                        found.get().setCategory(category);
                        break;
                    case 6:
                        System.out.println("Nhập giá (không trống và > 0)");
                        double price = Validator.validatePrice(sc);
                        found.get().setPrice(price);
                        break;
                    case 7:
                        System.out.println("Nhập số lượng (không trống và > 0):");
                        int quantity = Validator.validateQuantity(sc);
                        found.get().setQuantity(quantity);
                        break;
                    case 8:
                        exit = true;
                        break;
                    default:
                        System.out.println("lựa chọn không hợp lệ.");
                        break;
                }

                System.out.println("sửa thành công.");
            }
        }
    }

    public static void deleteBook (Scanner sc){
        if(books.isEmpty()){
            Validator.logger.warning("danh sách rỗng.");
            return;
        }

        System.out.println("nhập id cần xóa: ");
        Optional<Book> found = getBookBy(sc,"id");

        if(found.isPresent()){
            System.out.println("Bạn có chắc muốn xóa? (Y/N)");

            while (true) {
                String choice = sc.nextLine().trim().toLowerCase();

                switch (choice) {
                    case "y":
                        String id = found.get().getBookId();
                        books.removeIf(b -> b.getBookId().equals(id));
                        System.out.println("Đã xóa sách thành công.");
                        return;
                    case "n":
                        System.out.println("Đã hủy xóa sách.");
                        return;
                    default:
                        Validator.logger.warning("Lựa chọn không hợp lệ. Chỉ nhập 'Y' hoặc 'N'.");
                        break;
                }
            }

        }
    }

    public static void findBook (Scanner sc){
        if(books.isEmpty()){
            Validator.logger.warning("danh sách rỗng.");
            return;
        }

        boolean exit = false;
        while (!exit){
            printMenu("find");
            int choice = Validator.validateInt(sc);
            
            switch (choice){
                case 1:
                    Optional<Book> bTitle = getBookBy(sc,"title");
                    if(bTitle.isPresent()){
                        System.out.println("đã tìm thấy:");
                        bTitle.get().displayData();
                    }
                    break;
                case 2:
                    Optional<Book> bCategory = getBookBy(sc,"category");
                    if(bCategory.isPresent()){
                        System.out.println("đã tìm thấy:");
                        bCategory.get().displayData();
                    }
                    break;
                case 3:
                    filterBookBy(sc,"price");
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    Validator.logger.warning("lựa chọn không hợp ");
                    break;
            }
        }

    }

    public static void sortBook(Scanner sc) {
        if (books.isEmpty()) {
            Validator.logger.warning("Danh sách rỗng.");
            return;
        }

        boolean exit = false;
        while (!exit) {
            printMenu("sort");
            int choice = Validator.validateInt(sc);

            switch (choice) {
                case 1:
                    books.sort(Comparator.comparing(book -> book.getBooktitle().toLowerCase()));
                    System.out.println("Sắp xếp theo tiêu đề tăng dần:");
                    displayAllBook(books);
                    break;
                case 2:
                    books.sort(Comparator.comparing((Book book) -> book.getBooktitle().toLowerCase()).reversed());
                    System.out.println("Sắp xếp theo tiêu đề giảm dần:");
                    displayAllBook(books);
                    break;
                case 3:
                    books.sort(Comparator.comparingDouble(b -> b.getPrice()));
                    System.out.println("Sắp xếp theo giá tăng dần:");
                    displayAllBook(books);
                    break;
                case 4:
                    books.sort(Comparator.comparingDouble((Book b) -> b.getPrice()).reversed());
                    System.out.println("Sắp xếp theo giá giảm dần:");
                    displayAllBook(books);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    Validator.logger.warning("không hợp lệ");
                    break;
            }
        }
    }


    public static Optional<Book> getBookBy(Scanner sc, String type){
        if(type.equals("id")){
            try {
                String id = sc.nextLine();
                Optional<Book> found = books.stream()
                        .filter(b -> b.getBookId().equals(id))
                        .findFirst();

                if (found.isEmpty()) {
                    throw new IllegalArgumentException("ID không tồn tại.");
                }

                return found;
            } catch (IllegalArgumentException e) {
                Validator.logger.warning(e.getMessage());
            } catch (Exception e) {
                Validator.logger.severe(e.getMessage());
            }

            return Optional.empty();
        }

        if(type.equals("title")){
            try {
                String title = sc.nextLine();
                Optional<Book> found = books.stream()
                        .filter(b -> b.getBooktitle().contains(title))
                        .findFirst();

                if (found.isEmpty()) {
                    throw new IllegalArgumentException("Không thấy title");
                }

                return found;
            } catch (IllegalArgumentException e) {
                Validator.logger.warning(e.getMessage());
            } catch (Exception e) {
                Validator.logger.severe(e.getMessage());
            }

            return Optional.empty();
        }

        if(type.equals("category")){
            try {
                String category = sc.nextLine();
                Optional<Book> found = books.stream()
                        .filter(b -> b.getCategory().contains(category))
                        .findFirst();

                if (found.isEmpty()) {
                    throw new IllegalArgumentException("Không thấy category");
                }

                return found;
            } catch (IllegalArgumentException e) {
                Validator.logger.warning(e.getMessage());
            } catch (Exception e) {
                Validator.logger.severe(e.getMessage());
            }

            return Optional.empty();
        }

        return Optional.empty();
    }
    
    public static void filterBookBy (Scanner sc, String type){
        List<Book> results = new ArrayList<>();

        if(type.equals("price")){
            System.out.println("giá bắt đầu từ?");
            double min = Validator.validatePrice(sc);
            System.out.println("đến?");
            double max = Validator.validatePrice(sc);


            if (min >= max) {
                Validator.logger.warning("Giá trị min phải nhỏ hơn max.");
                return;
            }

            results = books.stream().filter(b -> b.getPrice() > min && b.getPrice() < max).toList();

            if(results.isEmpty()){
                Validator.logger.warning("không thấy giá trong khoảng này!");
                return;
            }

            displayAllBook(results);
        }
    }

}
