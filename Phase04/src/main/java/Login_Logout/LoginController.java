package Login_Logout;

public class LoginController {

    public AuthenticationService authService = new AuthenticationService();
    public String login(String email, String password) {
        if (authService.login(email, password)) {
            return "Login successful. Welcome " + authService.getLoggedInUser();
        }
        return "Invalid credentials. Please try again.";
    }

    public String logout() {
        if (authService.isLoggedIn()) {
            authService.logout();
            return "Logged out successfully.";
        }
        return "No user is currently loggedin.";
    }

}