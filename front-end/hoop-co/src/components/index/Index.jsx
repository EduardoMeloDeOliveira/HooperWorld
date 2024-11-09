import React, { useState, useEffect } from 'react';
import TypingEffect from 'react-typing-effect';
import Header from '../header/Header';
import Login from "../login/Login";
import Register from "../register/Register";

function Index() {
  const [isRegistering, setIsRegistering] = useState(false);
  const [isTypingComplete, setIsTypingComplete] = useState(false);

  const toggleAuthForm = () => {
    setIsRegistering(!isRegistering);
  };

  const handleTypingComplete = () => {
    setIsTypingComplete(true);
  };

  return (
    <>
      <Header />
      <div className="container mt-5 d-flex flex-column">
        <div className="row ">

          {/* Banner */}
          <div className="col-md-6 d-flex justify-content-center align-items-center p-4 text-white rounded-start shadow-lg text-center bg-dark flex-column" style={{ minHeight: '60vh' }}>
            <h1 className="display-1">
              <TypingEffect 
                text={["Hoop.co"]} 
                speed={120} 
                eraseDelay={1500} 
                typingDelay={500} 
                cursor={!isTypingComplete} 
                onTypingDone={handleTypingComplete} 
              />
            </h1>
            <p className="text-white fs-5 mt-4">
              <TypingEffect 
                text={["Acesse conteúdos exclusivos e conecte-se com outros usuários. Faça login ou cadastre-se para explorar mais."]} 
                speed={30} 
                eraseDelay={5500} 
                typingDelay={500} 
                cursor={!isTypingComplete} 
                onTypingDone={handleTypingComplete} 
              />
            </p>
          </div>

          {/* Form */}
          <div className="col-md-6 d-flex justify-content-center align-items-center p-4 bg-light rounded-end shadow-lg" style={{ minHeight: '60vh' }}>
            {isRegistering ? (
              <Register toggleAuthForm={toggleAuthForm} />
            ) : (
              <Login toggleAuthForm={toggleAuthForm} />
            )}
          </div>

        </div>
      </div>
    </>
  );
}

export default Index;
