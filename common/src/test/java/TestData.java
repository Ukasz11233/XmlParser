import logs.Logs;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import data.Data;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import static org.junit.Assert.*;

public class TestData {

    private Data data;
    private Data expectedData;
    @Before
    public void initalizeExpectedData() {
        expectedData = new Data();
        data = new Data();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        expectedData.setName("Firma5 SP. Z O.O.");
        expectedData.setAdress("UL. FELIKSA RADWAŃSKIEGO 15/1, 30-065 KRAKÓW");
        expectedData.setNIP("634-27-26-447");
        try {
            expectedData.setDateOfExpose(formatter.parse("2020-10-09"));
            expectedData.setDateOfSell(formatter.parse("2020-10-09"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        expectedData.setInvoiceNr("3-09/10/2020");
        expectedData.setPosName("Czynsz z tytułu najmu powierzchni w budynku przy ul. Radwańskiego 15 w Krakowie za Październik 2020");
        expectedData.setNumOfItems(1);
        expectedData.setUnitPrice(3000.0);
        expectedData.setTaxRate(23.0);
        expectedData.setTaxAmount(690.0);
        expectedData.setNetPriceOfItem(3000.0);
        expectedData.setGrossPriceOfItem(3690.0);
        expectedData.setNetInvoice(3000.0);
        expectedData.setGrossInvoice(3690.0);
    }
    @Test
    public void testFillValueFromCsvRecord() {
        try {
            Reader in = new FileReader("./../faktury-sprzedazowe-test-2023.csv");
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter('\t').parse(in);
            records.iterator().next();
            for (CSVRecord record : records) {
                data.fillValueFromCsvRecord(record);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(expectedData, data);
    }

    @Test
    public void testFillDataRowXls() {
        try {
            FileInputStream file = new FileInputStream("./../faktury-sprzedazowe-test-2023.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            {
                XSSFSheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                Row row = rowIterator.next();
                data.fillDataRowXls(row);

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assertEquals(expectedData, data);
    }
}
