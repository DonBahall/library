import Cookies from 'js-cookie';

import React, { useEffect, useState } from 'react';

function Home() {
    const [book, setBook] = useState(null);
    let token = Cookies.get('token')
    useEffect(() => {
        async function getUserProfile() {
            try {
                const response = await fetch('http://localhost:8080/users/me', {
                    method: 'GET',
                    headers: { Authorization: `Bearer ${token}` },
                });
                setBook(response.data);
            } catch (error) {
                console.error(error);
            }
        }
        getUserProfile();
    }, [token]);

    if (!book) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h2>{book.id}</h2>
            <p>Book: {book.get("book")}</p>
        </div>
    );
}

export default Home