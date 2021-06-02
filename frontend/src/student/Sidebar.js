import { Avatar } from "@material-ui/core";
import Button from "react-bootstrap/Button";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { useEffect, useState } from "react";
import "./Sidebar.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import LanguageIcon from "@material-ui/icons/Language";
import BusinessCenterIcon from "@material-ui/icons/BusinessCenter";
import image from "../images/Lotfi.jpeg";
import Modal from "react-bootstrap/Modal";
import { properties } from "../resources/properties";

function Sidebar(props) {
  const [refresh, setRefresh] = useState(false);
  const [languages, setLanguage] = useState(null);
  const [experiences, setExperiences] = useState(null);
  useEffect(() => {
    if (!refresh) {
      fetch(`${properties.url}${properties.StudentLanguage}${props.user.id}`)
        .then((res) => res.json())
        .then((data) => {
          setLanguage(data);
          setRefresh(true);
        });
      fetch(`${properties.url}${properties.StudentExperience}${props.user.id}`)
        .then((res) => res.json())
        .then((data) => {
          setExperiences(data);
          setRefresh(true);
          console.log(data);
        });
    }
  }, [refresh, languages]);

  //pour les experiences
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [description, setDescription] = useState("");
  const [sdate, setSdate] = useState(null);
  const [edate, setEdate] = useState(null);
  let detailsExp = {
    description: description,
    "start-date": sdate,
    "end-date": edate,
    "student-id": props.user.id,
  };
  let formBodyExp = [];
  for (let property in detailsExp) {
    let encodedKey = encodeURIComponent(property);
    let encodedValue = encodeURIComponent(detailsExp[property]);
    formBodyExp.push(encodedKey + "=" + encodedValue);
  }
  formBodyExp = formBodyExp.join("&");
  const handleSubmitExp = (e) => {
    e.preventDefault();
    if (description !== "") {
      fetch(`${properties.url}${properties.experiences}`, {
        method: "POST",
        //mode: "no-cors",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
        },
        body: formBodyExp,
      })
        .then((response) => response.json())
        .then((data) => {
          setRefresh(false);
          //console.log(data);
        });
      setDescription("");

      setSdate(null);
      setEdate(null);
      handleClose();
    } else {
      alert("you have to right your email and your password please");
    }
    //console.log(detailsEduc);
  };
  //pour les langues
  const [showL, setShowL] = useState(false);
  const handleCloseL = () => setShowL(false);
  const handleShowL = () => setShowL(true);

  const [namelangue, setNamelangue] = useState("");
  const [levellangue, setLevellangue] = useState("");
  let detailslangue = {
    name: namelangue,
    level: levellangue,
    "student-id": props.user.id,
  };
  let formBodylangue = [];
  for (let property in detailslangue) {
    let encodedKey = encodeURIComponent(property);
    let encodedValue = encodeURIComponent(detailslangue[property]);
    formBodylangue.push(encodedKey + "=" + encodedValue);
  }
  formBodylangue = formBodylangue.join("&");

  const handleSubmitLangue = (e) => {
    e.preventDefault();
    if (namelangue !== "" && levellangue !== "") {
      fetch(`${properties.url}${properties.languages}`, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
        },
        body: formBodylangue,
      })
        .then((response) => response.json())
        .then((data) => {
          setRefresh(false);
          console.log(data);
        });
      setNamelangue("");
      setLevellangue("");

      handleCloseL();
    } else {
      alert("you have to right your email and your password please");
    }
  };
  const recentItem = (topic, icon) => (
    <div className="sidebar__recentItem">
      <span className="sidebar__hash">{icon}</span>
      <p>{topic}</p>
    </div>
  );

  return (
    <>
      <div className="sidebar">
        <div className="sidebar__top">
          <img src={image} alt="" />
          <Avatar className="sidebar__avatar" src={image} />
          <h2>Lotfi {props.user.name}</h2>
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
          <p>Experiences</p>
          {experiences
            ? experiences.map((experience) =>
                recentItem(experience.description, <BusinessCenterIcon />)
              )
            : "Update your profile.."}

          <div className="ajoutInfo">
            <Button onClick={handleShow} variant="light">
              Ajouter
            </Button>
          </div>
        </div>
        <div className="sidebar__bottom">
          <p>Langues</p>
          {languages
            ? languages.map((language) =>
                recentItem(language.name, <LanguageIcon />)
              )
            : "Update your profile.."}
          <div className="ajoutInfo">
            <Button onClick={handleShowL} variant="light">
              Ajouter
            </Button>
          </div>
        </div>
      </div>
      {/* pour les experiences */}
      <Modal centered show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Nouvelle Expérience</Modal.Title>
        </Modal.Header>
        <form onSubmit={handleSubmitExp}>
          <Modal.Body>
            <div className="firstPart">
              <div>
                <label>Expirence Name :</label>
                <input
                  value={description}
                  type="text"
                  placeholder="Name"
                  onChange={(e) => {
                    setDescription(e.target.value);
                  }}
                />
              </div>
            </div>
            <div className="datepart">
              <div>
                <label>start date: </label>
                <DatePicker
                  selected={sdate}
                  onChange={(date) => setSdate(date)}
                  dateFormat="yyy-MM-dd"
                  maxDate={new Date()}
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
                  maxDate={new Date()}
                  isClearable
                  showYearDropdown
                />
              </div>
            </div>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseL}>
              Close
            </Button>
            <Button type="submit" variant="primary">
              Save Changes
            </Button>
          </Modal.Footer>
        </form>
      </Modal>

      {/* pour les langues */}
      <Modal centered show={showL} onHide={handleCloseL}>
        <Modal.Header closeButton>
          <Modal.Title>Nouvelle Langues </Modal.Title>
        </Modal.Header>
        <form onSubmit={handleSubmitLangue}>
          <Modal.Body>
            <div className="firstPart">
              <div>
                <label>Langue Name :</label>
                <input
                  value={namelangue}
                  type="text"
                  placeholder="Name"
                  onChange={(e) => {
                    setNamelangue(e.target.value);
                  }}
                />
              </div>
              <div>
                <label>Langue level :</label>
                <input
                  value={levellangue}
                  type="text"
                  placeholder="Level"
                  onChange={(e) => {
                    setLevellangue(e.target.value);
                  }}
                />
              </div>
            </div>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseL}>
              Close
            </Button>
            <Button type="submit" variant="primary">
              Save Changes
            </Button>
          </Modal.Footer>
        </form>
      </Modal>
    </>
  );
}

export default Sidebar;
