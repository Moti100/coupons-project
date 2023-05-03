package com.moti.server.logic;

import com.moti.server.dal.IUserDal;
import com.moti.server.dto.LoginDto;
import com.moti.server.dto.SuccessfulLoginDto;
import com.moti.server.entities.UserEntity;
import com.moti.server.enums.ErrorType;
import com.moti.server.enums.UserType;
import com.moti.server.exception.ApplicationException;
import com.moti.server.exceptions.ServerException;
import com.moti.server.dto.UserDto;
//import com.moti.server.util.JWTUtils;
import com.moti.server.util.JWTUtils;
import com.moti.server.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.moti.server.constans.Consts.PAGENIATION_FOR_USER;

@Service
public class UserLogic {
    private IUserDal iUserDal;

    @Autowired
    public UserLogic(IUserDal iUserDal) {
        this.iUserDal = iUserDal;
    }

    /***********************************************************************************
     Crud and more operational  functions
     ***********************************************************************************/

    //Login function!
    public String login(LoginDto userLoginDetails) throws Exception {
        SuccessfulLoginDto userData = iUserDal.findLoginUser(userLoginDetails.getUserName(), userLoginDetails.getPassword());
        //Validation - if null - no such
        if (userData == null) {
            throw new ApplicationException("Sorry, this user does not exists", ErrorType.INVALID_USER);
        }
        String token = JWTUtils.createJWT(userData);
        return token;
    }


    //Adding user to the DB
    //Based on build-in function of JPA
    public long createUser(UserEntity userEntity) throws Exception {
        System.out.println(userEntity);
        validateUser(userEntity);
        try {
            iUserDal.save(userEntity);
            return userEntity.getId();
        } catch (Exception e) {
            throw new ApplicationException("Error creating new user", e, ErrorType.CREATION_ERROR);
        }
    }

    //Remove user
    //Based on build-in function of JPA
    public void deleteUser(long id) throws Exception {
        if (isUserExists(id)) {
            try {
                iUserDal.deleteById(id);
            } catch (Exception e) {
                throw new ServerException(e, ErrorType.DELETION_ERROR, "Error deleting user");
            }
        } else {
            throw new ServerException(ErrorType.DELETION_ERROR, "User does not exist");
        }
    }

    //Getting all data from DB about Users-no pagination
    public List<UserDto> getAllDataOfUsers() throws Exception {
        try {
            List<UserDto> listOfUSers = iUserDal.findAllUsers();
            return listOfUSers;
        } catch (Exception e) {
            throw new ServerException(e, ErrorType.GETTING_DATA_FAILED, "Error deleting user");
        }
    }

    //Getting all data from DB about Users-no pagination
    public List<UserDto> getAllDataOfUsers(long pageNumber) throws Exception {
        try {
            Validation.validatePageNumber(pageNumber);
            Pageable paging = PageRequest.of((int) pageNumber, (int) PAGENIATION_FOR_USER);
            List<UserDto> listOfUSers = iUserDal.findAllUsers(paging);
            return listOfUSers;
        } catch (Exception e) {
            throw new ServerException(e, ErrorType.GETTING_DATA_FAILED, "Error deleting user");
        }
    }


    public void updateUser(UserEntity userEntity) throws Exception {
        Validation.validateId(userEntity.getId());
        if (isUserExists(userEntity.getId())) {
            try {
                iUserDal.save(userEntity);
            } catch (Exception e) {
                throw new ServerException(ErrorType.UPDATE_ERROR, "Updating user failed");
            }
        } else {
            throw new ServerException(ErrorType.UPDATE_ERROR, "User does not exist");
        }
    }

    //Getting info of the user- by id
    public UserDto getUserById(long id) throws Exception {
        if (isUserExists(id)) {
            try {
                UserDto userDto = iUserDal.findUserById(id);
                return userDto;
            } catch (Exception e) {
                throw new ServerException(e, ErrorType.Read_Error, "Error Reading the data, try later");
            }
        } else {
            throw new ServerException(ErrorType.Read_Error, "User does not exist");
        }
    }

