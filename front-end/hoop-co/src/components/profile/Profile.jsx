import React, { useState, useEffect } from 'react';
import { fetchUserProfile } from '../../Service/UserService';
import Post from '../post/Post';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

function Profile() {
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null);
  const [showModal, setShowModal] = useState(false);

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

  const handleProfileClick = () => {
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

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
    <div className="container" style={{ height: '100vh' }}>
      <div className="row d-flex justify-content-center align-items-center" style={{ height: '100%' }}>
        
        <div
          className="p-3"
          style={{
            backgroundColor: 'transparent',
            boxShadow: 'none',
            borderRadius: '50%',
            zIndex: 10,
          }}
        >
          <Button
            variant="outline-primary"
            size="sm"
            onClick={handleProfileClick}
           className='btn btn-outline-dark'
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
              </>
            ) : (
              <p>Carregando perfil...</p>
            )}
          </Modal.Body>
        </Modal>

        <div className="col-12 d-flex justify-content-center">
           
            <Post />
         
        </div>
      </div>
    </div>
  );
}

export default Profile;
