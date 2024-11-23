package com.sundevils.bookstore;

public class LoginView extends View {
    public LoginPage loginPage;
    public SignUpPage signupPage;
    public ForgotPasswordPage forgotPasswordPage;

    public LoginView() {
        super();
        
        loginPage = new LoginPage();
        signupPage = new SignUpPage();
        forgotPasswordPage = new ForgotPasswordPage();

        setPage(loginPage);
    }

    public void resetState() {
        
    }
}
