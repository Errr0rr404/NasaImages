package api;

import org.testng.annotations.Test;

public class APIUnitTest extends SearchEndpoints {

    @Test
    public void qTest() {
        SearchEndpoints.get().testQ();
    }

    @Test
    public void medio_type() {
        SearchEndpoints.get().mediaType();
    }

    @Test
    public void year_start() {
        SearchEndpoints.get().yearStart();
    }

    @Test
    public void year_end() {
        SearchEndpoints.get().yearEnd();
    }
}