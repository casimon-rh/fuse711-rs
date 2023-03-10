#!/bin/bash
# PRERREQ oc login (token)
# oc new-project fuse-example 2>/dev/null
oc import-image ubi8/openjdk-8 --from=registry.access.redhat.com/ubi8/openjdk-8 --confirm 2>/dev/null
oc new-app openjdk-8~https://github.com/casimon-rh/fuse-79-ds --name rest-spring
oc create route edge --service=rest-spring