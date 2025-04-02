package Login_Logout;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {

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

}