package com.example.scanner;

public class Product {
    private String Barcode;
    private String pinktax;
    private String productname;

    public Product(String barcode, String pinktax, String productname) {
        Barcode = barcode;
        this.pinktax = pinktax;
        this.productname = productname;
    }

    public Product() {
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
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
