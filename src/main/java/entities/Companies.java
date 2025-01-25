package entities;

import javax.persistence.*;

@Entity(name = "companies")
public class Companies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(optional = false)
    @JoinColumn(name = "plane_id",referencedColumnName = "id")
    private Plane plane;

    public Companies() {
    }

    public Companies(String name, Plane plane) {
        this.name = name;
        this.plane = plane;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
