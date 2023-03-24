import React, { Component } from 'react'
import ConfigServerService from '../services/ConfigServerService'
class ViewConfigComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            config: {}
        }
    }

    componentDidMount(){
        ConfigServerService.getConfigById(this.state.id).then( res => {
            this.setState({config: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> 
                    View Config Property Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label> Service Name: </label>
                            <div> { this.state.config.serviceName }
                            </div>
                        </div>
                        <div className = "row">
                            <label> Environment/Profile : </label>
                            <div> { this.state.config.profile }
                            </div>
                        </div>
                        <div className = "row">
                            <label> Label/Version : </label>
                            <div> { this.state.config.label }
                            </div>
                        </div>
                        <div className = "row">
                            <label> Property Name : </label>
                            <div> { this.state.config.property }
                            </div>
                        </div>
                        <div className = "row">
                            <label> Value : </label>
                            <div> { this.state.config.value }
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }
}

export default ViewConfigComponent
