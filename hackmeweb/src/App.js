import React, { Component } from "react";
import { Link } from "react-router-dom";
import { Nav, Navbar, NavItem } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
import Routes from "./Routes";
import "./App.css";
import Auth from "./containers/Auth";

class App extends Component {

    constructor(props) {
        super(props);
        this.Auth = new Auth();
    }

    render() {

        return (
            <div className="App container">
                <Navbar fluid collapseOnSelect>
                    <Navbar.Header>
                        <Navbar.Brand>
                            <Link to="/">Hack.me</Link>
                        </Navbar.Brand>
                        <Navbar.Toggle />
                    </Navbar.Header>
                    <Navbar.Collapse>
                        <Nav pullRight>
                            <LinkContainer to="/plugins">
                                <NavItem>Plugins</NavItem>
                            </LinkContainer>
                            <LinkContainer to="/maps">
                                <NavItem>Maps</NavItem>
                            </LinkContainer>
                            <LinkContainer to="/register">
                                <NavItem>Signup</NavItem>
                            </LinkContainer>
                            <LinkContainer to="/login"><NavItem>Login</NavItem></LinkContainer>
                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
                <Routes />
            </div>
        );
    }
}

export default App;
