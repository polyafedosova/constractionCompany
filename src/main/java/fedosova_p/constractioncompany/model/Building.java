package fedosova_p.constractioncompany.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuSeq")
    @SequenceGenerator(name = "menuSeq", initialValue = 10, allocationSize = 1, sequenceName = "MENU_SEQUENCE")
    private Long building_id;

    private String address;
    private String name;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date constraction_start_date;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date expected_commissioning_date;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date commissioning_date;

    public Long getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Long building_id) {
        this.building_id = building_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
