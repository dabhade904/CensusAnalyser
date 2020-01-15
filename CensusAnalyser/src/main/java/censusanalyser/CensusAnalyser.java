package censusanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {
    Map<String,CensusDao> censusList = new TreeMap<>();
    public CensusAnalyser() {}
    public enum Country{
        INDIA,US
    }

    public <E> int loadCensusData (Country country, String... csvFilePath) throws CensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.INDIA)) {
            return this.loadIndiaCensusData(IndiaCensusCSV.class, csvFilePath);
        } else if (country.equals(CensusAnalyser.Country.US)) {
            return this.loadUSCensusData(UsCensusCSV.class, csvFilePath[0]);
        } else {
            throw new CensusAnalyserException("INCORRECT_COUNTRY", CensusAnalyserException.ExceptionType.INVALID_COUNTRY);
        }
    }

    public int loadIndiaCensusData(Class  csvClass, String... csvFilePath) throws CensusAnalyserException {
        censusList = new IndiaCensusAdapter().loadCensusData(csvClass , csvFilePath);
        return censusList.size();
    }
    public int loadUSCensusData(Class  csvClass,String csvFilePath) throws CensusAnalyserException {
        censusList = new IndiaCensusAdapter().loadCensusData(csvClass , csvFilePath);
        return censusList.size();
    }



    public String sortingIndiaCensusData() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid File", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List<CensusDao> censuslist = censusList.values().stream().collect(Collectors.toList());
        Comparator<CensusDao> codeCSVComparator = (o1, o2) -> ((o1.state.compareTo(o2.state)) < 0) ? -1 : ((o1.state.compareTo(o2.state)) > 0) ? 1 : 0;
        Collections.sort(censuslist, codeCSVComparator);
        String json = new Gson().toJson(censuslist);
        System.out.println(json);
        return json;
    }

        public String sortingIndianStateCode() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
            List<CensusDao> dataList = censusList.values().stream().collect(Collectors.toList());
            Comparator<CensusDao> codeCSVComparator = (o1, o2) -> ((o1.state.compareTo(o2.state)) < 0) ? -1 : ((o1.state.compareTo(o2.state)) > 0) ? 1 : 0;
            Collections.sort(dataList, codeCSVComparator);
            String json = new Gson().toJson(dataList);
            System.out.println(json);
            return json;
    }

    public String sortingIndiaCensusByPopulation() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List<CensusDao> censuslist = censusList.values().stream().collect(Collectors.toList());
        Comparator<CensusDao> codeCSVComparator = (o1, o2) -> ((o1.population - (o2.population)) > 0) ? -1 :1;
        Collections.sort(censuslist, codeCSVComparator);
        String json = new Gson().toJson(censuslist);
        System.out.println(json);
        return json;
    }

    public String sortingIndiaCensusByDensity() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List<CensusDao> censuslist = censusList.values().stream().collect(Collectors.toList());
        Comparator<CensusDao> codeCSVComparator = (o1, o2) -> ((o1.densityPerSqKm - (o2.densityPerSqKm)) > 0) ? -1 :1;
        Collections.sort(censuslist, codeCSVComparator);
        String json = new Gson().toJson(censuslist);
        System.out.println(json);
        return json;
    }

    public String sortingIndiaCensusByAreaWise() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List<CensusDao> censuslist = censusList.values().stream().collect(Collectors.toList());
        Comparator<CensusDao> codeCSVComparator = (o1, o2) -> ((o1.areaInSqKm - (o2.areaInSqKm)) > 0) ? -1 :1;
        Collections.sort(censuslist, codeCSVComparator);
        String json = new Gson().toJson(censuslist);
        System.out.println(json);
        return json;

    }

}
