#!/bin/bash

[ $# -lt 1 ] && exit 1

AGENT=funetdelire
SECRET=XFL8KUkCxo7fgblfdDnQdTODMRr4jg
CLIENT_ID=9YwLN3L56_3QrWLTMtgqAQ

USER_PASS=$(echo -n $CLIENT_ID:$SECRET | base64)

curl https://www.reddit.com/api/v1/access_token \
	-H "User-agent: $AGENT" \
	-H "Authorization: Basic $USER_PASS" \
	-d grant_type=authorization_code \
	-d code=$1 \
	--data-urlencode redirect_uri=http://lille.funetdelire.fr/

echo -e "\n"
