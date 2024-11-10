import React from 'react';
import TypingEffect from 'react-typing-effect';
import { FaJava } from "react-icons/fa6";


function Footer() {
  return (
    <footer className="bg-dark text-white py-2 mt-5">
      <div className="container text-center d-flex justify-content-center align-items-center">
        <p className='fs-3'>
          <a href="https://github.com/EduardoMeloDeOliveira" target="_blank" rel="noopener noreferrer" className="text-white text-decoration-none">
            <TypingEffect
              text={["Melo Dev"]}
              speed={100}
              eraseDelay={5500}
              typingDelay={100}
              cursor={true}
            />
            <FaJava className/>
          </a>
        </p>
      </div>
    </footer>
  );
}

export default Footer;
