package Login_Logout;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
/*
* Hashmap called "users" store the users email address as keys and password as values
* loggedInUser keeps track for which user is currently authenticated,by storing the
* email of the user.
*/

    private Map<String, Integer> users = new HashMap<>();
    private String loggedInUser;
    public AuthenticationService() {

        users.put("koushik@utdallas.edu", "CrazyPapaya@123".hashCode());
        users.put("kushal@utdallas.edu", "Yoshi@94".hashCode());
        users.put("manat@utdallas.edu", "Manbat54".hashCode());
        users.put("kumud@utdallas.edu", "Kumon12".hashCode());
        users.put("liberty@utdallas.edu", "Libileigh1".hashCode());
        users.put("david@utdallas.edu", "salamander@67".hashCode());
        users.put("alice@utdallas.edu", "Alice@987".hashCode());
        users.put("bob@utdallas.edu", "34@bob43".hashCode());
        users.put("charlie@utdallas.edu", "87@6phoeniX".hashCode());
        users.put("diana@utdallas.edu", "Diana@123".hashCode());
        users.put("eve@utdallas.edu", "hazelnut@364".hashCode());
        users.put("frank@utdallas.edu", "frank@96".hashCode());
        users.put("george@utdallas.edu", "12@ruSSel".hashCode());
        users.put("harry@utdallas.edu", "windsor@5985".hashCode());
        users.put("irene@utdallas.edu", "Irene@123".hashCode());
    }
    public boolean login(String email, String password) {

     //The login method authenticates the user.
     //If email is empty or the password is empty display an error message
        if (email == null || email.isEmpty()) {
            System.out.println("Login failed: Email is empty or has not been entered please.");
            return false;
        }
        if (password == null || password.isEmpty()) {
            System.out.println("Login failed: Password is empty or null.");
            return false;
        }
       //checks if the provied email exists in our users hasp map, if it is not found it prints out an error message
        if (!users.containsKey(email)) {
            System.out.println("Login failed: No account found for email: " + email);
            return false;
        }
        //computing the hashed version of the password given by the user.
        //this adds an extra layer of security rather than checking the passwords using plain text.
        int providedPasswordHash = password.hashCode();
        System.out.println("Computed hash for provided password: " + providedPasswordHash);

        //we are retriving the stored hashed passwords corresponding to the email.
        int storedPasswordHash = users.get(email);
        System.out.println("Stored hash for email " + email + ": " + storedPasswordHash);

        //we compare the two provided hash values provided by the user and the stored passwords in the hashmap
        // if both are equal we print the login successful message
        //otherwise we print the invalid login to the console
        if (storedPasswordHash == providedPasswordHash) {
            loggedInUser = email;
            System.out.println("Login successful for user " + email);
            return true;
        } else {
            System.out.println("Login failed Incorrect password for the email " + email);
            return false;
        }
    }

    // if the user log's out we set the loggedInUser to null so that the session could end.
    public void logout() {
        System.out.println("Logging out user: " + loggedInUser);
        loggedInUser = null;
    }

    //
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }
}