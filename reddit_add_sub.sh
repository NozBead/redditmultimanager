#!/bin/bash

[ $# -lt 1 ] && exit 1

AGENT=funetdelire
TOKEN=$1

curl 	-v -X PUT https://oauth.reddit.com/api/multi/user/nozbead/m/testeurs/r/sources4porn/ \
	-H "User-agent: $AGENT" \
	-H "Authorization: Bearer $TOKEN" \
	--data-urlencode 'model={"name":"sources4porn"}'
