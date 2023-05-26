import React, { Component } from 'react'
import ConfigServerService from '../services/ConfigServerService';

class UpdatePropertyComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
            env: "",
            svc: "",
            prop:"",
            value: ""
            
        }
        
        this.changevalueHandler =
            this.changeValueHandler.bind(this);
        this.saveOrUpdateProperty =
            this.saveOrUpdateProperty.bind(this);
    }

    // step 3
    componentDidMount() {

        // step 4
        if (this.state.id === '_add') {
            return
        } else {
            ConfigServerService.getConfigById(this.state.id).
            then((res) => {
                console.log(res);
                let cfgprop = res.data;
                this.setState({
                     env: cfgprop[0].profile,
                     svc: cfgprop[0].serviceName,
                     prop: cfgprop[0].property,
                     value: cfgprop.value
                });
            });
        }
    }
    saveOrUpdateProperty = (e) => {
        e.preventDefault();
        let propConfig = { cfgvalue: this.state.value };
        console.log('cfgvalue => ' + JSON.stringify(propConfig));

        // step 5
        
       
            ConfigServerService.
            updateConfig(propConfig, this.state.id).then(res => {
                this.props.history.push('/');
            });
        
    }

    changeValueHandler = (event) => {
        this.setState({ value: event.target.value });
    }

    cancel() {
        this.props.history.push('/');
    }

    getTitle() {
        if (this.state.id === '_add') {
            return <h3 className="text-center">Add Property</h3>
        } else {
            return <h3 className="text-center">Update Property</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
        <div className="container">
            <div className="row">
               <div className="card col-md-6 offset-md-3 offset-md-3">
                            {
                                this.getTitle()
                            }
                            <div className="card-body">
                                <form>
              
            <div className="form-group">
              <label> Env: </label>
                <input disabled placeholder="Environment" 
                  name="env" className="form-control"
                    value={this.state.env} 
                       />
                          </div>
            <div className="form-group">
              <label> Service Name: </label>
                <input disabled placeholder="Service Name" 
                   name="svc" className="form-control"
                     value={this.state.svc} 
                       />
                                    </div>
            <div className="form-group">
              <label> Property: </label>
                <input disabled placeholder="Service Name" 
                   name="svc" className="form-control"
                     value={this.state.prop} 
                       />
                                    </div>                        
            <div className="form-group">
                <label> Value : </label>
                    <input placeholder="Value" 
                       name="value" className="form-control"
                        value={this.state.value} 
                         onChange={this.changevalueHandler} />
                                    </div>

             <button className="btn btn-success" 
                  onClick={this.saveOrUpdateProperty}>Save
                    </button>
             <button className="btn btn-danger" 
                  onClick={this.cancel.bind(this)} 
                     style={{ marginLeft: "10px" }}>Cancel
                        </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default UpdatePropertyComponent
