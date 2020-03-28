import React, { Component } from "react";
import EventService from "../service/EventService";
import CharityEventBar from "../Layout/CharityEventBar";

class CharityEvent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      events: [],
      message: null
    };  
    this.reloadEventsList = this.reloadEventsList.bind(this);
  }

  componentDidMount() {
    this.reloadEventsList();
  }

  reloadEventsList() {
    EventService.fetchEvents().then(response => {
      console.log(response.data);
      this.setState({ events: response.data });
    });
  }

  render() {
    return (
      <div>
        <CharityEventBar />
        <h2 className="text-center">CharityEvents </h2>
        <table className="table table-striped">
          <thead>
            <tr>
              <th>Charity Name</th>
              <th>Charity Phone</th>
              <th>Event Name</th>
              <th>Event Street</th>
              <th>Event City</th>
              <th>Event State</th>
              <th>Event Zip</th>
              <th>Event Date</th>
              <th>Event Time</th>
              <th>Event Description</th>
            </tr>
          </thead>
          <tbody>
            {this.state.events.map(events => (
              <tr key={events.id}>
                <td>{events.charityName}</td>
                <td>{events.charityPhone}</td>
                <td>{events.eventName}</td>
                <td>{events.eventStreet}</td>
                <td>{events.eventCity}</td>
                <td>{events.eventState}</td>
                <td>{events.eventZip}</td>
                <td>{events.eventDate}</td>
                <td>{events.eventTime}</td>
                <td>{events.eventDescription}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default CharityEvent;