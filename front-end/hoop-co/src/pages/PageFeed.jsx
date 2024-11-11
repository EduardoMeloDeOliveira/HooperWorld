import React from 'react'
import SideBar from '../components/side-bar/SideBar'
import Feed from '../components/feed/Feed'

function PageFeed() {
  return (
    <div style={{ display: 'flex', minHeight: '100vh' }}>
            <SideBar />
            <div style={{ flex: 1, padding: '20px', backgroundColor: '#f8f9fa' }}>
                <Feed />
            </div>
        </div>
  )
}

export default PageFeed