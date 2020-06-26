# trucker-consumer
Trucker Microservice

### Usage

#### Vehicle 
http://ec2-3-236-117-16.compute-1.amazonaws.com:8080/api/vehicles

#### Readings
http://ec2-3-236-117-16.compute-1.amazonaws.com:8080/api/readings

#### Alerts
http://ec2-3-236-117-16.compute-1.amazonaws.com:8081/api/alerts?priority=High&Vin=WP1AB29P63LA60179




#### Docker Commands

docker build -t truckerconsumer .

docker run -p 8081:8081 --env-file prop.env truckerconsumer

docker container rm -f 

