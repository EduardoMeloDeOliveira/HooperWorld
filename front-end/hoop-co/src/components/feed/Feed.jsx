import React, { useState } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import { createPost } from '../../Service/UserService';

function Feed() {
  const [showModal, setShowModal] = useState(false);
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [error, setError] = useState(null);

  const token = localStorage.getItem('token');

  const handleShow = () => setShowModal(true);
  const handleClose = () => setShowModal(false);

  const handleSubmit = async () => {
    if (title && content) {
      const postData = { title, content };
      try {
        await createPost(token, postData);
        setShowModal(false);
        setTitle('');
        setContent('');
      } catch (error) {
        setError('Erro ao criar o post. Tente novamente.');
      }
    } else {
      setError('Todos os campos são obrigatórios!');
    }
  };

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-center">
        <input
          type="text"
          className="form-control"
          placeholder="O que você está pensando?"
          onClick={handleShow}
          readOnly
        />
      </div>

      {error && <div className="alert alert-danger mt-3">{error}</div>}

      <Modal show={showModal} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Criar Post</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="formTitle">
              <Form.Label>Título</Form.Label>
              <Form.Control
                type="text"
                placeholder="Digite o título do post"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
              />
            </Form.Group>

            <Form.Group controlId="formContent">
              <Form.Label>Conteúdo</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                placeholder="Digite o conteúdo do post"
                value={content}
                onChange={(e) => setContent(e.target.value)}
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Fechar
          </Button>
          <Button variant="primary" onClick={handleSubmit}>
            Enviar Post
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default Feed;
