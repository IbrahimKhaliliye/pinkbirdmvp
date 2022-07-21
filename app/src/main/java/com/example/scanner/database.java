package com.example.scanner;

public class database {
    private String Barcode;
    private String pinkrax;
    private String productname;

    public database(String barcode, String pinkrax, String productname) {
        Barcode = barcode;
        this.pinkrax = pinkrax;
        this.productname = productname;
    }

    public database() {
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getPinkrax() {
        return pinkrax;
    }

    public void setPinkrax(String pinkrax) {
        this.pinkrax = pinkrax;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
