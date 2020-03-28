import React, { Component } from 'react'
import ApiService from "../service/ApiService";
import CharityProfileBar from "../Layout/CharityProfileBar";

class CharityProfile extends Component {
  constructor(props) {
    super(props);
    this.state = this.state = {
      id: "",
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
    this.loadUser = this.loadUser.bind(this);
  }

  componentDidMount() {
      this.loadUser();
  } 

  loadUser() {
      ApiService.fetchUser(("username"))
          .then((res) => {
              let user = res.data.result;
              this.setState({
                id: user.id,
                username: user.username,
                password: user.password,
                charityTitle: user.charityTitle,
                charityName: user.charityName,
                charityCat: user.charityCat,
                charityStreet: user.charityStreet,
                charityCity: user.charityCity,
                charityState: user.charityState,
                charityZip: user.charityZip,
                charityPhone: user.charityPhone,
                })
                console.log(this.state)
            });
    }

  saveUser = e => {
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
    ApiService.editUser(username)
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
        <CharityProfileBar />
        <h2 className="text-center">Charity Profile</h2>
        <form>
          <div className="form-group">
            <label>User Name:</label>
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
            Update
          </button>
        </form>
      </div>
    );
  }
}

export default CharityProfile;
