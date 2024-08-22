docker-compose pull
if [ $? -ne 0 ]; then
    exit 0
fi
docker-compose up --force-recreate --build -d
if [ $? -ne 0 ]; then
    exit 0
fi
sleep 1
docker system prune -f -a
docker volume prune -f