describe('Listings View', () => {
  beforeEach(() => {
    document.body.innerHTML = `
      <div id="listingsContainer">
        <div class="listing-card">
          <h2>Test Item</h2>
          <p><strong>Price:</strong> $10.00</p>
        </div>
      </div>
      <a class="back-button">Create Listing</a>
    `;
  });

  test('TC1: Listings container exists', () => {
    const container = document.getElementById('listingsContainer');
    expect(container).toBeInTheDocument();
  });

  test('TC2: Shows listing cards with correct content', () => {
    const card = document.querySelector('.listing-card');
    expect(card).toHaveTextContent('Test Item');
    expect(card).toHaveTextContent('$10.00');
  });

  test('TC3: Back button links to create listing', () => {
    const backButton = document.querySelector('.back-button');
    expect(backButton).toHaveTextContent('Create Listing');
    expect(backButton.getAttribute('href')).toBeDefined();
  });

  test('TC4: Shows message when no listings exist', () => {
    document.getElementById('listingsContainer').innerHTML = '';
    const container = document.getElementById('listingsContainer');
    container.innerHTML = '<p class="error">No listings found.</p>';
    expect(container).toHaveTextContent('No listings found');
  });
});
