import React, { useEffect, useState } from "react";
import { properties } from "../resources/properties";
import "./Candidature.css";
import StudentOffre from "./StudentOffre";

function Candidature(props) {
  const [candidatures, setCandidatures] = useState(null);
  useEffect(() => {
    console.log("id", props.location.id);
    if (!candidatures) {
      fetch(`${properties.url}application/${props.location.id}`)
        .then((res) => res.json())
        .then((data) => {
          setCandidatures(data);
          console.log(data);
        });
    }
  }, [candidatures]);

  return (
    <div className="candidatures">
      <div className="candidature">
        {candidatures ? (
          candidatures.map((candidature) => (
            <StudentOffre
              key={candidature.studentId}
              candidature={candidature}
            />
          ))
        ) : (
          <div className="updating">No students to apply for this offer</div>
        )}
      </div>
    </div>
  );
}

export default Candidature;
