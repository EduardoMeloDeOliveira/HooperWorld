import React, { useState, useEffect } from 'react';
import { fetchPostsByUserId } from '../../Service/UserService';
import { FaEdit, FaTrash } from 'react-icons/fa'; // Ícones de editar e deletar

function Post() {
  const [posts, setPosts] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  const token = localStorage.getItem('token');

  useEffect(() => {
    if (token) {
      fetchPostsByUserId(token)
        .then((data) => {
          setPosts(data);
          setLoading(false);
        })
        .catch((err) => {
          setError('Erro ao carregar os posts.');
          setLoading(false);
        });
    } else {
      setError('Token não encontrado.');
      setLoading(false);
    }
  }, [token]);

  const handleEdit = (postId) => {
    // Lógica para editar o post
    console.log(`Editando post com ID: ${postId}`);
  };

  const handleDelete = (postId) => {
    // Lógica para deletar o post
    console.log(`Deletando post com ID: ${postId}`);
  };

  if (loading) {
    return <div>Carregando...</div>;
  }

  if (error) {
    return <div className="alert alert-danger">{error}</div>;
  }

  return (
    <div className="container">
        <h2 className='text-center'>Meus posts</h2>
      {posts.length === 0 ? (
        <p>Não há posts para exibir.</p>
      ) : (
        <div className="row justify-content-center g-4"> 
          {posts.map((post) => (
            <div
              key={post.postId}
              className="col-12 col-md-6 col-lg-4" 
            >
              <div
                className="card shadow-sm border-light rounded-3"
                style={{
                  width: '100%', 
                  display: 'flex',
                  flexDirection: 'column',
                }}
              >
                <div className="card-body d-flex flex-column" style={{ height: '100%' }}>
                  <div className="d-flex justify-content-between align-items-center mb-3">
                    <div>
                      <strong>{post.user.name}</strong>
                      <small className="d-block text-muted">
                        {new Date(post.createdAt).toLocaleString()}
                      </small>
                    </div>
                    <div>
                      <button
                        className="btn btn-sm btn-primary me-2"
                        onClick={() => handleEdit(post.postId)}
                      >
                        <FaEdit />
                      </button>
                      <button
                        className="btn btn-sm btn-danger"
                        onClick={() => handleDelete(post.postId)}
                      >
                        <FaTrash />
                      </button>
                    </div>
                  </div>

                  <h5 className="card-title" style={{ fontSize: '1.25rem' }}>
                    {post.title}
                  </h5>

                  <hr /> {/* Traço separando título do conteúdo */}

                  <p
                    className="card-text"
                    style={{
                      fontSize: '1rem',
                      overflow: 'hidden',
                      textOverflow: 'ellipsis',
                      whiteSpace: 'nowrap', // Impede o texto de quebrar linha
                      height: 'auto',
                    }}
                  >
                    {post.content}
                  </p>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default Post;