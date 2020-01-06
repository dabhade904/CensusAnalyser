package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH_TYPE = "./src/main/resources/IndiaStateCensusData.txt";
    private static final String WRONG_CSV_FILE_PATH_DELIMITER = "/home/admin1/IdeaProjects/CensusAnalyser/CensusAnalyser/src/test/resources/IndianCsvDataDelimeter.csv";
    private static final String INDIA_STATE_CENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String STATE_CODE_WRONG_CSV_FILE_PATH = "./home/admin1/IdeaProjects/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String STATE_WRONG_CSV_FILE_PATH_TYPE = "/home/admin1/IdeaProjects/CensusAnalyser/CensusAnalyser/src/test/resources/StateCodeType.txt";
    private static final String STATE_WRONG_CSV_FILE_PATH_DELIMITER = "/home/admin1/IdeaProjects/CensusAnalyser/CensusAnalyser/src/test/resources/StateCodeDelimiter.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCSVFile_whenCorrectButTypeIncorrect_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void whenGivenCSVFileCorrect_butDelimiterIncorrect_shouldReturnCustomException(){
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        ExpectedException exceptionRule=ExpectedException.none();
        exceptionRule.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA,e.type);
        }
    }

    @Test
    public void whenGivenCSVFileCorrect_butCSVHeaderIncorrect_shouldReturnCustomException() {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        ExpectedException expectedException=ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA,e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadStateCensusData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            System.out.println(numOfRecords);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    @Test
    public void givenStateCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCensusData(STATE_CODE_WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenCSVStateFile_whenCorrectButTypeIncorrect_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(STATE_WRONG_CSV_FILE_PATH_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA,e.type);
        }
    }

    @Test
    public void whenGivenCSVStateFileCorrect_butDelimiterIncorrect_shouldReturnCustomException(){
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        try {
            censusAnalyser.loadStateCensusData(STATE_WRONG_CSV_FILE_PATH_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA,e.type);
        }
    }

    @Test
    public void whenGivenCSVStateFileCorrect_butCSVHeaderIncorrect_shouldReturnCustomException() {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        ExpectedException expectedException=ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadStateCensusData(STATE_WRONG_CSV_FILE_PATH_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA,e.type);
        }
    }
}
