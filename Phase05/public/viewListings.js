// viewListings.js
import { initializeApp }                     from "https://www.gstatic.com/firebasejs/10.12.0/firebase-app.js";
import {  getAuth, onAuthStateChanged } from "https://www.gstatic.com/firebasejs/10.12.0/firebase-auth.js";

import {
  getFirestore,
  collection,
  getDocs,
  query,
  orderBy
} from "https://www.gstatic.com/firebasejs/10.12.0/firebase-firestore.js";


const firebaseConfig = {
  apiKey:            "AIzaSyDq9BVXnpwA72r6ErtyRwiVGkVfPUn0mhg",
  authDomain:        "utd-marketplace-24fab.firebaseapp.com",
  projectId:         "utd-marketplace-24fab",
  storageBucket:     "utd-marketplace-24fab.firebasestorage.app",
  messagingSenderId: "912209099430",
  appId:             "1:912209099430:web:22e2eb9a07f6b62955d267",
  measurementId:     "G-W1GE797TQB"
};


initializeApp(firebaseConfig);
const auth = getAuth();
const db   = getFirestore();

const container = document.getElementById("listingsContainer");

//check if the user is authenticated
onAuthStateChanged(auth, user => {
  if (!user) {
  //if not logged in redirect users to the login page
    window.location.href = "/login.html";
  } else {
  //if user is logged in load the listing
    loadListings();
  }
});

//I created this function to load all listing from the fire base database
//(you all can change it if you have some recommendations)
async function loadListings() {
// displays loading message
  container.innerHTML = "<p>Loading listings…</p>";

  try {
  //Creates a firestore query which fetches all documents in the listing collection
    const q = query(
      collection(db, "listings"),
      orderBy("createdAt", "desc")
    );
    //executes the query and get the snapshot
    const snapshot = await getDocs(q);
    //If no listings are found displays the message
    if (snapshot.empty) {
      container.innerHTML = "<p class='error'>No listings found.</p>";
      return;
    }

    // clears the loading message mentioned in the line 45
    container.innerHTML = "";

    //loop which goes through each listing document
    snapshot.forEach(docSnap => {
      const data = docSnap.data(); //get data of the listing
      const card = document.createElement("div");
      card.className = "listing-card"; // add a class for css styling

    //format the posted date if it exists
      let postedAt = "";
      if (data.createdAt && data.createdAt.toDate) {
        postedAt = new Date(data.createdAt.toDate())
                      .toLocaleString();// convert firestore timestamp to readable date and time
      }
        //set the inner html of the listing card with the listings details
        //displays the listing title
        //displays the listing price formatted to 2 decimal places
        //displays the listings type like electronics, furniture
        //displays the description of the listing
        //displays the contact info for the listing email, phone number
        //displays the user's email who posted the listing it shows when it was posted
      card.innerHTML = `
        <h2>${data.title}</h2>
        <p><strong>Price:</strong> $${data.price.toFixed(2)}</p>
        <p><strong>Category:</strong> ${data.category}</p>
        <p>${data.description}</p>
        <p><strong>Contact:</strong> ${data.contact}</p>
        <p><small>Posted by ${data.userEmail}${postedAt ? ` on ${postedAt}` : ""}</small></p>
      `;
      //append the listing card to container
      container.appendChild(card);
    });

// if there is an error displays a message
  } catch (err) {
    console.error("Error fetching listings:", err);
    container.innerHTML = "<p class='error'>Failed to load listings.</p>";
  }
}