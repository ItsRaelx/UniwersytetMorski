package com.itsraelx.l07.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Cat {
    private @Id
    @GeneratedValue Long id;
    private String name;
    private String breed;

    public Cat(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    public Cat() {
    }

    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getBreed() {
        return this.breed;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof Cat cat))
            return false;
        return Objects.equals(this.id, cat.id) && Objects.equals(this.name, cat.name)
                && Objects.equals(this.breed, cat.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.breed);
    }

    @Override
    public String toString() {
        return "Cat{" + "id=" + this.id + ", name='" + this.name + '\'' + ", breed='" +
                this.breed + '\'' + '}';
    }
}