package censusanalyser;

public class CensusDao {
    public int areaInSqKm;
    public String state;
    public int population;
    public int densityPerSqKm;

    private String stateId;
    private int housingUnits;
    private double totalArea;
    private double waterArea;
    private double landArea;
    private double populationDensity;
    private double housingDensity;

    public CensusDao(IndiaCensusCSV indiaCensusCSV){
        state=indiaCensusCSV.state;
        areaInSqKm=indiaCensusCSV.areaInSqKm;
        population=indiaCensusCSV.population;
        densityPerSqKm=indiaCensusCSV.densityPerSqKm;
    }

    public CensusDao(UsCensusCSV usCensusCSV) {
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
