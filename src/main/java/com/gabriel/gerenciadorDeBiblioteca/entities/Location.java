package com.gabriel.gerenciadorDeBiblioteca.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String shelf;
    private int column;

    @OneToMany(mappedBy = "location")
    private Set<Book> books = new HashSet<>();

    public Location() {
    }

    public Location(int id, int column) {
        this.id = id;
        this.column = column;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColumn() {
        return column;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return shelf == location.shelf && column == location.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelf, column);
    }
}
