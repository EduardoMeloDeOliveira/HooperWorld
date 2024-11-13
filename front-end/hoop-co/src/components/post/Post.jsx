import React, { useState, useEffect } from 'react';
import { FaEdit, FaTrash, FaThumbsUp } from 'react-icons/fa';
import { useLocation, useNavigate } from 'react-router-dom';
import { deletePost, updatePost, toggleLike } from '../../Service/UserService';
import { toast } from 'react-toastify';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

function Post({ posts }) {
  const [updatedPosts, setUpdatedPosts] = useState([]);
  const [editModalVisible, setEditModalVisible] = useState(false);
  const [editPostData, setEditPostData] = useState({ title: '', content: '' });
  const [currentPostId, setCurrentPostId] = useState(null);
  const [showLikeModal, setShowLikeModal] = useState(false);
  const [likeDetails, setLikeDetails] = useState([]);

  const userId = localStorage.getItem("userId");
  const token = localStorage.getItem("token");
  const location = useLocation();
  const navigate = useNavigate();

  const getLikesFromLocalStorage = () => JSON.parse(localStorage.getItem("userLikes")) || {};

  const saveLikesToLocalStorage = (likes) => {
    localStorage.setItem("userLikes", JSON.stringify(likes));
  };

  useEffect(() => {
    const storedLikes = getLikesFromLocalStorage();
    const updatedPostsWithLikes = posts.map(post => ({
      ...post,
      likedByUser: storedLikes[post.postId]?.liked || false,
      likeId: storedLikes[post.postId]?.likeId || null,
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

  const handleLikeToggle = async (postId) => {
    try {
      const likeResponse = await toggleLike(token, postId);
      const updatedLikeStatus = { liked: likeResponse.liked, likeId: likeResponse.likeId };

      const storedLikes = getLikesFromLocalStorage();
      storedLikes[postId] = updatedLikeStatus;
      saveLikesToLocalStorage(storedLikes);

      setUpdatedPosts(prevPosts =>
        prevPosts.map(post =>
          post.postId === postId
            ? {
                ...post,
                likedByUser: updatedLikeStatus.liked,
                likeId: updatedLikeStatus.likeId,
                likes: updatedLikeStatus.liked
                  ? [...post.likes, { likedBy: userId, likedAt: new Date().toISOString() }]
                  : post.likes.filter(like => like.likedBy !== userId)
              }
            : post
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
                    <button
                      className={`btn btn-sm ${post.likedByUser ? 'btn-primary' : 'btn-outline-primary'}`}
                      onClick={() => handleLikeToggle(post.postId)}
                    >
                      <FaThumbsUp /> {post.likes.length}
                    </button>
                    <button className="btn btn-sm btn-outline-info" onClick={() => handleShowLikes(post.likes)}>
                      Ver Curtidas
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
          <input type="text" className="form-control mb-2" value={editPostData.title} onChange={(e) => setEditPostData({ ...editPostData, title: e.target.value })} placeholder="Título" />
          <textarea className="form-control" rows="5" value={editPostData.content} onChange={(e) => setEditPostData({ ...editPostData, content: e.target.value })} placeholder="Conteúdo"></textarea>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setEditModalVisible(false)}>Cancelar</Button>
          <Button variant="primary" onClick={handleEditSave}>Salvar</Button>
        </Modal.Footer>
      </Modal>

      <Modal show={showLikeModal} onHide={() => setShowLikeModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Curtidas</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {likeDetails.length > 0 ? (
            <ul className="list-group">
              {likeDetails.map((like, index) => (
                <li key={index} className="list-group-item">
                  <strong>{like.userName}</strong>
                  <small className="d-block text-muted">Curtido em {new Date(like.likedAt).toLocaleString()}</small>
                </li>
              ))}
            </ul>
          ) : (
            <p>Nenhuma curtida encontrada.</p>
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
