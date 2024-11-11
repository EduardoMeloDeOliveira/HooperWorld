import React, { useState, useEffect } from 'react';
import { fetchUserProfile } from '../../Service/UserService';
import Post from '../post/Post';  // Importando o componente Post

function Profile() {
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      fetchUserProfile(token)
        .then((data) => {
          setUser(data);
        })
        .catch((err) => {
          setError('Erro ao carregar o perfil.');
          console.error(err);
        });
    } else {
      setError('Token de autenticação não encontrado.');
    }
  }, []);

  if (error) {
    return (
      <div className="container d-flex justify-content-center align-items-center" style={{ height: '100vh' }}>
        <div className="alert alert-danger" style={{ fontSize: '1.2rem', padding: '15px 25px', textAlign: 'center' }}>
          {error}
        </div>
      </div>
    );
  }

  return (
    <div className="container d-flex justify-content-center align-items-center" style={{ height: '100vh' }}>
      <div className="card p-5 shadow-lg" style={{ width: '30rem', borderRadius: '15px' }}>
        <h2 className="card-title text-center mb-4" style={{ fontSize: '2rem', fontWeight: 'bold' }}>
          Perfil do Usuário
        </h2>
        <div className="card-body" style={{ fontSize: '1.1rem', lineHeight: '1.8' }}>
          {user ? (
            <>
              <p><strong>Nome:</strong> <span style={{ fontSize: '1.2rem', color: '#4CAF50' }}>{user.name}</span></p>
              <p><strong>E-mail:</strong> <span style={{ fontSize: '1.2rem', color: '#4CAF50' }}>{user.email}</span></p>
              <p><strong>ID:</strong> <span style={{ fontSize: '1.2rem', color: '#4CAF50' }}>{user.userId}</span></p>
            </>
          ) : (
            <p style={{ fontSize: '1.2rem', color: '#999' }}>Carregando perfil...</p>
          )}
        </div>
        
        {/* Seção Meus Posts */}
        <section className="my-5">
          <h3 className="text-center mb-4" style={{ fontSize: '1.5rem', fontWeight: 'bold' }}>
            Meus Posts
          </h3>
          <Post />
        </section>
      </div>
    </div>
  );
}

export default Profile;
