package com.example.lab03_gk.asm01;

import java.util.*;

public class Asm01 {
    private static final String TITLE  = "NGAN HANG SO";
    private static final String AUTHOR  = "DOAN MINH KHUE";
    private static final String VERSION  = "1.0.0";
    public void menu(){
        System.out.println("+-------------+------------------------+--------+");
        System.out.println("|\t"+ TITLE+"\t|\t"+AUTHOR+"@"+VERSION +"\t|");
        System.out.println("+-------------+------------------------+--------+");
        System.out.println("|\t1. Nhap CCCD \t\t\t\t\t\t\t\t|");
        System.out.println("|\t0. Thoat \t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------+------------------------+--------+");
        System.out.printf("Chuc nang: ");

        Scanner s =new Scanner(System.in);
        int chon = s.nextInt();
        switch (chon)
        {
            case 0:
                System.out.println("Ket thuc chuong trinh");
                System.exit(0);
                break;
            case 1:
                nhapMaXacThuc();
                break;
            default:
                System.out.println("Ban chon sai chuc nang, xin vui long nhap lai");
                menu();
        }
    }

    public void nhapMaXacThuc()
    {
        System.out.println("Chon che do sinh day so ngau nhien: ");
        String ngaunhien = ChonCheDo();
        System.out.println("Ma xac thuc: " + ngaunhien);

        //int dayNgauNhien = (int)(Math.random() * (999 - 100 + 1) + 100);
        //ChuoiNgauNhien rand = new ChuoiNgauNhien();
        //String ngaunhien = rand.dayChuSoNgauNhien();
        //System.out.println("Ma xac thuc: " + ngaunhien);

        //System.out.printf("Nhap ma xac thuc: ");
        Scanner s = new Scanner(System.in);
        String maXacThuc  = s.next();
        if (maXacThuc.compareTo(ngaunhien) == 0)
        {
            nhapCCCD();
        }
        else{
            System.out.println("Ma xac thuc khong dung. Vui long thu lai.");
            //Cho phep nguoi dung nhap lai mã xác thực mới de tiep tuc chuong trinh
            nhapMaXacThuc();
        }
    }
    public String ChonCheDo()
    {
        System.out.println("| 1. EASY (3 ky so)");
        System.out.println("| 2. HARD (6 chuoi chu va so)");
        System.out.printf("Che do: ");
        Scanner s = new Scanner(System.in);
        String mode  = s.next();
        try {
            if(Integer.parseInt(mode) ==1 ){
                return chedoEasy();
            }else if (Integer.parseInt(mode) ==2 ){
                return chedoHard();
            }
        } catch (ArithmeticException e) {
            //In ra màn hình tên ngoại lệ
            System.out.println("Loi:"+e.toString());
            //System.out.println("ArithmeticException => " + e.getMessage());
        }
        return chedoEasy();
    }
    public String chedoEasy(){
        ChuoiNgauNhien rand = new ChuoiNgauNhien();
        String dayNgauNhien = rand.soNgauNhien2();
        return dayNgauNhien;
    }
    public String chedoHard(){
        ChuoiNgauNhien rand = new ChuoiNgauNhien();
        String dayNgauNhien = rand.dayChuSoNgauNhien();
        return dayNgauNhien;
    }
    /* Ham Kiem tra chuoi CCCD nhap vao la cac ky tu so nguyen
    * */
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
    public void nhapCCCD(){
        Scanner sc = new Scanner(System.in);
        System.out.printf("Vui long nhap so can cuoc cong dan: ");
        String soCCCD = sc.next();

        //kiem tra tinh hop le cua ma so CCCD
        if (soCCCD.length() ==12 && laSoNguyen(soCCCD))
        {
            menuThongTinChiTiet(soCCCD);
        }
        else {
            System.out.println("So CCCD khong hop le.");
            System.out.printf("Vui long nhap lai hoac 'No' de thoat: ");
            //Neu nguoi dung go No thi goi  thoat chuong trinh
            thoatChuongTrinh();
        }
    }

