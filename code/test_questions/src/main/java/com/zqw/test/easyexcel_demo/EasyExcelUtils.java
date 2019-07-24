package com.zqw.test.easyexcel_demo;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;
import java.util.Objects;

@Slf4j
public class EasyExcelUtils {


    public static void main(String[] args) {
        String filePath = "/home/chenmingjian/Downloads/学生表.xlsx";

    }

    private static Sheet initSheet;

    static {
        initSheet = new Sheet(1, 0);
        initSheet.setSheetName("sheet");
        //设置自适应宽度
        initSheet.setAutoWidth(Boolean.TRUE);
    }

    public static List<Object> readLess(File file, Sheet sheet) throws FileNotFoundException {
        InputStream inputStream = null;
        try {
            sheet = Objects.isNull(sheet) ? initSheet : sheet;
            inputStream = new FileInputStream(file);
            return EasyExcelFactory.read(inputStream, sheet);
        } catch (FileNotFoundException e) {
            log.info("找不到文件或文件路径错误, 文件：{}", file.getAbsolutePath());
        }finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                log.info("excel文件读取失败, 失败原因：{}", e);
            }
        }
        return null;
    }
}
