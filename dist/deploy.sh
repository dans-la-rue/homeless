#!/bin/bash

set -x # Print commands and their arguments as they are executed.

docker-compose -f docker-compose.yml pull
docker-compose -f docker-compose.yml down
docker-compose -f docker-compose.yml up -d 
