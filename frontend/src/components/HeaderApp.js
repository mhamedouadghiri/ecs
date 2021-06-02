import React from "react";
import "./HeaderApp.css";
import HeaderOption from "./HeaderOption";
import HomeIcon from "@material-ui/icons/Home";
import BusinessCenterIcon from "@material-ui/icons/BusinessCenter";
import ChatIcon from "@material-ui/icons/Chat";
import NotificationsIcon from "@material-ui/icons/Notifications";
import image from "../images/Lotfi.jpeg";
import logo from "../images/logo.png";

function HeaderApp({ setToken }) {
  const logoutOfApp = () => {
    setToken();
    sessionStorage.clear();
  };

  const Homepage = () => {
    alert("test");
  };
  return (
    <div className="header">
      <div className="header__left">
        <img src={logo} alt="" />
      </div>

      <div className="header__right">
        <HeaderOption Icon={HomeIcon} title="Home" onClick={Homepage} />

        <HeaderOption Icon={BusinessCenterIcon} title="Stages" />
        <HeaderOption Icon={ChatIcon} title="Messaging" />
        <HeaderOption Icon={NotificationsIcon} title="Notifications" />
        <HeaderOption avatar={image} title="Log out" onClick={logoutOfApp} />
      </div>
    </div>
  );
}

export default HeaderApp;
