package com.example.appbanhang.utils;

import com.example.appbanhang.model.GioHang;
import com.example.appbanhang.model.User;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static final String BASE_URL="http://192.168.1.9/banhang/";
    public static List<GioHang> manggiohang;
    public static List<GioHang> mangmuahang = new ArrayList<>();
    public static User user_current = new User();

    public static String ID_RECEIVED;
    public static final String SENDID = "idsend";
    public static final String RECEIVEDID = "idreceived";
    public static final String MESS = "message";
    public static final String DATETIME = "datetime";
    public static final String PATH_CHAT = "chat";
    public static String statusOrder(int status){
        String result = "";
        switch (status){
            case 0:
                result ="Order is being processed";
                break;
            case 1:
                result ="Order received";
                break;
            case 2:
                result ="Deliver to shipping";
                break;
            case 3:
                result ="Delivery successful";
                break;
            case 4:
                result ="Order Cancelled";
                break;
            default:
                result = "...";
        }

        return result;
    }


}
