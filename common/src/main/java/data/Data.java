package data;

import logs.Logs;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.bind.annotation.*;
import org.apache.commons.csv.CSVRecord;
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    public Data() {
        // Required by XML api
    }

    private static final String WHITE_SPACE_AND_ZL = "zł|\u00a0";
    private static final String COMA = ",";
    public void fillValueFromCsvRecord(CSVRecord csvRecord) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            this.setName(csvRecord.get(0));
            this.setAdress(csvRecord.get(1));
            this.setNIP(csvRecord.get(2));
            this.setDateOfExpose(formatter.parse(csvRecord.get(3)));
            this.setDateOfSell(formatter.parse(csvRecord.get(4)));
            this.setInvoiceNr(csvRecord.get(5));
            this.setPosName(csvRecord.get(6));
            this.setNumOfItems(Double.parseDouble(csvRecord.get(7).replace(",", ".")));
            this.setUnitPrice(Double.parseDouble(csvRecord.get(8).replaceAll(WHITE_SPACE_AND_ZL, "").replace(COMA, ".")));
            this.setTaxRate(Double.parseDouble(csvRecord.get(9)));
            this.setTaxAmount(Double.parseDouble(csvRecord.get(10).replaceAll(WHITE_SPACE_AND_ZL, "").replace(COMA, ".")));
            this.setNetPriceOfItem(Double.parseDouble(csvRecord.get(11).replaceAll(WHITE_SPACE_AND_ZL, "").replace(COMA, ".")));
            this.setGrossPriceOfItem(Double.parseDouble(csvRecord.get(12).replaceAll(WHITE_SPACE_AND_ZL, "").replace(COMA, ".")));
            this.setNetInvoice(Double.parseDouble(csvRecord.get(13).replaceAll(WHITE_SPACE_AND_ZL, "").replace(COMA, ".")));
            this.setGrossInvoice(Double.parseDouble(csvRecord.get(14).replaceAll(WHITE_SPACE_AND_ZL, "").replace(COMA, ".")));

        } catch (ParseException e) {
            Logs.info(e.toString());
        }
    }
    public void fillDataRowXls(Row row) {
        int counter = 0;
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                fillDataDependingOnCounterXls(counter, cell);
                counter++;
            }
        }


    private void fillDataDependingOnCounterXls(int counter, Cell cell) {
        switch (counter) {
            case 0:
                this.setName(cell.getStringCellValue());
                break;
            case 1:
                this.setAdress(cell.getStringCellValue());
                break;
            case 2:
                this.setNIP(cell.getStringCellValue());
                break;
            case 3:
                this.setDateOfExpose(cell.getDateCellValue());
                break;
            case 4:
                this.setDateOfSell(cell.getDateCellValue());
                break;
            case 5:
                this.setInvoiceNr(cell.getStringCellValue());
                break;
            case 6:
                this.setPosName(cell.getStringCellValue());
                break;
            case 7:
                this.setNumOfItems(cell.getNumericCellValue());
                break;
            case 8:
                this.setUnitPrice(cell.getNumericCellValue());
                break;
            case 9:
                this.setTaxRate(cell.getNumericCellValue());
                break;
            case 10:
                this.setTaxAmount(cell.getNumericCellValue());
                break;
            case 11:
                this.setNetPriceOfItem(cell.getNumericCellValue());
                break;
            case 12:
                this.setGrossPriceOfItem(cell.getNumericCellValue());
                break;
            case 13:
                this.setNetInvoice(cell.getNumericCellValue());
                break;
            case 14:
                this.setGrossInvoice(cell.getNumericCellValue());
                break;
            default:
                Logs.info("Ivalid counter value in Data.java");
                break;
        }
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public void setNIP(String nip) {
        this.nip = nip;
    }

    public void setDateOfExpose(Date dateOfExpose) {
        this.dateOfExpose = dateOfExpose;
    }

    public void setDateOfSell(Date dateOfSell) {
        this.dateOfSell = dateOfSell;
    }

    public void setInvoiceNr(String invoiceNr) {
        this.invoiceNr = invoiceNr;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public void setNumOfItems(double numOfItems) {
        this.numOfItems = numOfItems;
    }


    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }


    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }


    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }


    public void setNetPriceOfItem(double netPriceOfItem) {
        this.netPriceOfItem = netPriceOfItem;
    }

    public void setGrossPriceOfItem(double grossPriceOfItem) {
        this.grossPriceOfItem = grossPriceOfItem;
    }


    public void setNetInvoice(double netInvoice) {
        this.netInvoice = netInvoice;
    }

    public void setGrossInvoice(double grossInvoice) {
        this.grossInvoice = grossInvoice;
    }

    private String name;
    private String adress;
    private String nip;
    private Date dateOfExpose;
    private Date dateOfSell;
    private String invoiceNr;
    private String posName;
    private double numOfItems;
    private double unitPrice;
    private double taxRate;
    private double taxAmount;
    private double netPriceOfItem;
    private double grossPriceOfItem;
    private double netInvoice;
    private double grossInvoice;

    @Override
    public String toString() {
        return "data.Data{" +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", nip='" + nip + '\'' +
                ", dateOfExpose=" + dateOfExpose +
                ", dateOfSell=" + dateOfSell +
                ", invoiceNr='" + invoiceNr + '\'' +
                ", posName='" + posName + '\'' +
                ", numOfItems=" + numOfItems +
                ", unitPrice=" + unitPrice +
                ", taxRate=" + taxRate +
                ", taxAmount=" + taxAmount +
                ", netPriceOfItem=" + netPriceOfItem +
                ", grossPriceOfItem=" + grossPriceOfItem +
                ", netInvoice=" + netInvoice +
                ", grossInvoice=" + grossInvoice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Double.compare(data.numOfItems, numOfItems) == 0 &&
                Double.compare(data.unitPrice, unitPrice) == 0 &&
                Double.compare(data.taxRate, taxRate) == 0 &&
                Double.compare(data.taxAmount, taxAmount) == 0 &&
                Double.compare(data.netPriceOfItem, netPriceOfItem) == 0 &&
                Double.compare(data.grossPriceOfItem, grossPriceOfItem) == 0 &&
                Double.compare(data.netInvoice, netInvoice) == 0 &&
                Double.compare(data.grossInvoice, grossInvoice) == 0 &&
                Objects.equals(name, data.name) && Objects.equals(adress, data.adress) &&
                Objects.equals(nip, data.nip) && Objects.equals(dateOfExpose, data.dateOfExpose) &&
                Objects.equals(dateOfSell, data.dateOfSell) && Objects.equals(invoiceNr, data.invoiceNr) &&
                Objects.equals(posName, data.posName);
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, adress, nip, dateOfExpose, dateOfSell, invoiceNr, posName, numOfItems, unitPrice, taxRate, taxAmount, netPriceOfItem, grossPriceOfItem, netInvoice, grossInvoice);
    }
}
