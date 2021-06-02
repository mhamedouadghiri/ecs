import { Avatar } from "@material-ui/core";
import React, { useEffect, useState } from "react";
import { Card, ListGroup } from "react-bootstrap";
import "./StudentOffre.css";

function StudentOffre({ candidature }) {
  return (
    <div className="studentOffres">
      <div className="post_header">
        <Avatar />
        <div className="post_info">
          <h2>{candidature.studentInfo.firstName}</h2>
          <p>{candidature.studentInfo.email}</p>
        </div>
      </div>
      <div className="infostudent">
        <Card style={{ width: "15rem" }}>
          <Card.Header>Educations</Card.Header>
          <ListGroup variant="flush">
            {candidature.studentInfo.educations
              ? candidature.studentInfo.educations.map((education) => (
                  <ListGroup.Item>{education.name}</ListGroup.Item>
                ))
              : "<p>toto</p>"}
          </ListGroup>
        </Card>
        <Card style={{ width: "15rem" }}>
          <Card.Header>Skills</Card.Header>
          <ListGroup variant="flush">
            {candidature.studentInfo.skills != null
              ? candidature.studentInfo.skills.map((skill) => (
                  <ListGroup.Item>{skill.name}</ListGroup.Item>
                ))
              : "<p>toto</p>"}
          </ListGroup>
        </Card>
        <Card style={{ width: "15rem" }}>
          <Card.Header>Experiences</Card.Header>
          <ListGroup variant="flush">
            {candidature.studentInfo.experiences
              ? candidature.studentInfo.experiences.map((experience) => (
                  <ListGroup.Item>{experience.description}</ListGroup.Item>
                ))
              : "<p>toto</p>"}
          </ListGroup>
        </Card>
      </div>
      <div className="coverletter">
        <h5>Cover Letter</h5>
        {candidature.coverLetter
          ? candidature.coverLetter
          : "No cover letter exist"}
      </div>
    </div>
  );
}

export default StudentOffre;
