package com.example.scanner;

public class Product {
    private Long barcode;
    private Long pinktax;
    private String productname;


    public Product (){

    }
    public Product (Long barcode,Long pinktax,String productname ){
        this.barcode = barcode;
        this.pinktax = pinktax;
        this.productname = productname;

    }
    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
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
