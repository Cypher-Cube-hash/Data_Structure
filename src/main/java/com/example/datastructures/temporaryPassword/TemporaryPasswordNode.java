package com.example.datastructures.temporaryPassword;

import com.example.models.TemporaryPassword;

public class TemporaryPasswordNode {
    private TemporaryPassword TemporaryPassword_entity;
    private TemporaryPasswordNode TemporaryPasswordNext;

    public TemporaryPasswordNode(TemporaryPassword TemporaryPassword) {
        this.TemporaryPassword_entity = TemporaryPassword;
        this.TemporaryPasswordNext = null;
    }

    //Accessors
    public TemporaryPassword getTemporaryPassword() { 
        return TemporaryPassword_entity; 
    }
    public TemporaryPasswordNode getTemporaryPasswordNext() { 
        return TemporaryPasswordNext; 
    }
    //Mutators
    public void setTemporaryPassword(TemporaryPassword TemporaryPassword) { 
        this.TemporaryPassword_entity = TemporaryPassword; 
    }
    public void setTemporaryPasswordNext(TemporaryPasswordNode next) { 
        this.TemporaryPasswordNext = next; 
    }
}
