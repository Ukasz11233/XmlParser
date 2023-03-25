import parser.Parser;
import data.*;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.*;

public class TestParser {

    private Parser parser;
    private DataBase actualDataBase;
    private Data expectedData;
    @Before
    public void initalizeExpectedData() {
        parser = new Parser();
        actualDataBase = new DataBase();
        expectedData = new Data();
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
    public void testReadFileCsv() {
        File directory = new File("./");
        System.out.println(directory.getAbsolutePath());
        parser.parseFile("./../faktury-sprzedazowe-test-2023.csv", actualDataBase);
        assertEquals(expectedData, actualDataBase.getRecords().get(0));
    }

    @Test
    public void testReadFileXlsx() {
        parser.parseFile("./../faktury-sprzedazowe-test-2023.xlsx", actualDataBase);
        assertEquals(expectedData, actualDataBase.getRecords().get(0));
    }

}
