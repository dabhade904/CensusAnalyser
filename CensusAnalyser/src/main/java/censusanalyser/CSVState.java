package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVState extends IndiaCensusCSV {
    @CsvBindByName(column = "SrNo", required = true)
    public int srNo;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Name", required = true)
    public String name;

    @CsvBindByName(column = "TIN", required = true)
    public int tin;

    @CsvBindByName(column = "StateCode" ,required =true)
    public String stateCode;

    public String getStateCode() {
        return stateCode;
    }

    @Override
    public String toString() {
        return "CSVState{" +
                "srNo=" + srNo +
                ", state='" + state + '\'' +
                ", name='" + name + '\'' +
                ", tin=" + tin +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
