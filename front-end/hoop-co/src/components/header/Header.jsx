import React from 'react';
import { useNavigate } from 'react-router-dom';
import { FaBasketballBall } from "react-icons/fa";


function Header() {
  const navigate = useNavigate();

  const handleIndex= () => {
    navigate('/'); 
  };

  return (
    <header className="d-flex justify-content-around align-items-center p-3 bg-dark text-white">
      
      
    <div className='w-25 d-flex justify-content-center align-items-center gap-4' style={{height:"45px"}}  role='button' tabIndex={0}>
      <FaBasketballBall className='fs-1 text-white'/>
      <h1>Hooper.co</h1>
      </div>

    </header>
  );
}

export default Header;
