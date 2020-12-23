package junit.cookbook.coffee.model;

import java.io.Serializable;

import com.diasparsoftware.java.util.Quantity;

public class CoffeeQuantity
	extends Quantity
	implements Serializable {
        
	public CoffeeQuantity(int kilograms, String coffeeName) {
		super(new Integer(kilograms), coffeeName);
	}

	public int getAmountInKilograms() {
		return magnitude.intValue();
	}

	public String getCoffeeName() {
		return (String) unitOfMeasure;
	}

	public Object clone() {
        return new CoffeeQuantity(getAmountInKilograms(), getCoffeeName());
	}
}
