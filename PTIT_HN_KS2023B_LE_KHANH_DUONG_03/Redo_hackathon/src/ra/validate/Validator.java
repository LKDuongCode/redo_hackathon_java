package ra.validate;

import ra.bussiness.BookBussines;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Validator {
    public static final Logger logger = Logger.getLogger(Validator.class.getName());

    public static int validateInt (Scanner sc){
        while(true){
            try {
                int num = Integer.parseInt(sc.nextLine());

                return num;
            }catch (NumberFormatException e){
                logger.warning("hãy nhập số nguyên.");
            }catch (Exception e){
                logger.severe(e.getMessage()); // có thể thay bằng e.printStackTrace(); nhưng dùng logger sẽ phù hợp hơn.
            }
        }
    }

    public static String validateId (Scanner sc){
        while (true){
            try{
                String id = sc.nextLine();

                if(!Pattern.matches("^B\\d{5}$",id)){
                    throw new IllegalArgumentException("id không đúng định dạng!");
                }

                boolean isExist = BookBussines.books.stream().anyMatch(b -> b.getBookId().equals(id));

                if(isExist){
                    throw new IllegalArgumentException("id đã tồn tại.");
                }

                return id;

            }catch (IllegalArgumentException e){
                logger.warning(e.getMessage());
            }catch (Exception e){
                logger.severe(e.getMessage());
            }
        }
    }

    public static String validateInputString (Scanner sc, int max, int min, String mes){
        System.out.println(mes);
        while(true){
            try{
                String str = sc.nextLine();
                str = str.trim();

                if(str.length() == 0 ){
                    throw new IllegalArgumentException("không được để trống.");
                }

                if(str.length() < min || str.length() > max){
                    throw new IllegalArgumentException("độ dài của chuỗi không đúng quy định.");
                }

                return str;
            }
            catch (IllegalArgumentException e){
                logger.warning(e.getMessage());
            }
            catch (Exception e){
                logger.severe(e.getMessage());
            }
        }
    }

    public static int validateYear(Scanner sc){
        while (true){
            try {
                int y = validateInt(sc);

                if(y == 0){
                    throw new IllegalArgumentException("năm không được để trống.");
                }

                return y;

            }catch (IllegalArgumentException e){
                logger.warning(e.getMessage());
            }catch (Exception e){
                logger.severe(e.getMessage());
            }
        }
    }

    public static double validatePrice(Scanner sc){
        while(true){
            try{
                double price = Double.parseDouble(sc.nextLine());
                if(price <= 0){
                    throw new IllegalArgumentException("giá sách cần lớn hơn không và không được để trống!");
                }

                return price;
            }catch (NumberFormatException e){
                logger.warning("giá không hợp lệ.");
            }
            catch (IllegalArgumentException e){
                logger.warning(e.getMessage());
            }
            catch (Exception e){
                logger.severe(e.getMessage());
            }
        }
    }

    public static int validateQuantity (Scanner sc){
        while (true){
            try{
                int q = validateInt(sc);

                if(q <= 0){
                    throw new IllegalArgumentException("số lượng không được để trống và > 0.");
                }

                return q;
            }catch (IllegalArgumentException e){
                logger.warning(e.getMessage());
            }catch (Exception e){
                logger.severe(e.getMessage());
            }
        }
    }
}
