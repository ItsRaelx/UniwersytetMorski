document.addEventListener('DOMContentLoaded', (event) => {
    const form = document.getElementById('comparisonForm');
    const num1Input = document.getElementById('num1');
    const num2Input = document.getElementById('num2');
    const resultDisplay = document.getElementById('result');

    form.onsubmit = function(event) {
        event.preventDefault();

        const num1 = parseInt(num1Input.value);
        const num2 = parseInt(num2Input.value);

        if (num1 <= 0 || num1 > 10 || num2 <= 0 || num2 > 10) {
            resultDisplay.textContent = 'Liczby muszą należeć do przedziału (0,10).';
            return;
        }

        if (num1 < num2) {
            resultDisplay.textContent = '<';
        } else if (num1 > num2) {
            resultDisplay.textContent = '>';
        } else {
            resultDisplay.textContent = '=';
        }
    };
});