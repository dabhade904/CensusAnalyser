package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/main/resources/IndiaStateCensusData.txt";
    private static final String WRONG_CSV_FILE_DATA = "/home/admin1/Downloads/CensusAnalyser/src/test/resources/WrongData.csv";
    private static final String INDIA_STATE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String Null_DATA_CSV_PATH="/home/admin1/Downloads/CensusAnalyser/src/test/resources/NullData.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "/home/admin1/Downloads/CensusAnalyser/src/test/resources/USCensusData.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongType_ShouldCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_DATA);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }

    }

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numberOfStateCode = censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE_PATH);
            Assert.assertEquals(29,numberOfStateCode);
        } catch (CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndiaStatesData_WithWrongType_ShouldCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateData_WithWrongDelimiter_ShouldCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_DATA);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaStateData_WithWrongHeader_ShouldCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_DATA);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (RuntimeException r){
        }
    }
    @Test
    public void givenIndianCensusCsv_WithSorting_ShouldReturn1stElement() throws CensusAnalyserException{
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE_PATH);
        String list = censusAnalyser.sortingIndiaCensusData();
        IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(list,IndiaCensusCSV[].class);
        Assert.assertEquals(true,indiaCensusCSVS[0].state.contains("Andhra Pradesh"));
    }
    @Test
    public void givenIndianCensusCsv_WithSorting_ShouldReturnLastElement() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE_PATH);
        String list = censusAnalyser.sortingIndiaCensusData();
        IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(list,IndiaCensusCSV[].class);
        Assert.assertEquals(true,indiaCensusCSVS[28].state.contains("West Bengal"));
    }
    @Test
    public void givenIndianStatesCsv_WithSorting_ShdataListouldReturn1stElement() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE_PATH);
        String list = censusAnalyser.sortingIndianStateCode();
        CSVState[] indiaStateData = new Gson().fromJson(list,CSVState[].class);
        Assert.assertEquals("Andhra Pradesh",indiaStateData[0].state);
    }
    @Test
    public void givenIndianStatesCsv_WithSorting_ShouldReturnLastElement() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE_PATH);
        String list = censusAnalyser.sortingIndianStateCode();
        CSVState[] indiaStateData = new Gson().fromJson(list,CSVState[].class);
        Assert.assertEquals(true,indiaStateData[28].state.contains("West Bengal"));
    }

    @Test
    public void givenIndianStatesCsv_ShouldReturnNullException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,Null_DATA_CSV_PATH,INDIA_STATE_CSV_FILE_PATH);
            String list = censusAnalyser.sortingIndianStateCode();
            CSVState[] indiaStateData = new Gson().fromJson(list,CSVState[].class);
            Assert.assertEquals(true,indiaStateData[36].state.contains("West Bengal"));
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCodeCsv_ShouldReturnNullException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,Null_DATA_CSV_PATH,INDIA_STATE_CSV_FILE_PATH);
            String list = censusAnalyser.sortingIndianStateCode();
            CSVState[] indiaStateData = new Gson().fromJson(list, CSVState[].class);
            Assert.assertEquals(true,indiaStateData[36].state.contains("West Bengal"));
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCensus_ReturnSortedInLargestPopulationState() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE_PATH);
        String list = censusAnalyser.sortingIndiaCensusByPopulation();
        IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(list,IndiaCensusCSV[].class);
        Assert.assertEquals(true,indiaCensusCSVS[0].state.contains("Uttar Pradesh"));
    }

    @Test
    public void givenIndianStateCensus_ReturnSortedInLowestPopulationState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE_PATH);
            String list = censusAnalyser.sortingIndiaCensusByPopulation();
            IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(list, IndiaCensusCSV[].class);
            Assert.assertEquals(true, indiaCensusCSVS[28].state.contains("Sikkim"));
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensus_ReturnSortedInLargestDensityState() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE_PATH);
        String list = censusAnalyser.sortingIndiaCensusByDensity();
        IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(list, IndiaCensusCSV[].class);
        Assert.assertEquals(true, indiaCensusCSVS[0].state.contains("Bihar"));
    }


    @Test
    public void givenIndianStateCensus_ReturnSortedInLowestDensityState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE_PATH);
            String list = censusAnalyser.sortingIndiaCensusByDensity();
            IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(list, IndiaCensusCSV[].class);
            Assert.assertEquals(true, indiaCensusCSVS[28].state.contains("Arunachal Pradesh"));
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCensus_ReturnSortedInLargestAreaWise() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE_PATH);
            String list = censusAnalyser.sortingIndiaCensusByAreaWise();
            IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(list, IndiaCensusCSV[].class);
            Assert.assertEquals(true, indiaCensusCSVS[0].state.contains("Rajasthan"));
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCensus_ReturnSortedInLowestAreaWise() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE_PATH);
            String list = censusAnalyser.sortingIndiaCensusByAreaWise();
            IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(list, IndiaCensusCSV[].class);
            Assert.assertEquals(true, indiaCensusCSVS[28].state.contains("Goa"));
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51,numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}

