package com.example.scanner;

public class





Product {
    private Long barcode;
    private String price;
    private String productname;
    private String productprice;
    private String category;
    private String imageLink;


    public Product (){

    }
    public Product (Long barcode,String price,String productname,String category,String imageLink){
        this.barcode = barcode;
        this.price = price;
        this.productname = productname;
        this.category=category;
        this.productprice=price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getProductname() {
        return productname;
    }

    public String getProductprice(){return productprice;}



    public void setProductname(String productname) {
        this.productname = productname;
    }
}
