package com.example.scanner;

public class Product {
    private Long barcode;
    private String pinktax;
    private String productname;
    private String category;
    private String image;


    public Product (){

    }
    public Product (Long barcode,String pinktax,String productname,String category,String imageLink ){
        this.barcode = barcode;
        this.pinktax = pinktax;
        this.productname = productname;
        this.category=category;
        this.image=imageLink;

    }
    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPinktax() {
        return pinktax;
    }

    public void setPinktax(String pinktax) {
        this.pinktax = pinktax;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
