package censusanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {
    Map<String,CensusDao> censusList = new HashMap<>();

    private Country country;

    public enum Country{
        INDIA,US
    }

    public int loadCensusData (Country country, String... csvFilePath) throws CensusAnalyserException {
        censusList = CensusAdapterFactory.getCensusData(country,csvFilePath);
        return censusList.size();
    }

    public String sortingIndiaCensusData() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid File", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List list = censusList.values().stream()
                .sorted((data1,data2)-> data1.state.compareTo(data2.state))
                .collect(Collectors.toList());
        String sortedData= new Gson().toJson(list);
        return sortedData;
    }

    public String sortingIndianStateCode() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid File", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List list = censusList.values().stream()
                .sorted((data1,data2)-> data1.state.compareTo(data2.state))
                .collect(Collectors.toList());
        String sortedData= new Gson().toJson(list);
      return sortedData;
    }

    public String sortingIndiaCensusByPopulation() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List list = censusList.values().stream()
                .sorted((data1,data2)-> data1.population - data2.population > 0 ? -1 : 1)
                .collect(Collectors.toList());
        String sortedData= new Gson().toJson(list);
        System.out.println(sortedData);
        return sortedData;
    }

    public String sortingIndiaCensusByDensity() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List list = censusList.values().stream()
                .sorted((data1,data2)-> data1.densityPerSqKm - data2.densityPerSqKm > 0 ? -1 : 1)
                .collect(Collectors.toList());
        String sortedData= new Gson().toJson(list);
        System.out.println(sortedData);
        return sortedData;
    }

    public String sortingIndiaCensusByAreaWise() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }

        List list = censusList.values().stream()
                .sorted((data1,data2)-> data1.areaInSqKm - data2.areaInSqKm > 0 ? -1 : 1)
                .collect(Collectors.toList());
        String sortedData= new Gson().toJson(list);
        System.out.println(sortedData);
        return sortedData;
    }


}