const form = document.getElementById('transpose-form');
const steps = document.getElementById('steps');
const result = document.getElementById('result');
let latestRequest = 0;

async function updateResult() {
    const requestId = ++latestRequest;

    if(!form.reportValidity()){
        result.textContent = '';
        return;
    }

    result.textContent = 'Transponuojama...';

    try {
        const response = await fetch("/api/transpose", {
            method: 'POST',
            body: new URLSearchParams(new FormData(form))
        });
        const text = await response.text();
        if(requestId !== latestRequest) return;

        result.textContent = text;

    } catch (error) {
        if(requestId !== latestRequest) return;
        result.textContent = 'Klaida: nepavyko susisiekti su serveriu.';
    }
}

form.addEventListener('submit', event => {
    event.preventDefault();
    updateResult();
});
document.getElementById("plus").addEventListener('click', () => {
    steps.value = parseInt(steps.value) + 1;
    updateResult();
});
document.getElementById("minus").addEventListener('click', () => {
    steps.value = parseInt(steps.value) - 1;
    updateResult();
});
steps.addEventListener("change", updateResult);


//Antra forma
const keyForm = document.getElementById('transpose-by-key-form');
const keyResult = document.getElementById('key-result');
let latestKeyRequest = 0;

keyForm.addEventListener('submit', async event => {
    event.preventDefault();
    const requestId = ++latestKeyRequest;

    keyResult.textContent = "Transponuojama...";

    try {
        const response = await fetch("/api/transpose-by-key", {
            method: 'POST',
            body: new URLSearchParams(new FormData(keyForm))
        });
        const text = await response.text();
        if(requestId !== latestKeyRequest) return;
        keyResult.textContent = text;
    } catch (error) {
        if(requestId !== latestKeyRequest) return;
        keyResult.textContent = 'Klaida: Nepavyko susisiekti su serveriu:<';
    }
})