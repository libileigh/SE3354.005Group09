/**
 * @jest-environment jsdom
 */

describe('Create Account Form', () => {
  let nameInput, emailInput, passwordInput, phoneInput, submitBtn, errorDiv, successDiv, form;

  beforeEach(() => {
    document.body.innerHTML = `
      <form id="createAccountForm">
        <input type="text" id="name" />
        <input type="email" id="email" />
        <input type="password" id="password" />
        <input type="tel" id="phone" />
        <button type="submit">Submit</button>
      </form>
      <div id="error"></div>
      <div id="success"></div>
    `;

    nameInput = document.getElementById('name');
    emailInput = document.getElementById('email');
    passwordInput = document.getElementById('password');
    phoneInput = document.getElementById('phone');
    submitBtn = document.querySelector('button[type="submit"]');
    errorDiv = document.getElementById('error');
    successDiv = document.getElementById('success');
    form = document.getElementById('createAccountForm');

    form.addEventListener('submit', (e) => {
      e.preventDefault();
      errorDiv.textContent = '';
      successDiv.textContent = '';

      if (!nameInput.value.trim()) {
        errorDiv.textContent = 'Name is required';
        return;
      }

      const emailValue = emailInput.value.trim();
      const emailRegex = /@/;

      if (!emailRegex.test(emailValue)) {
        errorDiv.textContent = 'Invalid email format';
        return;
      }

      if (passwordInput.value.length < 6) {
        errorDiv.textContent = 'Password must be at least 6 characters';
        return;
      }

      successDiv.textContent = 'Account created successfully';
    });
  });

  function triggerSubmit() {
    submitBtn.dispatchEvent(new Event('click', { bubbles: true }));
    form.dispatchEvent(new Event('submit', { bubbles: true }));
  }

  test('TC1: Successful form submission with valid inputs', () => {
    nameInput.value = 'Test User';
    emailInput.value = 'valid@utdallas.edu';
    passwordInput.value = 'Test1234';
    phoneInput.value = '1234567890';

    triggerSubmit();

    expect(errorDiv.textContent).toBe('');
    expect(successDiv.textContent).toBe('Account created successfully');
  });

  test('TC2: Missing name field shows error', () => {
    nameInput.value = '';
    emailInput.value = 'test@utdallas.edu';
    passwordInput.value = 'Test1234';
    phoneInput.value = '1234567890';

    triggerSubmit();

    expect(errorDiv.textContent).toBe('Name is required');
  });

  test('TC3: Invalid email format shows error (no @ symbol)', () => {
    nameInput.value = 'Test User';
    emailInput.value = 'invalid-email';
    passwordInput.value = 'Test1234';
    phoneInput.value = '1234567890';

    triggerSubmit();

    expect(errorDiv.textContent).toBe('Invalid email format');
  });

  test('TC4: Short password shows error', () => {
    nameInput.value = 'Test User';
    emailInput.value = 'test@utdallas.edu';
    passwordInput.value = '123'; // too short
    phoneInput.value = '1234567890';

    triggerSubmit();

    expect(errorDiv.textContent).toBe('Password must be at least 6 characters');
  });


  test('TC5: Whitespace name shows error', () => {
    nameInput.value = '   '; // just whitespace
    emailInput.value = 'test@utdallas.edu';
    passwordInput.value = 'Test1234';
    phoneInput.value = '1234567890';

    triggerSubmit();

    expect(errorDiv.textContent).toBe('Name is required');
  });
});
