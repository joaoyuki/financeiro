/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controleFinanceiro.model.converter;

import javax.persistence.AttributeConverter;

/**
 *
 * @author Administrador
 */
public class BooleanToStringConverter implements AttributeConverter<Boolean, String>{

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute == true){
            return "1";
        } else {
            return "2";
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        
        if (dbData.equals("1")){
            return true;
        } else {
            return false;
        }
        
    }
    
}
