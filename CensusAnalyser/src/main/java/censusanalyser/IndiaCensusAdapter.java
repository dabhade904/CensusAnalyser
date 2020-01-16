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

public class IndiaCensusAdapter extends CensusAdapter {
    Map<String, CensusDao> censusStateMap = new TreeMap<>();


    public <E> int loadIndianStateCode(String filePath, Map<String, CensusDao> censusStateMap) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<CSVState> stateDataList = icsvBuilder.getCSVFileList(reader, CSVState.class);
            StreamSupport.stream(stateDataList.spliterator(), false)
                    .filter(IndiaStateCode -> censusStateMap.get(IndiaStateCode.stateCode) != null)
                    .forEach(censusData -> censusStateMap.get(censusData.stateCode).state = censusData.stateCode);
            return censusStateMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    @Override
    public <E> Map<String, CensusDao> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        Map<String, CensusDao> censusStateMap = super.loadCensusData(IndiaCensusCSV.class, csvFilePath[0]);
        this.loadIndianStateCode(csvFilePath[1], censusStateMap);
        return censusStateMap;
    }
}
