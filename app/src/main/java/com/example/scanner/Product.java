package com.example.scanner;

public class Product {
    private Long barcode;
    private Long pinktax;
    private String productname;
    private String category;
    private String imageLink;


    public Product (){

    }
    public Product (Long barcode,Long pinktax,String productname,String category,String imageLink ){
        this.barcode = barcode;
        this.pinktax = pinktax;
        this.productname = productname;
        this.category=category;
        this.imageLink=imageLink;

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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Long getPinktax() {
        return pinktax;
    }

    public void setPinktax(Long pinktax) {
        this.pinktax = pinktax;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
