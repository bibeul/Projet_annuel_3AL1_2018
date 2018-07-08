import React, {Component} from "react";
import "../style/Maps.css";
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


    render(){
        return (
                <div class="Maps">
                {this.state.items.map(map =>
                    <div class="Map" id={map.id}>
                        <p>{map.name}</p>
                            <p>{map.description}</p>
                    </div>
                )}
                </div>
        );
    }
}