import React, { Component } from "react";
import ApiService from "../service/ApiService";
import CharityLoginBar from "../Layout/CharityLoginBar";
import Axios from "axios";

class CharityLogin extends Component {
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
    this.getUsername = this.getUsername.bind(this);
  } 

  getUsername = e => {
    e.preventDefault();
    let username = {
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
    ApiService.fetchUser(username)
      .then(res => {
        this.setState({ message: "User added successfully." });
        this.props.history.push("/CharityProfile");
      })
      .catch(err => console.log(err));
  };

  onChange = e => this.setState({ [e.target.name]: e.target.value });

  render() {
    return (
      <div>
        <CharityLoginBar />
        <h2 className="text-center">Charity Login</h2>
        <form onSubmit={this.onSubmit}>
          <div className="form-group">
            <label>User Name:</label>
            <input 
              type="text"
              name="userId"
              value={this.state.userId}
              onChange={this.onChange}
            />
          </div>

          <div className="form-group">
            <label>Password:</label>
            <input
              type="password"
              name="password"
              value={this.state.password}
              onChange={this.onChange}
            />
          </div>
          <button className="btn btn-success" onClick={this.loadUser}>
            Save
          </button>
        </form>
      </div>
    );
  }
}

export default CharityLogin;
