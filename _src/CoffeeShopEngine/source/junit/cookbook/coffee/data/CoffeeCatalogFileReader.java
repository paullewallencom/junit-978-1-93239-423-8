package junit.cookbook.coffee.data;

import java.io.*;
import java.util.regex.*;

import junit.cookbook.coffee.model.CoffeeCatalog;

import com.diasparsoftware.java.util.Money;

public class CoffeeCatalogFileReader {
    private Pattern catalogLinePattern = Pattern
        .compile("(.+),(.+),(.+)");

    public CoffeeCatalog load() throws IOException {
        CoffeeCatalog catalog = new CoffeeCatalog();

        BufferedReader reader = new BufferedReader(
            new FileReader(new File("data/catalog.txt")));

        while (true) {
            String line = reader.readLine();

            if (line == null) break;

            Matcher matcher = catalogLinePattern.matcher(line);
            if (matcher.matches()) {
                String productId = matcher.group(1);
                String coffeeName = matcher.group(2);
                String unitPriceAsString = matcher.group(3);
                Money unitPrice = Money
                    .parse(unitPriceAsString);

                catalog.addCoffee(productId, coffeeName,
                                  unitPrice);
            }
        }

        return catalog;
    }
}