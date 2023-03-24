import React, { Component } from 'react'
import ConfigServerService from '../services/ConfigServerService'
import Select from  'react-select'
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
                 </div>
            </div>
        )
    }
}

export default ConfigServerComponent
