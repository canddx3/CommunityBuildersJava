import React from 'react';
import ReactDOM from 'react-dom';
import Home from './Components/Home';
import CharitySignup from './Components/CharitySignup';
import CharityLogin from './Components/CharityLogin';
import CharityProfile from './Components/CharityProfile';
import CharityEvent from './Components/CharityEvent';
import EventSignup from './Components/EventSignup';
import Footer from './Layout/Footer';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';



const App = () => (
  <Router>
    <div>
      <Route exact path="/" component={Home} />
      <Route exact path="/CharitySignup" component={CharitySignup} />
      <Route exact path="/CharityLogin" component={CharityLogin} />
      <Route exact path="/CharityProfile" component={CharityProfile} />
      <Route exact path="/CharityEvent" component={CharityEvent} />
      <Route exact path="/EventSignup" component={EventSignup} />
      <Footer />
    </div>
    
  </Router>
);
 
ReactDOM.render(<App />, document.getElementById('root'));