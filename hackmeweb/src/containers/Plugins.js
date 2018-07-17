import React, {Component} from "react";
import "../style/Plugins.css";
import { LinkContainer } from "react-router-bootstrap";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import { Link } from "react-router-dom";
import Auth from './Auth';

export default class Plugins extends Component {
    constructor(props){
        super(props);
        this.Auth = new Auth();
        this.state = {
            items: []
        }
    }

    componentWillMount(){
        this.Auth.fetch('http://localhost:8080/plugin/displayAll',{
            method: 'GET'
        })
            .then(items => this.setState({items}));
    }


    render(){
        return (
            <div class="Plugins">
                {this.state.items.map(plugin =>
                    <div class="Map" id={plugin.name}>
                        <p>{plugin.name}</p>
                        <p>{plugin.description}</p>
                        <form method="get" action={"http://localhost:8080/plugin/download/" + plugin.name} ><button type="submit">Download!</button></form>
                    </div>
                )}
                <div class="Button">
                    <LinkContainer to="/uploadPlugin">
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