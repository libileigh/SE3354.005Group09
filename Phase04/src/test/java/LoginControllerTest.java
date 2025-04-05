import Login_Logout.LoginController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    // Test case 1: checking for valid login credentials for user koushik@utdallas.edu
    @Test
    public void TC_01()
    {
        LoginController controller = new LoginController();
        String expected = "Login successful. Welcome koushik@utdallas.edu";
        String actual = controller.login("koushik@utdallas.edu", "CrazyPapaya@123");
        assertEquals(expected, actual);
    }

    // Test case 2: checking for valid login credentials for user kushal@utdallas.edu
    @Test
    public void TC_02()
    {
        LoginController controller = new LoginController();
        String expected = "Login successful. Welcome kushal@utdallas.edu";
        String actual = controller.login("kushal@utdallas.edu", "Yoshi@94");
        assertEquals(expected, actual);
    }

    // Test case 3: checking for valid login credentials for user manat@utdallas.edu
    @Test
    public void TC_03()
    {
        LoginController controller = new LoginController();
        String expected = "Login successful. Welcome manat@utdallas.edu";
        String actual = controller.login("manat@utdallas.edu", "Manbat54");
        assertEquals(expected, actual);
    }

    // Test case 4: checking for a valid user email with an incorrect password
    @Test
    public void TC_04()
    {
        LoginController controller = new LoginController();
        String expected = "Invalid credentials. Please try again.";
        String actual = controller.login("koushik@utdallas.edu", "WrongPassword");
        assertEquals(expected, actual);
    }

    // Test case 5: checking for a valid user email with an incorrect password
    @Test
    public void TC_05()
    {
        LoginController controller = new LoginController();
        String expected = "Invalid credentials. Please try again.";
        String actual = controller.login("kushal@utdallas.edu", "Incorrect");
        assertEquals(expected, actual);
    }

    // Test case 6: checking for login with an invalid user
    @Test
    public void TC_06()
    {
        LoginController controller = new LoginController();
        String expected = "Invalid credentials. Please try again.";
        String actual = controller.login("nonexistent@utdallas.edu", "SomePassword");
        assertEquals(expected, actual);
    }

    // Test case 7: checking with empty email input
    @Test
    public void TC_07()
    {
        LoginController controller = new LoginController();
        String expected = "Invalid credentials. Please try again.";
        String actual = controller.login("", "SomePassword");
        assertEquals(expected, actual);
    }

    // Test case 8: checking with empty password input
    @Test
    public void TC_08()
    {
        LoginController controller = new LoginController();
        String expected = "Invalid credentials. Please try again.";
        String actual = controller.login("alice@utdallas.edu", "");
        assertEquals(expected, actual);
    }

    // Test case 9: checking with null email input
    @Test
    public void TC_09()
    {
        LoginController controller = new LoginController();
        String expected = "Invalid credentials. Please try again.";
        String actual = controller.login(null, "Alice@987");
        assertEquals(expected, actual);
    }
    
    // Test case 10: checking with null email input
    @Test
    public void TC_10()
    {
        LoginController controller = new LoginController();
        String expected = "Invalid credentials. Please try again.";
        String actual = controller.login("alice@utdallas.edu", null);
        assertEquals(expected, actual);
    }
}
