package com.sh.petking.pet.model.vo;

import com.sh.petking.grade.model.entity.UserGrade;
import com.sh.petking.pet.model.entity.Pet;
import com.sh.petking.user.model.entity.User;

public class PetVo extends User {
    private Pet pet;

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "PetVo{" +
                "pet=" + pet +
                '}';
    }
}
