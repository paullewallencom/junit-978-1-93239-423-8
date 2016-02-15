public void testMultipleParagraphs() throws Exception {
    XMLUnit.setIgnoreWhitespace(true);

    ArticleBuilder builder = 
        new ArticleBuilder("Testing and XML");

    builder.addAuthorName("J. B. Rainsberger");
    builder.addHeading("A heading.");
    builder.addParagraph("This is a paragraph.");

    String expected = 
        "<?xml version=\"1.0\" ?>"
            + "<article>"
            + "<title>Testing and XML</title>"
            + "<author>J. B. Rainsberger</author>"
            + "<p>This is a paragraph.</p>"
            + "<h1>A heading.</h1>"
            + "</article>";

    String actual = builder.toXml();

    Diff diff = new Diff(expected, actual);
    assertTrue(
        "Builder output is not identical to expected document",
        diff.identical());
}