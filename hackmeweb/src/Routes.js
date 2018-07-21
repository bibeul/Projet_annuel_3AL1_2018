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
import AppliedRoute from "./components/AppliedRoute";
import ScoreMap from "./containers/ScoreMap";



export default ({ childProps }) =>
    <Switch>
        <AppliedRoute path="/" exact component={Home} props={childProps} />
        <AppliedRoute path="/login" exact component={Login} props={childProps} />
        <Route path="/maps" exact component={Maps} />
        <Route path="/plugins" exact component={Plugins} />
        <Route path="/register" exact component={Signup} />
        <Route path="/uploadMap" exact component={UploadMap}/>
        <Route path="/uploadPlugin" exact component={UploadPlugin}/>
        <Route path="/map/:mapname" component={ScoreMap}/>
        <Route component={NotFound} />
    </Switch>;

/*export default () =>
    <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/login" exact component={Login} />
        <Route path="/maps" exact component={Maps} />
        <Route path="/plugins" exact component={Plugins} />
        <Route path="/register" exact component={Signup} />
        <Route path="/uploadMap" exact component={UploadMap}/>
        <Route path="/uploadPlugin" exact component={UploadPlugin}/>
        <Route component={NotFound} />
    </Switch>;*/
