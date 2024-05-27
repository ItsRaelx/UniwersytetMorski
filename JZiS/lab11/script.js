document.addEventListener('DOMContentLoaded', function() {
    const checkboxes = [
        { name: 'option1', label: 'Subskrypcja newslettera' },
        { name: 'option2', label: 'Akceptacja regulaminu' },
        { name: 'option3', label: 'Przetwarzanie danych osobowych' }
    ];

    const radios = [
        { name: 'choice', value: 'kobieta', label: 'Kobieta' },
        { name: 'choice', value: 'mężczyzna', label: 'Mężczyzna' },
        { name: 'choice', value: 'nie podano', label: 'Wole nie podawać' }
    ];

    function createCheckboxes(checkboxes) {
        const checkboxTable = document.getElementById('checkboxTable');
        checkboxes.forEach(checkbox => {
            const row = document.createElement('tr');
            const cell = document.createElement('td');
            const label = document.createElement('label');
            label.className = 'checkbox-label';

            const input = document.createElement('input');
            input.type = 'checkbox';
            input.name = checkbox.name;

            label.appendChild(input);
            label.appendChild(document.createTextNode(checkbox.label));
            cell.appendChild(label);
            row.appendChild(cell);
            checkboxTable.appendChild(row);
        });
    }

    function createRadios(radios) {
        const radioTable = document.getElementById('radioTable');
        radios.forEach(radio => {
            const row = document.createElement('tr');
            const cell = document.createElement('td');
            const label = document.createElement('label');
            label.className = 'radio-label';

            const input = document.createElement('input');
            input.type = 'radio';
            input.name = radio.name;
            input.value = radio.value;

            label.appendChild(input);
            label.appendChild(document.createTextNode(radio.label));
            cell.appendChild(label);
            row.appendChild(cell);
            radioTable.appendChild(row);
        });
    }

    createCheckboxes(checkboxes);
    createRadios(radios);

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
});
