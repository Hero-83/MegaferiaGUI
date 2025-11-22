package Manage;
public class FormatValidator {

    public static boolean isValidIdFormat(String idStr) {
        if(!isNotEmpty(idStr)) return false;
        try {
            long id = Long.parseLong(idStr.trim());
            return id >= 0 && idStr.trim().length() <= 15;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidPrice(String priceStr) {
        if(!isNotEmpty(priceStr)) return false;
        try {
            double price = Double.parseDouble(priceStr.trim());
            return price > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isValidNIT(String nit) {
        if (!isNotEmpty(nit)) return false;
        return nit.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d");
    }
    
    public static boolean isValidISBN(String isbn) {
        if (!isNotEmpty(isbn)) return false;
        return isbn.matches("\\d{3}-\\d-\\d{2}-\\d{6}-\\d");
    }
    
    public static boolean isNotEmpty(String field) {
        return field != null && !field.trim().isEmpty();
    }
} 