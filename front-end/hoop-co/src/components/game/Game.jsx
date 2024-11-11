import React, { useState, useEffect } from 'react';
import { fetchUserProfile } from '../../Service/UserService';
import Post from '../post/Post';
import { Button } from 'react-bootstrap';

function Profile() {
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null);
  const [posts, setPosts] = useState([]); // Lista de posts

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      fetchUserProfile(token)
        .then((data) => {
          setUser(data);
          // Exemplo: Carregar posts aqui
          setPosts(data.posts || []); // Supondo que `data.posts` seja a lista de posts do usuário
        })
        .catch((err) => {
          setError('Erro ao carregar o perfil.');
          console.error(err);
        });
    } else {
      setError('Token de autenticação não encontrado.');
    }
  }, []);

  return (
    <div className="container min-vh-100 d-flex flex-column align-items-center">
      {/* Botão "User Infos" no topo à esquerda */}
      <Button variant="link" style={{ position: 'absolute', top: '20px', left: '20px' }}>
        User Infos
      </Button>

      {/* Área de posts centralizada e exibida em rows */}
      <div className="container text-center mt-5">
        <h2 className="text-center mb-4" style={{ fontSize: '2rem', fontWeight: 'bold' }}>Posts</h2>
        {error && <p className="alert alert-danger">{error}</p>}
        <div className="row justify-content-center">
          {posts.length === 0 ? (
            <p>Nenhum post encontrado.</p>
          ) : (
            posts.map((post, index) => (
              <div key={index} className="col-md-4 mb-4">
                <div className="card h-100" style={{ width: '100%', height: '300px' }}>
                  <div className="card-body">
                    <Post post={post} />
                  </div>
                </div>
              </div>
            ))
          )}
        </div>
      </div>
    </div>
  );
}

export default Profile;
