import React, { Component } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import reactLogo from "./user-icon-4.png";
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
                            <div className="user-name">
                                <img className="user-icon" src={reactLogo} />
                                Malli Chandana
                            </div>  
                    </nav>
                </header>
            </div>
        )
    }
}

export default HeaderComponent
