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
	${GRADLE} build

###############################################################################
### FLYWAY 																	###
###############################################################################

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