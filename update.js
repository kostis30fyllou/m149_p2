// make data uniform
db.requests.update({'Type of Service Request': 'Street Light - 1/Out'}, {$set: {'Type of Service Request': 'Street Light Out'}}, {multi: true});
db.requests.update({'Type of Service Request': 'Pot Hole in Street'}, {$set: {'Type of Service Request': 'Pothole in Street'}}, {multi: true});

db.requests.updateMany({}, {$rename: {'Creation Date': 'CreationDate', 'Completion Date': 'CompletionDate'} });
var cursor = db.requests.find();
while (cursor.hasNext()){
	var doc = cursor.next();
	db.requests.update({_id : doc._id}, {$set: {'CreationDate': new Date(doc.CreationDate)}});
	db.requests.update({$and: [{_id : doc._id}, {'CompletionDate': {'$exists': true}}] }, {$set: {'CompletionDate': new Date(doc.CompletionDate)}});
}
