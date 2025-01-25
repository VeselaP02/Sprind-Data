package entities;

import javax.persistence.*;

@Entity(name = "plate_number")
public class Plate_number {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    @OneToOne(mappedBy = "plate_number",targetEntity = Car.class)
    private Car car;

    public Plate_number() {
    }

    public Plate_number(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
