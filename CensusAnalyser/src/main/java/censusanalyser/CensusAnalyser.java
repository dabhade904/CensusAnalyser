package censusanalyser;

import cessusanalyser.CSVBuilderException;
import cessusanalyser.CSVBuilderFactory;
import cessusanalyser.ICSVBuilder;
import org.json.JSONArray;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader,IndiaCensusCSV.class);
            return getCount(censusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public int loadStateCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVState> censusCSVIterator = csvBuilder.getCSVFileIterator(reader,CSVState.class);
            return this.getCount(censusCSVIterator);

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    private <E> int getCount(Iterator<E> censusCSVIterator) {
        Iterable<E> csvIterable = () -> censusCSVIterator;
        int namOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return namOfEateries;
    }

    public JSONArray getStateWiseSortedCensusData(String csvFilePath) throws IOException, CSVBuilderException {
        Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
        ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
        Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader,IndiaCensusCSV.class);
        return this.sortingCsvIndiaData(censusCSVIterator);
  }

    private JSONArray sortingCsvIndiaData(Iterator<IndiaCensusCSV>csvStateIterator) {
        List <IndiaCensusCSV>list=new ArrayList<>();
        while (csvStateIterator.hasNext()){
            list.add(csvStateIterator.next());
        }
        List<IndiaCensusCSV>stateList=list.stream().sorted(Comparator.comparing(IndiaCensusCSV::getState)).collect(Collectors.toList());
        JSONArray jsonArray=new JSONArray();
         for(int i=0;i<stateList.size();i++)
        {
            jsonArray.put(stateList.get(i));
        }
        System.out.println(jsonArray);
        return jsonArray;
    }


}