// createListing.js
import { initializeApp }                           from "https://www.gstatic.com/firebasejs/10.12.0/firebase-app.js";
import { getAuth,onAuthStateChanged} from "https://www.gstatic.com/firebasejs/10.12.0/firebase-auth.js";

import {
  getFirestore,
  collection,
  addDoc,
  serverTimestamp
} from "https://www.gstatic.com/firebasejs/10.12.0/firebase-firestore.js";

const firebaseConfig = {
  apiKey: "AIzaSyDq9BVXnpwA72r6ErtyRwiVGkVfPUn0mhg",
  authDomain: "utd-marketplace-24fab.firebaseapp.com",
  projectId: "utd-marketplace-24fab",
  storageBucket: "utd-marketplace-24fab.firebasestorage.app",
  messagingSenderId: "912209099430",
  appId: "1:912209099430:web:22e2eb9a07f6b62955d267",
  measurementId: "G-W1GE797TQB"
};


initializeApp(firebaseConfig);
const auth = getAuth();
const db   = getFirestore();

const form       = document.getElementById("listingForm");
const errorDiv   = document.getElementById("error-message");


onAuthStateChanged(auth, user => {
  if (!user) {
    window.location.href = "/login.html";
  }
});

form.addEventListener("submit", async e => {
  e.preventDefault();
  errorDiv.textContent = "";

  const title       = form.title.value.trim();
  const price       = parseFloat(form.price.value);
  const category    = form.category.value;
  const description = form.description.value.trim();
  const contact     = form.contact.value.trim();
  const user        = auth.currentUser;

  if (!title || !price || !category || !description || !contact) {
    errorDiv.textContent = "All fields are required.";
    return;
  }

  try {
    await addDoc(collection(db, "listings"), {
      title,
      price,
      category,
      description,
      contact,
      userEmail: user.email,
      userId:    user.uid,
      createdAt: serverTimestamp()
    });

    window.location.href = "/listings.html";

  } catch (err) {
    console.error("Firestore error: ", err);
    errorDiv.textContent = "Failed to create listing. Please try again.";
  }
});