package com.moti.server.util;

import com.moti.server.dal.IUserDal;
import com.moti.server.enums.ErrorType;
import com.moti.server.exceptions.ServerException;

public class Validation {
    public static void validateId(long id) throws Exception {
        if (id <= 0) {
            throw new ServerException(ErrorType.INVALID_ID, "id is not valid");
        }
    }


    //Validation of the page Number
    //Used also by Purchase Logic
    public static void validatePageNumber(long pageNumber) throws Exception {
        if (pageNumber < 1) {
            throw new ServerException(ErrorType.INVALID_FILED, "page number is not current");
        }
    }
}
