# Task Manager DevSecOps (Java / Spring Boot)

Application REST de gestion des taches (`GET`, `POST`, `PUT`, `DELETE` sur `/api/tasks`) integree dans une chaine CI/CD DevSecOps.

## Stack technique
- **Application** : Java 17, Spring Boot 3.3.5, Maven
- **Qualite & SAST** : SonarQube, JaCoCo[cite: 1]
- **Dependances** : OWASP Dependency-Check[cite: 1]
- **Conteneurisation & Scan** : Docker, Trivy, Docker Hub[cite: 1]
- **Orchestration & DAST** : Kubernetes (Minikube), OWASP ZAP[cite: 1]
- **CI/CD** : Jenkins (`Jenkinsfile`)[cite: 1]

## Execution locale
```bash
mvn clean test package
java -jar target/task-manager-1.0.0.jar


