package com.example.squepay;

public class Items {
    private String itemname;
    private String itemcategory;
    private String itemprice;
    private String itembarcode;

public  Items(){

    }
public Items(String itemname,String itemcategory,String itemprice,String itembarcode){
        this.itemname=itemname;
        this.itemcategory=itemcategory;
        this.itemprice=itemprice;
        this.itembarcode= itembarcode;
    }
    public static String getItemname(){
    return getItemname();
    }
    public static String getItemcategory(){
    return  getItemcategory();
    }
    public static String getItembarcode(){
    return getItembarcode();
    }
    public static String getItemprice(){
    return getItemprice();
    }

}
