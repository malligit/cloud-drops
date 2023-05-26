import axios from 'axios';

const CONFIG_SERVER_API_BASE_URL = "http://localhost:9080";


class ConfigServerService {

    getConfigs(){
        console.log("Reading config data");
        return axios.get(CONFIG_SERVER_API_BASE_URL+"/"+"configs");
    }
    getConfigById(id){
        console.log("Reading config data by Id");
        return axios.get(CONFIG_SERVER_API_BASE_URL+"/config/"+id);
    }
    getConfigsByEnv(env) {
        console.log(env);
        return axios.get(CONFIG_SERVER_API_BASE_URL+"/configs/"+env);
    }
    getConfigsByEnvBySvc(env,svc) {
        console.log(env+"@@@@@@"+svc);
        return axios.get(CONFIG_SERVER_API_BASE_URL+"/"+"configs/"+env+"/"+svc);
    }

    getEnvironments(){
        console.log("Reading Envs data");
        return axios.get(CONFIG_SERVER_API_BASE_URL+"/envs");
    }
        
    getServices(env){
        console.log("Reading services ");
        return axios.get(CONFIG_SERVER_API_BASE_URL+"/envs/"+env);
    }
//Write operations  

    updateConfig(configValue, configId){
        return axios.put(CONFIG_SERVER_API_BASE_URL + '/update-config/' + configId, configValue);
    }

    createConfig(config){
        return axios.post(CONFIG_SERVER_API_BASE_URL, config);
    }
   
}

export default new ConfigServerService()