import React, { Component } from 'react'
import ConfigServerService from '../services/ConfigServerService'
import Select from  'react-select'
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";
import './Dropdown.css'

class ConfigServerComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                configs: [],
                envs: [],
                services:[],
                env:"",
                svc:""
        }
        this.addProperty = this.addProperty.bind(this);
        this.editProperty = this.editProperty.bind(this);
        this.deleteProperty = this.deleteProperty.bind(this);
    }

    deleteProperty(id){
        ConfigServerService.deleteProperty(id).then( res => {
            this.setState({configs: 
                this.state.configs.filter(config => config.id !== id)});
        });
    }
    viewProperty(id){
        this.props.history.push(`/view-property/${id}`);
    }
 
    editProperty(id){
        this.props.history.push(`/update-property/${id}`);
    }
    componentDidMount(){
        ConfigServerService.getConfigs().then((res) => {          
            this.setState({ configs: res.data});
        });

        ConfigServerService.getEnvironments().then((res) => {            
            this.setState({ envs: res.data});
        });
        ConfigServerService.getServices("all").then((res) => {            
            this.setState({ services: res.data});
        });
        

    }

    addProperty(){
        this.props.history.push('/add-property/_add');
    }
    handleClickOpen = () => {
        this.setState({ open: true });
      };
    
      handleClose = () => {
        this.setState({ open: false });
      };
    
      handleAgree = () => {
        console.log("Ok");
        this.handleClose();
      };
      handleDisagree = () => {
        console.log("Canceled");
        this.handleClose();
      };

    render() {

        const svc_options = [];                 
        this.state.services.map(svc => 
          svc_options.push({value: svc, label: svc}));
        const env_options = [];



        console.log("Environments ..."+this.state.envs);
        this.state.envs.map(env => 
            env_options.push({value: env, label: env})); 
          

            

        return (
            <div>
                 <h2 className="text-center">
                     Config Server Properties</h2>
                 <br></br>  
                 <table className='search-panel'> 
                 <tr> <td className='search-box'>   
                 Environment: <Select isMulti options={env_options} onChange=
                 { opt => 
                    {
                        if (opt!=null) 
                        {
                            this.env=opt[0].value
                            ConfigServerService.getServices(opt[0].value).then((res) => {            
                                this.setState({ services: res.data});
                            });

                            ConfigServerService.getConfigsByEnv(opt[0].value).then((res) => {            
                                this.setState({ configs: res.data});
                                this.props.history.push('/');

                            });

                            this.state.services.map(svc => 
                                svc_options.push({value: svc, label: svc}));
                            console.log(opt[0].value)
                        }
                    }
                 }/>
                 </td>
                 <td className='search-box'>                 
                 Service: <Select isMulti options={svc_options} onChange=
                 {opt => 
                    {
                        if (opt!=null) 
                        {
                            console.log(this.env);
                            console.log(opt[0].value);
                            ConfigServerService.getConfigsByEnvBySvc(this.env,opt[0].value).then((res) => { 
                                console.log(res.data);
                                this.setState({ configs: res.data});                                
                            });                            
                        }
                    }
            
                 }
                 />
                 </td>
                 </tr>
                 </table>
                 <div className = "row">
                        <table className 
                        = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> ID</th>
                                    <th> Service Name</th>
                                    <th> Env</th>
                                    <th> Label </th>
                                    <th> Property</th>
                                    <th> Value</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.configs.map(
                                        config => 
                                        <tr key = {config.id}>
                                   <td> {config.id} </td>         
                                   <td> {config.serviceName} </td>   
                                   <td> {config.profile}</td>
                                   <td> {config.label}</td>
                                   <td> {config.property}</td>
                                   <td> {config.value}</td>

                                             <td>
                      <button onClick={ () => 
                          this.editProperty(config.id)} 
                               className="btn btn-info">Update 
                                 </button>
                                    </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>
                      <h2>GBP Production API Triggers</h2>  
                     <table className 
                        = "table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Service Name</th>
                                <th>Http Method</th>
                                <th>Activity</th>
                                <th>Request Body</th>
                                <th>Action</th>
                            </tr>

                        </thead>
                        <tbody>
                            <tr>
                                <td>Posting Service</td>
                                <td>POST</td>
                                <td>Update Balance</td>                                
                                <td><input type="text"></input></td>
                                <td><Button className="btn btn-info" onClick={this.handleClickOpen}>Execute</Button></td>
                            </tr>
                            <tr>
                                <td>Lending account management</td>
                                <td>POST</td>
                                <td>Update balances</td>                                
                                <td><input type="text"></input></td>
                                <td><Button className="btn btn-info" onClick={this.handleClickOpen}>Execute</Button></td>
                            </tr>
                            <tr>
                                <td>Eod-settlement</td>
                                <td>PUT</td>
                                <td>Move the event to diffrent state</td>                                
                                <td><input type="text"></input></td>
                                <td><Button className="btn btn-info" onClick={this.handleClickOpen}>Execute</Button></td>
                            </tr>
                            <tr>
                                <td>Eod-settlement</td>
                                <td>GET</td>
                                <td>Check the Events</td>                                
                                <td><input type="text"></input></td>
                                <td><Button className="btn btn-info" onClick={this.handleClickOpen}>Execute</Button></td>
                            </tr>
                            <tr>
                                <td>Eod-settlement</td>
                                <td>POST</td>
                                <td>Update the events</td>                                
                                <td><input type="text"></input></td>
                                <td>
                                <Button className="btn btn-info" onClick={this.handleClickOpen}>Execute</Button>
        
                                    <Dialog
                                    open={this.state.open}
                                    onClose={this.handleClose}
                                    aria-labelledby="alert-dialog-title"
                                    aria-describedby="alert-dialog-description"
                                    >
                                    <DialogTitle id="alert-dialog-title">
                                        {"Confirmation"}
                                    </DialogTitle>
                                    <DialogContent>
                                        <DialogContentText id="alert-dialog-description">
                                        Are you sure to trigger the API ?
                                        </DialogContentText>
                                    </DialogContent>
                                    <DialogActions>
                                        <Button onClick={this.handleDisagree} color="primary">
                                        Cancel
                                        </Button>
                                        <Button onClick={this.handleAgree} color="primary" autoFocus>
                                        Proceed
                                        </Button>
                                    </DialogActions>
                                    </Dialog>

                                </td>
                            </tr>
                        </tbody>
                     </table>   
                 </div>
            </div>
        )
    }
}

export default ConfigServerComponent
