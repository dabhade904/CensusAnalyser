package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVState {
    @CsvBindByName(column = "SrNo", required = true)
    public int srNo;

    @CsvBindByName(column = "StateName", required = true)
    public String stateName;

    @CsvBindByName(column = "TIN", required = true)
    public int tin;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

    @Override
    public String toString() {
        return "CSVState{" +
                "srNo=" + srNo +
                ", stateName='" + stateName + '\'' +
                ", tin=" + tin +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
