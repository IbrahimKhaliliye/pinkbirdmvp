package com.example.scanner;

public class Product {
    private String barcode;
    private String pinktax;
    private String productname;


    public Product (){

    }
    public Product (String barcode,String pinktax,String productname ){
        this.barcode = barcode;
        this.pinktax = pinktax;
        this.productname = productname;

    }
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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
