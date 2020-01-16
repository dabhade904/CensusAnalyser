package censusanalyser;

import java.util.Map;

public class CensusAdapterFactory {
    public static <E> Map<String, CensusDao> getCensusData(CensusAnalyser.Country country, String... csvFilePath) throws CensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.INDIA)) {
            return new IndiaCensusAdapter().loadCensusData(csvFilePath);
        } else if (country.equals(CensusAnalyser.Country.US)) {
            return new USCensusAdapter().loadCensusData(csvFilePath);
        } else {
            throw new CensusAnalyserException("INCORRECT_COUNTRY", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
}

