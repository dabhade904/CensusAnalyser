package censusanalyser;

import cessusanalyser.CSVBuilderException;
import cessusanalyser.ICSVBuilder;
import com.google.gson.Gson;

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

import static cessusanalyser.CSVBuilderFactory.createCSVBuilder;

public class CensusAnalyser {
    List<CensusDao> censusList = new ArrayList<CensusDao>();
    List<CSVStateDao> csvStateList = null;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        censusList= new CensusLoader().loadCensusData(csvFilePath, IndiaCensusCSV.class);
        return censusList.size();
    }

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        censusList= new CensusLoader().loadCensusData(csvFilePath,UsCensusCSV.class);
        return censusList.size();
    }

//    private List<CensusDao> loadCensusData(String csvFilePath, Class<CensusLoader> censusLoaderClass) {
//    }

    /*   private<E> int loadCensusData(String csvFilePath, Class<E> censusCSVClass) throws CensusAnalyserException {
           try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
               ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
               List <E> csvFileList = csvBuilder.getCSVFileList(reader, censusCSVClass);
               if (censusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")){
                   csvFileList.stream().filter(censusData -> censusList.add(new CensusDao((IndiaCensusCSV) censusData))).collect(Collectors.toList());
               } else if(censusCSVClass.getName().equals("censusanalyser.UsCensusCSV")) {
                   csvFileList.stream().filter(censusData -> censusList.add(new CensusDao((UsCensusCSV)censusData))).collect(Collectors.toList());
               }
               return censusList.size();
           } catch (IOException e) {
               throw new CensusAnalyserException(e.getMessage(),
                       CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
           } catch (RuntimeException e) {
               throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
           } catch (CSVBuilderException e) {
               throw new CensusAnalyserException(e.getMessage(), e.type.name());
           }
       }
   */
    public int loadStateData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = createCSVBuilder();
            List<CSVState>csvFileList = csvBuilder.getCSVFileList(reader,CSVState.class);
            csvFileList.stream().filter(stateCode -> csvFileList.add(new CSVStateDao(stateCode))).collect(Collectors.toList());
            return this.csvStateList.size();
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
        if (censusList == null || censusList.size()==0 ) {
                throw new CensusAnalyserException("No Census Data",
                       CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDao> indiaCensusCSVComparator= Comparator.comparing(census-> census.state);
        this.sortingCensusWiseCSV(censusList,indiaCensusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(this.censusList);
        return sortedStateCensusJson;
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size()==0 ) {
            throw new CensusAnalyserException("No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDao> indiaCensusCSVComparator= Comparator.comparing(census-> census.population);
        this.sortingCensusWiseCSVInDeccending(censusList,indiaCensusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(this.censusList);
        return sortedStateCensusJson;
    }

    public String getPopulationDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size()==0 ) {
            throw new CensusAnalyserException("No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDao> indiaCensusCSVComparator= Comparator.comparing(census-> census.densityPerSqKm);
        this.sortingCensusWiseCSVInDeccending(censusList,indiaCensusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(this.censusList);
        return sortedStateCensusJson;
    }

    public String geStateOnAreaWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size()==0 ) {
            throw new CensusAnalyserException("No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDao> indiaCensusCSVComparator= Comparator.comparing(census-> census.areaInSqKm);
        this.sortingCensusWiseCSVInDeccending(censusList,indiaCensusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(this.censusList);
        return sortedStateCensusJson;
    }

    private void sortingCensusWiseCSVInDeccending(List<CensusDao> censusDAOS, Comparator<CensusDao> censusCSVComparator) {
        for (int i = 0; i < censusDAOS.size() - 1; i++) {
            for (int j = 0; j < censusDAOS.size() - i - 1; j++) {
                CensusDao censusCSV1 = censusDAOS.get(j);
                CensusDao censusCSV2 = censusDAOS.get(j + 1);
                if (censusCSVComparator.compare(censusCSV1, censusCSV2) < 0) {
                    censusDAOS.set(j, censusCSV2);
                    censusDAOS.set(j + 1, censusCSV1);
                }
            }
        }
    }

    private void sortingCensusWiseCSV(List<CensusDao> censusDAOS, Comparator<CensusDao> censusCSVComparator) {
        for (int i = 0; i < censusDAOS.size() - 1; i++) {
            for (int j = 0; j < censusDAOS.size() - i - 1; j++) {
                CensusDao censusCSV1 = censusDAOS.get(j);
                CensusDao censusCSV2 = censusDAOS.get(j + 1);
                if (censusCSVComparator.compare(censusCSV1, censusCSV2) > 0) {
                    censusDAOS.set(j, censusCSV2);
                    censusDAOS.set(j + 1, censusCSV1);
                }
            }
        }
    }

    public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
        if (csvStateList == null || csvStateList.size()==0 ) {
            throw new CensusAnalyserException("No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateDao> csvStateComparator= Comparator.comparing(census-> census.stateCode);
        sortingCodeWiseCSV(csvStateList,csvStateComparator);
        String sortedStateCensusJson = new Gson().toJson(csvStateList);
        return sortedStateCensusJson;
    }

    private void sortingCodeWiseCSV(List<CSVStateDao> censusDAOS, Comparator<CSVStateDao> censusCSVComparator) {
        for (int i = 0; i < censusDAOS.size() - 1; i++) {
            for (int j = 0; j < censusDAOS.size() - i - 1; j++) {
                CSVStateDao censusCSV1 = censusDAOS.get(j);
                CSVStateDao censusCSV2 = censusDAOS.get(j + 1);
                if (censusCSVComparator.compare(censusCSV1, censusCSV2) > 0) {
                    censusDAOS.set(j, censusCSV2);
                    censusDAOS.set(j + 1, censusCSV1);
                }
            }
        }
    }
}