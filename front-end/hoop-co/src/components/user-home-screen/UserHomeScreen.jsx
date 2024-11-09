import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { CgGames } from "react-icons/cg";
import { MdDynamicFeed } from "react-icons/md";
import { CgProfile } from "react-icons/cg";
import { verifyToken, logoutService } from "../../Service/TokenVerifyAndLogoutService";

function UserHomeScreen() {
    const navigate = useNavigate();
    const [expanded, setExpanded] = useState(false);

    useEffect(() => {
        if (!verifyToken()) {
            navigate("/");
        }
    }, [navigate]);

    const handleNavigation = (path) => {
        navigate(path);
    };

    const handleLogout = () => {
        logoutService(navigate);
    };

    return (
        <div className="d-flex">
            <nav 
                className='d-flex flex-column bg-dark p-3 min-vh-100 justify-content-between' 
                style={{
                    width: expanded ? "250px" : "100px", 
                    transition: 'width 0.3s', 
                    cursor: 'pointer'
                }}
                onMouseEnter={() => setExpanded(true)} 
                onMouseLeave={() => setExpanded(false)}
            >
                <div className='h-75 justify-content-around d-flex w-100 flex-column'>
                    <div className='h-25 w-100 d-flex justify-content-center align-items-center flex-column'>
                        <a
                            href="#"
                            onClick={() => handleNavigation('/profile')}
                            style={{ textDecoration: 'none', color: 'white' }}
                            className='text-center'
                        >
                            <CgProfile className='fs-3 text-white' />
                            {expanded && <p>Profile</p>}
                        </a>
                    </div>

                    <div className='h-25 w-100 d-flex justify-content-center align-items-center flex-column'>
                        <a
                            href="#"
                            onClick={() => handleNavigation('/posts')}
                            style={{ textDecoration: 'none', color: 'white' }}
                            className='text-center'
                        >
                            <MdDynamicFeed className='fs-3 text-white' />
                            {expanded && <p>Feed</p>}
                        </a>
                    </div>

                    <div className='h-25 w-100 d-flex justify-content-center align-items-center flex-column'>
                        <a
                            href="#"
                            onClick={() => handleNavigation('/games')}
                            style={{ textDecoration: 'none', color: 'white' }}
                            className='text-center'
                        >
                            <CgGames className='fs-3 text-white' />
                            {expanded && <p>Games</p>}
                        </a>
                    </div>

                </div>

                <div className="h-25 w-100 d-flex justify-content-center align-items-center">
                    <button onClick={handleLogout} className="btn btn-danger">Logout</button>
                </div>

            </nav>
        </div>
    );
}

export default UserHomeScreen;