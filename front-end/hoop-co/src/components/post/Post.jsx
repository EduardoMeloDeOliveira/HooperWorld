import React, { useState, useEffect } from 'react';
import { FaEdit, FaTrash, FaThumbsUp } from 'react-icons/fa';
import { useLocation, useNavigate } from 'react-router-dom';
import { deletePost } from '../../Service/UserService'; 
import { toast } from 'react-toastify';

function Post({ posts }) {
  const [updatedPosts, setUpdatedPosts] = useState(posts);
  const userId = localStorage.getItem("userId");
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    setUpdatedPosts(posts);
  }, [posts]);

  const pageTitle = location.pathname === '/profile' ? 'Meus Posts' : 'Timeline';

  const handleEdit = (postId) => {
    console.log(`Editando post com ID: ${postId}`);
  };

  const handleDelete = async (postId) => {
    const token = localStorage.getItem('token');
    try {
      await deletePost(token, postId);
      setUpdatedPosts(updatedPosts.filter(post => post.postId !== postId));
      toast.success('Post deletado com sucesso!');
      navigate(0);  
    } catch (error) {
      console.error('Erro ao deletar o post:', error);
      toast.error('Erro ao deletar o post!');
    }
  };

  const handleLike = (postId) => {
    console.log(`Curtindo post com ID: ${postId}`);
  };

  return (
    <div className="container">
      <h2 className="text-center mt-3">{pageTitle}</h2>

      {updatedPosts.length === 0 ? (
        <p>Não há posts para exibir.</p>
      ) : (
        <div className="row justify-content-center g-4">
          {updatedPosts.map((post) => (
            <div key={post.postId} className="col-12 col-md-6 col-lg-4">
              <div className="card shadow-sm border-light rounded-3" style={{ width: '100%', display: 'flex', flexDirection: 'column' }}>
                <div className="card-body d-flex flex-column" style={{ height: '100%' }}>
                  <div className="d-flex justify-content-between align-items-center mb-3">
                    <div>
                      <strong>{post.user.name}</strong>
                      <small className="d-block text-muted">
                        {new Date(post.createdAt).toLocaleString()}
                      </small>
                    </div>
                    {parseInt(userId) === post.user.userId && (
                      <div>
                        <button className="btn btn-sm btn-primary me-2" onClick={() => handleEdit(post.postId)}>
                          <FaEdit />
                        </button>
                        <button className="btn btn-sm btn-danger" onClick={() => handleDelete(post.postId)}>
                          <FaTrash />
                        </button>
                      </div>
                    )}
                  </div>

                  <h5 className="card-title" style={{ fontSize: '1.25rem' }}>
                    {post.title}
                  </h5>

                  <hr />

                  <p className="card-text" style={{ fontSize: '1rem', overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>
                    {post.content}
                  </p>

                  <div className="d-flex justify-content-between align-items-center mt-auto">
                    <button className="btn btn-sm btn-outline-primary" onClick={() => handleLike(post.postId)}>
                      <FaThumbsUp /> Curtir
                    </button>
                    <small className="text-muted">{post.likes.length} Likes</small>
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
