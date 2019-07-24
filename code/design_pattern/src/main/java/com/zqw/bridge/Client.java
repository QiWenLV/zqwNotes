package com.zqw.bridge;

public class Client {
    public static void main(String[] args) {
        Brand xiaoMi = new XiaoMi();
        FoldePhone foldePhone = new FoldePhone(xiaoMi);
        foldePhone.open();
        foldePhone.call();
        foldePhone.close();
    }
}
