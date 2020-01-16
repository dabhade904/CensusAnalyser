package censusanalyser;

import cessusanalyser.CSVBuilderException;
import cessusanalyser.CSVBuilderFactory;
import cessusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {

    public abstract <E> Map<String, CensusDao> loadCensusData(String... csvFilePath) throws CensusAnalyserException;
    Map<String,CensusDao> censusStateMap = new TreeMap<>();

    public <E> Map<String, CensusDao> loadCensusData(Class<E> censusCSVClass, String... filePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath[0]))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<E> censusCSVList = icsvBuilder.getCSVFileList(reader, censusCSVClass);
            if (censusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                StreamSupport.stream(censusCSVList.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(censusCSV -> censusStateMap.put(censusCSV.state, new CensusDao(censusCSV)));
            }
            if (censusCSVClass.getName().equals("censusanalyser.UsCensusCSV")) {
                StreamSupport.stream(censusCSVList.spliterator(), false)
                        .map(UsCensusCSV.class::cast)
                        .forEach(censusCSV -> censusStateMap.put(censusCSV.stateID, new CensusDao(censusCSV)));
            }
            return censusStateMap ;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
    }

}
