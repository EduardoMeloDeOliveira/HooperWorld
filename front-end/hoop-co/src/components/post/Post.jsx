import React, { useState, useEffect } from 'react';
import { FaEdit, FaTrash, FaThumbsUp } from 'react-icons/fa';
import { useLocation, useNavigate } from 'react-router-dom';
import { deletePost, updatePost, likePost, unlikePost } from '../../Service/UserService'; 
import { toast } from 'react-toastify';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

function Post({ posts }) {
  const [updatedPosts, setUpdatedPosts] = useState(posts);
  const [editModalVisible, setEditModalVisible] = useState(false);
  const [editPostData, setEditPostData] = useState({ title: '', content: '' });
  const [currentPostId, setCurrentPostId] = useState(null);
  const [showLikeModal, setShowLikeModal] = useState(false);
  const [likeDetails, setLikeDetails] = useState([]);
  
  const userId = localStorage.getItem("userId");
  const token = localStorage.getItem("token");
  const location = useLocation();
  const navigate = useNavigate();

  // Recupera os likes armazenados no localStorage
  const getLikesFromLocalStorage = () => {
    const storedLikes = JSON.parse(localStorage.getItem("userLikes"));
    return storedLikes || {};
  };

  const saveLikesToLocalStorage = (likes) => {
    localStorage.setItem("userLikes", JSON.stringify(likes));
  };

  useEffect(() => {
    // Recupera o estado de likes do localStorage
    const storedLikes = getLikesFromLocalStorage();
    const updatedPostsWithLikes = posts.map(post => ({
      ...post,
      likedByUser: storedLikes[post.postId] || false
    }));
    setUpdatedPosts(updatedPostsWithLikes);
  }, [posts]);

  const pageTitle = location.pathname === '/profile' ? 'Meus Posts' : 'Timeline';

  const handleEdit = (post) => {
    setCurrentPostId(post.postId);
    setEditPostData({ title: post.title, content: post.content });
    setEditModalVisible(true);
  };

  const handleDelete = async (postId) => {
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

  const handleLike = async (postId) => {
    try {
      const post = updatedPosts.find(p => p.postId === postId);
      const isLiked = post.likedByUser;

      if (isLiked) {
        await unlikePost(token, postId);
        post.likedByUser = false;
      } else {
        await likePost(token, postId);
        post.likedByUser = true;
      }

      // Atualiza o estado local para persistir o estado do like
      const updatedLikes = { ...getLikesFromLocalStorage(), [postId]: post.likedByUser };
      saveLikesToLocalStorage(updatedLikes);

      // Atualiza o estado do post
      setUpdatedPosts(prevPosts =>
        prevPosts.map(p =>
          p.postId === postId ? { ...p, likedByUser: post.likedByUser } : p
        )
      );
    } catch (error) {
      console.error('Erro ao curtir/descurtir o post:', error);
      toast.error('Erro ao curtir/descurtir o post!');
    }
  };

  const handleEditSave = async () => {
    try {
      const updatedPost = await updatePost(token, currentPostId, editPostData);
      setUpdatedPosts((prevPosts) => 
        prevPosts.map((post) => 
          post.postId === currentPostId ? updatedPost : post
        )
      );
      toast.success('Post atualizado com sucesso!');
      setEditModalVisible(false);
    } catch (error) {
      console.error('Erro ao atualizar o post:', error);
      toast.error('Erro ao atualizar o post!');
    }
  };

  const handleShowLikes = (likes) => {
    const likeDetailsFormatted = likes.map(like => ({
      userName: like.likedBy,
      likedAt: like.likedAt
    }));
    setLikeDetails(likeDetailsFormatted);
    setShowLikeModal(true);
  };

  return (
    <div className="container">
      <h2 className={`text-center mt-3 ${pageTitle === "Meus Posts" ? "text-black" : "text-white"}`}>{pageTitle}</h2>

      {updatedPosts.length === 0 ? (
        <p>Não há posts para exibir.</p>
      ) : (
        <div className={`d-flex row ${pageTitle === "Meus Posts" ? "flex-row" : "flex-column"} align-items-center justify-content-center g-4`}>
          {updatedPosts.map((post) => (
            <div key={post.postId} className="col-12 col-md-6 col-lg-4">
              <div className="card shadow-sm border-light rounded-3" style={{ width: '100%', display: 'flex', flexDirection: 'column' }}>
                <div className="card-body d-flex flex-column" style={{ height: '350px' }}>
                  <div className="d-flex justify-content-between align-items-center mb-3">
                    <div>
                      <strong>{post.user.name}</strong>
                      <small className="d-block text-muted">{new Date(post.createdAt).toLocaleString()}</small>
                    </div>
                    {parseInt(userId) === post.user.userId && (
                      <div>
                        <button className="btn btn-sm btn-primary me-2" onClick={() => handleEdit(post)}>
                          <FaEdit />
                        </button>
                        <button className="btn btn-sm btn-danger" onClick={() => handleDelete(post.postId)}>
                          <FaTrash />
                        </button>
                      </div>
                    )}
                  </div>

                  <h5 className="card-title" style={{ fontSize: '1.25rem' }}>{post.title}</h5>
                  <hr />
                  <p className="card-text" style={{ fontSize: '1rem', overflowY: 'auto', maxHeight: '150px' }}>{post.content}</p>

                  <div className="d-flex justify-content-between align-items-center mt-auto">
                    <button className={`btn btn-sm ${post.likedByUser ? 'btn-danger' : 'btn-outline-primary'}`} onClick={() => handleLike(post.postId)}>
                      <FaThumbsUp /> {post.likedByUser ? 'Descurtir' : 'Curtir'}
                    </button>
                    <button 
                      className="btn btn-sm btn-outline-secondary" 
                      onClick={() => handleShowLikes(post.likes)}
                    >
                      {post.likes.length} Likes
                    </button>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}

      <Modal show={editModalVisible} onHide={() => setEditModalVisible(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Editar Post</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="form-group">
            <label>Título</label>
            <input
              type="text"
              className="form-control"
              value={editPostData.title}
              onChange={(e) => setEditPostData({ ...editPostData, title: e.target.value })}
            />
          </div>
          <div className="form-group mt-3">
            <label>Conteúdo</label>
            <textarea
              className="form-control"
              value={editPostData.content}
              onChange={(e) => setEditPostData({ ...editPostData, content: e.target.value })}
            />
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setEditModalVisible(false)}>Cancelar</Button>
          <Button variant="primary" onClick={handleEditSave}>Salvar</Button>
        </Modal.Footer>
      </Modal>

      <Modal show={showLikeModal} onHide={() => setShowLikeModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Likes</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {likeDetails.length > 0 ? (
            <ul>
              {likeDetails.map((like, index) => (
                <li key={index}>{like.userName} - {like.likedAt}</li>
              ))}
            </ul>
          ) : (
            <p>Não há likes para este post.</p>
          )}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowLikeModal(false)}>Fechar</Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default Post;
