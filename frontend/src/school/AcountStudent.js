import { Avatar } from "@material-ui/core";
import React from "react";
import "./AcountStudent.css";

function AcountStudent({ key, firstName, lastName, email, major, schoolYear }) {
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
      <div className="post_option">{major}</div>
      <div className="post_option">{schoolYear}ème année</div>
    </div>
  );
}

export default AcountStudent;
