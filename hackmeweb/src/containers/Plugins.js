import React, {Component} from "react";
import "../style/Plugins.css";
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
                    <div class="Plugin" id={plugin.id}>
                        <p>{plugin.name}</p>
                        <p>{plugin.description}</p>
                    </div>
                )}
            </div>
        );
    }
}