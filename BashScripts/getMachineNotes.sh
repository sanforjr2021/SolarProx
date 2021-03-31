#-----------------------------------------------------------------------------------------------#
#                                     Created by: Force of Mercury                              #
#                     This bash script takes variables from  Proxmox.config to print            #
#                     out all info given when running a GET request to                          #
#                     $HOST/api2/json/nodes/$NODE/qemu/$VMID/config. There is no                #
#                     additional input required to run this command. The info in config         #
#                     gives specific box info and is used to get box notes as well. user        #
#                     will also have to give VMID to specify which VM they want the info for.   #
#                                                                                               #
#                                                                      int                      #
#                     Example command to start: ./getMachineNotes.sh [VMID]                     #
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
VMID=${1} #user has to specify VMID of VM they are rolling back. First parameter when running command

#---------------------------------------------------------------------------------------------------------------------------------------------
#Runs curl command using PVEAuthCookie and CSRFPreventionToken to run a POST command to start the targeted VM.
#Variables being used: $TICKET , $CSRF , $HOST , $NODE , $VMID
START_TASK_DATA=`curl -s -k -b "PVEAuthCookie=$TICKET" -H "CSRFPreventionToken: $CSRF" -X GET $HOST/api2/json/nodes/$NODE/qemu/$VMID/config`
echo $START_TASK_DATA #prints output from GET request
#---------------------------------------------------------------------------------------------------------------------------------------------
