/* login.js */

import { initializeApp }                                  from "https://www.gstatic.com/firebasejs/10.12.0/firebase-app.js";
import { getAuth, signInWithEmailAndPassword }            from "https://www.gstatic.com/firebasejs/10.12.0/firebase-auth.js";

const firebaseConfig = {
  apiKey: "AIzaSyDq9BVXnpwA72r6ErtyRwiVGkVfPUn0mhg",
  authDomain: "utd-marketplace-24fab.firebaseapp.com",
  projectId: "utd-marketplace-24fab",
  storageBucket: "utd-marketplace-24fab.firebasestorage.app",
  messagingSenderId: "912209099430",
  appId: "1:912209099430:web:22e2eb9a07f6b62955d267",
  measurementId: "G-W1GE797TQB"
};

const app  = initializeApp(firebaseConfig);
const auth = getAuth(app);

const $ = (id) => document.getElementById(id);
const loginBtn = $("loginBtn");

loginBtn.addEventListener("click", async () => {
  $("error").textContent = "";

  const email    = $("email").value.trim();
  const password = $("password").value;

  try {
    await signInWithEmailAndPassword(auth, email, password);

    sessionStorage.setItem("isLoggedIn", "true");
    sessionStorage.setItem("userEmail",  email);

    window.location.href = "create_listing.html";
  } catch (err) {
    $("error").textContent = "Login failed: " + err.message;
  }
});
