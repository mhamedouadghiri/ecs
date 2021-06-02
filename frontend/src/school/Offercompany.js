import React, { useState } from "react";
import "../student/Offercompany.css";
import { Avatar } from "@material-ui/core";
import { properties } from "../resources/properties";

function Offercompany({ offer, userId }) {
  return (
    <>
      <div className="offercompany">
        <div className="post_header">
          <Avatar />
          <div className="post_info">
            <h2>{offer.title}</h2>

            <p>
              From {offer.startDate} to {offer.enDate}
            </p>
          </div>
        </div>
        <div className="post_body">
          <p className="field">{offer.field}</p>
          <p className="description">{offer.description}</p>
          {offer.pay ? <p className="remuneration">Pay : {offer.pay}$</p> : " "}
        </div>
      </div>
    </>
  );
}

export default Offercompany;
