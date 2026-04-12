package com.example.models;

import jakarta.persistence.*;

import com.example.enums.TypeGender;
import com.example.utils.AdminUtils;

import java.time.LocalDate;

@Entity
@Table(
    name = "admin"
)
public class Admin {
    @Id
    @Column(name = "AdminId", unique = true, nullable = false)
    private String adminId;

    @OneToOne
    @JoinColumn(name = "UserId", unique = true, nullable = false, referencedColumnName = "UserID")
    private User user;

    @Column(name = "Gender")
    private TypeGender gender;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    public Admin(){
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Admin(User user, TypeGender gender){
        this.adminId = AdminUtils.adminIdGenerator(10);
        this.user = user;
        this.gender = gender;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Admin(Admin admin){
        this.user = admin.user;
        this.gender = admin.gender;
        this.createdAt = admin.createdAt;
        this.updatedAt = admin.updatedAt;
    }

    public String getAdminId(){
        return adminId;
    }
    public void setAdminId(String adminId){
        this.adminId = adminId;
    }
    
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
    
    public TypeGender getGender(){
        return gender;
    }
    public void setGender(TypeGender gender){
        this.gender = gender;
    }
    
    public LocalDate getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDate createdAt){
        this.createdAt = createdAt;
    }
    
    public LocalDate getUpdatedAt(){
        return updatedAt;
    }
    public void setUpdatedAt(LocalDate updatedAt){
        this.updatedAt = updatedAt;
    }
    


}
