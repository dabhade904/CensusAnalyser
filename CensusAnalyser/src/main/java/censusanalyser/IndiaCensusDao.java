package censusanalyser;

public class IndiaCensusDao {
    private int areaInSqKm;
    public String state;
    private  int population;
    private  int densityPerSqKm;

    public IndiaCensusDao(IndiaCensusCSV indiaCensusCSV){
        state=indiaCensusCSV.state;
        areaInSqKm=indiaCensusCSV.areaInSqKm;
        population=indiaCensusCSV.population;
        densityPerSqKm=indiaCensusCSV.densityPerSqKm;

    }

}
