package com.pramod.firstapi.excel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.pramod.firstapi.model.Employee;

public class EmployeeExcelImporter {

    public static List<Employee> importEmployees(
            MultipartFile file)
            throws Exception {

        List<Employee> employees =
                new ArrayList<>();

        InputStream input =
                file.getInputStream();

        Workbook workbook =
                new XSSFWorkbook(input);

        Sheet sheet =
                workbook.getSheetAt(0);

        Iterator<Row> rows =
                sheet.iterator();

        rows.next(); // Skip Header

        while (rows.hasNext()) {

            Row row = rows.next();

            Employee employee =
                    new Employee();

            employee.setId(
                    (int) row.getCell(0)
                            .getNumericCellValue());

            employee.setName(
                    row.getCell(1)
                            .getStringCellValue());

            employee.setSalary(
                    row.getCell(2)
                            .getNumericCellValue());

            employees.add(employee);
        }

        workbook.close();

        return employees;
    }
}