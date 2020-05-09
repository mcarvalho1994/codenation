package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {
    @Override
    public BigDecimal somar(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        return calcularSomaPorAnnotation(fields, "somar", object);
    }

    @Override
    public BigDecimal subtrair(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        return calcularSomaPorAnnotation(fields, "subtrair", object);
    }

    @Override
    public BigDecimal totalizar(Object object) {
        return somar(object).subtract(subtrair(object));
    }

    private BigDecimal calcularSomaPorAnnotation(Field[] fields, String annotation, Object object) {
        BigDecimal bd = BigDecimal.ZERO;
        for(Field field : fields) {
            field.setAccessible(true);
            if(annotation.contains("somar")) {
                if(field.isAnnotationPresent(Somar.class))
                {
                    if(field.getType().equals(BigDecimal.class))
                    {
                        try
                        {
                            bd = bd.add((BigDecimal) field.get(object));
                        } catch (IllegalAccessException e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            else {
                if(field.isAnnotationPresent(Subtrair.class))
                {
                    if(field.getType().equals(BigDecimal.class))
                    {
                        try
                        {
                            bd = bd.add((BigDecimal) field.get(object));
                        } catch (IllegalAccessException e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
        return bd;
    }
}
