package com.utility;

import com.ui.pojo.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {
    public static Iterator<User> readExcelFile(String fileName) {
        File xlsxFile = new File(System.getProperty("user.dir") + "/testData/" + fileName);

        XSSFWorkbook xssfWorkbook = null;
        List<User> userList = null;
        Row row;
        Cell emailCell;
        Cell passwordCell;
        User user;
        XSSFSheet xssfSheet;
        Iterator<Row> rowIterator;

        try {
            xssfWorkbook = new XSSFWorkbook(xlsxFile);
            userList = new ArrayList<User>();
            xssfSheet = xssfWorkbook.getSheet("LoginTestData");
            rowIterator = xssfSheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                emailCell = row.getCell(0);
                passwordCell = row.getCell(1);
                user = new User(emailCell.toString(), passwordCell.toString());
                userList.add(user);
                xssfWorkbook.close();
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

        return userList.iterator();
    }
}
