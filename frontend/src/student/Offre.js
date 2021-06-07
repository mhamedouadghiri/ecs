import React, { useEffect, useState } from "react";
import { properties } from "../resources/properties";
import Offercompany from "./Offercompany";
import "./Offre.css";
function Offre(props) {
  const [offers, setOffers] = useState(null);
  useEffect(() => {
    if (!offers) {
      fetch(
        `${properties.url}${properties.companyoffers}${props.location.id.idcompany}`
      ).then((res) => {
        if (res.status === 200) {
          res.json().then((data) => {
            setOffers(data);
            //console.log(props.location);
          });
        }
      });
    }
  }, [offers]);
  return (
    <div className="offres">
      {offers ? (
        offers.map((offer) => (
          <Offercompany
            key={offer.id}
            internshipOffer={offer}
            student={props.location.id.idUser}
          />
        ))
      ) : (
        <div className="uploading">No offer apply</div>
      )}
    </div>
  );
}

export default Offre;
