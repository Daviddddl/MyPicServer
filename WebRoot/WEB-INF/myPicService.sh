#!/usr/bin/env bash

# 删除目录下原来的文件
rm -rf /Users/davidddl/myfiles/pics/*

# 将上传图片移动到我的文件夹
mv /Users/davidddl/IdeaProjects/ImageUploadServer/classes/artifacts/ImageUploadServer_war_exploded/files/images/*/*/* /Users/davidddl/myfiles/pics/

# 删除原来的文件夹
rm -rf /Users/davidddl/IdeaProjects/ImageUploadServer/classes/artifacts/ImageUploadServer_war_exploded/files/images/*

# 将结果文件回传至服务器文件夹中
cp /Users/davidddl/Pictures/Picture/link.jpg /Users/davidddl/IdeaProjects/ImageUploadServer/classes/artifacts/ImageUploadServer_war_exploded/files/images/caffeRes.jpg
