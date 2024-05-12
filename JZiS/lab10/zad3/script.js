document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('validationForm');
    const birthdate = document.getElementById('birthdate');
    const street = document.getElementById('street');
    const postalCode = document.getElementById('postalCode');
    const message = document.getElementById('message');

    form.onsubmit = function (event) {
        event.preventDefault();
        let valid = true;
        
        form.querySelectorAll('input').forEach(input => {
            input.classList.remove('error');
        });

        if (!street.value.trim()) {
            valid = false;
            street.classList.add('error');
        }
        
        if (!birthdate.value.match(/^\d{2}-\d{2}-\d{4}$/)) {
            valid = false;
            birthdate.classList.add('error');
        }

        if (!postalCode.value.match(/^\d{2}-\d{3}$/)) {
            valid = false;
            postalCode.classList.add('error');
        }

        if (valid) {
            message.textContent = 'OK';
            message.className = 'valid';
        } else {
            message.textContent = 'Nie wpisano wszystkich danych lub dane są nieprawidłowe.';
            message.className = 'error';
        }
    };
});

function resetForm() {
    document.getElementById('validationForm').reset();
    document.querySelectorAll('input').forEach(input => {
        input.classList.remove('error');
    });
    document.getElementById('message').textContent = '';
}