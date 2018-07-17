import React, {Component} from "react";
import "../style/UploadForm.css";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import Auth from './Auth';

export default class UploadPlugin extends Component {
    constructor(props){
        super(props);
        this.Auth = new Auth();
        this.state = {
            pluginname: "",
            description: "",
            pluginfile: "",
            items: []
        }
    }



    validateForm() {
        return this.state.pluginname.length > 0;
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value

        });
    }


    handleSubmit = event => {
        event.preventDefault();
        var form = document.forms.namedItem("pluginUpload");
        var formdata = new FormData(form);
        var resu = this.Auth.fetchForm('http://localhost:8080/plugin/upload',{
            method: 'POST',
            body: formdata
        }).then(res => {
            if(res.status == 201)
                this.props.history.replace('/plugins');
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
                      name="pluginUpload">
                    <FormGroup controlId="pluginname" bsSize="large">
                        <ControlLabel>Plugin name</ControlLabel>
                        <FormControl
                            name="pluginname"
                            id="pluginname"
                            autoFocus
                            type="text"
                            value={this.state.pluginname}
                            onChange={this.handleChange}
                        />
                    </FormGroup>
                    <FormGroup controlId="description" bsSize="large">
                        <ControlLabel>Plugin description</ControlLabel>
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
                    <FormGroup controlId="pluginfile" bsSize="large">
                        <ControlLabel>Upload plugin</ControlLabel>
                        <FormControl
                            name="pluginfile"
                            id="pluginfile"
                            value={this.state.pluginfile}
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