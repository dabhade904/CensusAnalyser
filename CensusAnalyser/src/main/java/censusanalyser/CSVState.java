package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVState {
    public int SrNo;

    @CsvBindByName(column = "StateName", required = true)
    public String StateName;
    public String state;

    @CsvBindByName(column = "TIN", required = true)
    public int TIN;

    @CsvBindByName(column = "StateCode" ,required =true)
    public String stateCode;

    @Override
    public String toString() {
        return "CSVState{" +
                "SrNo=" + SrNo +
                ", StateName='" + StateName + '\'' +
                ", state='" + state + '\'' +
                ", TIN=" + TIN +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
