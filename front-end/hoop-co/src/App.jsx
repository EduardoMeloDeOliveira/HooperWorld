import './App.css'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Register from './components/register/Register'
import Login from './components/login/Login'
import UserHomeScreen from './components/user-home-screen/UserHomeScreen';
import Index from './components/index/Index';

function App() {

  return (
    <BrowserRouter>

      <Routes>
        <Route path="/" element={<Index />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/home" element={<UserHomeScreen />} />
      </Routes>
      <ToastContainer />
    </BrowserRouter>
  )
}

export default App
