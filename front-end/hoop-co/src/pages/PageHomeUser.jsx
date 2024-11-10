import React from 'react';
import SideBar from '../components/side-bar/SideBar';
import Home from '../components/home/Home';

function PageHomeUser() {
    return (
        <div style={{ display: 'flex', minHeight: '100vh' }}>
            <SideBar />
            <div style={{ flex: 1, padding: '20px', backgroundColor: '#f8f9fa' }}>
                <Home />
            </div>
        </div>
    );
}

export default PageHomeUser;
