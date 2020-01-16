package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class UsCensusCSV {
    @CsvBindByName(column = "State Id", required = true)
    public String stateID;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "Housing units", required = true)
    public int housingUnits;

    @CsvBindByName(column = "Total area", required = true)
    public double totalArea;

    @CsvBindByName(column = "Water area", required = true)
    public double waterArea;

    @CsvBindByName(column = "Land area", required = true)
    public double landArea;

    @CsvBindByName(column = "Population Density", required = true)
    public double populationDensity;

    @CsvBindByName(column = "Housing Density", required = true)
    public double housingDensity;

    public UsCensusCSV(){

    }
    public UsCensusCSV(String stateID, String state, int population, double totalArea, double waterArea) {
        this.stateID = stateID;
        this.state = state;
        this.population = population;
        this.housingUnits = housingUnits;
        this.totalArea = totalArea;
        this.waterArea = waterArea;
        this.landArea = landArea;
        this.populationDensity = populationDensity;
        this.housingDensity = housingDensity;
    }

    @Override
    public String toString() {
        return "UsCensusCSV{" +
                "stateID='" + stateID + '\'' +
                ", state='" + state + '\'' +
                ", population=" + population +
                ", housingUnits=" + housingUnits +
                ", totalArea=" + totalArea +
                ", waterArea=" + waterArea +
                ", landArea=" + landArea +
                ", populationDensity=" + populationDensity +
                ", housingDensity=" + housingDensity +
                '}';
    }
}
