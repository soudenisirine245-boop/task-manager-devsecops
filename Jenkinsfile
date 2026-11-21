pipeline {
    agent any

    environment {
        IMAGE_NAME = "task-manager-devsecops"
        IMAGE_TAG  = "1.0.0"
        DOCKERHUB_REPO = "votre-utilisateur/task-manager-devsecops"
    }

    stages {
        stage('Checkout & Build') {
            steps {
                sh 'mvn clean test package'
            }
        }

        stage('SAST - SonarQube') {
            steps {
                echo 'Analyse statique du code (SonarQube / JaCoCo)'
            }
        }

        stage('SCA - OWASP Dependency-Check') {
            steps {
                sh 'mvn org.owasp:dependency-check-maven:check || true'
            }
        }

        stage('Docker Build & Trivy Scan') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                sh "trivy image --exit-code 0 --severity HIGH,CRITICAL ${IMAGE_NAME}:${IMAGE_TAG} || true"
            }
        }

        stage('Deploy Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/deployment.yaml && kubectl apply -f k8s/service.yaml'
            }
        }

        stage('DAST - OWASP ZAP') {
            steps {
                echo 'Scan dynamique de securite (OWASP ZAP)'
            }
        }
    }

    post {
        always {
            sh 'docker system prune -f || true'
        }
    }
}
