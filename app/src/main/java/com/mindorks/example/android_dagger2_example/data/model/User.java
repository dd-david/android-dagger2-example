package com.mindorks.example.android_dagger2_example.data.model;

/**
 * Created by janisharali on 25/12/16.
 */

/*
* Model classes for retrieving the DB table.
*
* Db Table 을 바인딩하기 위한 용도로 사용되었습니다
* 즉, User 라는 테이블을 대변합니다
*
* 내부에 column 에 해당하는 맴버변수가 존재하고(x5), 각 getter/setter 가 정의되어 있습니다
* */
public class User {

    private Long id;
    private String name;
    private String address;
    private String createdAt;
    private String updatedAt;

    public User() {
    }

    public User(String name, String address) {
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
