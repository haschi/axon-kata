node {
    stage('build') {
        checkout scm
        echo "Hello World"
        withMaven(maven: 'M3') {
            sh 'mvn clean package'
        }
    }

    stage('test') {

    }

    stage('deploy') {

    }
}