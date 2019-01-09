#!/bin/bash

# test db
dirPath='/home/john/Desktop/sxoli/dataBases/chicago-311-service-requests/'

fName='311-service-requests-abandoned-vehicles.csv'
path="$dirPath$fName"
mongoimport -d CI311 -c requests --type csv --file $path --headerline
fName='311-service-requests-alley-lights-out.csv'
path="$dirPath$fName"
mongoimport -d CI311 -c requests --type csv --file $path --headerline
fName='311-service-requests-garbage-carts.csv'
path="$dirPath$fName"
mongoimport -d CI311 -c requests --type csv --file $path --headerline
fName='311-service-requests-graffiti-removal.csv'
path="$dirPath$fName"
mongoimport -d CI311 -c requests --type csv --file $path --headerline
fName='311-service-requests-pot-holes-reported.csv'
path="$dirPath$fName"
mongoimport -d CI311 -c requests --type csv --file $path --headerline
fName='311-service-requests-rodent-baiting.csv'
path="$dirPath$fName"
mongoimport -d CI311 -c requests --type csv --file $path --headerline
fName='311-service-requests-sanitation-code-complaints.csv'
path="$dirPath$fName"
mongoimport -d CI311 -c requests --type csv --file $path --headerline
fName='311-service-requests-street-lights-all-out.csv'
path="$dirPath$fName"
mongoimport -d CI311 -c requests --type csv --file $path --headerline
fName='311-service-requests-street-lights-one-out.csv'
path="$dirPath$fName"
mongoimport -d CI311 -c requests --type csv --file $path --headerline
fName='311-service-requests-tree-debris.csv'
path="$dirPath$fName"
mongoimport -d CI311 -c requests --type csv --file $path --headerline
fName='311-service-requests-tree-trims.csv'
path="$dirPath$fName"
mongoimport -d CI311 -c requests --type csv --file $path --headerline

mongo localhost:27017/CI311  update.js

#mongo mongodb://127.0.0.1:27017/CI311 <<EOF
#db.requests.update(
#    {'Type of Service Request':'Street Light - 1/Out'},
#    {$set:{'Type of Service Request':'Street Light Out"'}},
#    {multi:true}
#)
#EOF