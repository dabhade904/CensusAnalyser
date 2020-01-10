package censusanalyser;

public class IndiaCensusDao {
    public int areaInSqKm;
    public String state;
    public int population;
    public int densityPerSqKm;

    public IndiaCensusDao(IndiaCensusCSV indiaCensusCSV){
        state=indiaCensusCSV.state;
        areaInSqKm=indiaCensusCSV.areaInSqKm;
        population=indiaCensusCSV.population;
        densityPerSqKm=indiaCensusCSV.densityPerSqKm;

    }

}
