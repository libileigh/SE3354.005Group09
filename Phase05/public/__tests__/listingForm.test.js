describe('Create Listing Form', () => {
  beforeEach(() => {
    document.body.innerHTML = `
      <form id="listingForm">
        <input type="text" id="title" required />
        <input type="number" id="price" required min="0" step="0.01" />
        <select id="category" required>
          <option value="">Select</option>
          <option value="electronics">Electronics</option>
        </select>
        <textarea id="description" required></textarea>
        <input type="text" id="contact" required />
        <div id="error-message"></div>
      </form>
    `;
  });

  test('TC1: Form has all required fields', () => {
    const form = document.getElementById('listingForm');
    expect(form.querySelector('#title')).toBeRequired();
    expect(form.querySelector('#price')).toBeRequired();
    expect(form.querySelector('#category')).toBeRequired();
    expect(form.querySelector('#description')).toBeRequired();
    expect(form.querySelector('#contact')).toBeRequired();
  });

  test('TC2: Price field only accepts numbers', () => {
  const priceInput = document.getElementById('price');

  priceInput.value = 'abc';
  const invalidEvent = new Event('input', { bubbles: true });
  priceInput.dispatchEvent(invalidEvent);

  expect(priceInput.validity.badInput || isNaN(priceInput.valueAsNumber)).toBe(true);

  priceInput.value = '123';
  const validEvent = new Event('input', { bubbles: true });
  priceInput.dispatchEvent(validEvent);

  expect(priceInput.validity.badInput).toBe(false);
  expect(priceInput.valueAsNumber).toBe(123);
});

  test('TC3: Form shows error when fields are empty', () => {
    const form = document.getElementById('listingForm');
    const errorDiv = document.getElementById('error-message');

   form.addEventListener('submit', (e) => {
      e.preventDefault();
      if (!form.title.value) {
        errorDiv.textContent = 'Title is required';
      }
    });

    form.dispatchEvent(new Event('submit'));
    expect(errorDiv.textContent).toBe('Title is required');
  });

  test('TC4: Category dropdown has options', () => {
    const categorySelect = document.getElementById('category');
    expect(categorySelect.options.length).toBe(2);
    expect(categorySelect.options[1].value).toBe('electronics');
  });
});
