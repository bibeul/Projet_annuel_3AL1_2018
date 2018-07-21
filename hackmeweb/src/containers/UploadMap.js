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
            mapsprites: "",
            mapriddle: "",
            riddles: []
        }
    }



    validateForm() {
        return this.state.mapname.length > 0 && this.state.mapfile.length > 0 && this.state.mapsprites.length > 0 && this.state.description.length > 0 && this.state.mapriddle.length > 0;
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value

        });
    }

    addRiddle(id) {
        var name = "mapriddle" + id;
        return (<FormGroup controlId={name} bsSize="large">
            <ControlLabel>Upload Riddle</ControlLabel>
            <FormControl
                name={name}
                id={name}
                type="file"
            />
        </FormGroup>)
    }

    validateAdd(){
        return this.state.riddles.length < 2;
    }
    handleAddRiddle = () => {
        this.setState({
            riddles: this.state.riddles.concat(["riddle"+this.state.riddles.length])
        })
    }

    validateRemove(){
        return this.state.riddles.length > 0;
    }
    handleRemoveRiddle = () => {
        this.state.riddles.pop();
        var newRiddles = this.state.riddles;
        this.setState({
            riddles: newRiddles
        })
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
            if(res.status === 201)
                this.props.history.replace('/maps');
        }).catch((err) => {
            alert(err);
        })
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
                            maxLength="255"
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
                            maxLength="255"
                            componentClass="textarea"
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
                    <FormGroup controlId="mapsprites" bsSize="large">
                        <ControlLabel>Upload sprites</ControlLabel>
                        <FormControl
                            name="mapsprites"
                            id="mapsprites"
                            value={this.state.mapsprites}
                            onChange={this.handleChange}
                            type="file"
                        />
                    </FormGroup>
                    <FormGroup controlId="mapriddle" bsSize="large">
                    <ControlLabel>Upload riddle</ControlLabel>
                    <FormControl
                        name="mapriddle"
                        id="mapriddle"
                        value={this.state.mapriddle}
                        onChange={this.handleChange}
                        type="file"
                    />
                </FormGroup>
                    {this.state.riddles.map(riddle =>
                        this.addRiddle(this.state.riddles.indexOf(riddle))
                    )}
                    <Button
                        block
                        onClick={this.handleAddRiddle}
                        disabled={!this.validateAdd()}
                        bsSize="medium"
                        type="button"
                    >Add riddle</Button>
                    <Button
                        block
                        onClick={this.handleRemoveRiddle}
                        disabled={!this.validateRemove()}
                        bsSize="medium"
                        type="button"
                    >Remove riddle</Button>
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