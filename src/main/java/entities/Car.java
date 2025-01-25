package entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "cars")
public class Car {
    private static final String CAR_TYPE = "Car";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String model;
    private double price;
    private String fuelType;
    private  Integer seats;
    @OneToOne(optional = false)
    @JoinColumn(name = "plate_number_id",
    referencedColumnName = "id")
    private Plate_number plate_number;

    public Car() {
    }

    public Car(String model, double price, String fuelType, Integer seats, Plate_number plate_number) {
        this.type = CAR_TYPE;
        this.model = model;
        this.price = price;
        this.fuelType = fuelType;
        this.seats = seats;
        this.plate_number = plate_number;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Plate_number getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(Plate_number plate_number) {
        this.plate_number = plate_number;
    }
}
