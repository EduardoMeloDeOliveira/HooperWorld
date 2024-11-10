import './App.css'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import PageIndex from './pages/PageIndex';
import PageUserWelcome from './pages/PageUserGame';
import PageHomeUser from './pages/PageHomeUser';

function App() {

  return (
    <BrowserRouter>

      
      <Routes>
        <Route path="/" element={<PageIndex />} />
        <Route path="/home" element={<PageHomeUser/>}/>
        <Route path="/games" element={<PageUserWelcome/>} />
      </Routes>
      


      <ToastContainer />
    </BrowserRouter>
  )
}

export default App
