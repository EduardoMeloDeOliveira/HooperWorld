import React from 'react';
import "../side-bar/SideBar.css";
import { LuSearch } from "react-icons/lu";
import { FaUserCircle } from "react-icons/fa";
import { IoSettingsSharp } from "react-icons/io5";
import { IoIosPhotos } from "react-icons/io";
import { GrAnalytics } from "react-icons/gr";
import { BiLogOut } from "react-icons/bi";



function SideBar() {
  return (
    <div className="side-bar">
      <div className="profile">
        <div className="image-profile">
          <img src="../../../src/assets/profile-tom.jpg" alt="" />
        </div>
        <div className="name-profile">
          <h3>John Doe</h3>
        </div>
      </div>

      <div className="contents">
        <div className="contents-item">
          <div className="search-input">
            <LuSearch className="search-icon" />
            <input type="text" placeholder="Buscar..." />
          </div>
        </div>

        <div className="contents-item">
          <button className="action-btn">
            <FaUserCircle className="action-icon" />
            <span>Perfil</span>
          </button>
        </div>

        <div className="contents-item">
          <button className="action-btn">
            <IoIosPhotos className="action-icon" />
            <span>Posts</span>
          </button>
        </div>


        <div className="contents-item">
          <button className="action-btn">
            <IoSettingsSharp className="action-icon" />
            <span>Configurações</span>
          </button>
        </div>


      </div>

      <div className="exit-menu">

      <div className="contents-item">
          <button className="action-btn">
            <GrAnalytics className="action-icon" />
            <span>Métricas</span>
          </button>
        </div>

        <div className="separation"></div>

        <div className="contents-item">
          <button className="action-btn" style={{backgroundColor:"red"}}>
            <BiLogOut className="action-icon"  style={{color : "white", fontSize: "30px"}}/>
            <span style={{color : "white" , fontWeight:"bold", fontSize:"20px"}}>Logout</span>
          </button>
        </div>

      </div>
    </div>
  );
}

export default SideBar;
