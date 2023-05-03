package com.moti.server.enums;

public enum ErrorType {

    GENERAL_ERROR(601, "General error", true),
    FAILED_TO_GENERATE_ID(602, "Failed to generate new id", false),
    LOGIN_FAILURE(603, "Login failed please try again", false),
    USER_DOES_NOT_EXIST(604, "User does not exist", false),
    PURCHASE_DOES_NOT_EXIST(605, "Purchase does not exist", false),
    COMPANY_DOES_NOT_EXIST(606, "Company does not exist", false),
    INVALID_USERNAME(607, "Username is Invalid please enter a valid email address", false),
    INVALID_PASSWORD(608, "Password must contain at least 8 characters, at least one Upper case letter, one number and one Lower case letter", false),
    INVALID_USER_TYPE(609, "Cannot proceed with this user type", false),
    USERNAME_ALREADY_EXIST(610, "Username already exist", false),
    USER_IS_SUSPENDED(611, "To many attempts to login - user is suspended for 5 minutes", false),
    NO_PERMISSION_GRANTED(612, "No permission granted", false),
    CUSTOMER_DOES_NOT_EXIST(613, "Customer does not exist", false),
    PURCHASE_ALREADY_EXIST(614, "Purchase already exist", false),
    INVALID_PHONE_NUMBER(615, "Phone number is invalid", false),
    COMPANY_ALREADY_EXIST(616, "Company already exist", false),
    INVALID_USER(617, "Invalid user", false),
    COUPON_DOES_NOT_EXIST(618, "Coupon does not exist", false),
    COUPON_ALREADY_EXIST(619, "Coupon already exist", false),
    SQL_ERROR(620, "Something went wrong while trying to execute SQL query", true),
    CREATION_ERROR(621, "Creation error", false),
    DELETION_ERROR(622, "Deletion error", false),
    INVALID_NAME(623, "Please enter a valid name", false),
    UPDATE_ERROR(624, "Update Error", false),
    GET_OBJECT_ERROR(625, "Get object error", false),
    INVALID_DATE(626, "Invalid date", false),
    INVALID_FILED(627, "Invalid filed", false),
    INVALID_COUPON_CODE(628, "Coupon code must contain only upper case letters amd numbers, and code length is between 2 to 6 characters", false),
    INVALID_IMAGE(629, "Image file is invalid", false),
    CUSTOMER_ALREADY_EXIST(630, "Customer is already exist", false),
    COUPON_IS_NOT_EXPIRED(631, "Coupon date is not expired", false),
    INVALID_USER_ID(632, "User id given is wrong", false),
    INVALID_COUPON_ID(633, "Coupon id given is wrong", false),
    COUPON_PRICE_WAS_NOT_EXCEPTED(634, "Price for this coupon was not received", false),
    CATEGORY_CREATION_ISSUE(635, "Category is now can not be  create"),
    INVALID_COMPANY_ID(636, "Invalid Company Id", false),
    COMPANY_IN_USE(637, "Company In Use", false),
    Read_Error(638, "Error getting the information,false"),
    INVALID_CUSROMER_ID(639,"Customer id is not valid",false),
    INVALID_ID_NUMBER(640,"The id number is wrong",false),
    INVALID_PURCHASE_AMOUNT_OR_COST(641,"Purchase amount or cost is wrong",false),
    ErrorWithMaxMinRange(642,"Values are wrong for price range",false),
    INVALID_ID(643,"Id id not valid",false),
    GETTING_DATA_FAILED(644,"Getting data from the DB failed",false);


    private int internalErrorCode;
    private String errorMessage;
    private boolean isShowStackTrace;

    ErrorType(int internalErrorCode, String errorMessage, boolean isShowStackTrace) {
        this.internalErrorCode = internalErrorCode;
        this.errorMessage = errorMessage;
        this.isShowStackTrace = isShowStackTrace;
    }

    ErrorType(int internalErrorCode, String errorMessage) {
        this.internalErrorCode = internalErrorCode;
        this.errorMessage = errorMessage;
    }

    ErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getInternalErrorCode() {
        return internalErrorCode;
    }

    public void setInternalErrorCode(int internalErrorCode) {
        this.internalErrorCode = internalErrorCode;
    }

    public boolean isShowStackTrace() {
        return isShowStackTrace;
    }

    @Override
    public String toString() {
        return "ErrorType{" +
                "internalErrorCode=" + internalErrorCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", isShowStackTrace=" + isShowStackTrace +
                '}';
    }
}
