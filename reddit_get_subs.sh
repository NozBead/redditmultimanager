#!/bin/bash

[ $# -lt 1 ] && exit 1

AGENT=funetdelire
TOKEN=$1

curl 	-X GET https://oauth.reddit.com/subreddits/mine/subscriber \
	-H "User-agent: $AGENT" \
	-H "Authorization: Bearer $TOKEN"
