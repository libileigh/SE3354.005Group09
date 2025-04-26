// createAccount.js
import { initializeApp }                           from "https://www.gstatic.com/firebasejs/10.12.0/firebase-app.js";
import { getAuth, createUserWithEmailAndPassword } from "https://www.gstatic.com/firebasejs/10.12.0/firebase-auth.js";
import { getFirestore, doc, setDoc }               from "https://www.gstatic.com/firebasejs/10.12.0/firebase-firestore.js";


const firebaseConfig = {
  apiKey:            "AIzaSyDq9BVXnpwA72r6ErtyRwiVGkVfPUn0mhg",
  authDomain:        "utd-marketplace-24fab.firebaseapp.com",
  projectId:         "utd-marketplace-24fab",
  storageBucket:     "utd-marketplace-24fab.firebasestorage.app",
  messagingSenderId: "912209099430",
  appId:             "1:912209099430:web:22e2eb9a07f6b62955d267",
  measurementId:     "G-W1GE797TQB"
};

const app  = initializeApp(firebaseConfig);
const auth = getAuth(app);
const db   = getFirestore(app);
const form  = document.getElementById("createAccountForm");
const error = document.getElementById("error");

// Eventlistener waits for the submit button to be clicked, So if the user hits the submit
// button it stores the user entered data into the database
form.addEventListener("submit", async (e) => {
  e.preventDefault();//stops the page from refreshing
  error.textContent = ""; //clears any pre-existing errors
success.textContent = "";
//takes in the values users entered in the form and trims the white spaces.
  const name     = form.name.value.trim();
  const email    = form.email.value.trim();
  const password = form.password.value;
  const phone    = form.phone.value.trim();

//create a new user with firebase authentication
  try {
    const { user } = await createUserWithEmailAndPassword(auth, email, password);

//stores the additional user information in firestore using user ID as a document name,
//where it shows name, phone, email.
    await setDoc(doc(db, "users", user.uid), {
      name,
      phone,
      email

    });
    //gives the success message after after clicking create account.
 success.textContent = "Account created. Please click login to continue!";
    alert("Account created successfully! You can log in now.");
    form.reset(); //clears all the fields
    window.location.href = "login.html"; //re-directs to login page
  } catch (err) {
    error.textContent = err.message;
  }
});