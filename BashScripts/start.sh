#!/bin/bash
decodeDataFromJson(){
    echo `echo $1 \
            | sed 's/{\"data\"\:{//g' \
            | sed 's/\\\\\//\//g' \
            | sed 's/[{}]//g' \
            | awk -v k="text" '{n=split($0,a,","); for (i=1; i<=n; i++) print a[i]}' \
            | sed 's/\"\:\"/\|/g' \
            | sed 's/[\,]/ /g' \
            | sed 's/\"// g' \
            | grep -w $2 \
            | awk -F "|" '{print $2}'`
}
. /Users/Trevor/Desktop/BashScripts/Proxmox.config
PROX_USERNAME="$username"
PROX_PASSWORD="$password"
HOST="$host"

DATA=`curl -s -k -d "username=$PROX_USERNAME&password=$PROX_PASSWORD" $HOST/api2/json/access/ticket`
TICKET=$(decodeDataFromJson $DATA 'ticket')
CSRF=$(decodeDataFromJson $DATA 'CSRFPreventionToken')

NODE=${1}
TARGET_VMID=${2}

START_TASK_DATA=`curl -s -k -b "PVEAuthCookie=$TICKET" -H "CSRFPreventionToken: $CSRF" -X POST $HOST/api2/json/nodes/$NODE/qemu/$TARGET_VMID/status/start`

START_TASK_RESULT=$(decodeDataFromJson $START_TASK_DATA 'data')