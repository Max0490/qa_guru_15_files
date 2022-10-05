package qa.homework;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import guru.qa.model.Employee;
import guru.qa.model.Teacher;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
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
            String entryName = entry.getName();
        }
    }
}
