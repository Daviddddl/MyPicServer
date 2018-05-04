#!/usr/bin/env bash
# 先将指定文件夹中的文件删除
rm -rf /home/suheng/caffe/examples/HWDB_AD/origin/*

# 将上传图片转移到指定文件夹
mv /usr/tomcat/webapps/ImageUploadServer_war/files/images/*/*/* /home/suheng/caffe/examples/HWDB_AD/origin

# 删除原路径的废弃文件夹
rm -rf /usr/tomcat/webapps/ImageUploadServer_war/files/images/*

# 将结果图片回传至服务器路径下
cp /home/suheng/caffe/examples/HWDB_AD/result/* /usr/tomcat/webapps/ImageUploadServer_war/files/images/caffeRes.png