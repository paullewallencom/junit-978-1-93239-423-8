public class IgnoreCatalogDetailsDifferenceListener
    implements DifferenceListener {

    public int differenceFound(Difference difference) {
        int response = RETURN_ACCEPT_DIFFERENCE;

        int differenceId = difference.getId();
        if (DifferenceConstants.TEXT_VALUE_ID
            == differenceId) {

            String currentTagName = 
                getCurrentTagName(difference);

            if (tagNamesToIgnore.contains(currentTagName)) {
                response = RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
            }
        }
        else if (DifferenceConstants.ATTR_VALUE_ID
            == differenceId) {

            Attr attribute = getCurrentAttributes(difference);

            if ("id".equals(attribute.getName())
                && "item".equals(
                    attribute
                        .getOwnerElement()
                        .getNodeName())) {
            response = 
                RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
            }
        }

        return response;
    }

    public void skippedComparison(
        Node expectedNode,
        Node actualNode) {

        // Nothing to do here
    }
}