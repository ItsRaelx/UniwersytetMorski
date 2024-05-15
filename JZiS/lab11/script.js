document.getElementById('myForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    const radioButtons = document.querySelectorAll('input[type="radio"]');

    let allCheckboxesChecked = true;
    checkboxes.forEach(checkbox => {
        if (!checkbox.checked) {
            allCheckboxesChecked = false;
        }
    });

    let radioButtonChecked = false;
    let selectedRadioValue = '';
    radioButtons.forEach(radioButton => {
        if (radioButton.checked) {
            radioButtonChecked = true;
            selectedRadioValue = radioButton.value;
        }
    });

    let errors = [];

    const usernameRegex = /^[a-zA-Z0-9]{3,20}$/;
    if (!usernameRegex.test(username)) {
        errors.push('Nazwa użytkownika musi zawierać przynajmniej 3 znaki (max 20) i składać się tylko z liter i cyfr.');
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        errors.push('Nieprawidłowy format email.');
    }

    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,29}$/;
    if (!passwordRegex.test(password)) {
        errors.push('Hasło musi zawierać przynajmniej 8 znaków (max 29), w tym jedną literę i jedną cyfrę.');
    }

    if (!allCheckboxesChecked) {
        errors.push('Wszystkie zgody muszą być zaznaczone.');
    }

    if (!radioButtonChecked) {
        errors.push('Musisz wybrać swoją płeć.');
    }

    if (errors.length > 0) {
        alert('Błędy formularza:\n' + errors.join('\n'));
    } else {
        alert(`Dane formularza:\nNazwa użytkownika: ${username}\nEmail: ${email}\nHasło: ${password}\nZgody zaznaczone: Tak\nPłeć: ${selectedRadioValue}`);
    }
});

document.getElementById('resetButton').addEventListener('click', function() {
    document.getElementById('myForm').reset();
});