package staffAMTest;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider
    public static Object[][] getTestParameters() {
        return new Object[][]{
               {"Student", "Software development"},
                {"Legal", "Full time"},
                {"Contract", "Yerevan"}
        };
    }
}