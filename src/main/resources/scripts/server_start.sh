#!/usr/bin/env bash
cd /home/project/SombraPromotion || exit
sudo java -Dspring.profiles.active=dev -jar promotion-0.0.1-SNAPSHOT.jar