package censusanalyser;

import static censusanalyser.CensusAnalyserException.ExceptionType.*;

public class CensusAnalyserException extends Exception {

    public CensusAnalyserException(String message, String name) {
        super(message);
        this.type= valueOf(name);
    }

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE,INCORRECT_FILE_DATA,NO_CENSUS_DATA,DELIMITER_OR_HEADER_PROBLEM,INVALID_COUNTRY,;
    }

    public ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
