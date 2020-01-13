package censusanalyser;

import cessusanalyser.CSVBuilderException;
import cessusanalyser.CSVBuilderFactory;
import cessusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CensusLoader {

    List<CensusDao> censusList=new ArrayList<>();

    public <E> List<CensusDao> loadCensusData(String csvFilePath, Class<E> censusCSVClass) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<E> csvFileList = csvBuilder.getCSVFileList(reader, censusCSVClass);
            if (censusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")){
                csvFileList.stream().filter(censusData -> censusList.add(new CensusDao((IndiaCensusCSV) censusData))).collect(Collectors.toList());
            } else if(censusCSVClass.getName().equals("censusanalyser.UsCensusCSV")) {
                csvFileList.stream().filter(censusData -> censusList.add(new CensusDao((UsCensusCSV)censusData))).collect(Collectors.toList());
            }
            return this.censusList;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

}
