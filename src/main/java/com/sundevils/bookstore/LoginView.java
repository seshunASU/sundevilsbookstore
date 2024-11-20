package com.sundevils.bookstore;

public class LoginView extends View {
    private LoginPage loginPage;

    public LoginView() {
        super();
        
        loginPage = new LoginPage();

        setPage(loginPage);
    }
}
