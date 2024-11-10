import React from 'react';
import TypingEffect from 'react-typing-effect';
import { FaGithub } from "react-icons/fa";


function Footer() {
  return (
   <footer>
    <div className='flex-row d-flex justify-content-center align-items-center bg-dark py-3'>

      <div>
        <span><a href="https://github.com/EduardoMeloDeOliveira" target='_blank' className='text-decoration-none text-white'>
        
        <TypingEffect
        text={`Developed by Eduardo Melo de Oliveira`}
        speed={100}
        eraseSpeed={100}
        eraseDelay={100000}
        typingDelay={0}
        
        />
        <FaGithub className='fs-4'/>
        </a></span>
      </div>

    </div>
   </footer>
  );
}

export default Footer;
