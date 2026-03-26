package com.example.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.repositories.TemporaryPasswordRepository;
import com.example.models.TemporaryPassword;

import java.util.List;

@Service
public class TemporaryPasswordService {

    private final TemporaryPasswordRepository temp;

    public TemporaryPasswordService(TemporaryPasswordRepository temp){
        this.temp = temp;
    }
    
    public List<TemporaryPassword> getAllPasswords(){
        return temp.findAll();
    }

    /* @Scheduled(fixedRate = 60000) 
    public void cleanup() {
        temp.deletedExpired();
        System.out.println("Expired passwords cleaned");
    } */
    

}
