#!/bin/bash

[ $# -lt 1 ] && exit 1

AGENT=funetdelire
TOKEN=$1

curl 	-v -X POST https://oauth.reddit.com/api/multi/user/nozbead/m/test/ \
	-H "User-agent: $AGENT" \
	-H "Authorization: Bearer $TOKEN" \
	--data-urlencode 'model={"description_md": "descr", "display_name": "testeurs", "icon_img": "https://m.media-amazon.com/images/I/518IVUZ7HAL._AC_SX425_.jpg", "key_color": "#AABBCC", "subreddits": [], "visibility": "private"}'
