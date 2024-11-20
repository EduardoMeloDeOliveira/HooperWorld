import { useState } from 'react'
import './App.css'
import SideBar from './components/side-bar/SideBar'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import UserFeedPage from './pages/user-feed/UserFeedPage'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <BrowserRouter>
    
    <Routes>

      <Route path='/feed' element={<UserFeedPage/>}></Route>

    </Routes>
    
    </BrowserRouter>
    
    </>
  )
}

export default App
