package censusanalyser;

public class CSVStateDao extends CSVState {
    public int SrNo;
    public String StateName;
    public String State;
    public int TIN;
    public String StateCode;

    public CSVStateDao(CSVState csvStates) {
        SrNo = csvStates.SrNo;
        StateName = csvStates.StateName;
        State = csvStates.state;
        TIN = csvStates.TIN;
        StateCode = csvStates.stateCode;
    }
}