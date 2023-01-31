package org.example.Entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Flats" )
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Flats_id")
    private Long id;
    @Column(name = "Regions",nullable = false)
    private String region;

    @Column(name = "Addresses",nullable = false)
    private String address;
    @Column(name = "Squares",nullable = false)
    private Double square;
    @Column(name = "Rooms",nullable = false)
    private Integer rooms;

    @Column(name = "Prices",nullable = false)
    private BigDecimal price;

    public Flat() {
    }

    public Flat(Long id, String region, String address, Double square, Integer rooms, BigDecimal price) {
        this.id = id;
        this.region = region;
        this.address = address;
        this.square = square;
        this.rooms = rooms;
        this.price = price;
    }

    public Flat(String region, String address, Double square, Integer rooms, BigDecimal price) {
        this.region = region;
        this.address = address;
        this.square = square;
        this.rooms = rooms;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flat flat = (Flat) o;

        if (!getId().equals(flat.getId())) return false;
        if (!getRegion().equals(flat.getRegion())) return false;
        if (!getAddress().equals(flat.getAddress())) return false;
        if (!getSquare().equals(flat.getSquare())) return false;
        if (!getRooms().equals(flat.getRooms())) return false;
        return getPrice().equals(flat.getPrice());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getRegion().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getSquare().hashCode();
        result = 31 * result + getRooms().hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", square=" + square +
                ", rooms=" + rooms +
                ", price=" + price +
                '}';
    }
}
