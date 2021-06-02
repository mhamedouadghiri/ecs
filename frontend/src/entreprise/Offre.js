import React, { useEffect, useState } from "react";
import "./Offre.css";
import Button from "react-bootstrap/Button";
import { properties } from "../resources/properties";
import Modal from "react-bootstrap/Modal";
import Header from "react-bootstrap/ModalHeader";
import Title from "react-bootstrap/ModalTitle";
import Footer from "react-bootstrap/ModalFooter";
import Body from "react-bootstrap/ModalBody";
import DatePicker from "react-datepicker";
import InternshipOffre from "./InternshipOffre";
import { Col, Form } from "react-bootstrap";

function Offre(props) {
  const [offres, setOffres] = useState(null);
  const [refresh, setRefresh] = useState(false);
  const [show, setShow] = useState(false);
  const [pay, setPay] = useState("");
  const [field, setField] = useState("");
  const [description, setDescription] = useState("");
  const [title, setTitle] = useState("");
  const [sdate, setSdate] = useState(null);
  const [edate, setEdate] = useState(null);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  let detailsOffr = {
    title: title,
    description: description,
    field: field,
    "start-date": sdate,
    "end-date": edate,
    "company-id": props.token.id,
    pay: pay,
  };
  let formBodyOffr = [];
  for (let property in detailsOffr) {
    let encodedKey = encodeURIComponent(property);
    let encodedValue = encodeURIComponent(detailsOffr[property]);
    formBodyOffr.push(encodedKey + "=" + encodedValue);
  }
  formBodyOffr = formBodyOffr.join("&");
  useEffect(() => {
    // console.log(props.token);
    if (!refresh) {
      fetch(`${properties.url}${properties.companyoffers}${props.token.id}`)
        .then((res) => res.json())
        .then((data) => {
          setOffres(data);
          setRefresh(true);
          console.log(data);
        });
    }
  }, [refresh]);

  const handleOffre = (e) => {
    e.preventDefault();
    if (description !== "" && title !== "" && field !== "") {
      fetch(`${properties.url}${properties.offer}`, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
        },
        body: formBodyOffr,
      })
        .then((response) => response.json())
        .then((data) => {
          setRefresh(false);
          //console.log(data);
        });
      setDescription("");
      setField("");
      setTitle("");
      setSdate(null);
      setEdate(null);
      handleClose();
    } else {
      alert("you have to right your email and your password please");
    }
  };
  return (
    <div className="offre">
      <div className="offres">
        {offres ? (
          offres.map((offre) => (
            <InternshipOffre
              key={offre.id}
              id={offre.id}
              title={offre.title}
              sdate={offre.startDate}
              edate={offre.endDate}
              field={offre.field}
              description={offre.description}
              remuneration={offre.pay}
            />
          ))
        ) : (
          <div className="updating">
            Welcome {props.token.name} You can add your first offer.
          </div>
        )}
      </div>
      <div className="creatoffres">
        <Button onClick={handleShow} className="btn btn-warning">
          Create an offer
        </Button>
      </div>
      {/* Insértion d'une offre de stage */}
      <Modal centered show={show} onHide={handleClose} animation={false}>
        <Header closeButton>
          <Title>Ajouter Une Offre</Title>
        </Header>
        <form onSubmit={handleOffre} className="inscriptionStudent">
          <Body>
            <Form.Row>
              <Form.Group as={Col} controlId="formGridEmail">
                <Form.Label>Title</Form.Label>
                <Form.Control
                  onChange={(e) => {
                    setTitle(e.target.value);
                  }}
                  value={title}
                  type="text"
                  placeholder="Title"
                />
              </Form.Group>

              <Form.Group as={Col} controlId="formGridPassword">
                <Form.Label>Field</Form.Label>
                <Form.Control
                  onChange={(e) => {
                    setField(e.target.value);
                  }}
                  value={field}
                  type="text"
                  placeholder="Feild"
                />
              </Form.Group>
            </Form.Row>
            <Form.Group controlId="exampleForm.ControlTextarea1">
              <Form.Label>Example textarea</Form.Label>
              <Form.Control
                onChange={(e) => {
                  setDescription(e.target.value);
                }}
                value={description}
                as="textarea"
                rows={3}
              />
            </Form.Group>
            <div className="datepart">
              <div>
                <label>start date: </label>
                <DatePicker
                  selected={sdate}
                  onChange={(date) => setSdate(date)}
                  dateFormat="yyy-MM-dd"
                  isClearable
                  showYearDropdown
                />
              </div>
              <div>
                <label>end date: </label>
                <DatePicker
                  selected={edate}
                  onChange={(date) => setEdate(date)}
                  dateFormat="yyy-MM-dd"
                  isClearable
                  showYearDropdown
                />
              </div>
            </div>
            <div className="pay">
              <Form.Group as={Col} controlId="formGridEmail">
                <Form.Label>Pay</Form.Label>
                <Form.Control
                  className="payinput"
                  onChange={(e) => {
                    setPay(e.target.value);
                  }}
                  value={pay}
                  type="text"
                  placeholder="Pay"
                />
              </Form.Group>
            </div>
          </Body>
          <Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            <Button type="submit" variant="primary">
              Save Offre
            </Button>
          </Footer>
        </form>
      </Modal>
    </div>
  );
}

export default Offre;
