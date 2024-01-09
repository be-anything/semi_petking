package com.sh.petking.user.model.vo;

import com.sh.petking.club.model.entity.ClubUsers;
import com.sh.petking.grade.model.entity.UserGrade;
import com.sh.petking.user.model.entity.User;

public class UserVo extends User {
    private UserGrade userGrade;
    private ClubUsers clubUsers;



    public ClubUsers getClubUsers() {
        return clubUsers;
    }

    public void setClubUsers(ClubUsers clubUsers) {
        this.clubUsers = clubUsers;
    }

    public void setUserGrade(UserGrade userGrade) {
        this.userGrade = userGrade;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userGrade=" + userGrade +
                ", clubUsers=" + clubUsers +
                '}';
    }

    public UserGrade getUserGrade() {
        return userGrade;
    }
}