    /*
    //Getting all data from DB about Users-With pagination
    public List<UserDto> getAllDataOfUsersWithPagination() throws Exception {
        try {
            List<UserDto> listOfUSers = iUserDal.findAllUsers();
            return listOfUSers;
        } catch (Exception e) {
            throw new ServerException(e, ErrorType.GETTING_DATA_FAILED, "Error deleting user");
        }
    }
    */
//
//    //This function gets the user's password
//    //Only Admin can do this action
//    public String getUserPasswordById(int id, UserType userType) throws Exception {
//        validateUserTypeAsAdmin(userType);
//        validateId(id);
//        userDal.getUserPasswordById(id);
//        String password = userDal.getUserPasswordById(id);
//        return password;
//    }
//    //Updating user Password
//    public void updatePassword(int id, String password) throws Exception {
//        validateUserPassword(password);
//        userDal.updateUserPassword(id, password);
//    }
//    //This function get data of a user by id
//    public User getDataOfUserDataById(int id) throws Exception {
//        validateId(id);
//        User user = userDal.getDataOfUserDataById(id);
//        return user;
//    }
//
//    //This function updates the user name
//    public void updateUserName(int id, String name) throws Exception {
//        validateId(id);
//        validateUserName(name);
//        userDal.updateUserName(id, name);
//    }
//
//    //This function updates the user data without password update
//    public void updateUserDetailsWithoutPassword(int id, User user) throws Exception {
//        validateId(id);
//        validateUser(user);
//        userDal.updateUserDetailsWithoutPassword(id, user);
//    }
//
//
//
//    //Getting User data without password
//    public List<User> getAllDataOfUsersWithoutPassword(int pageNumber, UserType userType) throws Exception {
//        validateUserTypeAsAdmin(userType);
//        List<User> userList = userDal.getAllDataOfUsersWithoutPassword(pageNumber);
//        return userList;
//    }
//
//    //Getting users names from DB
//    public List<String> getUserNamesInDb(UserType userType, int pageNumber) throws Exception {
//        validateUserTypeAsAdmin(userType);
//        List<String> usersNames = userDal.getUserNamesInDb(pageNumber);
//        return usersNames;
//    }

    /***********************************************************
     Validations functions
     ************************************************************/
    //    //This function validates user data
    private void validateUser(UserEntity user) throws Exception {
        validateUserName(user.getUserName());
        validateUserType(user.getUserType());
        validateUserPassword(user.getPassword());
        Validation.validateId(user.getCompany().getId());
    }

    //    //validate user password is not empty
    private void validateUserPassword(String password) throws Exception {
        if (password.equals(null) || password.equals("") || password.isEmpty()) {
            //throw new ServerException(ErrorType.INVALID_USER, " User can not be without a password");
            throw new ApplicationException(ErrorType.INVALID_USER);
        }
    }

    //    //Validation of user name
    private void validateUserName(String userName) throws Exception {
        if (userName.equals(null)) {
            throw new ServerException(ErrorType.INVALID_NAME, "The name of the user is not valid");
        }
    }

    //    //This function is used for several logic functions and resources  validations
    void validateUserType(UserType userType) throws Exception {
        if (userType == null) {
            throw new ServerException(ErrorType.INVALID_USER_TYPE, "User Type must have value");
        }
    }

    public boolean isUserExists(long id) {
        boolean isUserExists = iUserDal.existsById(id);
        return isUserExists;
    }

    /*
    public SuccessfulLoginDto getLoginDataForToken(LoginDto loginDto) throws Exception {
        if (loginDto == null) {
            throw new ApplicationException(ErrorType.INVALID_USER);
        }
        try {
            SuccessfulLoginDto successfulLoginDto = iUserDal.findLoginData(loginDto.getUserName(), loginDto.getPassword());
            System.out.println(successfulLoginDto.toStringNoCompanyId());
            return successfulLoginDto;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

*/

    //    public boolean isUserPasswordCorrect(String userName, String password) throws Exception {
//        validateUserPassword(password);
//        String passwordInDb = "";
//        passwordInDb = userDal.getPasswordByUserName(userName);
//        if (passwordInDb.equals(password)) {
//            return true;
//        } else {
//            throw new ServerException(ErrorType.INVALID_PASSWORD, "Password is not correct");
//        }
//    }
//
//    private void validateIdForDeleteUserInDB(long id, UserType userType) throws ServerException {
//        if (id <= 0) {
//            throw new ServerException(ErrorType.DELETION_ERROR, "id number is not valid");
//        }
//        if (userType.equals("Admin")) {
//            throw new ServerException(ErrorType.DELETION_ERROR, "Cant delete an admin user!");
//        }
//    }
//
//    //Validation of user type before creating or deleting user
//    void validateUserTypeAsAdmin(UserType userType) throws ServerException {
//        if (userType == null) {
//            throw new ServerException(ErrorType.INVALID_USER_TYPE, "User Type must have value");
//        }
//        if (userType != UserType.Admin) {
//            throw new ServerException(ErrorType.INVALID_USER_TYPE, "This  user type can not create or remove   information in DB!");
//        }
//    }
//
//

//    void validateUserTypeForDeleteRecords(UserType userType) throws Exception {
//        if (userType != UserType.Admin) {
//            throw new ServerException(ErrorType.INVALID_USER_TYPE, "Only admin can remove a company");
//        }
//        if (userType == null) {
//            throw new ServerException(ErrorType.INVALID_USER_TYPE, "User Type must have value");
//        }
    //    //Used by Purchase Logic-Making sure that the user exists in DB
//    boolean isUserExistsCheckById(int id, UserType userType) throws Exception {
//        validateId(id);
//        validateUserTypeAsAdmin(userType);
//        boolean isUserExists = userDal.isUserExistsCheckById(id);
//        return isUserExists;
//    }
//
    /*
     boolean isUserExists(long id) {
        boolean isUserExists = iUserDal.existsById(id);
        return isUserExists;
    }
    */


}

