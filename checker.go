package main

import (
	"fmt"
	"net/http"
	"io/ioutil"
)

func main() {
	// Pinging your Spring Boot Actuator endpoint
	resp, err := http.Get("http://localhost:8080/actuator/health")
	if err != nil {
		fmt.Printf("Error: %s\n", err)
		return
	}
	defer resp.Body.Close()

	body, _ := ioutil.ReadAll(resp.Body)
	fmt.Println("Spring Boot Health Status:", string(body))
}