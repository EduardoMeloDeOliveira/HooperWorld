import React, { useState } from 'react';
import SideBar from '../../components/side-bar/SideBar';
import '../user-feed/UserFeedPage.css';

function UserFeedPage() {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleInputClick = () => {
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  return (
    <>
      <div className="user-feed-container">
        <SideBar />

        <div className="feed">
          <div className="feed-send">
            <input
              type="text"
              className="create-post-input"
              readOnly
              value="O que anda pensando..."
              onClick={handleInputClick}
            />
          </div>

          <div className="feed-post">
            <div className="post-render"></div>
          </div>
        </div>
      </div>



    </>
  );
}

export default UserFeedPage;
