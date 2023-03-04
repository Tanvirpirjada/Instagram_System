package com.example.Instagram_System.Exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHndaler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> methodArgumentNotValidException(MethodArgumentNotValidException obj){
        BindingResult resutl=obj.getBindingResult();
        List<ObjectError> error=resutl.getAllErrors();
        Map<String,String> eroorsmap=new HashMap<>();
        for(ObjectError er: error){
            String message=er.getDefaultMessage();
            String field=((FieldError)er).getField();
            eroorsmap.put(field,message);

        }
        return eroorsmap;
    }

//    @ExceptionHandler({Exception.class})
//    public String exception(Exception obj){
//        String massage=obj.getMessage();
//        return massage;
//    }
}
