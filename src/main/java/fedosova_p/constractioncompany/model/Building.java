package fedosova_p.constractioncompany.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buildingSeq")
    @SequenceGenerator(name = "buildingSeq", initialValue = 10, allocationSize = 1, sequenceName = "building_SEQUENCE")
    private Long building_id;

    private String city;
    private String street;
    private String number;
    private String name;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date constraction_start_date;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date expected_commissioning_date;
    @Nullable
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date commissioning_date;

    public Long getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Long building_id) {
        this.building_id = building_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getConstraction_start_date() {
        return constraction_start_date;
    }

    public void setConstraction_start_date(Date constraction_start_date) {
        this.constraction_start_date = constraction_start_date;
    }

    public Date getExpected_commissioning_date() {
        return expected_commissioning_date;
    }

    public void setExpected_commissioning_date(Date expected_commissioning_date) {
        this.expected_commissioning_date = expected_commissioning_date;
    }

    public Date getCommissioning_date() {
        return commissioning_date;
    }

    public void setCommissioning_date(Date commissioning_date) {
        this.commissioning_date = commissioning_date;
    }
}
