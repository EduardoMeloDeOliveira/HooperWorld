import React from 'react'
import SideBar from '../components/side-bar/SideBar'
import Profile from '../components/profile/Profile'

function PageProfile() {
  return (
    <div style={{ display: 'flex', minHeight: '100vh' }}>
    <SideBar />
    <div style={{ flex: 1, padding: '20px', backgroundColor: '#f8f9fa' }}>
        <Profile />
    </div>
</div>
  )
}

export default PageProfile