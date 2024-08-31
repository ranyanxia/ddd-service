/* global use, db */
// MongoDB Playground
// To disable this template go to Settings | MongoDB | Use Default Template For Playground.
// Make sure you are connected to enable completions and to be able to run a playground.
// Use Ctrl+Space inside a snippet or a string literal to trigger completions.
// The result of the last command run in a playground is shown on the results panel.
// By default the first 20 documents will be returned with a cursor.
// Use 'console.log()' to print to the debug output.
// For more documentation on playgrounds please refer to
// https://www.mongodb.com/docs/mongodb-vscode/playgrounds/

// Select the database to use.
use('ddd-data');

// Insert a few documents into the sales collection.
db.getCollection('events').insertMany([
    {
        "id": "2",
        "name": "dummy event 02",
        "payload": "{from:roy2, to:yanxia}"
    },
    {
        "id": "3",
        "name": "dummy event 03",
        "payload": "{from:roy3, to:yanxia}"
    },
]);
