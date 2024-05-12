document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('loginForm');
    const login = document.getElementById('login');
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirmPassword');
    const message = document.getElementById('message');

    form.onsubmit = function (event) {
        event.preventDefault();
        message.textContent = '';

        if (password.value === confirmPassword.value) {
            if (password.value.match(/^[a-zA-Z{}]{1,12}$/)) {
                message.textContent = 'Hasła są poprawne i zgodne.';
                message.style.color = 'green';
            } else {
                message.textContent = 'Hasło nie spełnia kryteriów.';
                message.style.color = 'red';
            }
        } else {
            message.textContent = 'Podane hasła nie są identyczne.';
            message.style.color = 'red';
        }
    };
});