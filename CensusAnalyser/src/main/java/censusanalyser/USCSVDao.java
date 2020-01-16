package censusanalyser;

public class USCSVDao extends UsCensusCSV {
    private String stateId;
    private String state;
    private int population;
    private int housingUnits;
    private double totalArea;
    private double waterArea;
    private double landArea;
    private double populationDensity;
    private double housingDensity;

    public USCSVDao(UsCensusCSV usCensusCSV){
        stateId=usCensusCSV.stateID;
        state=usCensusCSV.state;
        population=usCensusCSV.population;
        housingUnits=usCensusCSV.housingUnits;
        totalArea=usCensusCSV.totalArea;
        waterArea=usCensusCSV.waterArea;
        landArea=usCensusCSV.landArea;
        populationDensity=usCensusCSV.populationDensity;
        housingDensity=usCensusCSV.housingDensity;
    }
}
