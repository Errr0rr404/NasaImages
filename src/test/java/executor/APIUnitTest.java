package executor;

import api.SearchEndpoints;
import org.testng.annotations.Test;

public class APIUnitTest extends api.SearchEndpoints {

    /**
     * This method will search for a given keys &
     * validate the status code,header and complete response
     */
    @Test
    public void qTest() {
        SearchEndpoints.get().testQ();
    }

    /**
     * This method will search for media type as images
     * validate the status code,header and complete response
     */
    @Test
    public void medio_type() {
        SearchEndpoints.get().mediaType();
    }

    /**
     * This method will search for start year as given year &
     * validate the status code,header and complete response
     */
    @Test
    public void year_start() {
        SearchEndpoints.get().yearStart();
    }

    /**
     * This method will search for end year as given year &
     * validate the status code,header and complete response
     */
    @Test
    public void year_end() {
        SearchEndpoints.get().yearEnd();
    }
}