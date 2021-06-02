import React from "react";
import "./InternshipOffre.css";
import { Avatar } from "@material-ui/core";
import { Link } from "react-router-dom";

function InternshipOffre({
  id,
  title,
  sdate,
  edate,
  field,
  description,
  remuneration,
}) {
  return (
    <div className="internshipoffre">
      <div className="post_header">
        <Avatar />
        <div className="post_info">
          <h2>
            <Link to={{ pathname: "/entreprise/candidature", id: id }}>
              {title}
            </Link>
          </h2>

          <p>
            From {sdate} to {edate}
          </p>
        </div>
      </div>
      <div className="post_body">
        <p className="field">{field}</p>
        <p className="description">{description}</p>
        {remuneration ? (
          <p className="remuneration">Pay : {remuneration}$</p>
        ) : (
          " "
        )}
      </div>
    </div>
  );
}

export default InternshipOffre;
