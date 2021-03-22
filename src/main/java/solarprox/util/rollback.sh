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
PROX_USERNAME="root@pam"
PROX_PASSWORD="G6601007r"
HOST="https://172.16.66.10:8006"

DATA=`curl -s -k -d "username=$PROX_USERNAME&password=$PROX_PASSWORD" $HOST/api2/json/access/ticket` 
TICKET=$(decodeDataFromJson $DATA 'ticket')
CSRF=$(decodeDataFromJson $DATA 'CSRFPreventionToken')

NODE="pve"
TARGET_VMID=${1:-"100"}
SNAPSHOT_NAME=${2:-"base"}
ROLLBACK_TASK_DATA=`curl -s -k -b "PVEAuthCookie=$TICKET" -H "CSRFPreventionToken: $CSRF" -X POST $HOST/api2/json/nodes/$NODE/qemu/$TARGET_VMID/snapshot/$SNAPSHOT_NAME/rollback`

ROLLBACK_TASK_RESULT=$(decodeDataFromJson $ROLLBACK_TASK_DATA 'data')