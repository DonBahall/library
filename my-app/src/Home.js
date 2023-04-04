import Cookies from 'js-cookie';

import React, { useEffect, useState } from 'react';

function Home() {
    const [user, setUser] = useState(null);
    let token = Cookies.get('token')
    useEffect(() => {
        async function getUserProfile() {
            try {
                const response = await fetch('http://localhost:8080/users/me', {
                    method: 'GET',
                    headers: { Authorization: `Bearer ${token}` },
                });
                setUser(response.data);
            } catch (error) {
                console.error(error);
            }
        }
        getUserProfile();
    }, [token]);

    if (!user) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h2>{user.name}</h2>
            <p>Email: {user.email}</p>
            <p>Role: {user.role}</p>
        </div>
    );
}

export default Home