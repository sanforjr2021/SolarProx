#-----------------------------------------------------------------------------------------------#
#                                     Created by: Force of Mercury                              #
#                     This bash script takes variables from  Proxmox.config to print            #
#                     out all info given when running a GET request to                          #
#                     $HOST/api2/json/nodes/$NODE/qemu. There is no additional input required   #
#                     to run this command.                                                      #
#                                                                                               #
#                     Example command to start: ./getAllMachineInfo.sh                          #
#-----------------------------------------------------------------------------------------------#

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
TICKET=$(decodeDataFromJson $DATA 'ticket')
CSRF=$(decodeDataFromJson $DATA 'CSRFPreventionToken')
#---------------------------------------------------------------------------------------------------------------------------------------------

NODE="$node" #pulls node from Proxmox.config and sets it for Rollback command

#---------------------------------------------------------------------------------------------------------------------------------------------
#Runs curl command using PVEAuthCookie and CSRFPreventionToken to run a POST command to start the targeted VM.
#Variables being used: $TICKET , $CSRF , $HOST , $NODE ,
START_TASK_DATA=`curl -s -k -b "PVEAuthCookie=$TICKET" -H "CSRFPreventionToken: $CSRF" -X GET $HOST/api2/json/nodes/$NODE/qemu`
START_TASK_RESULT=$(decodeDataFromJson $START_TASK_DATA 'data')
echo $START_TASK_DATA #prints out result data
#---------------------------------------------------------------------------------------------------------------------------------------------
