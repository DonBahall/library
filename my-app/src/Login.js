import { useState } from 'react';
import Cookies from 'js-cookie';

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    function handleSubmit(event) {
        event.preventDefault();
        fetch("http://localhost:8080/users/auth/authenticate", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password }),
        })
            .then((response) => response.text())
            .then((data) => {
                Cookies.set(data)
               console.log(data);
            })
            .catch((error) => {
                console.error(error);
            });
    }

    return (
        <div>
            <h2>Вход</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="email">Почта пользователя:</label>
                    <input
                        type="text"
                        id="email"
                        value={email}
                        onChange={(event) => setEmail(event.target.value)}
                    />
                </div>
                <div>
                    <label htmlFor="password">Пароль:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(event) => setPassword(event.target.value)}
                    />
                </div>
                <button type="submit">Войти</button>
            </form>
        </div>
    );
}

export default Login;