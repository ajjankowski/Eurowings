pipeline {
    agent any

    parameters {
        string defaultValue: 'main', name: 'BRANCH', trim: true
        string defaultValue: 'https://github.com/ajjankowski/Eurowings.git', name: 'GITURL', trim: true
    }

    stages {
        stage('Git checkout') {
            steps {
                git branch: '${BRANCH}', url: '${GITURL}'
                echo "Repository checked on ${BRANCH} branch from ${GITURL}"
            }
        }
        stage('Run tests') {
            steps {
                echo 'Running tests stage'
                bat "mvn clean test"
                echo 'Tests completed'
            }
        }
    }
    post {
        always {
            echo 'Building report'
            cucumber buildStatus: 'null',
                     customCssFiles: '',
                     customJsFiles: '',
                     failedFeaturesNumber: -1,
                     failedScenariosNumber: -1,
                     failedStepsNumber: -1,
                     fileIncludePattern: '**/*.json',
                     pendingStepsNumber: -1,
                     skippedStepsNumber: -1,
                     sortingMethod: 'ALPHABETICAL',
                     undefinedStepsNumber: -1
            echo "Report build"
        }
    }
}
