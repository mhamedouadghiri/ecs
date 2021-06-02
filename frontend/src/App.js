import React from "react";
import "./App.scss";

import Header from "./components/Header";
import Responsable from "./school/Responsable";

import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Etudiant from "./student/Etudiant";
import Entreprise from "./entreprise/Entreprise";

function App() {
  return (
    <Router>
      <div>
        <Switch>
          <Route exact path="/" component={Home} />
          <Route exact path="/opportunities" component={Opportunities} />
          <Route exact path="/solutions" component={Solutions} />
          <Route exact path="/contact-us" component={Contact} />
          <Route exact path="/responsable" component={Responsable} />
          <Route exact path="/etudiant" component={Etudiant} />
          <Route exact path="/entreprise" component={Entreprise} />
        </Switch>
      </div>
    </Router>
  );
}

function Opportunities() {
  return (
    <div>
      <Header />
      <div className="solutions">
        <p>Discover our numerous opportunities.</p>
      </div>
    </div>
  );
}

function Solutions() {
  return (
    <div>
      <Header />
      <div className="solutions">
        <p>Solutions that help you.</p>
      </div>
    </div>
  );
}

function Contact() {
  return (
    <div>
      <Header />
      <div className="solutions">
        <p>Feel free to reach us.</p>
      </div>
    </div>
  );
}

function Home() {
  return (
    <div>
      <Header />
      <div className="container">
        <div className="wrapper">
          <div className="homepage">
            <h4>
              <b>ECS</b> is a creative application that helps student find an internship;
              and similarly helps companies cherry pick the best profiles that match their expectations.
            </h4>
          </div>
        </div>
      </div>
    </div>
  );
}
export default App;
