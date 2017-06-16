nohup java -jar ./target/project-1.0-SNAPSHOT.jar server project.yaml > project.log 2>&1 &
echo $! > project.pid
