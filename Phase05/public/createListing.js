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

//checks if the user is logged in
onAuthStateChanged(auth, user => {
  if (!user) {
  //if no user is logged in; it goes to/ redirects to the login page
    window.location.href = "/login.html";
  }
});
//Same as the create account i added an event listener here as well for the submit button
form.addEventListener("submit", async e => {
  e.preventDefault();
  errorDiv.textContent = ""; // clear the error messages if we get any

// gets all the values from the form and trims all white spaces
  const title       = form.title.value.trim();
  const price       = parseFloat(form.price.value);
  const category    = form.category.value;
  const description = form.description.value.trim();
  const contact     = form.contact.value.trim();
  const user        = auth.currentUser;// this is for the current logged-in user where we
  //show posted the listing.

//all of our input fields are required, so I put an if case which checks if all the fields are entered or not
  if (!title || !price || !category || !description || !contact) {
    errorDiv.textContent = "All fields are required.";
    return;
  }
// this adds new listings to a document in firestore called "listings" collections
  try {
    await addDoc(collection(db, "listings"), {
      title,
      price,
      category,
      description,
      contact,
      //stores the user email, uid, and time stamp to exactly see which user added what listing.
      userEmail: user.email,
      userId:    user.uid,
      createdAt: serverTimestamp()
    });
   //Redirect to listings page after successful submission
    window.location.href = "/listings.html";

  } catch (err) {
  //Handle firestore writes error
    console.error("Firestore error: ", err);
    errorDiv.textContent = "Failed to create listing. Please try again.";
  }
});