package com.example.models;

import com.vaadin.flow.component.login.LoginI18n.ErrorMessage;

import jakarta.persistence.Embeddable;

@Embeddable
public class Telephone {
    
    private String countryCode;
    private String areaCode;
    private String exchangeCode;
    private String subscriberLine;

    public Telephone(String countryCode, String areaCode, String exchangeCode, String subscriberLine){
        setCountryCode(countryCode);
        setAreaCode(areaCode);
        setExchangeCode(exchangeCode);
        setSubscriberLine(subscriberLine);
    }

    //Accessors
    public String getCountryCode(){
        return this.countryCode;
    }
    public String getAreaCode(){
        return this.areaCode;
    }
    public String getExchangeCode(){
        return this.exchangeCode;
    }
    public String getSubscriberLine(){
        return this.subscriberLine;
    }

    //Mutators
    /* All the error codes would have been from the original file that i created the models in but have to add to this new one
    no ai was used just modification of existing code ro add the jakarta setup */
    public void setCountryCode(String countryCode){
        /* if(countryCode == null){
            throw new IllegalArgumentException(
                new ErrorMessage(ErrorMessage.ErrCode.NULL_VALUE);
            );
        }
        if(countryCode.length() > 3){
            throw new IllegalArgumentException(
                new ErrorMessage(ErrorMessage.ErrCode.INVALID_LENGTH);
            );
        } */
        this.countryCode = countryCode;
    }
    public void setAreaCode(String areaCode){
        /* if(areaCode == null){
            throw new IllegalArgumentException(
                    new ErrorMessage(ErrorMessage.ErrCode.NULL_VALUE);
            );
        }
        if(areaCode.length() != 3){
            throw new IllegalArgumentException(
                    new ErrorMessage(ErrorMessage.ErrCode.INVALID_LENGTH);
            );
        } */
        this.areaCode = areaCode;
    }
    public void setExchangeCode(String exchangeCode){
        /* if(exchangeCode == null){
            throw new IllegalArgumentException(
                    new ErrorMessage(ErrorMessage.ErrCode.NULL_VALUE);
            );
        }
        if(exchangeCode.length() != 3){
            throw new IllegalArgumentException(
                    new ErrorMessage(ErrorMessage.ErrCode.INVALID_LENGTH);
            );
        } */
        this.exchangeCode = exchangeCode;
    }
    public void setSubscriberLine(String subscriberLine){
        /* if(exchangeCode == null){
            throw new IllegalArgumentException(
                    new ErrorMessage(ErrorMessage.ErrCode.NULL_VALUE).toString();
            );
        }
        if(exchangeCode.length() != 4){
            throw new IllegalArgumentException(
                    new ErrorMessage(ErrorMessage.ErrCode.INVALID_LENGTH).toString();
            );
        } */
        this.subscriberLine = subscriberLine;
    }

    public String getNumber(){
        return String.format("%s %s-%s-%s", getCountryCode(), getAreaCode(), getExchangeCode(), getSubscriberLine());
    }







}
