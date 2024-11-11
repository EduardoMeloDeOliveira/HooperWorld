import React, { useState, useEffect } from 'react';
import { fetchPostsByUserId } from '../../Service/UserService';

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

  if (loading) {
    return <div>Carregando...</div>;
  }

  if (error) {
    return <div className="alert alert-danger">{error}</div>;
  }

  return (
    <div className="container">
      <h3>Meus Posts</h3>
      {posts.length === 0 ? (
        <p>Não há posts para exibir.</p>
      ) : (
        <div className="row">
          {posts.map((post) => (
            <div key={post.postId} className="col-md-6 mb-4">
              <div className="card p-4 shadow-lg">
                <h5>{post.title}</h5>
                <p>{post.content}</p>
                {post.comments.length > 0 && (
                  <div>
                    <strong>Comentários:</strong>
                    <ul>
                      {post.comments.map((comment) => (
                        <li key={comment.commentId}>
                          <strong>{comment.user.name}: </strong>
                          {comment.content}
                        </li>
                      ))}
                    </ul>
                  </div>
                )}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default Post;
