import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { registerUser } from '../../Service/UserService';

function Register({ toggleAuthForm }) {
  const [formData, setFormData] = useState({ name: '', email: '', password: '' });
  const [errors, setErrors] = useState({});
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
    setErrors({ ...errors, [e.target.name]: '' });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await registerUser(formData);
      toast.success('Conta criada com sucesso!');
      setTimeout(() => {
        // Alterna para a tela de login após o sucesso
        toggleAuthForm();
        navigate('/');  // Navega para a página de login
      }, 3000);
    } catch (error) {
      if (error.response && error.response.data) {
        const backendErrors = error.response.data;
        const errorObj = {};

        backendErrors.forEach((err) => {
          errorObj[err.field] = err.message;
        });

        setErrors(errorObj);
        toast.error('Erro ao criar conta. Verifique os campos.');
      } else {
        toast.error('Erro inesperado. Tente novamente.');
      }
    }
  };

  return (
    <div className="container mt-5 w-100 mx-auto d-flex flex-column justify-content-between">
      <h2 className="text-center mb-4">Cadastro</h2>
      <form onSubmit={handleSubmit} className="p-4 border rounded shadow-sm">
        <div className="mb-3">
          <label htmlFor="name" className="form-label">Nome</label>
          <input
            type="text"
            className={`form-control ${errors.name ? 'is-invalid' : ''}`}
            id="name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
          {errors.name && <div className="invalid-feedback">{errors.name}</div>}
        </div>

        <div className="mb-3">
          <label htmlFor="email" className="form-label">E-mail</label>
          <input
            type="text"
            className={`form-control ${errors.email ? 'is-invalid' : ''}`}
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
          />
          {errors.email && <div className="invalid-feedback">{errors.email}</div>}
        </div>

        <div className="mb-3">
          <label htmlFor="password" className="form-label">Senha</label>
          <input
            type="password"
            className={`form-control ${errors.password ? 'is-invalid' : ''}`}
            id="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
          />
          {errors.password && <div className="invalid-feedback">{errors.password}</div>}
        </div>

        <button type="submit" className="btn btn-dark w-100">Cadastrar</button>
      </form>

      <p className="text-center mt-3">
        Já tem uma conta? <span className="text-primary" style={{ cursor: 'pointer' }} onClick={toggleAuthForm}>Entrar</span>
      </p>
    </div>
  );
}

export default Register;
