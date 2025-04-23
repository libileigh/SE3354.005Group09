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

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  error.textContent = "";

  const name     = form.name.value.trim();
  const email    = form.email.value.trim();
  const password = form.password.value;
  const phone    = form.phone.value.trim();

  try {
    const { user } = await createUserWithEmailAndPassword(auth, email, password);

    await setDoc(doc(db, "users", user.uid), {
      name,
      phone,
      email

    });
 success.textContent = "Account created. Please click login to continue!";
    alert("Account created successfully! You can log in now.");
    form.reset();
    window.location.href = "login.html";
  } catch (err) {
    error.textContent = err.message;
  }
});