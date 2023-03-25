package data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DataBase {
    @XmlElementWrapper(name="records")
    @XmlElement(name="data.Data")
    private List<Data> records = new ArrayList<>();

    public List<Data> getRecords() {
        return records;
    }

}
