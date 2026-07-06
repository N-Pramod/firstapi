package com.pramod.firstapi.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pramod.firstapi.model.Employee;

public class EmployeeExcelExporter {

    public static ByteArrayInputStream export(
            List<Employee> employees)
            throws IOException {

        XSSFWorkbook workbook =
                new XSSFWorkbook();

        XSSFSheet sheet =
                workbook.createSheet("Employees");

        Row header =
                sheet.createRow(0);

        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Salary");

        int rowNum = 1;

        for (Employee employee : employees) {

            Row row =
                    sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(employee.getId());

            row.createCell(1)
                    .setCellValue(employee.getName());

            row.createCell(2)
                    .setCellValue(employee.getSalary());
        }

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        workbook.write(out);

        workbook.close();

        return new ByteArrayInputStream(
                out.toByteArray());
    }
}