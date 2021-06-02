import React from "react";
import "./Responsable.css";
import useToken from "../components/useToken";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Login from "../Login";
import HeaderApp from "../components/HeaderApp";
import Sidebar from "./Sidebar";
import Offre from "./Offre";
import Student from "./Student";
import Entreprise from "./Entreprise";

function Responsable() {
  const { token, setToken } = useToken();
  const gUserType = "school";
  if (!token) {
    return <Login setToken={setToken} userType={gUserType} />;
  }
  return (
    <div className="App">
      <HeaderApp setToken={setToken} />
      <div className="app_body">
        <Router>
          <Sidebar key={token.id} user={token} />
          <Switch>
            <Route
              exact
              path="/responsable"
              render={(props) => <Entreprise user={token} />}
            />
            <Route
              exact
              path="/responsable/entreprise/offre"
              component={Offre}
            />
            <Route
              exact
              path="/responsable/student"
              render={(props) => <Student key={token.id} token={token} />}
            />
          </Switch>
        </Router>
      </div>
    </div>
  );
}

export default Responsable;
