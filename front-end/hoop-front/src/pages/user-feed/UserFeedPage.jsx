import React, { useState } from 'react';
import SideBar from '../../components/side-bar/SideBar';
import '../user-feed/UserFeedPage.css';

function UserFeedPage() {
  const [showModal, setShowModal] = useState(false);
  const [postContent, setPostContent] = useState('');

  const abrirModal = () => {
    setShowModal(true);
  };

  const fecharModal = () => {
    setShowModal(false); 
  };

  const sendPost = () => {
    setShowModal(false);
    console.log(postContent);
    setPostContent("");
  };

  const handleInputChange = (event) => {
    setPostContent(event.target.value);
  };

  return (
    <>
      <div className="user-feed-container">
        <SideBar />

        <div className="feed">
          <div className="feed-send">
            {!showModal && (
              <input
                type="text"
                className="create-post-input"
                value="O que anda pensando..."
                onClick={abrirModal}
                readOnly
              />
            )}

            {showModal && (
              <div className="modal-container">
                <h3>Crie um post...</h3>
                <input 
                  type="text" 
                  placeholder="Diz aí..." 
                  value={postContent} 
                  onChange={handleInputChange} 
                />
                <div className="modal-buttons">
                  <button onClick={fecharModal} style={{backgroundColor : "red"}}>sair</button>
                  <button onClick={sendPost}>postar</button>
                </div>
              </div>
            )}
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
