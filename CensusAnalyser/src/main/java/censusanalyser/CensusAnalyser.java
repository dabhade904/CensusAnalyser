package censusanalyser;

import cessusanalyser.CSVBuilderException;
import cessusanalyser.CSVBuilderFactory;
import cessusanalyser.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;


public class CensusAnalyser {
    List<IndiaCensusCSV> censusCSVIterator = null;
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusCSVIterator = csvBuilder.getCSVFileList(reader,IndiaCensusCSV.class);
            return censusCSVIterator.size();
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
            List<CSVState> censusCSVIterator = csvBuilder.getCSVFileList(reader,CSVState.class);
            return censusCSVIterator.size();
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

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVIterator == null || censusCSVIterator.size()==0 ) {
                throw new CensusAnalyserException("No Census Data",
                       CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> indiaCensusCSVComparator= Comparator.comparing(census-> census.state);
        this.sort(censusCSVIterator,indiaCensusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(censusCSVIterator);
        return sortedStateCensusJson;
    }

    private void sort(List<IndiaCensusCSV> censusDAOS, Comparator<IndiaCensusCSV> censusCSVComparator) {
        for (int i = 0; i < censusDAOS.size() - 1; i++) {
            for (int j = 0; j < censusDAOS.size() - i - 1; j++) {
                IndiaCensusCSV censusCSV1 = censusDAOS.get(j);
                IndiaCensusCSV censusCSV2 = censusDAOS.get(j + 1);
                if (censusCSVComparator.compare(censusCSV1, censusCSV2) > 0) {
                    censusDAOS.set(j, censusCSV2);
                    censusDAOS.set(j + 1, censusCSV1);
                }
            }
        }
    }
}