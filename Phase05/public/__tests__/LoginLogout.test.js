/**
 * @jest-environment jsdom
 */
describe('Login Form', () => {
  let emailInput, passwordInput, loginBtn, errorDiv;

  beforeEach(() => {
    document.body.innerHTML = `
      <input type="email" id="email" />
      <input type="password" id="password" />
      <button id="loginBtn">Login</button>
      <div id="error"></div>
    `;

    emailInput = document.getElementById('email');
    passwordInput = document.getElementById('password');
    loginBtn = document.getElementById('loginBtn');
    errorDiv = document.getElementById('error');

    loginBtn.addEventListener('click', () => {
      if (!emailInput.value) {
        errorDiv.textContent = 'Email is required';
        return;
      }
      if (!passwordInput.value) {
        errorDiv.textContent = 'Password is required';
        return;
      }
      if (!emailInput.value.includes('@utdallas.edu')) {
        errorDiv.textContent = 'Only UTD emails allowed';
        return;
      }
      errorDiv.textContent = 'Login successful (mock)';
    });
  });

  test('TC1: Successful login with valid credentials', () => {
    emailInput.value = 'test@utdallas.edu';
    passwordInput.value = 'validPassword';

    loginBtn.click();

    expect(errorDiv.textContent).toBe('Login successful (mock)');
  });

  test('TC2: Empty email shows error', () => {
    passwordInput.value = 'somepassword';

    loginBtn.click();

    expect(errorDiv.textContent).toBe('Email is required');
  });

  test('TC3: Empty password shows error', () => {
    emailInput.value = 'test@utdallas.edu';

    loginBtn.click();

    expect(errorDiv.textContent).toBe('Password is required');
  });

  test('TC4: Non-UTD email shows error', () => {
    emailInput.value = 'test@gmail.com';
    passwordInput.value = 'somepassword';

    loginBtn.click();

    expect(errorDiv.textContent).toBe('Only UTD emails allowed');
  });
});
