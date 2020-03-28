import React, { Component } from 'react'
import EventService from "../service/EventService";
import CharityProfileBar from "../Layout/CharityProfileBar";

class EventSignup extends Component{
    constructor(props){
        super(props);
        this.state ={
            charityName: '',
            charityPhone: '', 
            eventName: '',
            eventLocation: '',
            eventDate: '',
            eventTime: '',
            eventDescription: '',
        }
        this.saveUser = this.saveUser.bind(this);
    }
 
    saveUser = (e) => {
        e.preventDefault();
        let events = {charityName: this.state.charityName, charityPhone: this.state.charityPhone, eventName: this.state.eventName, eventLocation: this.state.eventLocation, eventDate: this.state.eventDate, eventTime: this.state.eventTime, eventDescription: this.state.eventDescription};
        EventService.addEvents(events)
            .then(res => {
                this.props.history.push('/CharityLogin');
            })
            .catch(err => console.log(err));
    }

    onChange = (e) =>
        this.setState({[e.target.name]: e.target.value });

    render() {
        return(
            <div class="container">
                <CharityProfileBar />
                <h2 className="text-center">Events Signup</h2>
                <form>
                <div className="form-group">
                    <label classname="item-title">Charity Name:</label>
                    <input type="text" name="charityName"  value={this.state.charityName} onChange={this.onChange}/>
                </div>

                <div className="form-group">
                    <label classname="item-title">Charity Phone:</label>
                    <input type="text" name="charityPhone"  value={this.state.charityPhone} onChange={this.onChange}/>
                </div>

               <div className="form-group">
                    <label classname="item-title">Event Name:</label>
                    <input  type="text" name="eventName"  value={this.state.eventName} onChange={this.onChange}/>
                </div>
                
                <div className="form-group">
                    <label classname="item-title">Event Street:</label>
                    <input type="text" name="eventStreet"  value={this.state.eventStreet} onChange={this.onChange}/>
                </div>

                <div className="form-group">
                    <label classname="item-title">Event City:</label>
                    <input type="text" name="eventCity"  value={this.state.eventCity} onChange={this.onChange}/>
                </div>

                <div className="form-group">
                    <label classname="item-title">Event State:</label>
                    <input type="text" name="eventState"  value={this.state.eventState} onChange={this.onChange}/>
                </div>

                <div className="form-group">
                    <label classname="item-title">Event Zip:</label>
                    <input type="text" name="eventZip"  value={this.state.eventZip} onChange={this.onChange}/>
                </div>

                <div className="form-group">
                    <label classname="item-title">Event Date:</label>
                    <input type="text" name="eventDate"  value={this.state.eventDate} onChange={this.onChange}/>
                </div>

                <div className="form-group">
                    <label classname="item-title">Event Time:</label>
                    <br></br>
                    <select type="text"  name="eventTime"  value={this.state.eventTime} onChange={this.onChange}>
                        <option>12:00am</option>
                        <option>12:30am</option>
                        <option>1:00am</option>
                        <option>1:30am</option>
                        <option>2:00am</option>
                        <option>2:30am</option>
                        <option>3:00am</option>
                        <option>3:30am</option>
                        <option>4:00am</option>
                        <option>4:30am</option>
                        <option>5:00am</option>
                        <option>5:30am</option>
                        <option>6:00am</option>
                        <option>6:30am</option>
                        <option>7:00am</option>
                        <option>7:30am</option>
                        <option>8:00am</option>
                        <option>8:30am</option>
                        <option>9:00am</option>
                        <option>9:30am</option>
                        <option>10:00am</option>
                        <option>10:30am</option>
                        <option>11:00am</option>
                        <option>11:30am</option>
                        <option>12:00pm</option>
                        <option>12:30pm</option>
                        <option>1:00pm</option>
                        <option>1:30pm</option>
                        <option>2:00pm</option>
                        <option>2:30pm</option>
                        <option>3:00pm</option>
                        <option>3:30pm</option>
                        <option>4:00pm</option>
                        <option>4:30pm</option>
                        <option>5:00pm</option>
                        <option>5:30pm</option>
                        <option>6:00pm</option>
                        <option>6:30pm</option>
                        <option>7:00pm</option>
                        <option>7:30pm</option>
                        <option>8:00pm</option>
                        <option>8:30pm</option>
                        <option>9:00pm</option>
                        <option>9:30pm</option>
                        <option>10:00pm</option>
                        <option>10:30pm</option>
                        <option>11:00pm</option>
                        <option>11:30pm</option>
                    </select>
                </div>

                <div className="form-group">
                    <label classname="item-title">Event Description:</label>
                    <textarea type="text"  name="eventDescription"  value={this.state.eventDescription} onChange={this.onChange}/>
                </div>

                <button className="btn btn-success" onClick={this.saveUser}>Update</button>
            </form>
    </div>
        );
    }
}

export default EventSignup;