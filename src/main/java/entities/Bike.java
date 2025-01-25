package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "bikes")
@DiscriminatorValue(value = "bike")
public class Bike extends Vehicle{
    public Bike() {
    }

    private static final String BIKE_TYPE = "Bike";
    public Bike(String model, double price, String fuelType) {
        super(BIKE_TYPE, model, price, fuelType);
    }
}
