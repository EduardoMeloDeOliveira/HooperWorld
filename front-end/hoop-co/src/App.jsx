import './App.css'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Index from './components/index/Index';
import SideBar from './components/side-bar/SideBar';
import MainLayout from './components/game-layout/MainLayout';

function App() {

  return (
    <BrowserRouter>

      
      <Routes>
        <Route path="/" element={<Index />} />
        <Route path="/home" element={<SideBar />} />
        <Route path="/games" element={<MainLayout/>} />
      </Routes>
      


      <ToastContainer />
    </BrowserRouter>
  )
}

export default App
