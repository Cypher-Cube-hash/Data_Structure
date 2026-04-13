package com.example.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.repositories.TemporaryPasswordRepository;

import jakarta.transaction.Transactional;

import com.example.models.TemporaryPassword;
import com.example.datastructures.temporaryPassword.TemporaryPasswordLinkedList;
import com.example.datastructures.temporaryPassword.TemporaryPasswordList;


@Service
public class TemporaryPasswordService {

    private final TemporaryPasswordRepository temp;

    public TemporaryPasswordService(TemporaryPasswordRepository temp){
        this.temp = temp;
    }
    
    public TemporaryPasswordList getPasswordsFromLinkedList(TemporaryPasswordLinkedList linkedList) {
        return linkedList.toList(); 
    }

    @Scheduled(fixedRate = 60000) 
    public void cleanup() {
        temp.deletedExpired();
        System.out.println("Expired passwords cleaned");
    }
    

}
