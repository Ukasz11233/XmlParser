package parser;
import logs.Logs;

import data.Data;
import data.DataBase;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
public class Parser {
    public static void main(String[] args) {
        Parser parser = new Parser();
        DataBase records = new DataBase();
        String filePath = "";
        Logs.info(args[0]);
        Logs.info(args[1]);
        if (args.length < 2) {
            Logs.info("Please enter type of file (xlsx/csv) and name of output file (xml).");
        } else if (args.length == 2) {
            if(args[0].toLowerCase().contains("csv"))
            {
                filePath = "faktury-sprzedazowe-test-short-2023.csv";
            } else if (args[0].toLowerCase().contains("xlsx")) {
                filePath = "faktury-sprzedazowe-test-2023.xlsx";
            }
            if (args[1].contains(".xml")) {
                parser.parseFile(filePath, records);
                parser.generateXML(records, args[1]);
            }else{
                Logs.info("Output file must contain .xml extension.");
            }
        }
    }
    public void parseFile(String filePath, DataBase records) {
        if (Pattern.matches(".*\\.xlsx", filePath)) {
            this.parseXlsx(filePath, records);
        } else if (Pattern.matches(".*\\.csv", filePath)) {
            this.parseCsv(filePath, records);
        } else {
            Logs.info("Wrong input file");
        }
    }

    private void parseXlsx(String filePath, DataBase records)
    {
        try (FileInputStream file = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(file))
        {
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Data dataToInsert = new Data();
                dataToInsert.fillDataRowXls(row);
                records.getRecords().add(dataToInsert);
            }
        }
        catch (Exception e)
        {
            Logs.info(e.toString());
        }
    }

    private void parseCsv(String filePath, DataBase db) {
        try(Reader in = new FileReader(filePath))
        {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter('\t').parse(in);
            records.iterator().next();
            for (CSVRecord csvRecord : records) {
                Data data = new Data();
                data.fillValueFromCsvRecord(csvRecord);
                db.getRecords().add(data);
            }
        } catch (Exception e) {
            Logs.info(e.toString());
        }
    }

    public void generateXML(DataBase records, String outputFile) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(DataBase.class);
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File output = new File(outputFile);
            marshaller.marshal(records, output);
        } catch (Exception e) {
            Logs.info(e.toString());
        }
    }
}
