document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    try {
        const response = await axios.post('/api/auth/signin', {
            username: username,
            password: password
        });

        // Handle successful login
        const data = response.data;
        alert('Login successful! JWT: ' + data.jwt);
        // You can now store the JWT in localStorage or redirect the user
        localStorage.setItem('token', data.jwt);
        window.location.href = '/home'; // redirect to a secured page
    } catch (error) {
        // Handle login error
        document.getElementById('loginStatus').textContent = 'Login failed: ' + (error.response ? error.response.data.message : 'Unknown error');
    }
});
