import React from "react";
import { Route, Switch } from "react-router-dom";
import Home from "./containers/Home";
import Maps from "./containers/Maps"
import NotFound from "./containers/NotFound";
import Login from "./containers/Login";
import Plugins from "./containers/Plugins";
import Signup from "./containers/Signup";


export default () =>
    <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/login" exact component={Login} />
        <Route path="/maps" exact component={Maps} />
        <Route path="/plugins" exact component={Plugins} />
        <Route path="/register" exact component={Signup} />
        <Route component={NotFound} />
    </Switch>;
