package com.example.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.example.models.Telephone;;

@Converter
public class TelephoneConverter implements AttributeConverter<Telephone, String> {

    @Override
    public String convertToDatabaseColumn(Telephone tel) {
        if (tel == null) return null;

        return "+" + tel.getCountryCode() + "-" +
               tel.getAreaCode() + "-" +
               tel.getExchangeCode() + "-" +
               tel.getSubscriberLine();
    }

    @Override
    public Telephone convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        String[] parts = dbData.replace("+", "").split("-");
        return new Telephone(parts[0], parts[1], parts[2], parts[3]);
    }
}