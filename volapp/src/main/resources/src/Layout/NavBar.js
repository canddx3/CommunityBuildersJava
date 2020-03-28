import React, {Component} from "react";

export default class NavBar extends Component {


  render() {
  return (
    <nav className="navbar navbar-inverse">
      <div className="container-fluid">
        <div className="navbar-header">
          <button
            type="button"
            className="navbar-toggle"
            data-toggle="collapse"
            data-target="#myNavbar"
          >
            <span className="icon-bar"></span>
            <span className="icon-bar"></span>
            <span className="icon-bar"></span>
          </button>
          <img id="NavLogo" src="https://github.com/bethel-school-of-technology/community-builders-frontend/blob/Resources/builders.png?raw=true" alt="Logo" />;
          <h4 className="navbar-brand">Community Builders</h4>
        </div>
        <div className="collapse navbar-collapse" id="myNavbar">
          <ul className="nav navbar-nav">
            <li>
              <a href="http://localhost:3000">Home</a>
            </li>
            <li>
              <a href="http://localhost:3000/CharityEvent">Volunteer Events</a>
            </li>
          </ul>
          <ul className="nav navbar-nav navbar-right">
            <li>
              <h5 className="navbar-brand">Charities</h5>
            </li>
            <li>
              <a href="http://localhost:3000/CharitySignup" target="_self">
                <span className="glyphicon glyphicon-user"></span> Sign Up
              </a>
            </li>
            <li>
              <a href="http://localhost:3000/CharityLogin">
                <span className="glyphicon glyphicon-log-in"></span> Login
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}}
