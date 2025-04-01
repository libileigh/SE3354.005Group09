package Login_Logout;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
    private Map<String, Integer> users = new HashMap<>();
    public AuthenticationService() {
        // Pre-populate with 15 Gmail accounts and store the hash code of their passwords
        users.put("koushik@gmail.com", "CrazyPapaya@123".hashCode());
        users.put("kushal@gmail.com", "Yoshi@94".hashCode());
        users.put("manat@gmail.com", "Manbat54".hashCode());
        users.put("kumud@gmail.com", "Kumon12".hashCode());
        users.put("liberty@gmail.com", "Libileigh1".hashCode());
        users.put("david@gmail.com", "salamander@67".hashCode());
        users.put("alice@gmail.com", "Alice@987".hashCode());
        users.put("bob@gmail.com", "34@bob43".hashCode());
        users.put("charlie@gmail.com", "87@6phoeniX".hashCode());
        users.put("diana@gmail.com", "Diana@123".hashCode());
        users.put("eve@gmail.com", "hazelnut@364".hashCode());
        users.put("frank@gmail.com", "frank@96".hashCode());
        users.put("george@gmail.com", "12@ruSSel".hashCode());
        users.put("harry@gmail.com", "windsor@5985".hashCode());
        users.put("irene@gmail.com", "Irene@123".hashCode());
    }



}
