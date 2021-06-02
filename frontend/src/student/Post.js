import { Avatar } from "@material-ui/core";
import React, { useState } from "react";
import "./Post.css";
import { Link } from "react-router-dom";

function Post({ company, userId }) {
  return (
    <>
      <div className="post">
        <div className="post_header">
          <Avatar />
          <div className="post_info">
            <h2>{company.name}</h2>
            <p>{company.email}</p>
          </div>
        </div>
        <div className="post_body">
          <p>{company.city}</p>
          <p className="description">{company.description}</p>
        </div>
        <h5 className="offrecompany">
          <Link
            to={{
              pathname: "/etudiant/offre",
              id: { idcompany: company.id, idUser: userId },
            }}
          >
            Show Offer
          </Link>
        </h5>
      </div>
    </>
  );
}

export default Post;
