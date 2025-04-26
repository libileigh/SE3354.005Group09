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
//Initialize firebase app and authentication service
const app  = initializeApp(firebaseConfig);
const auth = getAuth(app);

//shortcut function to get element by ID and get reference to the login button element
const $ = (id) => document.getElementById(id);
const loginBtn = $("loginBtn");

//Adding an event listener when the user clicks login button
loginBtn.addEventListener("click", async () => {
  $("error").textContent = "";

//gets the user input from the email and password fields
  const email    = $("email").value.trim();//gets email input and trim any extra space
  const password = $("password").value;//gets password input


  try {
  // try to sign in the user with firebase authentication
    await signInWithEmailAndPassword(auth, email, password);

  //if successful store the session data in the browser, marks the session as logged in, stores the user email
    sessionStorage.setItem("isLoggedIn", "true");
    sessionStorage.setItem("userEmail",  email);

  // Redirects the user to the create listings page
    window.location.href = "create_listing.html";
  } catch (err) {
  //if login fails displays the error message on page
    $("error").textContent = "Login failed: " + err.message;
  }
});
