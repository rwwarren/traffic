$(./stop.sh)
git reset --hard HEAD
git pull
mvn clean install
$(./run.sh)
