import React, {Component} from "react";
import "../style/Maps.css";
import { LinkContainer } from "react-router-bootstrap";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import Auth from './Auth';

export default class ScoreMap extends Component {
    constructor(props){
        super(props);
        this.Auth = new Auth();
        this.state = {
            items: []
        }
    }

    componentWillMount(){
        this.Auth.fetch('http://localhost:8080/score/displayAll',{
            method: 'GET'
        })
            .then(items => this.setState({items}));
    }

    render(){
        return (
        );
    }
}