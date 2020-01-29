#!/usr/bin/env bash

set -o allexport
source .env;
set +o allexport

activator -jvm-debug "8000" "run 9000";
