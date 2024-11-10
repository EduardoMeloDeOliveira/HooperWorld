import React from 'react'
import SideBar from '../components/side-bar/SideBar'
import Game from '../components/game/Game'

function PageUserGame() {
    return (
        <div style={{ display: 'flex', minHeight: '100vh' }}>
            <SideBar />
            <div style={{ flex: 1, padding: '20px', backgroundColor: '#f8f9fa' }}>
                <Game />
            </div>
        </div>
    )
}

export default PageUserGame