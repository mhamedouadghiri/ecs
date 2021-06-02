import { Avatar } from "@material-ui/core";
import React from "react";
import "./AcountStudent.css";

function AcountStudent({ key, firstName, lastName, email }) {
  return (
    <div className="accountstudent">
      <div className="post_header">
        <Avatar />
        <div className="post_info">
          <h2>{lastName}</h2>
          <p>{email}</p>
        </div>
      </div>
      <div className="post_option">{firstName}</div>
      <div className="post_option">GL</div>
      <div className="post_option">2ème année</div>
    </div>
  );
}

export default AcountStudent;
