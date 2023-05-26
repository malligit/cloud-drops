package main

import (
	"database/sql"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"

	_ "github.com/go-sql-driver/mysql"
	"github.com/gorilla/mux"
)

func main() {
	Routers()
}

func Routers() {
	InitDB()
	defer db.Close()
	log.Println("Starting the HTTP server on port 9080")
	router := mux.NewRouter()

	router.HandleFunc("/configs",
		GetConfigs).Methods("GET")

	router.HandleFunc("/envs",
		GetEnvironments).Methods("GET")
	router.HandleFunc("/envs/{env}",
		GetServicesByEnv).Methods("GET")
	router.HandleFunc("/configs/{env}",
		GetConfigsByEnv).Methods("GET")
	router.HandleFunc("/configs/{env}/{svc}",
		GetConfigsByEnvBySvc).Methods("GET")
	router.HandleFunc("/config/{id}",
		GetConfigById).Methods("GET")
	router.HandleFunc("/update-config/{id}",
		UpdateProperty).Methods("PUT")

	router.HandleFunc("/users",
		GetUsers).Methods("GET")
	router.HandleFunc("/services/{env}",
		CreateUser).Methods("POST")
	router.HandleFunc("/users/{id}",
		GetUser).Methods("GET")
	router.HandleFunc("/users/{id}",
		UpdateUser).Methods("PUT")
	router.HandleFunc("/users/{id}",
		DeleteUser).Methods("DELETE")
	http.ListenAndServe(":9080",
		&CORSRouterDecorator{router})
}

/***************************************************/

func GetConfigs(w http.ResponseWriter, r *http.Request) {
	fmt.Println("@@@@@Loading config server data...")
	w.Header().Set("Content-Type", "application/json")
	var configs []Config
	result, err := db.Query("SELECT id, service_name," +
		"profile,label,`key`,value from configserver")
	if err != nil {
		panic(err.Error())
	}
	defer result.Close()
	for result.Next() {
		var config Config
		err := result.Scan(&config.ID, &config.ServiceName,
			&config.Profile, &config.Label, &config.Property, &config.Value)
		if err != nil {
			panic(err.Error())
		}
		configs = append(configs, config)
	}
	json.NewEncoder(w).Encode(configs)
}

func GetConfigById(w http.ResponseWriter, r *http.Request) {
	fmt.Println("@@@@@Loading config server data...")
	w.Header().Set("Content-Type", "application/json")
	var configs []Config
	params := mux.Vars(r)
	result, err := db.Query("SELECT id, service_name," +
		"profile,label,`key`,value from configserver where id=" + params["id"])
	if err != nil {
		panic(err.Error())
	}
	defer result.Close()
	for result.Next() {
		var config Config
		err := result.Scan(&config.ID, &config.ServiceName,
			&config.Profile, &config.Label, &config.Property, &config.Value)
		if err != nil {
			panic(err.Error())
		}
		configs = append(configs, config)
	}
	json.NewEncoder(w).Encode(configs)
}

func GetServicesByEnv(w http.ResponseWriter, r *http.Request) {
	fmt.Println("Services By env ...")
	w.Header().Set("Content-Type", "application/json")
	var services []string
	var queryString string
	params := mux.Vars(r)
	if params["env"] == "all" {
		queryString = "SELECT DISTINCT service_name from configserver"
	} else {
		queryString = "SELECT DISTINCT service_name from configserver where profile='" + params["env"] + "'"
	}
	result, err := db.Query(queryString)

	if err != nil {
		panic(err.Error())
	}
	defer result.Close()
	for result.Next() {
		var svc string
		err := result.Scan(&svc)
		if err != nil {
			panic(err.Error())
		}

		services = append(services, svc)

	}
	fmt.Println(services)
	json.NewEncoder(w).Encode(services)
}

func GetConfigsByEnv(w http.ResponseWriter, r *http.Request) {
	fmt.Println("Configs By env...")
	w.Header().Set("Content-Type", "application/json")
	var configs []Config

	var queryString string
	params := mux.Vars(r)
	var config Config

	queryString = "SELECT id, service_name," +
		"profile,label,`key`,value  from configserver where profile='" + params["env"] + "'"

	result, err := db.Query(queryString)

	if err != nil {
		panic(err.Error())
	}
	defer result.Close()
	for result.Next() {
		err := result.Scan(&config.ID, &config.ServiceName,
			&config.Profile, &config.Label, &config.Property, &config.Value)
		if err != nil {
			panic(err.Error())
		}

		configs = append(configs, config)

	}
	fmt.Println(configs)
	json.NewEncoder(w).Encode(configs)
}

func GetConfigsByEnvBySvc(w http.ResponseWriter, r *http.Request) {
	fmt.Println("Services By env By Svc ...")
	w.Header().Set("Content-Type", "application/json")
	var configs []Config

	var queryString string
	params := mux.Vars(r)
	var config Config

	queryString = "SELECT id, service_name," +
		"profile,label,`key`,value  from configserver where profile='" + params["env"] + "' AND service_name='" + params["svc"] + "'"

	result, err := db.Query(queryString)

	if err != nil {
		panic(err.Error())
	}
	defer result.Close()
	for result.Next() {
		err := result.Scan(&config.ID, &config.ServiceName,
			&config.Profile, &config.Label, &config.Property, &config.Value)
		if err != nil {
			panic(err.Error())
		}

		configs = append(configs, config)

	}
	fmt.Println(configs)
	json.NewEncoder(w).Encode(configs)
}

