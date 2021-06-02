import { Avatar } from "@material-ui/core";
import React from "react";
import "./Sidebar.css";
import SchoolIcon from "@material-ui/icons/School";
import BusinessCenterIcon from "@material-ui/icons/BusinessCenter";
import image from "../images/Lotfi.jpeg";
import { Link } from "react-router-dom";
function Sidebar(props) {
  const recentItem = (topic, icon) => (
    <div className="sidebar__recentItem">
      <span className="sidebar__hash">{icon}</span>
      <p>{topic}</p>
    </div>
  );

  return (
    <div className="sidebar">
      <div className="sidebar__top">
        <img src={image} alt="" />
        <Avatar className="sidebar__avatar" src={image} />
        <h2>{props.user.name}</h2>
        <h4>{props.user.email}</h4>
      </div>
      <div className="sidebar__stats">
        <div className="sidebar__stat">
          <p>Nombre d'entreprise</p>
          <p className="sidebar__statNumber">2.543</p>
        </div>
        <div className="sidebar__stat">
          <p>Nombre d'étudiant</p>
          <p className="sidebar__statNumber">2.448</p>
        </div>
      </div>

      <div className="sidebar__bottom">
        <p>Comptes</p>
        <Link to="/responsable">
          {recentItem("EntrepriseInterships", <BusinessCenterIcon />)}
        </Link>
        <Link to="/responsable/student">
          {recentItem("Etudiant", <SchoolIcon />)}
        </Link>
      </div>
    </div>
  );
}

export default Sidebar;
