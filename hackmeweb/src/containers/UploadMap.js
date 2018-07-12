import React, {Component} from "react";
import "../style/UploadForm.css";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import Auth from './Auth';

export default class UploadMap extends Component {
    constructor(props){
        super(props);
        this.Auth = new Auth();
        this.state = {
            mapname: "",
            description: "",
            mapfile: "",
            mapimg: "",
            items: []
        }
    }



    validateForm() {
        return this.state.mapname.length > 0 && this.state.mapfile.length > 0 && this.state.mapimg.length > 0 && this.state.description;
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value

        });
    }

    handleSubmit = event => {
        event.preventDefault();
        var form = document.forms.namedItem("mapUpload");
        var formdata = new FormData(form);
        console.log("test");
        this.Auth.fetchForm('http://localhost:8080/map/upload',{
            method: 'POST',
            body: formdata
        }).then(res => {
            return Promise.resolve(res);
        });
    }

    componentWillMount(){
        if(!this.Auth.loggedIn()){
            alert('Please login')
            this.props.history.replace('/login');
        }

    }


    render() {
        return (
            <div className="Upload">
                <form onSubmit={this.handleSubmit}
                    /*action = "http://localhost:8080/map/upload"*/
                    method="post" enctype="multipart/form-data"
                name="mapUpload">
                    <FormGroup controlId="mapname" bsSize="large">
                        <ControlLabel>Map name</ControlLabel>
                        <FormControl
                            name="mapname"
                            id="mapname"
                            autoFocus
                            type="text"
                            value={this.state.mapname}
                            onChange={this.handleChange}
                        />
                    </FormGroup>
                    <FormGroup controlId="description" bsSize="large">
                        <ControlLabel>Map description</ControlLabel>
                        <FormControl
                            name="description"
                            id="description"
                            autoFocus
                            type="text"
                            value={this.state.description}
                            onChange={this.handleChange}
                        />
                    </FormGroup>
                    <FormGroup controlId="mapfile" bsSize="large">
                        <ControlLabel>Upload map</ControlLabel>
                        <FormControl
                            name="mapfile"
                            id="mapfile"
                            value={this.state.mapfile}
                            onChange={this.handleChange}
                            type="file"
                        />
                    </FormGroup>
                    <FormGroup controlId="mapimg" bsSize="large">
                        <ControlLabel>Upload image</ControlLabel>
                        <FormControl
                            name="mapimg"
                            id="mapimg"
                            value={this.state.mapimg}
                            onChange={this.handleChange}
                            type="file"
                        />
                    </FormGroup>
                    <Button
                        block
                        bsSize="large"
                        disabled={!this.validateForm()}
                        type="submit"
                    >
                        Upload
                    </Button>
                </form>
            </div>

        );
    }
}