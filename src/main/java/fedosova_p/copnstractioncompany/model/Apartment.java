package fedosova_p.copnstractioncompany.model;

import javax.persistence.*;

@Entity(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long apartment_id;

    private int number;
    private int floor;
    private int entrance;
    private double total_area;
    private double living_area;
    private int number_rooms;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buildings_id")
    private Building building;

    public Long getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(Long apartment_id) {
        this.apartment_id = apartment_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getEntrance() {
        return entrance;
    }

    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }

    public double getTotal_area() {
        return total_area;
    }

    public void setTotal_area(double total_area) {
        this.total_area = total_area;
    }

    public double getLiving_area() {
        return living_area;
    }

    public void setLiving_area(double living_area) {
        this.living_area = living_area;
    }

    public int getNumber_rooms() {
        return number_rooms;
    }

    public void setNumber_rooms(int number_rooms) {
        this.number_rooms = number_rooms;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
