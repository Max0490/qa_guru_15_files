package qa.homework;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import guru.qa.model.Employee;
import jdk.internal.jmod.JmodFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileParseTestHomework {

    ClassLoader cl = FileParseTestHomework.class.getClassLoader();

    @Test
    void jsonTestWithModel() {
        InputStream is = cl.getResourceAsStream("employee.json");
        Gson gson = new Gson();
        Employee employee = gson.fromJson(new InputStreamReader(is), Employee.class);
        assertThat(Employee.name).isEqualTo("Elena");
        assertThat(Employee.isEmployee).isTrue();
        assertThat(Employee.organization.title).isEqualTo("VTB");
        assertThat(Employee.organization.id).isEqualTo("1234567");
    }

    @Test
    void zipTest() throws Exception {
        Class<Object> cl;
        InputStream is = cl.getResourceAsStream("ZIIPP.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            Assertions.assertThat(entry.getName()).isEqualTo("Example_xlsxl.xlsx");
            Assertions.assertThat(entry.getName()).isEqualTo("Example_pdf.pdf");
            Assertions.assertThat(entry.getName()).isEqualTo("Example_csv.csv");
            switch (entry.getName()) {
                case "Example_csv.csv":
                    JmodFile zf;
                    try (InputStream inputStream = zf.getInputStream(entry);
                         CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                        List<String[]> content = reader.readAll();
                        String[] row = content.get(0);
                        String searchWords = row[1];
                        assertThat(searchWords).isEqualTo("Samara");
                    }
                    break;
                case "Example_xlsxl.xlsx":
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        XLS xls = new XLS(inputStream);
                        assertThat(
                                xls.excel.getSheetAt(1)
                                        .getRow(1)
                                        .getCell(2)
                                        .getStringCellValue()
                        ).isEqualTo("Samara");
                    }
                    break;
                case "Example_pdf.pdf":
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        PDF pdf = new PDF(inputStream);
                        assertThat(pdf.author).isEqualTo("Максим");
                    }
                    break;
        }
    }
}
