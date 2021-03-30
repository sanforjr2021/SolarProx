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
. Proxmox.config
PROX_USERNAME="$username"
PROX_PASSWORD="$password"
HOST="$host"

DATA=`curl -s -k -d "username=$PROX_USERNAME&password=$PROX_PASSWORD" $HOST/api2/json/access/ticket`
TICKET=$(decodeDataFromJson $DATA 'ticket')
CSRF=$(decodeDataFromJson $DATA 'CSRFPreventionToken')

NODE=${1}


START_TASK_DATA=`curl -s -k -b "PVEAuthCookie=$TICKET" -H "CSRFPreventionToken: $CSRF" -X GET $HOST/api2/json/nodes/$NODE/qemu`

START_TASK_RESULT=$(decodeDataFromJson $START_TASK_DATA 'data')
echo $START_TASK_DATA

#echo $data['name']
#$url="https://172.16.66.10:8006/api2/json/nodes/$NODE/qemu";
#$text = file_get_contents($url);

#$data = json_decode($text, true);

##if ($data['Success'] === true) {
#    $v = [];
#    $columns = [""name","status""];
#    for ($i = 0; $i< count($data['Value']); $i++) {
#        foreach ($data['Value'][$i] as $key => $val) {
#            if (in_array($key, $columns)) {
#                $v[$key] = $val;
#            }
#        }
#        $data['Value'][$i] = $v;
#    }
#}

#$text = json_encode($data);##
