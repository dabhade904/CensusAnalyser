package censusanalyser;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {

    @Override
    public <E> Map<String, CensusDao> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        Map<String, CensusDao> censusStateMap = super.loadCensusData(UsCensusCSV.class, csvFilePath);
        return censusStateMap;
    }
}
