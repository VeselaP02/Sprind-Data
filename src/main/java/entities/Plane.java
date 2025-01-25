package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "planes")
public class Plane{
    private static final String PLANE_TYPE = "Plane";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String model;
    private double price;
    private String fuelType;
    private Integer passengerCapacity;
    @OneToMany(mappedBy = "plane",targetEntity = Companies.class,fetch = FetchType.LAZY)
    private Set<Companies> companies;

    public Plane() {
    }

    public Plane(String model, double price, String fuelType, Integer passengerCapacity) {
        this.type = PLANE_TYPE;
        this.model = model;
        this.price = price;
        this.fuelType = fuelType;
        this.passengerCapacity = passengerCapacity;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
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

    public Set<Companies> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Companies> companies) {
        this.companies = companies;
    }
}
