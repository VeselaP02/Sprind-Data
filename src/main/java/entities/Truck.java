package entities;


import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "trucks")
public class Truck{
    private static final String TRUCK_TYPE = "Truck";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String model;
    private double price;
    private String fuelType;
    private Double loadCapacity;
    @ManyToMany
    @JoinTable(name = "drivers_trucks",
    joinColumns = @JoinColumn(name = "truck_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "driver_id",referencedColumnName = "id"))
    private Set<Drivers> drivers;

    public Truck() {
    }

    public Truck(String model, double price, String fuelType, Double loadCapacity) {
        this.type = TRUCK_TYPE;
        this.model = model;
        this.price = price;
        this.fuelType = fuelType;
        this.loadCapacity = loadCapacity;

        this.drivers = new HashSet<>();
    }

    public Double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(Double loadCapacity) {
        this.loadCapacity = loadCapacity;
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

    public Set<Drivers> getDrivers() {
        return Collections.unmodifiableSet(this.drivers);
    }

    public void setDrivers(Set<Drivers> drivers) {
        this.drivers = drivers;
    }

    public void addDrivers(Drivers driver){
        this.drivers.add(driver);
    }
}
