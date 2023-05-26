import React, { Component } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import reactLogo from "./user-icon-7.png";
import logoutlogo from "./logout.png";
import { Button } from 'bootstrap';
class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {

        }
    }

    render() {
        return (
            <div>
                <header>
                    <nav 
                    className="navbar navbar-dark bg-primary">
                        <div>
                            <a href="/users"
                                className="navbar-brand">
                                    Config Server Admin Console 
                            </a></div>
                            <div><div className="user-name">
                                <img className="user-icon" src={reactLogo} />
                                Malli Chandana
                                
                            </div>
                            <div>
                            <a className="a-link" href="/users"><img className="logout-icon" src={logoutlogo} /></a>
                            </div>  
                            </div>
                            
                    </nav>
                </header>
            </div>
        )
    }
}

export default HeaderComponent
