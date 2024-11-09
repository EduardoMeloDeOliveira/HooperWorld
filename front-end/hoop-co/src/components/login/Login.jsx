import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { loginUser } from '../../Service/UserService';

function Login({ toggleAuthForm }) {
  const [formData, setFormData] = useState({ email: '', password: '' });
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
      const response = await loginUser(formData);
      localStorage.setItem('token', response.data.token);
      toast.success('Login realizado com sucesso!');
      navigate('/home');
    } catch (error) {
      setErrors({ general: 'Email ou senha incorretos. Tente novamente.' });
      toast.error('Erro ao fazer login. Tente novamente.');
    }
  };

  return (
    <div className="container mt-5 w-100 mx-auto d-flex flex-column justify-content-between">
      <h2 className="text-center mb-4">Login</h2>
      <form onSubmit={handleSubmit} className="p-4 border rounded shadow-sm">
        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email</label>
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

        {errors.general && <div className="alert alert-danger">{errors.general}</div>}

        <button type="submit" className="btn btn-dark w-100">Entrar</button>
      </form>
      <p className="text-center mt-3">
        Não tem uma conta? <span className="text-primary" style={{ cursor: 'pointer' }} onClick={toggleAuthForm}>Registre-se</span>
      </p>
    </div>
  );
}

export default Login;
