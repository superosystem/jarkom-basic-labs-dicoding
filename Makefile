## VARIABLES
GRADLE		:= gradle


###############################################################################
### GRADLE 																	###
###############################################################################
run:
	${GRADLE} bootRun

tests:
	${GRADLE} test

build:
	${GRADLE} clean build

###############################################################################
### FLYWAY 																	###
###############################################################################
flywayInfo:
	# flywayInfo URL=jdbc:postgresql://localhost:5432/pkodb USER=developer PASSWORD=mysecretpw
	${GRADLE} flywayInfo -Dflyway.url=${URL} -Dflyway.user=${USER} -Dflyway.password=${PASSWORD}

flywayValidate:
	${GRADLE} flywayValidate -Dflyway.url=${URL} -Dflyway.user=${USER} -Dflyway.password=${PASSWORD}

flywayMigrate:
	${GRADLE} flywayMigrate -Dflyway.url=${URL} -Dflyway.user=${USER} -Dflyway.password=${PASSWORD}

flywayUndo:
	${GRADLE} flywayUndo -Dflyway.url=${URL} -Dflyway.user=${USER} -Dflyway.password=${PASSWORD}

flywayClean:
	${GRADLE} flywayClean -Dflyway.url=${URL} -Dflyway.user=${USER} -Dflyway.password=${PASSWORD}

flywayBaseline:
	${GRADLE} flywayBaseline -Dflyway.url=${URL} -Dflyway.user=${USER} -Dflyway.password=${PASSWORD}

flywayRepair:
	${GRADLE} flywayRepair -Dflyway.url=${URL} -Dflyway.user=${USER} -Dflyway.password=${PASSWORD}

###############################################################################
### DOCKER 																	###
###############################################################################
container-dev:
	docker compose -f docker-compose-dev.yml up

container:
	docker compose -f docker-compose.yml up

image:
	docker build -t superosystem/pkobackend .

###############################################################################
### HELPER 																	###
###############################################################################
backup-postgres:
	pg_dump -h ${PGHOST} -U ${PGUSER} -p ${PGPORT} ${PGDB} > ${NAMEFILE}.sql

copy-docker-local:
	# docker cp <container-name>:/path/to/file/myfile.txt .
	docker cp <container-name>:<container-path> <local-path>

copy-local-docker:
	# docker cp my.sql <container-name>:<container-destination>
	docker cp <schema-file> <container-name>:<container-destination>