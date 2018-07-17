import React from "react";
import { Route, Switch } from "react-router-dom";
import Home from "./containers/Home";
import Maps from "./containers/Maps"
import NotFound from "./containers/NotFound";
import Login from "./containers/Login";
import Plugins from "./containers/Plugins";
import Signup from "./containers/Signup";
import UploadMap from "./containers/UploadMap";
import UploadPlugin from "./containers/UploadPlugin";


export default () =>
    <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/login" exact component={Login} />
        <Route path="/maps" exact component={Maps} />
        <Route path="/plugins" exact component={Plugins} />
        <Route path="/register" exact component={Signup} />
        <Route path="/uploadMap" exact component={UploadMap}/>
        <Route path="/uploadPlugin" exact component={UploadPlugin}/>
        <Route component={NotFound} />
    </Switch>;
