package url_manager;

public class UrlSetter {
    private static String url;

    public static void setUrl() {
        String TESTS_RUN_ENV = System.getProperty("env");
        if (TESTS_RUN_ENV == null)
            throw new RuntimeException("ERROR: No environment is set");

        url = switch (TESTS_RUN_ENV) {
            case "dev" -> "https://www.staff.am";
            case "staging" -> "https://stage.www.staff.am/en/jobs";
            default -> throw new RuntimeException("ERROR: Wrong environment " + TESTS_RUN_ENV);
        };
    }

    public static String getUrl() {

        return url;
    }
}
//  mvn test -Denv=dev