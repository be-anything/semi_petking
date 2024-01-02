package com.sh.petking.user.model.vo;

import com.sh.petking.grade.model.entity.UserGrade;
import com.sh.petking.user.model.entity.User;

public class UserVo extends User {
    private UserGrade userGrade;

    public void setUserGrade(UserGrade userGrade) {
        this.userGrade = userGrade;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userGrade=" + userGrade +
                "} " + super.toString();
    }

    public UserGrade getUserGrade() {
        return userGrade;
    }
}
