package com.example.lab03_gk.asm02;

public abstract class User {
    private  String name;
    private  String customerId;

    public  User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }


    /*
    * Kiểm tra điều kiện hàm setCustomerId () đúng CCCD thì mới gán gía trị,
    *  ngược lại quăng lỗi exception
    * */
    public void setCustomerId(String customerId) {
        //them hang kiem tra co phai cccd hop le ko
        try{
            if (checkCCCD(customerId)){
                this.customerId = customerId;
            }else {
                System.out.println("So CCCD khong hop le.");
                System.out.printf("Vui long nhap lai!");
                setCustomerId(customerId);
            }
        }catch (ArithmeticException e) {
            //In ra màn hình loi khong phai CCCD hop le
            System.out.println("Loi:"+e.toString());
        }

    }

    private boolean checkCCCD(String soCCCD ){
        if (soCCCD.length() ==12 && laSoNguyen(soCCCD)) {
            return true;
        }
        return false;
    }

    public boolean laSoNguyen(String s)
    {
        for(int i=0;i < s.length();i++)
        {
            //Neu ky ty dau tien khong phai so 0 thi cung khong hop le
            if(i == 0 && s.charAt(i) != '0') return false;
            if( !Character.isDigit(s.charAt(i)) ) return false;
        }
        return true;
    }
}