func GetEnvironments(w http.ResponseWriter, r *http.Request) {
	fmt.Println("@@@@@Loading config server profiles/Envs ...")
	w.Header().Set("Content-Type", "application/json")
	var envs []string

	result, err := db.Query("SELECT DISTINCT profile from configserver")
	if err != nil {
		panic(err.Error())
	}
	defer result.Close()
	for result.Next() {
		var env string
		err := result.Scan(&env)
		if err != nil {
			panic(err.Error())
		}

		envs = append(envs, env)

	}
	fmt.Println(envs)
	json.NewEncoder(w).Encode(envs)
}

func UpdateProperty(w http.ResponseWriter, r *http.Request) {

	w.Header().Set("Content-Type", "application/json")
	params := mux.Vars(r)
	fmt.Println("Updating property with id" + params["id"])
	stmt, err := db.Prepare("UPDATE configserver SET value = ? WHERE id = ?")
	if err != nil {
		panic(err.Error())
	}
	body, err := ioutil.ReadAll(r.Body)
	if err != nil {
		panic(err.Error())
	}
	keyVal := make(map[string]string)
	json.Unmarshal(body, &keyVal)
	cfgvalue := keyVal["cfgvalue"]

	_, err = stmt.Exec(cfgvalue,
		params["id"])
	if err != nil {
		panic(err.Error())
	}
	fmt.Fprintf(w, "Property with ID = %s was updated",
		params["id"])
}

// **************User Entity functions**************

// Get all users
func GetUsers(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	var users []User
	result, err := db.Query("SELECT id, first_name," +
		"last_name,email from users")
	if err != nil {
		panic(err.Error())
	}
	defer result.Close()
	for result.Next() {
		var user User
		err := result.Scan(&user.ID, &user.FirstName,
			&user.LastName, &user.Email)
		if err != nil {
			panic(err.Error())
		}
		users = append(users, user)
	}
	json.NewEncoder(w).Encode(users)
}

// Create user
func CreateUser(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	stmt, err := db.Prepare("INSERT INTO users(first_name," +
		"last_name,email) VALUES(?,?,?)")
	if err != nil {
		panic(err.Error())
	}
	body, err := ioutil.ReadAll(r.Body)
	if err != nil {
		panic(err.Error())
	}
	keyVal := make(map[string]string)
	json.Unmarshal(body, &keyVal)
	first_name := keyVal["firstName"]
	last_name := keyVal["lastName"]
	email := keyVal["email"]
	_, err = stmt.Exec(first_name, last_name, email)
	if err != nil {
		panic(err.Error())
	}
	fmt.Fprintf(w, "New user was created")
}

// Get user by ID
func GetUser(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	params := mux.Vars(r)
	result, err := db.Query("SELECT id, first_name,"+
		"last_name,email from users WHERE id = ?", params["id"])
	if err != nil {
		panic(err.Error())
	}
	defer result.Close()
	var user User
	for result.Next() {
		err := result.Scan(&user.ID, &user.FirstName,
			&user.LastName, &user.Email)
		if err != nil {
			panic(err.Error())
		}
	}
	json.NewEncoder(w).Encode(user)
}

// Update user
func UpdateUser(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	params := mux.Vars(r)
	stmt, err := db.Prepare("UPDATE users SET first_name = ?," +
		"last_name= ?, email=? WHERE id = ?")
	if err != nil {
		panic(err.Error())
	}
	body, err := ioutil.ReadAll(r.Body)
	if err != nil {
		panic(err.Error())
	}
	keyVal := make(map[string]string)
	json.Unmarshal(body, &keyVal)
	first_name := keyVal["firstName"]
	last_name := keyVal["lastName"]
	email := keyVal["email"]
	_, err = stmt.Exec(first_name, last_name, email,
		params["id"])
	if err != nil {
		panic(err.Error())
	}
	fmt.Fprintf(w, "User with ID = %s was updated",
		params["id"])
}

// Delete User
func DeleteUser(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	params := mux.Vars(r)
	stmt, err := db.Prepare("DELETE FROM users WHERE id = ?")
	if err != nil {
		panic(err.Error())
	}
	_, err = stmt.Exec(params["id"])
	if err != nil {
		panic(err.Error())
	}
	fmt.Fprintf(w, "User with ID = %s was deleted",
		params["id"])
}

/***************************************************/

type User struct {
	ID        string `json:"id"`
	FirstName string `json:"firstName"`
	LastName  string `json:"lastName"`
	Email     string `json:"email"`
}

type Config struct {
	ID          string `json:"id"`
	ServiceName string `json:"serviceName"`
	Profile     string `json:"profile"`
	Label       string `json:"label"`
	Property    string `json:"property"`
	Value       string `json:"value"`
}

// Db configuration
var db *sql.DB
var err error

func InitDB() {
	db, err = sql.Open("mysql",
		"root:mpassword@tcp(127.0.0.1:3306)/mdb")
	if err != nil {
		panic(err.Error())
	}
}

/***************************************************/

// CORSRouterDecorator applies CORS headers to a mux.Router
type CORSRouterDecorator struct {
	R *mux.Router
}

func (c *CORSRouterDecorator) ServeHTTP(rw http.ResponseWriter,
	req *http.Request) {
	if origin := req.Header.Get("Origin"); origin != "" {
		rw.Header().Set("Access-Control-Allow-Origin", origin)
		rw.Header().Set("Access-Control-Allow-Methods",
			"POST, GET, OPTIONS, PUT, DELETE")
		rw.Header().Set("Access-Control-Allow-Headers",
			"Accept, Accept-Language,"+
				" Content-Type, YourOwnHeader")
	}
	// Stop here if its Preflighted OPTIONS request
	if req.Method == "OPTIONS" {
		return
	}

	c.R.ServeHTTP(rw, req)
}
