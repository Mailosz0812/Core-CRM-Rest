package org.mailosz.crmrest.product;

public enum Category {
    DAIRY,
    MEAT,
    VEGETABLE,
    OTHER;

//    Consider changing to more strict
    public static Category getCategory(String category){
        try{
            return Category.valueOf(category);
        } catch (IllegalArgumentException e) {
            return Category.OTHER;
        }
    }
}
