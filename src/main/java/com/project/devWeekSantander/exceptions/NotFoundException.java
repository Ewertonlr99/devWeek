package com.project.devWeekSantander.exceptions;

import com.project.devWeekSantander.util.MessageUtils;

public class NotFoundException extends RuntimeException{

    public NotFoundException (){
        super(MessageUtils.NO_RECORDS_FOUND);
    }
}
