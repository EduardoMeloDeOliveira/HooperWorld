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
      {posts.length === 0 ? (
        <p>Não há posts para exibir.</p>
      ) : (
        <div className="row justify-content-center g-4"> {/* g-4 adiciona o gap entre os itens */}
          {posts.map((post) => (
            <div
              key={post.postId}
              className="col-12 col-md-4 col-lg-3"
            >
              <div
                className="card shadow-sm border-light rounded-3"
                style={{
                  width: '300px', 
                  height: 'auto',  // Ajusta a altura do card conforme o conteúdo
                  display: 'flex',
                  flexDirection: 'column',
                }}
              >
                <div className="card-body d-flex flex-column" style={{ height: '100%' }}>
                  <div className="mb-3">
                    <div>
                      <strong>{post.user.name}</strong>
                    </div>
                    <small className="d-block text-muted mb-2">
                      {new Date(post.createdAt).toLocaleString()}
                    </small>
                    <h5 className="card-title" style={{ fontSize: '1.25rem' }}>
                      {post.title}
                    </h5>
                  </div>

                  <hr /> {/* Traço separando título do conteúdo */}

                  <p
                    className="card-text"
                    style={{
                      fontSize: '1rem',
                      overflow: 'hidden',
                      textOverflow: 'ellipsis',
                      height: 'auto', // Deixa o conteúdo ajustar sua altura
                    }}
                  >
                    {post.content}
                  </p>

                  <div className="mt-3">
                    <button
                      className="btn btn-warning me-2"
                      onClick={() => handleEdit(post.postId)}
                    >
                      <FaEdit /> Editar
                    </button>
                    <button
                      className="btn btn-danger"
                      onClick={() => handleDelete(post.postId)}
                    >
                      <FaTrash /> Deletar
                    </button>
                  </div>
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
