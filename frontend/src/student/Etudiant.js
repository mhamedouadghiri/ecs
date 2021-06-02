import React from "react";
import useToken from "../components/useToken";
import Login from "../Login";
import HeaderApp from "../components/HeaderApp";
import Sidebar from "./Sidebar";
import EntrepriseS from "./EntrepriseS";
import InfoStudent from "./InfoStudent";
import Offre from "./Offre";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import "./Etudiant.css";

const gUserType = "student";

function Etudiant() {
  const {token, setToken} = useToken();
  if (!token) {
    return <Login setToken={setToken} userType={gUserType}/>;
  }
  return (
    <div className="App">
      <HeaderApp setToken={setToken}/>
      <div className="app_body">
        <Router>
          <Sidebar user={token}/>
          <Switch>
            <Route
              exact
              path="/etudiant"
              render={(props) => <EntrepriseS user={token}/>}
            />
            <Route exact path="/etudiant/offre" component={Offre}/>
          </Switch>
          <InfoStudent user={token}/>
        </Router>
      </div>
    </div>
  );
}

export default Etudiant;
