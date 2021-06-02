import React, { useState } from "react";
import "./Offercompany.css";
import EmailIcon from "@material-ui/icons/Email";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import { Avatar } from "@material-ui/core";
import { Form } from "react-bootstrap";
import { properties } from "../resources/properties";

function Offercompany({ offer, userId }) {
  const [coverletter, setCoverLetter] = useState("");
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  let detailsCond = {
    "cover-letter": coverletter,
    "student-id": userId,
    "offer-id": offer.id,
  };
  let formBodyCond = [];
  for (let property in detailsCond) {
    let encodedKey = encodeURIComponent(property);
    let encodedValue = encodeURIComponent(detailsCond[property]);
    formBodyCond.push(encodedKey + "=" + encodedValue);
  }
  formBodyCond = formBodyCond.join("&");
  const handleSubmitCond = (e) => {
    e.preventDefault();
    if (coverletter !== "") {
      fetch(`${properties.url}${properties.studentapply}`, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
        },
        body: formBodyCond,
      });
      setCoverLetter("");

      handleClose();
    } else {
      alert("you have to right your email and your password please");
    }
    console.log(detailsCond);
  };
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
        <div className="post_buttons">
          <div onClick={handleShow} className="inputOption">
            <EmailIcon style={{ color: "gray" }} />
            <h5>Postule</h5>
          </div>
        </div>
      </div>
      {/* pour postuler à une offre de stage */}
      <Modal centered show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Postuler à cette offre</Modal.Title>
        </Modal.Header>
        <form onSubmit={handleSubmitCond}>
          <Modal.Body>
            <Form.Group controlId="exampleForm.ControlTextarea1">
              <Form.Label>Cover Letter</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                placeholder="Convince us"
                onChange={(e) => setCoverLetter(e.target.value)}
              />
            </Form.Group>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            <Button variant="primary" type="submit">
              Save Changes
            </Button>
          </Modal.Footer>
        </form>
      </Modal>
    </>
  );
}

export default Offercompany;
