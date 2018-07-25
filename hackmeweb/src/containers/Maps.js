import React, {Component} from "react";
import "../style/Maps.css";
import { Link } from "react-router-dom";
import { Nav, Navbar, NavItem } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import Auth from './Auth';

export default class Maps extends Component {
    constructor(props){
        super(props);
        this.Auth = new Auth();
        this.state = {
            items: []

        }
    }

    componentWillMount(){
        this.Auth.fetch('http://localhost:8080/map/displayAll',{
            method: 'GET'
        })
            .then(items => this.setState({items}));
    }

    handledelete = (mapname) => (event) => {
        event.preventDefault();

        this.Auth.fetchDelete('http://localhost:8080/map/delete/'+mapname,{
            method: 'DELETE'
        })
            .then(() => {})
            .catch((err)=>{
                alert(err);
            });
    }

    render(){
        return (
                <div class="Maps">
                {this.state.items.map(map =>
                    <div class="Map" id={map.id}>
                        <p>{map.name}</p>
                            <p>{map.description}</p>
                        <div class="allButton">
                        <form method="get" action={"http://localhost:8080/map/download/" + map.name} ><button type="submit">Download!</button></form>
                            <LinkContainer to={"/map/"+map.name}><button type="submit">Check Score</button></LinkContainer>
                            <form  onSubmit={this.handledelete(map.name)} ><button type="submit">Delete</button></form>
                        </div>
                    </div>
                )}
                <div class="Button">
                    <LinkContainer to="/uploadMap">
                        <Button
                        block
                        bsSize="large"
                        type="submit"
                    >
                        Upload
                    </Button></LinkContainer>
                </div>
                </div>
        );
    }
}