    public void thoatChuongTrinh()
    {
        Scanner sc = new Scanner(System.in);
        //ky tu nguoi dung nhap vao de thoat chuong trinh
        String no = sc.next();
        if (no.length()==2 && no.charAt(0) == 'N' && no.charAt(1)=='o')
        {
            System.out.println("Ket thuc chuong trinh");
            System.exit(0);
        }else {
            System.out.println("Ban nhap vao sai tu No. Chua the thoat chuong trinh");
            nhapCCCD();
        }
    }
    public void menuThongTinChiTiet(String soCD){
        System.out.println("| 1. Kiem tra noi sinh");
        System.out.println("| 2. Kiem tra tuoi, gioi tinh");
        System.out.println("| 3. Kiem tra so ngau nhien");
        System.out.println("| 0. Thoat");
        System.out.printf("Chuc nang: ");

        Scanner s =new Scanner(System.in);
        int chon = s.nextInt();
        switch (chon)
        {
            case 0:
                System.out.println("Ket thuc chuong trinh");
                System.exit(0);
                break;
            case 1:
                xuatNoiSinh(soCD);
                menuThongTinChiTiet(soCD);
               break;
            case 2:
                xuatGioiTinhVaNamSinh(soCD);
                menuThongTinChiTiet(soCD);
                break;
            case 3:
                xuatSoNgauNhien(soCD);
                menuThongTinChiTiet(soCD);
                break;
            default:
                System.out.println("Ban chon sai chuc nang. Xin vui long nhap lai.");
                menuThongTinChiTiet(soCD);
        }
    }

