import React, { useState, useEffect } from 'react';
import { fetchUserProfile, fetchPostsByUserId, uploadUserProfileImage } from '../../Service/UserService';
import Post from '../post/Post';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

function Profile() {
  const [user, setUser] = useState(null);
  const [posts, setPosts] = useState([]);
  const [error, setError] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [selectedImage, setSelectedImage] = useState(null);

  const token = localStorage.getItem('token');
  

  useEffect(() => {
    if (token) {
      fetchUserProfile(token)
        .then((data) => setUser(data))
        .catch((err) => {
          setError('Erro ao carregar o perfil.');
          console.error(err);
        });

      fetchPostsByUserId(token)
        .then((data) => {
          if (Array.isArray(data)) {
            console.log(data)
            const storedLikes = getLikesFromLocalStorage();
            const postsWithLikes = data.map(post => ({
              ...post,
              likedByUser: storedLikes[post.postId]?.liked || false,
              likeId: storedLikes[post.postId]?.likeId || null,
            }));
            setPosts(postsWithLikes);
          } else {
            setPosts([]); 
          }
        })
        .catch((err) => {
          setError('Erro ao carregar os posts.');
          console.error(err);
        });
    } else {
      setError('Token de autenticação não encontrado.');
    }
  }, [token]);

  const getLikesFromLocalStorage = () => {
    return JSON.parse(localStorage.getItem('likes')) || {};
  };

  const handleLike = (postId) => {
    const newLikes = { ...getLikesFromLocalStorage() };
    if (newLikes[postId]) {
      newLikes[postId].liked = !newLikes[postId].liked;
    } else {
      newLikes[postId] = { liked: true, likeId: Date.now() };
    }
    localStorage.setItem('likes', JSON.stringify(newLikes));

    const updatedPostsWithNewLike = posts.map(post =>
      post.postId === postId
        ? { ...post, likedByUser: newLikes[postId].liked, likeId: newLikes[postId].likeId }
        : post
    );
    setPosts(updatedPostsWithNewLike);
  };

  const handleProfileClick = () => {
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleImageChange = (e) => {
    setSelectedImage(e.target.files[0]);
  };

  const handleImageUpload = () => {
    if (selectedImage) {
      const formData = new FormData();
      formData.append('file', selectedImage);
      uploadUserProfileImage(formData, token)
        .then(() => {
          alert('Imagem de perfil atualizada com sucesso!');
          setSelectedImage(null);
          fetchUserProfile(token).then((data) => setUser(data));
        })
        .catch((err) => {
          console.error('Erro ao enviar a imagem:', err);
          alert('Erro ao enviar a imagem.');
        });
    } else {
      alert('Por favor, selecione uma imagem primeiro.');
    }
  };

  return (
    <div className="container" style={{ minHeight: '100vh', paddingTop: '20px' }}>
      {error ? (
        <div className="alert alert-danger text-center mt-4" role="alert" style={{ fontSize: '1.2rem' }}>
          {error}
        </div>
      ) : (
        <div className="row">
          <div className="col-12 d-flex justify-content-start mb-3">
            <Button
              variant="outline-primary"
              size="sm"
              onClick={handleProfileClick}
              className="btn btn-outline-dark"
            >
              Infos do Perfil
            </Button>
          </div>

          <Modal show={showModal} onHide={handleCloseModal}>
            <Modal.Header closeButton>
              <Modal.Title>Perfil do Usuário</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              {user ? (
                <>
                  <p><strong>Nome:</strong> {user.name}</p>
                  <p><strong>E-mail:</strong> {user.email}</p>
                  <input type="file" onChange={handleImageChange} />
                  <Button onClick={handleImageUpload} variant="primary" className="mt-2">Enviar Imagem</Button>
                </>
              ) : (
                <p>Carregando perfil...</p>
              )}
            </Modal.Body>
          </Modal>

          <div className="col-12 d-flex justify-content-center mt-4">
            {posts.length === 0 ? (
              <div className="alert alert-info text-center" style={{ fontSize: '1.2rem' }}>
                Nenhum post encontrado, vá ao feed e crie um novo post.
              </div>
            ) : (
              <Post posts={posts} onLike={handleLike} />
            )}
          </div>
        </div>
      )}
    </div>
  );
}

export default Profile;
