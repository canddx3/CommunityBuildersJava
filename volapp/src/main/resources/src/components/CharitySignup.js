import React, { Component } from "react";

import ApiService from "../service/ApiService";
import CharitySignupBar from "../Layout/CharitySignupBar";

class CharitySignup extends Component {
  constructor(props) {
    super(props);
    this.state = {
      
      username: "", 
      password: "",
      charityTitle: "",
      charityName: "",
      charityCat: "",
      charityStreet: "",
      charityCity: "",
      charityState: "",
      charityZip: "",
      charityPhone: ""
    };
    this.saveUser = this.saveUser.bind(this);
  } 

  saveUser = e => {
    e.preventDefault();
    let user = {
      username: this.state.username,
      password: this.state.password,
      charityTitle: this.state.charityTitle,
      charityName: this.state.charityName,
      charityCat: this.state.charityCat,
      charityCity: this.state.charityCity,
      charityStreet: this.state.charityStreet,
      charityState: this.state.charityState,
      charityZip: this.state.charityZip,
      charityPhone: this.state.charityPhone
    };
    ApiService.addUser(user)
      .then(res => {
        this.setState({ message: "User added successfully." });
        this.props.history.push("/CharityLogin");
      })
      .catch(err => console.log(err));
  };

  onChange = e => this.setState({ [e.target.name]: e.target.value });

  render() {
    return (
      <div>
        <CharitySignupBar />
        <h2 className="text-center">Charity Signup</h2>
        <div className="bg-img">
          <form action="/action_page.php" >
            <div className="form-group">
              <label>
                <b>User Name:</b>
              </label>
              <input
                type="text"
                name="username"
                value={this.state.username}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <label>Password:</label>
              <input
                type="password"
                name="password"
                value={this.state.Password}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <label>Charity Title:</label>
              <input
                type="text"
                name="charityTitle"
                value={this.state.charityTitle}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <label>Charity Name:</label>
              <input
                type="text"
                name="charityName"
                value={this.state.charityName}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <label>Charity Cat:</label>
              <input
                type="text"
                name="charityCat"
                value={this.state.charityCat}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <label>Charity Street:</label>
              <input
                type="text"
                name="charityStreet"
                value={this.state.charityStreet}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <label>Charity City:</label>
              <input
                type="text"
                name="charityCity"
                value={this.state.charityCity}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <label>Charity State:</label>
              <input
                type="text"
                name="charityState"
                value={this.state.charityState}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <label>Charity Zipcode:</label>
              <input
                type="text"
                name="charityZip"
                value={this.state.charityZip}
                onChange={this.onChange}
              />
            </div>
            <div className="form-group">
              <label>Charity Phone:</label>
              <input
                type="text"
                name="charityPhone"
                value={this.state.charityPhone}
                onChange={this.onChange}
              />
            </div>
            <button className="btn btn-success" onClick={this.saveUser}>
              Signup
            </button>
          </form>
        </div>
      </div>
    );
  }
}

export default CharitySignup;
