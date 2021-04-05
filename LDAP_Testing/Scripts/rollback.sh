#-------------------------------------------------------------------------------------------------------#
#                                         Created by: Force of Mercury                                  #
#             This bash script takes variables from both Proxmox.config and user input and retrieves    #
#             $TICKET and $CSRF values. Using these values, this file then rolls back any VM that       #
#             is specified with the first input and then the name of the snapshot which is specified    #
#             with the $SNAPSHOT_NAME variable.                                                         #
#                                                                                                       #
#                                                          int        string                            #
#             Example command to rollback: ./rollback.sh [VMID] [SNAPSHOT_NAME]                         #
#-------------------------------------------------------------------------------------------------------#

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

. Proxmox.config #config file that pulls set input for multiple variables
PROX_USERNAME="$username" #pulls username from Proxmox.config and sets it for PVEAuthCookie and CSRFPreventionToken
PROX_PASSWORD="$password" #pulls password from Proxmox.config and sets it for PVEAuthCookie and CSRFPreventionToken
HOST="$host" #pulls host address from Proxmox.config for commands using $HOST

#---------------------------------------------------------------------------------------------------------------------------------------------
#Runs curl command to retrieve Proxmox user's PVEAuthCookie and CSRFPreventionToken
#Variables being used: $PROX_USERNAME , $PROX_PASSWORD , $HOST
DATA=`curl -s -k -d "username=$PROX_USERNAME&password=$PROX_PASSWORD" $HOST/api2/json/access/ticket`
TICKET=$(decodeDataFromJson $DATA 'ticket') #sets $TICKET value
CSRF=$(decodeDataFromJson $DATA 'CSRFPreventionToken') #sets CSRF value
#---------------------------------------------------------------------------------------------------------------------------------------------

NODE="$node" #pulls node from Proxmox.config and sets it for Rollback command
TARGET_VMID=${1} #user has to specify VMID of VM they are rolling back. First parameter when running command
SNAPSHOT_NAME=${2} #user has to specify SNAPSHOT_NAME of VM that they want VM rolled back to. Second parameter when running command

#---------------------------------------------------------------------------------------------------------------------------------------------
#Runs curl command with PVEAuthCookie and CSRFPreventionToken to run a POST command to rollback the targeted VM.
#Variables being used: $TICKET , $CSRF , $HOST , $NODE , $TARGET_VMID , $SNAPSHOT_NAME
ROLLBACK_TASK_DATA=`curl -s -k -b "PVEAuthCookie=$TICKET" -H "CSRFPreventionToken: $CSRF" -X POST $HOST/api2/json/nodes/$NODE/qemu/$TARGET_VMID/snapshot/$SNAPSHOT_NAME/rollback`
ROLLBACK_TASK_RESULT=$(decodeDataFromJson $ROLLBACK_TASK_DATA 'data')
#---------------------------------------------------------------------------------------------------------------------------------------------
