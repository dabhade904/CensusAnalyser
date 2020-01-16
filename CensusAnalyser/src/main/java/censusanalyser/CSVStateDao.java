package censusanalyser;

public class CSVStateDao extends CSVState {
    private int srNo;
    private String stateName;
    private int tin;
    public String stateCode;

    public CSVStateDao(CSVState csvState) {
        srNo = csvState.srNo;
        stateName = csvState.stateName;
        tin = csvState.tin;
        stateCode = csvState.stateCode;
    }
}
