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


onAuthStateChanged(auth, user => {
  if (!user) {
    window.location.href = "/login.html";
  } else {
    loadListings();
  }
});


async function loadListings() {
  container.innerHTML = "<p>Loading listings…</p>";

  try {

    const q = query(
      collection(db, "listings"),
      orderBy("createdAt", "desc")
    );
    const snapshot = await getDocs(q);

    if (snapshot.empty) {
      container.innerHTML = "<p class='error'>No listings found.</p>";
      return;
    }


    container.innerHTML = "";

    snapshot.forEach(docSnap => {
      const data = docSnap.data();
      const card = document.createElement("div");
      card.className = "listing-card";

      let postedAt = "";
      if (data.createdAt && data.createdAt.toDate) {
        postedAt = new Date(data.createdAt.toDate())
                      .toLocaleString();
      }

      card.innerHTML = `
        <h2>${data.title}</h2>
        <p><strong>Price:</strong> $${data.price.toFixed(2)}</p>
        <p><strong>Category:</strong> ${data.category}</p>
        <p>${data.description}</p>
        <p><strong>Contact:</strong> ${data.contact}</p>
        <p><small>Posted by ${data.userEmail}${postedAt ? ` on ${postedAt}` : ""}</small></p>
      `;
      container.appendChild(card);
    });

  } catch (err) {
    console.error("Error fetching listings:", err);
    container.innerHTML = "<p class='error'>Failed to load listings.</p>";
  }
}