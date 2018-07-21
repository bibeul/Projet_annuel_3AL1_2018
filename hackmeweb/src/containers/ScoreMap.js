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
            items: [],
            mapname: this.props.match.params.mapname
        }
    }

    componentWillMount(){
        this.Auth.fetch('http://localhost:8080/score/mapscore/'+this.state.mapname,{
            method: 'GET'
        })
            .then(items => this.setState({items}));
    }


    render(){
        return (<table border="1" cellpadding="10" cellspacing="1" width="50%">
            <tr>
                <th>Username</th>
                <th>Score</th>
            </tr>
            {this.state.items.map(score =>
                <tr>
                    <td>{score.username}</td>
                    <td>{score.score}</td>
                </tr>
            )}
        </table>);
    }
}