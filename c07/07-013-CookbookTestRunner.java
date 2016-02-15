public static void main(String args[]) {
    TestResult results = null:
    CookbookTestRunner runner = null;

    if (args.length == 3 && args[0].equals("-o")) {
        try {
            runner = new CookbookTestRunner(args[1], args[2]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    } else if (args.length == 1) {
        try {
            runner = new CookbookTestRunner(args[0]);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }else {
        throw new RuntimeException(
            "Usage: java TestRunner [-o outputFile] Test "
                + System.getProperty("line.separator");
                + "where Test is the fully qualified name of "
                + "a TestCase or TestSuite");
    }

    System.out.println("assertion count = "
        + CountingAssert.getAssertionCount());
}