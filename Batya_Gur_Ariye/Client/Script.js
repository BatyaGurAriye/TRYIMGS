async function sendToServer() {
    const exercise = document.getElementById('exercise').value; 
    try {
        const response = await fetch('/calculate', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ exercise })
        });
        if (!response.ok) throw new Error('Server error'); 
        const data = await response.json();
        document.getElementById('result').textContent = data.result;
    } catch (error) {
        document.getElementById('result').textContent = 'Error: ' + error.message; 
    }
}