    public Map<String, String> bangThongTinTinh() {
        Map<String, String> mapThongTinTinh = new HashMap<String, String>();
        mapThongTinTinh.put("001","Ha Noi");
        mapThongTinTinh.put("002","Ha Giang");
        mapThongTinTinh.put("004","Cao Bang");
        mapThongTinTinh.put("005","Ha Noi");
        mapThongTinTinh.put("006","Bac Kan");
        mapThongTinTinh.put("008","Tuyen Quang");
        mapThongTinTinh.put("010","Lao Cai");
        mapThongTinTinh.put("011","Dien Bien");
        mapThongTinTinh.put("012","Lai Chau");
        mapThongTinTinh.put("014","Son La");
        mapThongTinTinh.put("015","Yen Bai");
        mapThongTinTinh.put("017","Hoa Binh");
        mapThongTinTinh.put("019","Thai Nguyen");
        mapThongTinTinh.put("020","Lang Son");
        mapThongTinTinh.put("022","Quang Ninh");
        mapThongTinTinh.put("024","Bac Giang");
        mapThongTinTinh.put("025","Phu Tho");
        mapThongTinTinh.put("026","Vinh Phuc");
        mapThongTinTinh.put("027","Bac Ninh");
        mapThongTinTinh.put("030","Hai Duong");
        mapThongTinTinh.put("031","Hai Phong");
        mapThongTinTinh.put("033","Hung Yen");
        mapThongTinTinh.put("034","Thai Binh");
        mapThongTinTinh.put("035","Ha Nam");
        mapThongTinTinh.put("036","Nam Dinh");
        mapThongTinTinh.put("037","Ninh Binh");
        mapThongTinTinh.put("038","Thanh Hoa");
        mapThongTinTinh.put("040","Nghe An");
        mapThongTinTinh.put("042","Ha Tinh");
        mapThongTinTinh.put("044","Quang Binh");
        mapThongTinTinh.put("045","Quang Tri");
        mapThongTinTinh.put("046","Thua Thien Hue");
        mapThongTinTinh.put("048","Da Nang");
        mapThongTinTinh.put("049","Quang Nam");
        mapThongTinTinh.put("051","Quang Ngai");
        mapThongTinTinh.put("052","Binh Dinh");
        mapThongTinTinh.put("054","Phu Yen");
        mapThongTinTinh.put("056","Khanh Hoa");
        mapThongTinTinh.put("058","Ninh Thuan");
        mapThongTinTinh.put("060","Binh Thuan");
        mapThongTinTinh.put("062","Kon Tum");
        mapThongTinTinh.put("064","Gia Lai");
        mapThongTinTinh.put("066","Dak Lak");
        mapThongTinTinh.put("067","Dak Nong");
        mapThongTinTinh.put("068","Lam Dong");
        mapThongTinTinh.put("070","Binh Phuoc");
        mapThongTinTinh.put("072","Tay Ninh");
        mapThongTinTinh.put("074","Binh Duong");
        mapThongTinTinh.put("075","Dong Nai");
        mapThongTinTinh.put("077","Ba Ria Vung Tau");
        mapThongTinTinh.put("079","Ho Chi Minh");
        mapThongTinTinh.put("080","Long An");
        mapThongTinTinh.put("082","Tien Giang");
        mapThongTinTinh.put("083","Ben Tre");
        mapThongTinTinh.put("084","Tra Vinh");
        mapThongTinTinh.put("086","Vinh Long");
        mapThongTinTinh.put("087","Dong Thap");
        mapThongTinTinh.put("089","An Giang");
        mapThongTinTinh.put("091","Kien Giang");
        mapThongTinTinh.put("092","Can Tho");
        mapThongTinTinh.put("093","Hau Giang");
        mapThongTinTinh.put("094","Soc Trang");
        mapThongTinTinh.put("095","Bac Lieu");
        mapThongTinTinh.put("096","Ca Mau");
        return mapThongTinTinh;
    }
    public void xuatNoiSinh(String soCD){
        //Lay thong tin tinh thu Bang thong tin Tinh Thanh
        Map<String, String> mapThongTinTinh = bangThongTinTinh();

        //xuat 3 ky tu dau tu so can cuoc do nguoi dung nhap vao
        String maTinh = soCD.substring(0,3);
        //System.out.println(" "+ maTinh);

        //Xuat thong tin noi sinh
        Set<String> tapMaTinh = mapThongTinTinh.keySet();
        for (String ma : tapMaTinh) {
            if (ma.compareTo(maTinh)== 0){
                System.out.println("Noi sinh: " + mapThongTinTinh.get(maTinh));
            }
        }
    }
    public void xuatGioiTinhVaNamSinh(String soCD){
        //tach ky tu thu 4 tu so can cuoc cong dan
        String gioiTinh = soCD.substring(3,4);
        //tach 2 ky tu la ky tu thu 5 va 6 tu so can cuoc cong dan
        String namSinh = soCD.substring(4,6);

        if (Integer.parseInt(gioiTinh) == 0 )
        {
            System.out.println("Gioi Tinh: Nam | 19" + namSinh);
        }
        else if (Integer.parseInt(gioiTinh) == 1)
        {
            System.out.println("Gioi Tinh: Nu | 19" + namSinh);
        }
        else if (Integer.parseInt(gioiTinh) == 2)
        {
            System.out.println("Gioi Tinh: Nam | 20" + namSinh);
        }
        else if (Integer.parseInt(gioiTinh) == 3)
        {
            System.out.println("Gioi Tinh: Nu | 20" + namSinh);
        }
        else if (Integer.parseInt(gioiTinh) == 4)
        {
            System.out.println("Gioi Tinh: Nam | 21" + namSinh);
        }
        else if (Integer.parseInt(gioiTinh) == 5)
        {
            System.out.println("Gioi Tinh: Nu | 21" + namSinh);
        }
        else if (Integer.parseInt(gioiTinh) == 6)
        {
            System.out.println("Gioi Tinh: Nam | 22" + namSinh);
        }
        else if (Integer.parseInt(gioiTinh) == 7)
        {
            System.out.println("Gioi Tinh: Nu | 22" + namSinh);
        }
        else if (Integer.parseInt(gioiTinh) == 8)
        {
            System.out.println("Gioi Tinh: Nam | 23" + namSinh);
        }
        else if (Integer.parseInt(gioiTinh) == 9)
        {
            System.out.println("Gioi Tinh: Nu | 23" + namSinh);
        }
        else {
            System.out.println("Chua co du lieu quoc gia ve gioi tinh");
        }
    }
    public void xuatSoNgauNhien(String soCD){
        //tach 6 ký tu cuoi tu so can cuoc
        String dayNgauNhien = soCD.substring(6);
        System.out.println("So Ngau Nhien: "+ dayNgauNhien);
    }
}
