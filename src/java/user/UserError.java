/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author SnoweyMTT
 */
public class UserError {
    private String userIDError;
    private String fullNameError;
    private String passwordError;
    private String confirmPasswordError;
    private String userEmailError;

    public UserError() {
    }

    public UserError(String userIDError, String fullNameError, String passwordError, String confirmPasswordError, String userEmailError) {
        this.userIDError = userIDError;
        this.fullNameError = fullNameError;
        this.passwordError = passwordError;
        this.confirmPasswordError = confirmPasswordError;
        this.userEmailError = userEmailError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }

    public String getUserEmailError() {
        return userEmailError;
    }

    public void setUserEmailError(String userEmailError) {
        this.userEmailError = userEmailError;
    }

    
}
